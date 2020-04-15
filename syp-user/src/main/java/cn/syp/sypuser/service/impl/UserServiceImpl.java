package cn.syp.sypuser.service.impl;

import cn.syp.sypuser.entity.User;
import cn.syp.sypuser.mapper.UserMapper;
import cn.syp.sypuser.service.UserService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * TODO
 *
 * @Author syp
 * @Date 2020/3/18 18:38
 * @Description
 */
@Service
public class UserServiceImpl implements UserService {
    @Autowired(required = false)
    private UserMapper userMapper;

    @Override
    public User selectUserByPhoneAndPassword(String phone,String password) {
        return userMapper.selectUserByPhoneAndPassword(phone,password);
    }

    @Override
    public List<User> selectUserByPhone(String phone_number) {
        return userMapper.selectUserByPhone(phone_number);
    }

    @Override
    public int addUser(String phone_number, String username, String password) {
        return userMapper.addUser( phone_number,  username,  password);
    }

    @Override
    public User selectUserById(int id) {
        return userMapper.selectUserById(id);
    }

    @Override
    public User selectUserByEmail(String email) {
        return userMapper.selectUserByEmail(email);
    }

    @Override
    public int insertUserByEmail(int id, String email) {
        return userMapper.insertUserByEmail(id,email);
    }

    @Override
    public int updateUserById(int id, String password) {
        return userMapper.updateUserById(id,password);
    }

    @Override
    public List<User> selectUserList(User user) {
        return userMapper.selectUserList(user);
    }

    @Override
    public PageInfo<User> selectUserListByPage(User user,int pageNum, int pageSize) {
        PageHelper.startPage(pageNum, pageSize);
        List<User> lists = userMapper.selectUserList(user);
        PageInfo<User> pageInfo = new PageInfo<User>(lists);
        return pageInfo;
    }

    @Override
    public int deleteUserById(int id) {
        return userMapper.deleteUserById(id);
    }

    @Override
    public int updateUserParamsById(User user) {
        return userMapper.updateUserParamsById(user);
    }

    @Override
    public User getUserByPhone(String phone_number) {
        return userMapper.getUserByPhone(phone_number);
    }
}
