package cn.syp.sypuser.controller;

import cn.syp.sypcommons.utils.ResultData;
import cn.syp.sypcommons.utils.StatusCode;
import cn.syp.sypuser.entity.Role;
import cn.syp.sypuser.entity.User;
import cn.syp.sypuser.service.RoleService;
import com.github.pagehelper.PageInfo;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.sql.Timestamp;
import java.util.List;
import java.util.Map;

/**
 * TODO
 *
 * @Author syp
 * @Date 2020/3/24 21:28
 * @Description
 */

@RestController
@RequestMapping("/role")
public class RoleController {
    private static final Logger logger = LoggerFactory.getLogger(RoleController.class);
    @Autowired
    private RoleService roleService;

    @RequestMapping(value = "/getRolesList", method = RequestMethod.POST)
    public @ResponseBody
    ResultData getRolesList(@RequestBody Map<String ,String > map){
        // 接参
        //String id = map.get("id");
        //String role_name = map.get("role_name");
        //String add_time = map.get("add_time");
        //String role_encoding = map.get("role_encoding");
        int  isDelete = Integer.parseInt(map.get("is_delete"));
        // return
        ResultData resultData = new ResultData();
        Role role = new Role();
        role.setIsDelete(isDelete);
        List<Role> roleList = roleService.selectRolesList(role);
        if(roleList!=null&&roleList.size()>0){
            resultData.setData(roleList);
            resultData.setStatus(StatusCode.SUCCESS_STATUS);
            resultData.setMsg(StatusCode.SUCCESS_MSG);
            logger.info("查询成功");
            logger.info("返回结果--->"+resultData.toString());
            return resultData;
        }
        resultData.setData(roleList);
        resultData.setStatus(StatusCode.DEFEAT_STATUS);
        resultData.setMsg(StatusCode.DEFEAT_MSG);
        logger.info("查询失败");
        logger.info("返回结果--->"+resultData.toString());
        return resultData;
    }

    /**
     * 角色列表分页
     * @date 2020/3/24 21:42
     * @author syp
     * @params
     * @return
     */
    @RequestMapping(value = "/getRoleListPager", method = RequestMethod.POST)
    public @ResponseBody
    ResultData getRoleListPager(@RequestBody Map<String,String> requestBody){
        // 接参
        String isDelete = requestBody.get("isDelete");
        int pageNum = Integer.parseInt(requestBody.get("pageNum"));
        int pageSize = Integer.parseInt(requestBody.get("pageSize"));
        String roleName = requestBody.get("role_name");
        Role role = new Role();
        // 空值判断
        if(StringUtils.isNotBlank(roleName)){
            role.setRoleName(roleName);
            logger.info("参数--->roleName为空");
        }
        if(StringUtils.isBlank(isDelete)){
            role.setIsDelete(0);
            logger.info("参数--->isDelete为空，已默认设置为0");
        }
        // 分页查询
        PageInfo<Role> roleListPage = roleService.selecRolesListByPage(role, pageNum, pageSize);
        // 结果集赋值
        ResultData resultData = new ResultData();
        if(roleListPage!=null){
            resultData.setData(roleListPage);
            resultData.setStatus(StatusCode.SUCCESS_STATUS);
            resultData.setMsg(StatusCode.SUCCESS_MSG);
            logger.info("查询成功--->分页查询角色");
            logger.info("返回结果--->"+resultData.toString());
            return resultData;
        }
        resultData.setStatus(StatusCode.DEFEAT_STATUS);
        resultData.setMsg(StatusCode.DEFEAT_MSG);
        logger.info("查询失败--->分页查询");
        logger.info("返回结果--->"+resultData.toString());
        return resultData;
    }

    @RequestMapping(value = "/addRole",method = RequestMethod.POST)
    public @ResponseBody
    ResultData addRole(@RequestBody Map<String ,String > requestBody){
        String roleName = requestBody.get("role_name");
        String roleEncoding = requestBody.get("role_encoding");
        ResultData resultData = new ResultData();
        if(StringUtils.isBlank(roleName)){
            resultData.setStatus(StatusCode.DEFEAT_STATUS);
            resultData.setMsg(StatusCode.DEFEAT_MSG);
            logger.info("参数roleName--->为空");
            logger.info("返回结果--->"+resultData.toString());
            return resultData;
        }
        if(StringUtils.isBlank(roleEncoding)){
            resultData.setStatus(StatusCode.DEFEAT_STATUS);
            resultData.setMsg(StatusCode.DEFEAT_MSG);
            logger.info("参数roleEncoding--->为空");
            logger.info("返回结果--->"+resultData.toString());
            return resultData;
        }
        Role role = new Role();
        role.setRoleName(roleName);
        role.setRoleEncoding(roleEncoding);
        role.setIsDelete(0);
        int insertRoleStatus = roleService.addRole(role);
        if(insertRoleStatus>0){
            resultData.setData(insertRoleStatus);
            resultData.setStatus(StatusCode.SUCCESS_STATUS);
            resultData.setMsg(StatusCode.SUCCESS_MSG);
            logger.info("添加角色--->添加角色成功");
            logger.info("返回结果--->"+resultData.toString());
            return resultData;
        }
        resultData.setStatus(StatusCode.DEFEAT_STATUS);
        resultData.setMsg(StatusCode.DEFEAT_MSG);
        logger.info("添加橘色--->添加角色失败");
        logger.info("返回结果--->"+resultData.toString());
        return resultData;
    }

    @RequestMapping(value = "/deleteRoleById",method = RequestMethod.POST)
    public @ResponseBody
    ResultData deleteRoleById(@RequestBody Map<String,String> requestBody){
        String id = requestBody.get("id");
        ResultData resultData = new ResultData();
        if(StringUtils.isBlank(id)){
            resultData.setStatus(StatusCode.DEFEAT_STATUS);
            resultData.setMsg(StatusCode.DEFEAT_MSG);
            logger.info("参数id--->为空");
            logger.info("返回结果--->"+resultData.toString());
            return resultData;
        }
        int roleId = Integer.parseInt(requestBody.get("id"));
        int deleteRoleStatus = roleService.deleteRoleById(roleId);
        if(deleteRoleStatus>0){
            resultData.setData(deleteRoleStatus);
            resultData.setStatus(StatusCode.SUCCESS_STATUS);
            resultData.setMsg(StatusCode.SUCCESS_MSG);
            logger.info("删除角色--->删除成功");
            logger.info("返回结果--->"+resultData.toString());
            return resultData;
        }
        resultData.setStatus(StatusCode.DEFEAT_STATUS);
        resultData.setMsg(StatusCode.DEFEAT_MSG);
        logger.info("删除角色--->删除失败");
        logger.info("返回结果--->"+resultData.toString());
        return resultData;
    }

    @RequestMapping(value = "/roleEdit",method = RequestMethod.POST)
    public @ResponseBody
    ResultData editRole(@RequestBody Map<String,String> requestBody){
        String id = requestBody.get("id");
        String roleName = requestBody.get("role_name");
        String addTime = requestBody.get("add_time");
        String roleEncoding = requestBody.get("role_encoding");

        ResultData resultData = new ResultData();
        // id不能为空
        if(StringUtils.isBlank(id)){
            resultData.setStatus(StatusCode.DEFEAT_STATUS);
            resultData.setMsg(StatusCode.DEFEAT_MSG);
            logger.info("编辑角色--->编辑失败id为空");
            logger.info("返回结果--->"+resultData.toString());
            return resultData;
        }
        // 实例赋值
        Role role = new Role();
        if(StringUtils.isNotBlank(roleName)){
            role.setRoleName(roleName);
        }
        if(StringUtils.isNotBlank(addTime)){
            role.setAddTime(Timestamp.valueOf(addTime));
        }
        if(StringUtils.isNotBlank(roleEncoding)){
            role.setRoleEncoding(roleEncoding);
        }
        int roleId = Integer.parseInt(id);
        role.setId(roleId);
        // 数据修改
        int deleteRoleStatus = roleService.editRoleInfo(role);
        if(deleteRoleStatus>0){
            resultData.setData(deleteRoleStatus);
            resultData.setStatus(StatusCode.SUCCESS_STATUS);
            resultData.setMsg(StatusCode.SUCCESS_MSG);
            logger.info("编辑--->编辑成功");
            logger.info("返回结果--->"+resultData.toString());
            return resultData;
        }
        resultData.setStatus(StatusCode.DEFEAT_STATUS);
        resultData.setMsg(StatusCode.DEFEAT_MSG);
        logger.info("编辑--->编辑失败");
        logger.info("返回结果--->"+resultData.toString());
        return resultData;
    }


}
