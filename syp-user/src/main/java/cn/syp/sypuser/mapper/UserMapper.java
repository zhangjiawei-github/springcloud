package cn.syp.sypuser.mapper;

import cn.syp.sypuser.entity.User;
import org.apache.ibatis.annotations.Param;
import org.mapstruct.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * TODO 用户相关接口
 *
 * @Author syp
 * @Date 2020/3/18 18:40
 * @Description
 */
@Mapper
public interface UserMapper {

    User selectUserByPhoneAndPassword(@Param("phone_number") String phone_number, @Param("password") String password);

    List<User> selectUserByPhone(@Param("phone_number") String phone_number);

    int addUser(@Param("phone_number") String phone_number,@Param("username") String username,@Param("password") String password);

    User selectUserById(@Param("id") int id);

    User selectUserByEmail(@Param("e_mail") String email);

    int insertUserByEmail(@Param("id") int id,@Param("e_mail") String email);

    int updateUserById(@Param("id") int id,@Param("password") String password);

    List<User> selectUserList(User user);

    int deleteUserById(@Param("id") int id);

    int updateUserParamsById(User user);

    User getUserByPhone(@Param(value = "phone_number") String phone_number);





}
