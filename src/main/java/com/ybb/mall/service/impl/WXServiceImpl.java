package com.ybb.mall.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.ybb.mall.config.ApplicationProperties;
import com.ybb.mall.domain.*;
import com.ybb.mall.repository.*;
import com.ybb.mall.service.WXService;
import com.ybb.mall.service.dto.order.OrderDTO;
import com.ybb.mall.service.mapper.SysOrderMapper;
import com.ybb.mall.web.rest.controller.wx.vm.order.*;
import com.ybb.mall.web.rest.util.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.*;
import java.util.stream.Collectors;

/**
 * @Description : 微信小程序管理
 * @Author 黄志成
 * @Date 2019-05-09
 * @Version
 */

@Service
@Transactional
public class WXServiceImpl implements WXService {

    private static Logger logger = LoggerFactory.getLogger(WXServiceImpl.class);

    private final OrderRepository orderRepository;

    private final OrderProductRepository orderProductRepository;

    private final SysOrderMapper orderMapper;

    private final ProductRepository productRepository;

    private final ShoppingProductRepository shoppingProductRepository;

    private final ShoppingCarRepository shoppingCarRepository;

    private final ApplicationProperties applicationProperties;

    private final SUserRepository userRepository;

    private final CouponRepository couponRepository;

    private final CouponUserRepository couponUserRepository;

    public WXServiceImpl(OrderRepository orderRepository, OrderProductRepository orderProductRepository, SysOrderMapper orderMapper, ProductRepository productRepository, ShoppingProductRepository shoppingProductRepository, ShoppingCarRepository shoppingCarRepository, ApplicationProperties applicationProperties, SUserRepository userRepository, CouponRepository couponRepository, CouponUserRepository couponUserRepository) {
        this.orderRepository = orderRepository;
        this.orderProductRepository = orderProductRepository;
        this.orderMapper = orderMapper;
        this.productRepository = productRepository;
        this.shoppingProductRepository = shoppingProductRepository;
        this.shoppingCarRepository = shoppingCarRepository;
        this.applicationProperties = applicationProperties;
        this.userRepository = userRepository;
        this.couponRepository = couponRepository;
        this.couponUserRepository = couponUserRepository;
    }


    @Override
    public ResultObj decodeUserInfo(String encryptedData, String iv, String code) {

        JSONObject userInfo = new JSONObject();

        //登录凭证不能为空
        if (TypeUtils.isEmpty(code)) {
            return ResultObj.backInfo(false, 200, "登录凭证不能为空", null);
        }

        String session_key = getSessionKey(code);
        logger.debug("session_key", session_key);
        // encryptedData进行AES解密
        try {
            userInfo = AESUtil.decrypt(encryptedData, session_key, iv);
        } catch (Exception e) {
            e.printStackTrace();
        }

        SysUser haveUser = userRepository.findDatabaseUserByOpenid(userInfo.getString("openId"));
        if(!TypeUtils.isEmpty(haveUser)){
            return ResultObj.backInfo(true, 200, "登录成功", haveUser);
        }
        // 用户信息入库
        SysUser user = new SysUser();
        user.setOpenid(userInfo.getString("openId"));
        user.setNickname(userInfo.getString("nickName"));

        String sex = "";
        Integer gender = userInfo.getInteger("gender");
        if(!TypeUtils.isEmpty(gender)){
            if(gender.equals(0)){
                sex = "男";
            }else {
                sex = "女";
            }
        }
        user.setSex(sex);
        user.setSessionKey(session_key);
        user.setAvatar(userInfo.getString("avatarUrl"));
        user.setIntegral(0);
        user.setGrowthValue(0);
        user.setCreateTime(DateUtil.getZoneDateTime());
        user.setUpdateTime(DateUtil.getZoneDateTime());
        SysUser sysUser = userRepository.save(user);

        List<SysShoppingCar> sysShoppingCars = insertShoppingCars(sysUser);

        if(TypeUtils.isEmpty(sysUser) || TypeUtils.isEmpty(sysShoppingCars)){
            return ResultObj.backCRUDError("用户绑定失败");
        }else {
            return ResultObj.backInfo(true, 200, "用户绑定成功", sysUser);
        }
    }

    /**
     * 入库购物车表
     * @param sysUser
     * @return
     */
    public List<SysShoppingCar> insertShoppingCars(SysUser sysUser) {
        List<SysShoppingCar> shoppingCarList = new ArrayList<>();
        SysShoppingCar sell = new SysShoppingCar();
        sell.setUser(sysUser);
        sell.setType(0);
        sell.setCreateTime(DateUtil.getZoneDateTime());
        sell.setUpdateTime(DateUtil.getZoneDateTime());
        shoppingCarList.add(sell);

        SysShoppingCar lease = new SysShoppingCar();
        lease.setUser(sysUser);
        lease.setType(1);
        lease.setCreateTime(DateUtil.getZoneDateTime());
        lease.setUpdateTime(DateUtil.getZoneDateTime());
        shoppingCarList.add(lease);

        return shoppingCarRepository.saveAll(shoppingCarList);
    }

    @Override
    public ResultObj decodeUserPhone(String openid, String encryptedData, String iv, String code) {
        //登录凭证不能为空
        if (TypeUtils.isEmpty(code)) {
            return ResultObj.backInfo(false, 200, "登录凭证不能为空", null);
        }

        String session_key = getSessionKey(code);

        SysUser sysUser = userRepository.findDatabaseUserByOpenid(openid);

        if(TypeUtils.isEmpty(session_key)){
            session_key = sysUser.getSessionKey();
        }
        if(TypeUtils.isEmpty(sysUser)){
            return ResultObj.backCRUDError("用户不存在");
        }

        // encryptedData进行AES解密
        JSONObject result = new JSONObject();
        try {
            result = AESUtil.decrypt(encryptedData, session_key, iv);
        } catch (Exception e) {
            e.printStackTrace();
        }

        sysUser.setPhone(result.getString("phoneNumber"));
        return ResultObj.back(200, "手机号绑定成功", userRepository.save(sysUser));
    }

    /**
     * 获取微信session_key
     */
    public String getSessionKey(String code){
        String AppID = applicationProperties.getAppID();
        String AppSecret = applicationProperties.getAppSecret();
        String grant_type = "authorization_code";

        // 向微信服务器 使用登录凭证 code 获取 session_key 和 openid
        String params = "appid=" + AppID + "&secret=" + AppSecret + "&js_code=" + code + "&grant_type=" + grant_type;
        String url = "https://api.weixin.qq.com/sns/jscode2session?";

        JSONObject data = OkHttpUtil.getRequest(url + params , null);
        return data.getString("session_key");
    }

    @Override
    public ResultObj wxPayMethod(SubmitOrderVM submitOrder) throws Exception {
        BigDecimal value = new BigDecimal(submitOrder.getPayPrice());
        if(!TypeUtils.isEmpty(submitOrder.getLeaseCouponId())){
            SysCoupon coupon = couponRepository.findCouponById(submitOrder.getLeaseCouponId());
            value = value.subtract(coupon.getValue());
        }

        // 订单号
        String tradeNo =  WxUtil.getTradeNoMethod();
        // 订单金额
        BigDecimal coefficient = new BigDecimal(100);
        BigDecimal price = value.multiply(coefficient).setScale(0, BigDecimal.ROUND_HALF_UP);
        String payPrice = price.toString();

        // 生成待付款订单
        createOrder(submitOrder, 0, tradeNo);

        String appId = applicationProperties.getAppID();
        String mchId = applicationProperties.getMchID();
        String key = applicationProperties.getKey();

        SortedMap<String, String> payData = new TreeMap<>();
        payData.put("appid",appId);
        String body = new String("支付通知".toString().getBytes("ISO8859-1"),"UTF-8");
        payData.put("body",body);
        payData.put("mch_id",mchId);
        payData.put("nonce_str",WxUtil.generateNonceStr());
        payData.put("notify_url",WxUtil.NOTIFY_URL);
        payData.put("openid",submitOrder.getOpenid());
        payData.put("out_trade_no", tradeNo);
        payData.put("spbill_create_ip",WxUtil.getIp());
        payData.put("total_fee", payPrice);
        payData.put("trade_type",WxUtil.TRADE_TYPE);
        String xml = WxUtil.postXML("https://api.mch.weixin.qq.com/pay/unifiedorder", WxUtil.generateSignedXml(payData, key));
        Map<String, String> data = WxUtil.xmlToMap(xml);
        SortedMap<String, String> secondPayData = new TreeMap<>();
        secondPayData.put("appId", appId);
        secondPayData.put("timeStamp", String.valueOf(System.currentTimeMillis() / 1000));
        secondPayData.put("nonceStr",data.get("nonce_str"));
        secondPayData.put("package", "prepay_id=" + data.get("prepay_id"));
        secondPayData.put("signType", "MD5");
        secondPayData.put("paySign", WxUtil.generateSignature(secondPayData, key));
        secondPayData.put("payNo", tradeNo);
        return ResultObj.back(200, secondPayData);
    }

    public void createOrder(SubmitOrderVM submitOrder, Integer status, String payNo){

        SysCoupon coupon = couponRepository.findCouponById(submitOrder.getLeaseCouponId());

        // 处理出售商品订单
        List<SysOrderProduct> orderProductList = new ArrayList<>();
        List<SellVM> sellVMList = submitOrder.getSell();
        for(SellVM sellVM : sellVMList){
            // 入库订单表
            SysOrder order = new SysOrder();
            order.setTradeNo(WxUtil.getTradeNoMethod());
            order.setPrice(sellVM.getTotalPrice());
            order.setPayNo(payNo);
            order.setType(0);
            order.setPayType(0);
            order.setStatus(status);
            order.setNumber(sellVM.getNumber());
            order.setDescription(sellVM.getDescription());
            order.setMaintenancePlanStatus(0);

            SysUser user = new SysUser();
            user.setId(submitOrder.getUserId());
            order.setUser(user);

            SysReceiverAddress receiverAddress = new SysReceiverAddress();
            receiverAddress.setId(submitOrder.getReceiveAddressId());
            order.setReceiverAddress(receiverAddress);

            order.setCreateTime(DateUtil.getZoneDateTime());
            order.setUpdateTime(DateUtil.getZoneDateTime());

            SysOrder sysOrder = orderRepository.save(order);

            SysOrderProduct orderProduct = new SysOrderProduct();
            orderProduct.setProductStatus(0);
            orderProduct.setOrder(sysOrder);
            orderProduct.setProductNumber(sellVM.getNumber());

            SysProduct product = new SysProduct();
            product.setId(sellVM.getProductId());
            orderProduct.setProduct(product);

            orderProduct.setUpdateTime(DateUtil.getZoneDateTime());
            orderProduct.setCreateTime(DateUtil.getZoneDateTime());
            orderProductList.add(orderProduct);
        }


        // 处理租赁商品
        if(!TypeUtils.isEmpty(submitOrder.getLease().getProductList())){
            SysOrder order = new SysOrder();
            order.setTradeNo(WxUtil.getTradeNoMethod());

            if(!TypeUtils.isEmpty(coupon)){
                order.setPrice(submitOrder.getLease().getTotalPrice().subtract(coupon.getValue()));
            }else {
                order.setPrice(submitOrder.getLease().getTotalPrice());
            }

            order.setStatus(status);
            order.setPayNo(payNo);
            order.setType(1);
            order.setPayType(0);
            order.setNumber(submitOrder.getLease().getNumber());
            order.setMaintenancePlanStatus(0);
            order.setDescription(submitOrder.getLease().getDescription());
            order.setUpdateTime(DateUtil.getZoneDateTime());
            order.setCreateTime(DateUtil.getZoneDateTime());

            SysReceiverAddress receiverAddress = new SysReceiverAddress();
            receiverAddress.setId(submitOrder.getReceiveAddressId());
            order.setReceiverAddress(receiverAddress);

            SysUser user = new SysUser();
            user.setId(submitOrder.getUserId());
            order.setUser(user);

            SysOrder sysOrder = orderRepository.save(order);
            for(LeaseProductVM data : submitOrder.getLease().getProductList()){
                SysOrderProduct orderProduct = new SysOrderProduct();
                orderProduct.setProductStatus(0);
                orderProduct.setOrder(sysOrder);
                orderProduct.setProductNumber(data.getProductNumber());
                orderProduct.setUpdateTime(DateUtil.getZoneDateTime());
                orderProduct.setCreateTime(DateUtil.getZoneDateTime());

                SysProduct product = new SysProduct();
                product.setId(data.getProductId());
                orderProduct.setProduct(product);

                orderProductList.add(orderProduct);
            }
        }
        orderProductRepository.saveAll(orderProductList);

        // 清除购物车记录
        if(!TypeUtils.isEmpty(submitOrder.getShoppingProductIdList())){
            List<SysShoppingProduct> list = new ArrayList<>();
            for(Long id : submitOrder.getShoppingProductIdList()){
                SysShoppingProduct object = new SysShoppingProduct();
                object.setId(id);
                list.add(object);
            }
            shoppingProductRepository.deleteInBatch(list);
        }

        if(!TypeUtils.isEmpty(submitOrder.getLeaseCouponId())){
            List<SysCouponUser> couponList = couponUserRepository.findCouponInfoByUserId(submitOrder.getUserId(), submitOrder.getLeaseCouponId());
            if(!TypeUtils.isEmpty(couponList)){
                SysCouponUser couponUser = couponList.get(0);
                couponUser.setUseStatus(1);
                couponUserRepository.save(couponUser);
            }
        }
    }

    @Override
    public ResultObj payFinishMethod(SubmitOrderVM submitOrder) {

        // 修改订单状态为已完成
        List<SysOrder> orderList = orderRepository.findOrderByUserIdAndTradeNo(submitOrder.getUserId(), submitOrder.getPayNo());
        for(SysOrder data : orderList){
            data.setStatus(1);
        }
        orderRepository.saveAll(orderList);

        // 同步商品销量、库存
        List<SysProduct> productList = new ArrayList<>();
        List<OrderDTO> orderDTOList = orderList.stream().map(orderMapper::toDto).collect(Collectors.toCollection(LinkedList::new));
        for(OrderDTO data : orderDTOList){
            productList.addAll(synchronizedProduct(data));
        }
        productRepository.saveAll(productList);
        return ResultObj.backCRUDSuccess("支付成功");
    }

    @Override
    public ResultObj payWaitPayOrder(WaitPayOrderVM waitPayOrderVM) throws Exception {
        // 订单号
        String tradeNo =  WxUtil.getTradeNoMethod();
        // 订单金额
        BigDecimal value = new BigDecimal(waitPayOrderVM.getTotalPrice());
        BigDecimal coefficient = new BigDecimal(100);
        BigDecimal price = value.multiply(coefficient).setScale(0, BigDecimal.ROUND_HALF_UP);
        String payPrice = price.toString();

        String appId = applicationProperties.getAppID();
        String key = applicationProperties.getKey();
        String mchId = applicationProperties.getMchID();

        SortedMap<String, String> payData = new TreeMap<>();
        payData.put("appid",appId);
        payData.put("mch_id",mchId);
        String body = new String("支付通知".toString().getBytes("ISO8859-1"),"UTF-8");
        payData.put("body",body);
        payData.put("notify_url",WxUtil.NOTIFY_URL);
        payData.put("nonce_str",WxUtil.generateNonceStr());
        payData.put("openid",waitPayOrderVM.getOpenid());
        payData.put("out_trade_no", tradeNo);
        payData.put("trade_type",WxUtil.TRADE_TYPE);
        payData.put("spbill_create_ip",WxUtil.getIp());
        payData.put("total_fee", payPrice);
        String xml = WxUtil.postXML("https://api.mch.weixin.qq.com/pay/unifiedorder", WxUtil.generateSignedXml(payData, key));
        Map<String, String> data = WxUtil.xmlToMap(xml);
        SortedMap<String, String> secondPayData = new TreeMap<>();
        secondPayData.put("appId", appId);
        secondPayData.put("timeStamp", String.valueOf(System.currentTimeMillis() / 1000));
        secondPayData.put("nonceStr",data.get("nonce_str"));
        secondPayData.put("package", "prepay_id=" + data.get("prepay_id"));
        secondPayData.put("signType", "MD5");
        secondPayData.put("paySign", WxUtil.generateSignature(secondPayData, key));
        secondPayData.put("payNo", tradeNo);
        return ResultObj.back(200, secondPayData);
    }

    @Override
    public ResultObj updateWaitPayOrder(UpdateOrderStatusVM orderStatusVM) {
        SysOrder order = orderRepository.findSysOrderById(orderStatusVM.getId());
        OrderDTO orderDTO = orderMapper.toDto(order);

        // 同步商品库存
        List<SysProduct> productList = synchronizedProduct(orderDTO);
        productRepository.saveAll(productList);

        order.setStatus(1);
        order.setPayNo(orderStatusVM.getPayNo());
        orderRepository.save(order);
        return ResultObj.backCRUDSuccess("修改成功");
    }

    public List<SysProduct> synchronizedProduct(OrderDTO data){
        List<SysProduct> productList = new ArrayList<>();
        Set<SysOrderProduct> orderProductList = data.getOrderProducts();
        for(SysOrderProduct orderProduct : orderProductList){
            SysProduct product = orderProduct.getProduct();
            Integer productNumber = orderProduct.getProductNumber();
            if(!TypeUtils.isEmpty(product)){
                Integer inventory = product.getInventory() - productNumber;
                if(inventory < 0){
                    inventory = 0;
                }
                product.setInventory(inventory);

                Integer sale = product.getSale() + productNumber;
                product.setSale(sale);
                productList.add(product);
            }
        }
        return productList;
    }
}
