package com.ybb.mall.service.scheduled;

import com.ybb.mall.domain.SysForm;
import com.ybb.mall.domain.SysOrder;
import com.ybb.mall.repository.FormRepository;
import com.ybb.mall.repository.OrderRepository;
import com.ybb.mall.web.rest.util.DateUtil;
import com.ybb.mall.web.rest.util.TypeUtils;
import com.ybb.mall.web.rest.util.WXInterfaceUtil;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

/**
 * Desc : 定时任务
 */

@Component
@EnableScheduling
public class WXMessage {

    private final FormRepository formRepository;

    private final OrderRepository orderRepository;

    public WXMessage(FormRepository formRepository, OrderRepository orderRepository) {
        this.formRepository = formRepository;
        this.orderRepository = orderRepository;
    }

    @Scheduled(cron = "0 0 12 * * ?")
    public void pushMessage() {
        String tomorrow = DateUtil.zonedDateTimeFormat(DateUtil.getZoneDateTime().plusDays(1L), "yyyy-MM-dd");
        List<SysOrder> orders = orderRepository.findOrderListByTime(tomorrow);
        for(SysOrder order : orders){
            String name = order.getReceiverAddress().getName();
            String phone = order.getReceiverAddress().getPhone();
            String address = order.getReceiverAddress().getArea() + " " + order.getReceiverAddress().getAddress();
            String openid = order.getMaintenancePersonnel().getUser().getOpenid();
            List<SysForm> formList = formRepository.findFormIdByUserId(order.getMaintenancePersonnel().getUser().getId());
            if(!TypeUtils.isEmpty(formList)){
                WXInterfaceUtil.sendMessageToMaintenance(formList.get(0).getFormId(), openid, name, phone, address);
                formRepository.delete(formList.get(0));
            }
        }
    }

    @Scheduled(cron = "0 0 2 * * ?")
    public void refreshFormId() {
        List<SysForm> formList = formRepository.findAll();
        List<SysForm> result = new ArrayList<>();
        if(!TypeUtils.isEmpty(formList)){
            for(SysForm form : formList){
                Integer validity = form.getValidity() - 1;
                if(validity.equals(0)){
                    formRepository.delete(form);
                }else {
                    form.setValidity(validity);
                    result.add(form);
                }
            }
            formRepository.saveAll(result);
        }
    }
}
