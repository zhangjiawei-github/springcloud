package cn.syp.sypuser.mapper;

import cn.syp.sypuser.entity.Permissions;
import cn.syp.sypuser.entity.Role;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * TODO
 *
 * @Author syp
 * @Date 2020/3/25 16:18
 * @Description
 */
public interface PermissionMapper {

    List<Permissions> selectPermissionsList(Permissions permissions);

    int addPermission(Permissions permissions);

    int deletePermissionById(@Param("id") int id);

    int editPermissionsInfo(Permissions permissions);

    List<Permissions> selectRolePermissionsByRoleId(@Param("roleId") int roleId);
}
