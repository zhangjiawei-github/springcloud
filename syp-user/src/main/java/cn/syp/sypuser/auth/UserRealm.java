package cn.syp.sypuser.auth;

import cn.syp.sypuser.entity.Permissions;
import cn.syp.sypuser.entity.Role;
import cn.syp.sypuser.entity.User;
import cn.syp.sypuser.service.PermissionService;
import cn.syp.sypuser.service.RoleService;
import cn.syp.sypuser.service.UserService;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * TODO 自定义shiro的授权信息配置类 UserRealm
 *
 * @Author syp
 * @Date 2020/3/25 19:03
 * @Description
 */
public class UserRealm extends AuthorizingRealm {

    @Autowired
    private UserService userService;

    @Autowired
    private RoleService roleService;

    @Autowired
    private PermissionService permissionService;

    // 授权配置
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        // 简单授权信息对象
        SimpleAuthorizationInfo authorizationInfo = new SimpleAuthorizationInfo();
        // 获取主要的身份
        User user = (User)principalCollection.getPrimaryPrincipal();
        System.out.println("primaryPrincipal-->"+user.toString());
        // 获取用户下的所有角色
        List<Role> rolesList = roleService.selectUserRolesByUserId(user.getId());
        // 存放角色的编码
        Set<String> roleEncodings = new HashSet<>(rolesList.size());
        for(int i = 0;i<rolesList.size();i++){
            roleEncodings.add(rolesList.get(i).getRoleEncoding());
        }
        // 把所有信息交给shiro
        authorizationInfo.setRoles(roleEncodings);
        // 获取角色下的所有权限
        int roleId = 0;
        Set<String> permissionEncodings = new HashSet<>();
        for(int i = 0;i<rolesList.size();i++){
            roleId = rolesList.get(i).getId();
            List<Permissions> permissionsList = permissionService.selectRolePermissionsByRoleId(roleId);
            for(int j = 0;j<permissionsList.size();j++){
                permissionEncodings.add(permissionsList.get(j).getPermissionEncoding());
            }
        }
        // 把权限交给shiro
        authorizationInfo.setStringPermissions(permissionEncodings);
        return authorizationInfo;
    }

    // 认证配置
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
        // 获取token中的 第一个参数  也就是username
        String phoneNumber = (String) token.getPrincipal();
        // 根据手机号获取用户
        User user = userService.getUserByPhone(phoneNumber);
        if(user==null){
            return null;
        }
        // 认证信息配置
        SimpleAuthenticationInfo authenticationInfo = new SimpleAuthenticationInfo(
                user, // 用户名
                user.getPassword(), // 密码
                null, // salt 密码加密的盐
                getName() // realmName
        );
        return authenticationInfo;
    }
}
