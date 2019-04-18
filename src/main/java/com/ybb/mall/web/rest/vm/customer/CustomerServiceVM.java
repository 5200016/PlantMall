package com.ybb.mall.web.rest.vm.customer;

import java.time.ZonedDateTime;

/**
 * @Description :
 * @Author 黄志成
 * @Date 2019-04-18
 * @Version
 */

public class CustomerServiceVM {

    private Long id;

    private String phone;

    private String email;

    private String address;

    private ZonedDateTime createTime;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public ZonedDateTime getCreateTime() {
        return createTime;
    }

    public void setCreateTime(ZonedDateTime createTime) {
        this.createTime = createTime;
    }

    @Override
    public String toString() {
        return "CustomerServiceVM{" +
            "id=" + id +
            ", phone='" + phone + '\'' +
            ", email='" + email + '\'' +
            ", address='" + address + '\'' +
            ", createTime=" + createTime +
            '}';
    }
}
