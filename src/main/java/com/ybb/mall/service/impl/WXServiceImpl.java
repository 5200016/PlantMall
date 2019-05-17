package com.ybb.mall.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.ybb.mall.config.ApplicationProperties;
import com.ybb.mall.domain.SysUser;
import com.ybb.mall.repository.SUserRepository;
import com.ybb.mall.service.WXService;
import com.ybb.mall.service.dto.user.UserListDTO;
import com.ybb.mall.service.dto.user.WXUserDTO;
import com.ybb.mall.web.rest.util.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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

    private final ApplicationProperties applicationProperties;

    private final SUserRepository userRepository;

    public WXServiceImpl(ApplicationProperties applicationProperties, SUserRepository userRepository) {
        this.applicationProperties = applicationProperties;
        this.userRepository = userRepository;
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
        if(TypeUtils.isEmpty(sysUser)){
            return ResultObj.backCRUDError("用户绑定失败");
        }else {
            return ResultObj.backInfo(true, 200, "用户绑定成功", sysUser);
        }
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
}
