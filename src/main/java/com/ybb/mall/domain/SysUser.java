package com.ybb.mall.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import javax.persistence.*;

import java.io.Serializable;
import java.time.ZonedDateTime;
import java.util.HashSet;
import java.util.Set;
import java.util.Objects;

/**
 * 用户表
 */
@ApiModel(description = "用户表")
@Entity
@Table(name = "sys_user")
public class SysUser implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * 微信openid
     */
    @ApiModelProperty(value = "微信openid")
    @Column(name = "openid")
    private String openid;

    /**
     * 手机号
     */
    @ApiModelProperty(value = "手机号")
    @Column(name = "phone")
    private String phone;

    /**
     * 微信session_key
     */
    @ApiModelProperty(value = "微信session_key")
    @Column(name = "session_key")
    private String sessionKey;

    /**
     * 用户姓名
     */
    @ApiModelProperty(value = "用户姓名")
    @Column(name = "username")
    private String username;

    /**
     * 用户头像
     */
    @ApiModelProperty(value = "用户头像")
    @Column(name = "avatar")
    private String avatar;

    /**
     * 用户昵称
     */
    @ApiModelProperty(value = "用户昵称")
    @Column(name = "nickname")
    private String nickname;

    /**
     * 用户性别
     */
    @ApiModelProperty(value = "用户性别")
    @Column(name = "sex")
    private String sex;

    /**
     * 用户积分
     */
    @ApiModelProperty(value = "用户积分")
    @Column(name = "integral")
    private Integer integral;

    /**
     * 用户成长值
     */
    @ApiModelProperty(value = "用户成长值")
    @Column(name = "growth_value")
    private Integer growthValue;

    @Column(name = "create_time")
    private ZonedDateTime createTime;

    @Column(name = "update_time")
    private ZonedDateTime updateTime;

    @ManyToOne
    @JsonIgnoreProperties("users")
    private SysMemberLevel memberLevel;

    @ManyToMany
    @JoinTable(name = "sys_user_role",
               joinColumns = @JoinColumn(name = "sys_users_id", referencedColumnName = "id"),
               inverseJoinColumns = @JoinColumn(name = "roles_id", referencedColumnName = "id"))
    private Set<SysRole> roles = new HashSet<>();

    @OneToMany(mappedBy = "user")
    private Set<SysReceiverAddress> receiverAddresses = new HashSet<>();
    @OneToMany(mappedBy = "user")
    private Set<SysCouponUser> couponUsers = new HashSet<>();
    @OneToMany(mappedBy = "user")
    private Set<SysOrder> orders = new HashSet<>();
    @OneToMany(mappedBy = "user")
    private Set<SysReview> reviews = new HashSet<>();
    @OneToMany(mappedBy = "user")
    private Set<SysCollection> collections = new HashSet<>();
    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getOpenid() {
        return openid;
    }

    public SysUser openid(String openid) {
        this.openid = openid;
        return this;
    }

    public void setOpenid(String openid) {
        this.openid = openid;
    }

    public String getPhone() {
        return phone;
    }

    public SysUser phone(String phone) {
        this.phone = phone;
        return this;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getSessionKey() {
        return sessionKey;
    }

    public SysUser sessionKey(String sessionKey) {
        this.sessionKey = sessionKey;
        return this;
    }

    public void setSessionKey(String sessionKey) {
        this.sessionKey = sessionKey;
    }

    public String getUsername() {
        return username;
    }

    public SysUser username(String username) {
        this.username = username;
        return this;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getAvatar() {
        return avatar;
    }

    public SysUser avatar(String avatar) {
        this.avatar = avatar;
        return this;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public String getNickname() {
        return nickname;
    }

    public SysUser nickname(String nickname) {
        this.nickname = nickname;
        return this;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getSex() {
        return sex;
    }

    public SysUser sex(String sex) {
        this.sex = sex;
        return this;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public Integer getIntegral() {
        return integral;
    }

    public SysUser integral(Integer integral) {
        this.integral = integral;
        return this;
    }

    public void setIntegral(Integer integral) {
        this.integral = integral;
    }

    public Integer getGrowthValue() {
        return growthValue;
    }

    public SysUser growthValue(Integer growthValue) {
        this.growthValue = growthValue;
        return this;
    }

    public void setGrowthValue(Integer growthValue) {
        this.growthValue = growthValue;
    }

    public ZonedDateTime getCreateTime() {
        return createTime;
    }

    public SysUser createTime(ZonedDateTime createTime) {
        this.createTime = createTime;
        return this;
    }

    public void setCreateTime(ZonedDateTime createTime) {
        this.createTime = createTime;
    }

    public ZonedDateTime getUpdateTime() {
        return updateTime;
    }

    public SysUser updateTime(ZonedDateTime updateTime) {
        this.updateTime = updateTime;
        return this;
    }

    public void setUpdateTime(ZonedDateTime updateTime) {
        this.updateTime = updateTime;
    }

    public SysMemberLevel getMemberLevel() {
        return memberLevel;
    }

    public SysUser memberLevel(SysMemberLevel sysMemberLevel) {
        this.memberLevel = sysMemberLevel;
        return this;
    }

    public void setMemberLevel(SysMemberLevel sysMemberLevel) {
        this.memberLevel = sysMemberLevel;
    }

    public Set<SysRole> getRoles() {
        return roles;
    }

    public SysUser roles(Set<SysRole> sysRoles) {
        this.roles = sysRoles;
        return this;
    }

    public SysUser addRole(SysRole sysRole) {
        this.roles.add(sysRole);
        sysRole.getUsers().add(this);
        return this;
    }

    public SysUser removeRole(SysRole sysRole) {
        this.roles.remove(sysRole);
        sysRole.getUsers().remove(this);
        return this;
    }

    public void setRoles(Set<SysRole> sysRoles) {
        this.roles = sysRoles;
    }

    public Set<SysReceiverAddress> getReceiverAddresses() {
        return receiverAddresses;
    }

    public SysUser receiverAddresses(Set<SysReceiverAddress> sysReceiverAddresses) {
        this.receiverAddresses = sysReceiverAddresses;
        return this;
    }

    public SysUser addReceiverAddress(SysReceiverAddress sysReceiverAddress) {
        this.receiverAddresses.add(sysReceiverAddress);
        sysReceiverAddress.setUser(this);
        return this;
    }

    public SysUser removeReceiverAddress(SysReceiverAddress sysReceiverAddress) {
        this.receiverAddresses.remove(sysReceiverAddress);
        sysReceiverAddress.setUser(null);
        return this;
    }

    public void setReceiverAddresses(Set<SysReceiverAddress> sysReceiverAddresses) {
        this.receiverAddresses = sysReceiverAddresses;
    }

    public Set<SysCouponUser> getCouponUsers() {
        return couponUsers;
    }

    public SysUser couponUsers(Set<SysCouponUser> sysCouponUsers) {
        this.couponUsers = sysCouponUsers;
        return this;
    }

    public SysUser addCouponUser(SysCouponUser sysCouponUser) {
        this.couponUsers.add(sysCouponUser);
        sysCouponUser.setUser(this);
        return this;
    }

    public SysUser removeCouponUser(SysCouponUser sysCouponUser) {
        this.couponUsers.remove(sysCouponUser);
        sysCouponUser.setUser(null);
        return this;
    }

    public void setCouponUsers(Set<SysCouponUser> sysCouponUsers) {
        this.couponUsers = sysCouponUsers;
    }

    public Set<SysOrder> getOrders() {
        return orders;
    }

    public SysUser orders(Set<SysOrder> sysOrders) {
        this.orders = sysOrders;
        return this;
    }

    public SysUser addOrder(SysOrder sysOrder) {
        this.orders.add(sysOrder);
        sysOrder.setUser(this);
        return this;
    }

    public SysUser removeOrder(SysOrder sysOrder) {
        this.orders.remove(sysOrder);
        sysOrder.setUser(null);
        return this;
    }

    public void setOrders(Set<SysOrder> sysOrders) {
        this.orders = sysOrders;
    }

    public Set<SysReview> getReviews() {
        return reviews;
    }

    public SysUser reviews(Set<SysReview> sysReviews) {
        this.reviews = sysReviews;
        return this;
    }

    public SysUser addReview(SysReview sysReview) {
        this.reviews.add(sysReview);
        sysReview.setUser(this);
        return this;
    }

    public SysUser removeReview(SysReview sysReview) {
        this.reviews.remove(sysReview);
        sysReview.setUser(null);
        return this;
    }

    public void setReviews(Set<SysReview> sysReviews) {
        this.reviews = sysReviews;
    }

    public Set<SysCollection> getCollections() {
        return collections;
    }

    public SysUser collections(Set<SysCollection> sysCollections) {
        this.collections = sysCollections;
        return this;
    }

    public SysUser addCollection(SysCollection sysCollection) {
        this.collections.add(sysCollection);
        sysCollection.setUser(this);
        return this;
    }

    public SysUser removeCollection(SysCollection sysCollection) {
        this.collections.remove(sysCollection);
        sysCollection.setUser(null);
        return this;
    }

    public void setCollections(Set<SysCollection> sysCollections) {
        this.collections = sysCollections;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here, do not remove

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        SysUser sysUser = (SysUser) o;
        if (sysUser.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), sysUser.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "SysUser{" +
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
            "}";
    }
}
