package cn.syp.sypuser.service.impl;

import cn.syp.sypuser.entity.Permissions;
import cn.syp.sypuser.mapper.PermissionMapper;
import cn.syp.sypuser.service.PermissionService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * TODO
 *
 * @Author syp
 * @Date 2020/3/25 16:24
 * @Description
 */
@Service
public class PermissionServiceImpl implements PermissionService {

    @Autowired(required = false)
    private PermissionMapper permissionMapper;


    @Override
    public List<Permissions> selectPermissionsList(Permissions permissions) {
        return permissionMapper.selectPermissionsList(permissions);
    }

    @Override
    public PageInfo<Permissions> selecPermissionsListByPage(Permissions permissions, int pageNum, int pageSize) {
        PageHelper.startPage(pageNum, pageSize);
        List<Permissions> lists = permissionMapper.selectPermissionsList(permissions);
        PageInfo<Permissions> pageInfo = new PageInfo<Permissions>(lists);
        return pageInfo;
    }

    @Override
    public int addPermission(Permissions permissions) {
        return permissionMapper.addPermission(permissions);
    }

    @Override
    public int deletePermissionById(int id) {
        return permissionMapper.deletePermissionById(id);
    }

    @Override
    public int editPermissionInfo(Permissions permissions) {
        return permissionMapper.editPermissionsInfo(permissions);
    }

    @Override
    public List<Permissions> selectRolePermissionsByRoleId(int roleId) {
        return permissionMapper.selectRolePermissionsByRoleId(roleId);
    }
}
