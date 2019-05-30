package com.ybb.mall.web.rest.vm.user;

/**
 * @Description : 修改用户实体
 * @Author 黄志成
 * @Date 2019-04-15
 * @Version
 */

public class UpdateUserVM {
    private Long id;
    private Long maintenancePersonnelId;
    private String username;
    private Integer setStatus;

    public Integer getSetStatus() {
        return setStatus;
    }

    public void setSetStatus(Integer setStatus) {
        this.setStatus = setStatus;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getMaintenancePersonnelId() {
        return maintenancePersonnelId;
    }

    public void setMaintenancePersonnelId(Long maintenancePersonnelId) {
        this.maintenancePersonnelId = maintenancePersonnelId;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    @Override
    public String toString() {
        return "UpdateUserVM{" +
            "id=" + id +
            ", maintenancePersonnelId=" + maintenancePersonnelId +
            ", username='" + username + '\'' +
            ", setStatus=" + setStatus +
            '}';
    }
}
