package cn.syp.sypuser.service;

import cn.syp.sypuser.entity.User;
import com.github.pagehelper.PageInfo;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * TODO
 *
 * @Author syp
 * @Date 2020/3/18 1:27
 * @Description
 */
public interface UserService {
    /**
     * 根据手机号、密码查找用户
     * @param phone_number
     * @param password
     * @return
     */
    User selectUserByPhoneAndPassword(String phone_number,String password);

    /**
     * 根据手机号查找用户 注册时查手机号是否使用
     * @param phone_number
     * @return
     */
    List<User> selectUserByPhone(String phone_number);

    /**
     * 根据手机号、用户名、密码 注册
     * @param phone_number
     * @param username
     * @param password
     * @return
     */
    int addUser(String phone_number,String username,String password);

    /**
     * 根据id 查用户的个人信息
     * @param id
     * @return
     */
    User selectUserById(int id);

    /**
     * 根据邮箱查询 邮箱是否被绑定
     * @param email
     * @return
     */
    User selectUserByEmail(String email);

    /**
     * 根据邮箱进行绑定用户 邮箱验证绑定
     * @param id
     * @param email
     * @return
     */
    int insertUserByEmail(int id,String email);
    /**
     * 根据邮箱进行绑定用户 邮箱验证绑定
     * @param id
     * @param password
     * @return
     */
    int updateUserById(int id,String password);

    /**
     * 查询所有用户列表
     * @date 2020/3/23 16:07
     * @author syp
     * @params
     * @return
     */
    List<User> selectUserList(User user);

    /**
     * 查询所有未删除的用户 （分页）
     * @date 2020/3/23 20:03
     * @author syp
     * @params
     * @return
     */
    PageInfo<User> selectUserListByPage(User user,int pageNum, int pageSize);

    /**
     * 删除用户
     * @date 2020/3/24 0:10
     * @author syp
     * @params
     * @return
     */
    int deleteUserById(int id);

    /**
     * 编辑用户
     * @date 2020/3/24 16:05
     * @author syp
     * @params
     * @return
     */
    int updateUserParamsById(User user);

    /**
     * 根据手机号获取用户
     * @date 2020/3/25 22:29
     * @author syp
     * @params
     * @return
     */
    User getUserByPhone(String phone_number);




}
