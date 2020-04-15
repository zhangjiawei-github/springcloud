package cn.syp.sypuser.entity;

import java.io.Serializable;
import java.sql.Timestamp;

/**
 * TODO
 *
 * @Author syp
 * @Date 2020/3/23 2:41
 * @Description
 */
public class Permissions implements Serializable {
    private int id;
    private String permissionName;
    private Timestamp addTime;
    private String permissionEncoding;
    private int isDelete;

    @Override
    public String toString() {
        return "Permissions{" +
                "id=" + id +
                ", permissionName='" + permissionName + '\'' +
                ", addTime=" + addTime +
                ", permissionEncoding='" + permissionEncoding + '\'' +
                ", isDelete=" + isDelete +
                '}';
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getPermissionName() {
        return permissionName;
    }

    public void setPermissionName(String permissionName) {
        this.permissionName = permissionName;
    }

    public Timestamp getAddTime() {
        return addTime;
    }

    public void setAddTime(Timestamp addTime) {
        this.addTime = addTime;
    }

    public String getPermissionEncoding() {
        return permissionEncoding;
    }

    public void setPermissionEncoding(String permissionEncoding) {
        this.permissionEncoding = permissionEncoding;
    }

    public int getIsDelete() {
        return isDelete;
    }

    public void setIsDelete(int isDelete) {
        this.isDelete = isDelete;
    }

    public Permissions() {
    }

    public Permissions(int id, String permissionName, Timestamp addTime, String permissionEncoding, int isDelete) {
        this.id = id;
        this.permissionName = permissionName;
        this.addTime = addTime;
        this.permissionEncoding = permissionEncoding;
        this.isDelete = isDelete;
    }
}
