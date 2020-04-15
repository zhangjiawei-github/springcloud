package cn.syp.sypuser.entity;

import java.io.Serializable;
import java.sql.Timestamp;

/**
 * TODO
 *
 * @Author syp
 * @Date 2020/3/23 2:21
 * @Description
 */
public class Role implements Serializable {
    private int id;
    private String roleName;
    private Timestamp addTime;
    private String roleEncoding;
    private int isDelete;

    @Override
    public String toString() {
        return "Role{" +
                "id=" + id +
                ", roleName='" + roleName + '\'' +
                ", addTime=" + addTime +
                ", roleEncoding='" + roleEncoding + '\'' +
                ", isDelete=" + isDelete +
                '}';
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }

    public Timestamp getAddTime() {
        return addTime;
    }

    public void setAddTime(Timestamp addTime) {
        this.addTime = addTime;
    }

    public String getRoleEncoding() {
        return roleEncoding;
    }

    public void setRoleEncoding(String roleEncoding) {
        this.roleEncoding = roleEncoding;
    }

    public int getIsDelete() {
        return isDelete;
    }

    public void setIsDelete(int isDelete) {
        this.isDelete = isDelete;
    }

    public Role() {
    }

    public Role(int id, String roleName, Timestamp addTime, String roleEncoding, int isDelete) {
        this.id = id;
        this.roleName = roleName;
        this.addTime = addTime;
        this.roleEncoding = roleEncoding;
        this.isDelete = isDelete;
    }
}
