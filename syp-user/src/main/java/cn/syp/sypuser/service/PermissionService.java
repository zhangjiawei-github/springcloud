package cn.syp.sypuser.service;

import cn.syp.sypuser.entity.Permissions;
import cn.syp.sypuser.entity.Role;
import com.github.pagehelper.PageInfo;

import java.util.List;

/**
 * TODO
 *
 * @Author syp
 * @Date 2020/3/25 16:22
 * @Description
 */
public interface PermissionService {
    /**
     * 查询所有权限列表
     * @date 2020/3/24 21:19
     * @author syp
     * @params
     * @return
     */
    List<Permissions> selectPermissionsList(Permissions permissions);

    /**
     * 查询所有权限列表（分页）
     * @date 2020/3/24 21:23
     * @author syp
     * @params
     * @return
     */
    PageInfo<Permissions> selecPermissionsListByPage(Permissions permissions, int pageNum, int pageSize);

    /**
     * 添加权限
     * @date 2020/3/24 22:47
     * @author syp
     * @params
     * @return
     */
    int addPermission(Permissions permissions);

    /**
     * 删除权限
     * @date 2020/3/24 23:47
     * @author syp
     * @params
     * @return
     */
    int deletePermissionById(int id);

    /**
     * 编辑权限信息
     * @date 2020/3/25 0:09
     * @author syp
     * @params
     * @return
     */
    int editPermissionInfo(Permissions permissions);

    List<Permissions> selectRolePermissionsByRoleId(int roleId);
}
