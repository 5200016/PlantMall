package com.ybb.mall.service.dto.sysdto;

import java.io.Serializable;
import java.time.ZonedDateTime;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

/**
 * A DTO for the SysUser entity.
 */
public class SysUserDTO implements Serializable {

    private Long id;

    private String openid;

    private String phone;

    private String sessionKey;

    private String username;

    private String avatar;

    private String nickname;

    private String sex;

    private Integer integral;

    private Integer growthValue;

    private ZonedDateTime createTime;

    private ZonedDateTime updateTime;

    private Long memberLevelId;

    private Set<SysRoleDTO> roles = new HashSet<>();

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getOpenid() {
        return openid;
    }

    public void setOpenid(String openid) {
        this.openid = openid;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getSessionKey() {
        return sessionKey;
    }

    public void setSessionKey(String sessionKey) {
        this.sessionKey = sessionKey;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
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

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
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

    public ZonedDateTime getCreateTime() {
        return createTime;
    }

    public void setCreateTime(ZonedDateTime createTime) {
        this.createTime = createTime;
    }

    public ZonedDateTime getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(ZonedDateTime updateTime) {
        this.updateTime = updateTime;
    }

    public Long getMemberLevelId() {
        return memberLevelId;
    }

    public void setMemberLevelId(Long sysMemberLevelId) {
        this.memberLevelId = sysMemberLevelId;
    }

    public Set<SysRoleDTO> getRoles() {
        return roles;
    }

    public void setRoles(Set<SysRoleDTO> sysRoles) {
        this.roles = sysRoles;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        SysUserDTO sysUserDTO = (SysUserDTO) o;
        if (sysUserDTO.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), sysUserDTO.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "SysUserDTO{" +
            "id=" + getId() +
            ", openid='" + getOpenid() + "'" +
            ", phone='" + getPhone() + "'" +
            ", sessionKey='" + getSessionKey() + "'" +
            ", username='" + getUsername() + "'" +
            ", avatar='" + getAvatar() + "'" +
            ", nickname='" + getNickname() + "'" +
            ", sex='" + getSex() + "'" +
            ", integral=" + getIntegral() +
            ", growthValue=" + getGrowthValue() +
            ", createTime='" + getCreateTime() + "'" +
            ", updateTime='" + getUpdateTime() + "'" +
            ", memberLevel=" + getMemberLevelId() +
            "}";
    }
}
