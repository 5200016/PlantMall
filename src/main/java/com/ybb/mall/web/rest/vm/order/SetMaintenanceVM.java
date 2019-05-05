package com.ybb.mall.web.rest.vm.order;

import java.time.ZonedDateTime;
import java.util.List;

/**
 * @Description : 设置养护计划
 * @Author 黄志成
 * @Date 2019-05-05
 * @Version
 */

public class SetMaintenanceVM {
    private Long orderId;

    private List<ZonedDateTime> maintenanceTime;

    private Long maintenancePersonnelId;

    private String maintenanceDescription;

    public Long getOrderId() {
        return orderId;
    }

    public void setOrderId(Long orderId) {
        this.orderId = orderId;
    }

    public List<ZonedDateTime> getMaintenanceTime() {
        return maintenanceTime;
    }

    public void setMaintenanceTime(List<ZonedDateTime> maintenanceTime) {
        this.maintenanceTime = maintenanceTime;
    }

    public Long getMaintenancePersonnelId() {
        return maintenancePersonnelId;
    }

    public void setMaintenancePersonnelId(Long maintenancePersonnelId) {
        this.maintenancePersonnelId = maintenancePersonnelId;
    }

    public String getMaintenanceDescription() {
        return maintenanceDescription;
    }

    public void setMaintenanceDescription(String maintenanceDescription) {
        this.maintenanceDescription = maintenanceDescription;
    }

    @Override
    public String toString() {
        return "SetMaintenanceVM{" +
            "orderId=" + orderId +
            ", maintenanceTime=" + maintenanceTime +
            ", maintenancePersonnelId=" + maintenancePersonnelId +
            ", maintenanceDescription='" + maintenanceDescription + '\'' +
            '}';
    }
}
