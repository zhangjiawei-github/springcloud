package cn.syp.sypuser.service;

import cn.syp.sypuser.entity.Role;
import com.github.pagehelper.PageInfo;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * TODO
 *
 * @Author syp
 * @Date 2020/3/24 21:17
 * @Description
 */
public interface RoleService {

    /**
     * 查询所有角色列表
     * @date 2020/3/24 21:19
     * @author syp
     * @params
     * @return
     */
    List<Role> selectRolesList(Role role);

    /**
     * 查询所有角色列表（分页）
     * @date 2020/3/24 21:23
     * @author syp
     * @params
     * @return
     */
    PageInfo<Role> selecRolesListByPage(Role role,int pageNum,int pageSize);

    /**
     * 添加角色
     * @date 2020/3/24 22:47
     * @author syp
     * @params
     * @return
     */
    int addRole(Role role);

    /**
     * 删除角色
     * @date 2020/3/24 23:47
     * @author syp
     * @params
     * @return
     */
    int deleteRoleById(int id);

    /**
     * 编辑角色信息
     * @date 2020/3/25 0:09
     * @author syp
     * @params
     * @return
     */
    int editRoleInfo(Role role);

    /**
     * 通过用户ID查询用户的所有角色
     * @date 2020/3/25 19:30
     * @author syp
     * @params
     * @return
     */
    List<Role> selectUserRolesByUserId(int userId);
}
