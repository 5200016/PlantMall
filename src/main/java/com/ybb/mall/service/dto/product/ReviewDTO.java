package com.ybb.mall.service.dto.product;

import com.ybb.mall.domain.SysUser;

import java.time.ZonedDateTime;

/**
 * @Description : 商品评论
 * @Author 黄志成
 * @Date 2019-04-24
 * @Version
 */

public class ReviewDTO {
    private Long id;

    /**
     * 用户昵称
     */
    private String nickname;

    /**
     * 用户头像
     */
    private String avatar;


    /**
     * 评论内容
     */
    private String content;

    /**
     * 评论等级
     */
    private Integer level;

    private ZonedDateTime createTime;

    public ReviewDTO() {
    }

    public ReviewDTO(Long id, String nickname, String avatar, String content, Integer level, ZonedDateTime createTime) {
        this.id = id;
        this.nickname = nickname;
        this.avatar = avatar;
        this.content = content;
        this.level = level;
        this.createTime = createTime;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
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

    @Override
    public String toString() {
        return "ReviewDTO{" +
            "id=" + id +
            ", nickname='" + nickname + '\'' +
            ", avatar='" + avatar + '\'' +
            ", content='" + content + '\'' +
            ", level=" + level +
            ", createTime=" + createTime +
            '}';
    }
}
