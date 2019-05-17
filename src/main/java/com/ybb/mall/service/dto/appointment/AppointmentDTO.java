package com.ybb.mall.service.dto.appointment;

import java.time.ZonedDateTime;

/**
 * @Description :
 * @Author 黄志成
 * @Date 2019-04-29
 * @Version
 */

public class AppointmentDTO {

    /**
     * 主键id
     */
    private Long id;

    /**
     * 预约时间
     */
    private ZonedDateTime time;

    /**
     * 预约备注
     */
    private String remark;

    /**
     * 预约状态（0：未处理 1：已处理）
     */
    private Integer status;

    /**
     * 创建时间
     */
    private ZonedDateTime createTime;

    /**
     * 用户姓名
     */
    private String name;

    /**
     * 联系方式
     */
    private String phone;

    /**
     * 地区
     */
    private String area;

    /**
     * 详细地址
     */
    private String address;

    public AppointmentDTO(Long id, ZonedDateTime time, String remark, Integer status, ZonedDateTime createTime, String name, String phone) {
        this.id = id;
        this.time = time;
        this.remark = remark;
        this.status = status;
        this.createTime = createTime;
        this.name = name;
        this.phone = phone;
    }

    public AppointmentDTO(Long id, ZonedDateTime time, String remark, Integer status, ZonedDateTime createTime, String name, String phone, String area, String address) {
        this.id = id;
        this.time = time;
        this.remark = remark;
        this.status = status;
        this.createTime = createTime;
        this.name = name;
        this.phone = phone;
        this.area = area;
        this.address = address;
    }

    public String getArea() {
        return area;
    }

    public void setArea(String area) {
        this.area = area;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public ZonedDateTime getTime() {
        return time;
    }

    public void setTime(ZonedDateTime time) {
        this.time = time;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public ZonedDateTime getCreateTime() {
        return createTime;
    }

    public void setCreateTime(ZonedDateTime createTime) {
        this.createTime = createTime;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    @Override
    public String toString() {
        return "AppointmentDTO{" +
            "id=" + id +
            ", time=" + time +
            ", remark='" + remark + '\'' +
            ", status=" + status +
            ", createTime=" + createTime +
            ", name='" + name + '\'' +
            ", phone='" + phone + '\'' +
            ", area='" + area + '\'' +
            ", address='" + address + '\'' +
            '}';
    }
}
