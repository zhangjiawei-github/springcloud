package cn.syp.sypuser.mapper;

import cn.syp.sypuser.entity.Role;
import org.apache.ibatis.annotations.Param;
import org.mapstruct.Mapper;

import java.util.List;

/**
 * TODO 角色相关接口
 *
 * @Author syp
 * @Date 2020/3/24 20:53
 * @Description
 */
@Mapper
public interface RoleMapper {

    List<Role> selectRolesList(Role role);

    int addRole(Role role);

    int deleteRoleById(@Param("id") int id);

    int editRoleInfo(Role role);

    List<Role> selectUserRolesByUserId(@Param("userId") int userId);

}
