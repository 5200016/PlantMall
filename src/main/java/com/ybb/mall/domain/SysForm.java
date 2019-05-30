package com.ybb.mall.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import javax.persistence.*;

import java.io.Serializable;
import java.util.Objects;

/**
 * 微信formId表
 */
@ApiModel(description = "微信formId表")
@Entity
@Table(name = "sys_form")
public class SysForm implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * 微信formId
     */
    @ApiModelProperty(value = "微信formId")
    @Column(name = "form_id")
    private String formId;

    /**
     * 有效期天数（默认6天）
     */
    @ApiModelProperty(value = "有效期天数（默认6天）")
    @Column(name = "validity")
    private Integer validity;

    @ManyToOne
    @JsonIgnoreProperties("forms")
    private SysUser user;

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFormId() {
        return formId;
    }

    public SysForm formId(String formId) {
        this.formId = formId;
        return this;
    }

    public void setFormId(String formId) {
        this.formId = formId;
    }

    public Integer getValidity() {
        return validity;
    }

    public SysForm validity(Integer validity) {
        this.validity = validity;
        return this;
    }

    public void setValidity(Integer validity) {
        this.validity = validity;
    }

    public SysUser getUser() {
        return user;
    }

    public SysForm user(SysUser sysUser) {
        this.user = sysUser;
        return this;
    }

    public void setUser(SysUser sysUser) {
        this.user = sysUser;
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
        SysForm sysForm = (SysForm) o;
        if (sysForm.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), sysForm.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "SysForm{" +
            "id=" + getId() +
            ", formId='" + getFormId() + "'" +
            ", validity=" + getValidity() +
            "}";
    }
}
