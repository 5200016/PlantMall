package com.ybb.mall.service.dto.user;

/**
 * @Description : 微信用户信息
 * @Author 黄志成
 * @Date 2019-05-10
 * @Version
 */

public class WXUserDTO {
    private Long id;
    private String avatar;
    private String nickname;
    private String username;
    private String sex;
    private String phone;
    private Integer integral;
    private Integer growthValue;
    private String memberLevel;
    private String openid;
    private Long maintenanceId;

    public WXUserDTO(Long id, String avatar, String nickname, String username, String sex, String phone, Integer integral, Integer growthValue, String memberLevel, String openid, Long maintenanceId) {
        this.id = id;
        this.avatar = avatar;
        this.nickname = nickname;
        this.username = username;
        this.sex = sex;
        this.phone = phone;
        this.integral = integral;
        this.growthValue = growthValue;
        this.memberLevel = memberLevel;
        this.openid = openid;
        this.maintenanceId = maintenanceId;
    }

    public Long getMaintenanceId() {
        return maintenanceId;
    }

    public void setMaintenanceId(Long maintenanceId) {
        this.maintenanceId = maintenanceId;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public Integer getIntegral() {
        return integral;
    }

    public void setIntegral(Integer integral) {
        this.integral = integral;
    }

    public Integer getGrowthValue() {
        return growthValue;
    }

    public void setGrowthValue(Integer growthValue) {
        this.growthValue = growthValue;
    }

    public String getMemberLevel() {
        return memberLevel;
    }

    public void setMemberLevel(String memberLevel) {
        this.memberLevel = memberLevel;
    }

    public String getOpenid() {
        return openid;
    }

    public void setOpenid(String openid) {
        this.openid = openid;
    }

    @Override
    public String toString() {
        return "WXUserDTO{" +
            "id=" + id +
            ", avatar='" + avatar + '\'' +
            ", nickname='" + nickname + '\'' +
            ", username='" + username + '\'' +
            ", sex='" + sex + '\'' +
            ", phone='" + phone + '\'' +
            ", integral=" + integral +
            ", growthValue=" + growthValue +
            ", memberLevel='" + memberLevel + '\'' +
            ", openid='" + openid + '\'' +
            ", maintenanceId='" + maintenanceId + '\'' +
            '}';
    }
}
