package com.ybb.mall.service.dto.sysdto;

import javax.persistence.Lob;
import java.io.Serializable;
import java.time.ZonedDateTime;
import java.util.Objects;

/**
 * A DTO for the SysReview entity.
 */
public class SysReviewDTO implements Serializable {

    private Long id;

    @Lob
    private String content;

    private Integer level;

    private ZonedDateTime createTime;

    private ZonedDateTime updateTime;

    private Long productId;

    private Long userId;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Integer getLevel() {
        return level;
    }

    public void setLevel(Integer level) {
        this.level = level;
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

    public Long getProductId() {
        return productId;
    }

    public void setProductId(Long sysProductId) {
        this.productId = sysProductId;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long sysUserId) {
        this.userId = sysUserId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        SysReviewDTO sysReviewDTO = (SysReviewDTO) o;
        if (sysReviewDTO.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), sysReviewDTO.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "SysReviewDTO{" +
            "id=" + getId() +
            ", content='" + getContent() + "'" +
            ", level=" + getLevel() +
            ", createTime='" + getCreateTime() + "'" +
            ", updateTime='" + getUpdateTime() + "'" +
            ", product=" + getProductId() +
            ", user=" + getUserId() +
            "}";
    }
}
