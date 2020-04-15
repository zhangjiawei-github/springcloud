package cn.syp.sypuser.service.impl;

import cn.syp.sypuser.entity.Role;
import cn.syp.sypuser.entity.User;
import cn.syp.sypuser.mapper.RoleMapper;
import cn.syp.sypuser.service.RoleService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * TODO
 *
 * @Author syp
 * @Date 2020/3/24 21:17
 * @Description
 */
@Service
public class RoleServiceImpl implements RoleService {

    @Autowired(required = false)// required 表示该bean必须存在，不存在就报错
    private RoleMapper roleMapper;

    @Override
    public List<Role> selectRolesList(Role role) {
        return roleMapper.selectRolesList(role);
    }

    @Override
    public PageInfo<Role> selecRolesListByPage(Role role, int pageNum, int pageSize) {
        PageHelper.startPage(pageNum, pageSize);
        List<Role> lists = roleMapper.selectRolesList(role);
        PageInfo<Role> pageInfo = new PageInfo<Role>(lists);
        return pageInfo;
    }

    @Override
    public int addRole(Role role) {
        return roleMapper.addRole(role);
    }

    @Override
    public int deleteRoleById(int id) {
        return roleMapper.deleteRoleById(id);
    }

    @Override
    public int editRoleInfo(Role role) {
        return roleMapper.editRoleInfo(role);
    }

    @Override
    public List<Role> selectUserRolesByUserId(int userId) {
        return roleMapper.selectUserRolesByUserId(userId);
    }
}
