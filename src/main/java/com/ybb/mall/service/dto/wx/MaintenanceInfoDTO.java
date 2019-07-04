package com.ybb.mall.service.dto.wx;

import com.alibaba.fastjson.JSONArray;
import com.ybb.mall.domain.SysMaintenanceFinish;
import com.ybb.mall.domain.SysOrder;

import java.util.List;

/**
 * @Description :
 * @Author 黄志成
 * @Date 2019-07-03
 * @Version
 */

public class MaintenanceInfoDTO {
    private SysOrder order;
    private JSONArray finish;

    public MaintenanceInfoDTO(SysOrder order) {
        this.order = order;
    }

    public SysOrder getOrder() {
        return order;
    }

    public void setOrder(SysOrder order) {
        this.order = order;
    }

    public JSONArray getFinish() {
        return finish;
    }

    public void setFinish(JSONArray finish) {
        this.finish = finish;
    }

    @Override
    public String toString() {
        return "MaintenanceInfoDTO{" +
            "order=" + order +
            ", finish=" + finish +
            '}';
    }
}
