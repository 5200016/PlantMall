package com.ybb.mall.web.rest.controller.wx.vm;

/**
 * @Description : 修改预约订单状态
 * @Author 黄志成
 * @Date 2019-05-14
 * @Version
 */

public class UpdateAppointmentStatusVM {
    private Long id;
    private Integer status;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "UpdateAppointmentStatusVM{" +
            "id=" + id +
            ", status=" + status +
            '}';
    }
}
