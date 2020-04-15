package cn.syp.sypuser.entity;

import java.io.Serializable;

/**
 * TODO
 *
 * @Author syp
 * @Date 2020/3/23 2:28
 * @Description
 */
public class UserRole implements Serializable {

    private int id;
    private int userId;
    private int roleId;

    @Override
    public String toString() {
        return "UserRole{" +
                "id=" + id +
                ", userId=" + userId +
                ", roleId=" + roleId +
                '}';
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public int getRoleId() {
        return roleId;
    }

    public void setRoleId(int roleId) {
        this.roleId = roleId;
    }

    public UserRole() {
    }

    public UserRole(int id, int userId, int roleId) {
        this.id = id;
        this.userId = userId;
        this.roleId = roleId;
    }
}
