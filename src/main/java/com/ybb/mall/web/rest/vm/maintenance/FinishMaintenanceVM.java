package com.ybb.mall.web.rest.vm.maintenance;

import io.swagger.annotations.ApiModelProperty;

/**
 * @Description :
 * @Author 黄志成
 * @Date 2019-07-02
 * @Version
 */

public class FinishMaintenanceVM {
    /**
     * 订单id
     */
    @ApiModelProperty(value = "订单id")
    private Long orderId;

    /**
     * 完成时间
     */
    @ApiModelProperty(value = "完成时间")
    private String time;

    /**
     * 照片地址
     */
    @ApiModelProperty(value = "照片地址")
    private String url;

    public Long getOrderId() {
        return orderId;
    }

    public void setOrderId(Long orderId) {
        this.orderId = orderId;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    @Override
    public String toString() {
        return "FinishMaintenanceVM{" +
            "orderId=" + orderId +
            ", time='" + time + '\'' +
            ", url='" + url + '\'' +
            '}';
    }
}
