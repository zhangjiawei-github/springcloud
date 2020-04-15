package cn.syp.sypuser.controller;

import cn.syp.sypcommons.utils.ResultData;
import cn.syp.sypcommons.utils.StatusCode;
import cn.syp.sypuser.entity.User;
import cn.syp.sypuser.service.UserService;
import cn.syp.sypuser.util.MD5Util;
import com.github.pagehelper.PageInfo;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.sql.Timestamp;
import java.text.ParseException;
import java.util.List;
import java.util.Map;

/**
 * TODO
 *
 * @author syp
 * @date 2020/3/18 1:10
 */
@RestController
@RequestMapping("/user")
public class UserController {
    private static final Logger logger = LoggerFactory.getLogger(RoleController.class);
    @Autowired
    private UserService userService;

    /**
     * 用户列表
     * @date 2020/3/23 20:08
     * @author syp
     * @params
     * @return
     */
    //@CrossOrigin(origins = "*")
    @RequestMapping(value = "/getUserList", method = RequestMethod.POST)
    public @ResponseBody
    ResultData getUserList(@RequestBody User user){
        ResultData resultData = new ResultData();
        List<User> userList = userService.selectUserList(user);
        if(userList!=null){
            resultData.setData(userList);
            resultData.setStatus(StatusCode.SUCCESS_STATUS);
            resultData.setMsg(StatusCode.SUCCESS_MSG);
            logger.info("查询成功--->获取用户列表成功");
            logger.info("返回结果--->"+resultData.toString());
            return resultData;
        }
        logger.info("查询失败--->");
        resultData.setStatus(StatusCode.DEFEAT_STATUS);
        resultData.setMsg(StatusCode.DEFEAT_MSG);
        logger.info("返回结果--->"+resultData.toString());
        return resultData;
    }

    /**
     * 用户列表分页
     * @date 2020/3/23 20:08
     * @author syp
     * @params
     * @return
     */
    @RequestMapping(value = "/getUserListPager", method = RequestMethod.POST)
    public @ResponseBody
    ResultData getUserListPager(@RequestBody Map<String,String> requestBody){
        // 接参
        String isDelete = requestBody.get("isDelete");
        int pageNum = Integer.parseInt(requestBody.get("pageNum"));
        int pageSize = Integer.parseInt(requestBody.get("pageSize"));
        String phoneNumber = requestBody.get("phone_number");
        User user = new User();
        // 实例赋值
        if(StringUtils.isNotBlank(phoneNumber)){
            user.setPhoneNumber(phoneNumber);
            logger.info("参数phoneNumber--->为空");
        }
        if(StringUtils.isBlank(isDelete)){
            logger.info("参数isDelete--->为空默认设置为0");
            user.setIsDelete(0);
        }
        // 结果集赋值
        ResultData resultData = new ResultData();
        // 分页查询（pageHelper插件）
        PageInfo<User> userListPager = userService.selectUserListByPage(user, pageNum, pageSize);
        if(userListPager!=null){
            resultData.setData(userListPager);
            resultData.setStatus(StatusCode.SUCCESS_STATUS);
            resultData.setMsg(StatusCode.SUCCESS_MSG);
            logger.info("分页查询--->分页查询成功");
            logger.info("返回结果--->"+resultData.toString());
            return resultData;
        }
        resultData.setStatus(StatusCode.DEFEAT_STATUS);
        resultData.setMsg(StatusCode.DEFEAT_MSG);
        logger.info("分页查询--->失败");
        logger.info("返回结果--->"+resultData.toString());
        return resultData;
    }

    /**
     * 根据用户ID删除用户
     * @date 2020/3/23 20:08
     * @author syp
     * @params
     * @return
     */
    @RequestMapping(value = "/deleteUserById", method = RequestMethod.POST)
    public @ResponseBody
    ResultData deleteUserById(@RequestBody Map<String,String> requestBody){
        int id = Integer.parseInt(requestBody.get("id"));
        int deleteStatus = userService.deleteUserById(id);
        ResultData resultData = new ResultData();
        if(deleteStatus>0){
            resultData.setStatus(StatusCode.SUCCESS_STATUS);
            resultData.setMsg(StatusCode.SUCCESS_MSG);
            logger.info("删除用户--->成功");
            logger.info("返回结果--->"+resultData.toString());
            return resultData;
        }
        resultData.setStatus(StatusCode.DEFEAT_STATUS);
        resultData.setMsg(StatusCode.DEFEAT_MSG);
        logger.info("删除用户--->失败");
        logger.info("返回结果--->"+resultData.toString());
        return resultData;
    }

    /**
     * 添加用户
     * @date 2020/3/24 2:09
     * @author syp
     * @params
     * @return
     */
    @RequestMapping(value = "/addUser", method = RequestMethod.POST)
    public @ResponseBody
    ResultData addUser(@RequestBody Map<String,String> requestBody){
        String phoneNumber = requestBody.get("phone_number");
        String username = requestBody.get("username");
        String password = requestBody.get("password");
        // md5加密
        String enPassword = MD5Util.calc(password);
        int addStatus = userService.addUser(phoneNumber,username,enPassword);
        ResultData resultData = new ResultData();
        if(addStatus>0){
            resultData.setStatus(StatusCode.SUCCESS_STATUS);
            resultData.setMsg(StatusCode.SUCCESS_MSG);
            logger.info("添加用户--->成功");
            logger.info("返回结果--->"+resultData.toString());
            return resultData;
        }
        resultData.setStatus(StatusCode.DEFEAT_STATUS);
        resultData.setMsg(StatusCode.DEFEAT_MSG);
        logger.info("添加用户--->失败");
        logger.info("返回结果--->"+resultData.toString());
        return resultData;
    }

    @RequestMapping(value = "/getUserByPhone", method = RequestMethod.POST)
    public @ResponseBody
    ResultData selectByPhone(@RequestBody Map<String,String> requestBody){
        String phoneNumber = requestBody.get("phone_number");
        List<User> userList = userService.selectUserByPhone(phoneNumber);
        ResultData resultData = new ResultData();
        if(userList!=null&&userList.size()>0){
            resultData.setData(userList);
            resultData.setStatus(StatusCode.SUCCESS_STATUS);
            resultData.setMsg(StatusCode.SUCCESS_MSG);
            logger.info("获取用户给成功--->");
            logger.info("返回结果--->"+resultData.toString());
            return resultData;
        }
        resultData.setStatus(StatusCode.DEFEAT_STATUS);
        resultData.setMsg(StatusCode.DEFEAT_MSG);
        logger.info("获取失败--->根据手机号查询用户");
        logger.info("返回结果--->"+resultData.toString());
        return resultData;
    }

    @RequestMapping(value = "/userEdit", method = RequestMethod.POST)
    public @ResponseBody
    ResultData setUserInfo(@RequestBody Map<String,String> requestBody) throws ParseException {
        ResultData resultData = new ResultData();
        // 接参
        String id = requestBody.get("id");

        String username = requestBody.get("username");
        String password = requestBody.get("password");
        String realName = requestBody.get("real_name");
        String phoneNumber = requestBody.get("phone_number");
        String age = requestBody.get("age");
        String birthDate = requestBody.get("birth_date");
        String sex = requestBody.get("sex");
        String headImg = requestBody.get("head_img");
        String status = requestBody.get("status");
        String eMail = requestBody.get("e_mail");
        User user = null;
        if(StringUtils.isNotBlank(id)){
            user = new User(Integer.parseInt(id),username,password,realName,phoneNumber,Integer.parseInt(age),
                    Timestamp.valueOf(birthDate),
                    Integer.parseInt(sex),headImg,null,0,Integer.parseInt(status),eMail);
        }else{
            resultData.setStatus(StatusCode.DEFEAT_STATUS);
            resultData.setMsg(StatusCode.DEFEAT_MSG);
            logger.info("参数id--->为空");
            logger.info("返回结果--->"+resultData.toString());
            return resultData;
        }
        System.out.println(user.toString());
        int editStatus = userService.updateUserParamsById(user);
        if(editStatus>0){
            resultData.setStatus(StatusCode.SUCCESS_STATUS);
            resultData.setMsg(StatusCode.SUCCESS_MSG);
            logger.info("编辑结果--->编辑成功");
            logger.info("返回结果--->"+resultData.toString());
            return resultData;
        }
        resultData.setStatus(StatusCode.DEFEAT_STATUS);
        resultData.setMsg(StatusCode.DEFEAT_MSG);
        logger.info("编辑结果--->编辑失败");
        logger.info("返回结果--->"+resultData.toString());
        return resultData;
    }

}
