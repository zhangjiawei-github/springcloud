package cn.syp.sypuser.controller;

import cn.syp.sypcommons.utils.ResultData;
import cn.syp.sypcommons.utils.StatusCode;
import cn.syp.sypuser.constant.RedisConstant;
import cn.syp.sypuser.entity.Permissions;
import cn.syp.sypuser.entity.Role;
import cn.syp.sypuser.entity.User;
import cn.syp.sypuser.service.PermissionService;
import cn.syp.sypuser.service.RoleService;
import cn.syp.sypuser.service.UserService;
import cn.syp.sypuser.util.MD5Util;
import cn.syp.sypuser.util.RedisUtil;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.lang.StringUtils;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * TODO
 *
 * @Author syp
 * @Date 2020/3/23 14:00
 * @Description
 */
@RestController
public class LoginController {
    private static final Logger logger = LoggerFactory.getLogger(LoginController.class);
    @Autowired
    private RedisUtil redisUtil;
    @Autowired
    private UserService userService;
    @Autowired
    private RoleService roleService;
    @Autowired
    private PermissionService permissionService;
    /**
     * 用户登录
     * @date 2020/3/18 1:23
     * @author syp
     * @params
     * @return
     */
    @ApiOperation(value = "登陆", notes = "参数:手机号 密码")
    @RequestMapping(value = "/login",method = RequestMethod.POST)
    public @ResponseBody
    ResultData login(@RequestBody Map<String,String> requestBody){
        String phoneNumber = requestBody.get("phone_number");
        String password = requestBody.get("password");

        ResultData resultData = new ResultData();

        // shiro登录 创建对象加密等操作
        Subject subject = SecurityUtils.getSubject();
        System.out.println(subject);
        // md5加密
        String enPassword = MD5Util.calc(password);

        // 用户密码token生成
        UsernamePasswordToken token = new UsernamePasswordToken(
                phoneNumber,enPassword
        );
        // 执行登录方法
        try{
            subject.login(token);
        }catch (AuthenticationException e){
            logger.warn("请求参数：phoneNumber-->"+phoneNumber);
            logger.warn("登录失败，账号密码错误！");
            resultData.setStatus(StatusCode.DEFEAT_STATUS);
            resultData.setMsg(StatusCode.DEFEAT_MSG);
            logger.info("返回结果--->"+resultData.toString());
            return resultData;
        }
        // 获取用户基本信息
        User user = userService.getUserByPhone(phoneNumber);
        // 缓存登录信息
        String key = MD5Util.calc(RedisConstant.REDIS_WHACCOUNT_USER_KEY_PREFIX+user.getId());
        String sessionValue = MD5Util.calc(user.getId()+user.getPhoneNumber()+user.getPassword());
        redisUtil.set(key,sessionValue);
        redisUtil.setKeyLiveTime(key,RedisConstant.REDIS_SESSION_MAX_ACTIVE); // 设置为20分钟
        // return数据封装
        Map<String,Object> loginMap = new HashMap<>();
        user.setPassword(null);
        loginMap.put("user",user);
        loginMap.put("token",key);
        List<Role> roles = roleService.selectUserRolesByUserId(user.getId());
        loginMap.put("roles",roles);
        Map<String,Object> permissionsMap = new HashMap<>();
        for(int i = 0 ;i<roles.size();i++){
            int roleId = roles.get(i).getId();
            List<Permissions> permissions = permissionService.selectRolePermissionsByRoleId(roleId);
            for(int j = 0 ;j<permissions.size();j++){
                String permissionEncoding = permissions.get(j).getPermissionEncoding();
                permissionsMap.put(permissionEncoding,permissions.get(j));
            }
        }
        loginMap.put("permissions",permissionsMap);

        resultData.setData(loginMap);
        resultData.setStatus(StatusCode.SUCCESS_STATUS);
        resultData.setMsg(StatusCode.SUCCESS_MSG);
        logger.info("返回结果--->"+resultData.toString());
        return resultData;
    }

    /**
     * 注册
     * @date 2020/3/23 1:50
     * @author syp
     * @params
     * @return
     */
    @ApiOperation(value = "注册", notes = "参数:手机号 密码 用户名")
    @RequestMapping(value = "/register",method = RequestMethod.POST)
    public @ResponseBody
    ResultData register(String phone_number,String username,String password){
        ResultData resultData = new ResultData();
        if(StringUtils.isBlank(phone_number)){
            resultData.setStatus(StatusCode.DEFEAT_STATUS);
            resultData.setMsg("phone_number"+StatusCode.NULL_MSG);
            logger.info("注册--->phone_number为空");
            logger.info("返回结果--->"+resultData.toString());
            return resultData;
        }
        if(StringUtils.isBlank(username)){
            resultData.setStatus(StatusCode.DEFEAT_STATUS);
            resultData.setMsg("username"+StatusCode.NULL_MSG);
            logger.info("注册--->username为空");
            logger.info("返回结果--->"+resultData.toString());
            return resultData;
        }
        if(StringUtils.isBlank(password)){
            resultData.setStatus(StatusCode.DEFEAT_STATUS);
            resultData.setMsg("password"+StatusCode.NULL_MSG);
            logger.info("注册--->password为空");
            logger.info("返回结果--->"+resultData.toString());
            return resultData;
        }
        int insertStatus = userService.addUser(phone_number, username, password);
        if(insertStatus>0){
            resultData.setStatus(StatusCode.SUCCESS_STATUS);
            resultData.setMsg(StatusCode.SUCCESS_MSG);
            logger.info("添加用户--->"+username);
            logger.info("返回结果--->"+resultData.toString());
            return resultData;
        }
        resultData.setStatus(StatusCode.DEFEAT_STATUS);
        resultData.setMsg(StatusCode.DEFEAT_MSG);
        logger.info("添加用户--->"+username+" 失败");
        logger.info("返回结果--->"+resultData.toString());
        return resultData;
    }

    /**
     * 页面跳转验证
     * @date 2020/3/26 15:11
     * @author syp
     * @params
     * @return
     */
    @ApiOperation(value = "token验证", notes = "用户id和token")
    @RequestMapping(value = "/tokenVeryFy",method = RequestMethod.POST)
    public @ResponseBody
    ResultData tokenVeryFy(@RequestBody Map<String,String> requestBody){
        String userId = requestBody.get("userId");
        String token = requestBody.get("token");
        ResultData resultData = new ResultData();

        if(StringUtils.isBlank(userId)||StringUtils.isBlank(token)){
            resultData.setMsg(StatusCode.DEFEAT_MSG);
            resultData.setStatus(StatusCode.DEFEAT_STATUS);
            logger.info("token验证--->userId为空");
            logger.info("返回结果--->"+resultData.toString());
        }
        String getRedisKey = redisUtil.get(token);

        if(getRedisKey==null){
            // 验证失败
            resultData.setMsg(RedisConstant.REDIS_SESSION_DIE_MSG);
            resultData.setStatus(RedisConstant.REDIS_SESSION_DIE_STATUS);
            logger.info("验证失败--->redis键已过期或为null");
            logger.info("返回结果--->"+resultData.toString());
            return resultData;
        }
        String reToken = MD5Util.calc(RedisConstant.REDIS_WHACCOUNT_USER_KEY_PREFIX + userId);
        // 验证成功
        if(reToken.equals(token)){
            logger.info("验证成功--->token比对成功！");
            logger.info("返回结果--->"+resultData.toString());
            resultData.setMsg(StatusCode.SUCCESS_MSG);
            resultData.setStatus(StatusCode.SUCCESS_STATUS);
            return resultData;
        }
        // 验证失败
        resultData.setMsg(StatusCode.DEFEAT_MSG);
        resultData.setStatus(StatusCode.DEFEAT_STATUS);
        logger.info("验证失败--->非法途径验证");
        logger.info("返回结果--->"+resultData.toString());
        return resultData;




    }

}
