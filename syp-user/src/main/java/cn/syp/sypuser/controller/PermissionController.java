package cn.syp.sypuser.controller;

import cn.syp.sypcommons.utils.ResultData;
import cn.syp.sypcommons.utils.StatusCode;
import cn.syp.sypuser.entity.Permissions;
import cn.syp.sypuser.service.PermissionService;
import com.github.pagehelper.PageInfo;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.sql.Timestamp;
import java.util.List;
import java.util.Map;

/**
 * TODO
 *
 * @Author syp
 * @Date 2020/3/25 16:31
 * @Description
 */
@RestController
@RequestMapping("/permission")
public class PermissionController {
    private static final Logger logger = LoggerFactory.getLogger(PermissionController.class);
    @Autowired
    private PermissionService permissionService;

    @RequestMapping(value = "/getPermissionsList", method = RequestMethod.POST)
    public @ResponseBody
    ResultData getPermissionsList(@RequestBody Map<String ,String > map){
        // 接参
        int  isDelete = Integer.parseInt(map.get("is_delete"));
        // return
        ResultData resultData = new ResultData();
        Permissions permissions = new Permissions();
        permissions.setIsDelete(isDelete);
        List<Permissions> permissionsList = permissionService.selectPermissionsList(permissions);
        if(permissionsList!=null&&permissionsList.size()>0){
            resultData.setData(permissionsList);
            resultData.setStatus(StatusCode.SUCCESS_STATUS);
            resultData.setMsg(StatusCode.SUCCESS_MSG);
            logger.info("获取成功--->"+permissionsList.toString());
            logger.info("返回结果--->"+resultData.toString());
            return resultData;
        }
        resultData.setData(permissionsList);
        resultData.setStatus(StatusCode.DEFEAT_STATUS);
        resultData.setMsg(StatusCode.DEFEAT_MSG);
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
    @RequestMapping(value = "/getPermissionsListPager", method = RequestMethod.POST)
    public @ResponseBody
    ResultData getPermissionsListPager(@RequestBody Map<String,String> requestBody){
        // 接参
        String isDelete = requestBody.get("isDelete");
        int pageNum = Integer.parseInt(requestBody.get("pageNum"));
        int pageSize = Integer.parseInt(requestBody.get("pageSize"));
        String permissionName = requestBody.get("permission_name");
        Permissions permissions = new Permissions();
        // 空值判断
        if(StringUtils.isNotBlank(permissionName)){
            permissions.setPermissionName(permissionName);
            logger.info("参数permissionName--->permissionName为空");
        }
        if(StringUtils.isBlank(isDelete)){
            permissions.setIsDelete(0);
            logger.info("参数isDelete--->isDelete为空，默认设置为0");
        }
        // 分页查询
        PageInfo<Permissions> permissionsListPage = permissionService.selecPermissionsListByPage(permissions, pageNum, pageSize);
        // 结果集赋值
        ResultData resultData = new ResultData();
        if(permissionsListPage!=null){
            resultData.setData(permissionsListPage);
            resultData.setStatus(StatusCode.SUCCESS_STATUS);
            resultData.setMsg(StatusCode.SUCCESS_MSG);
            logger.info("查询成功");
            logger.info("返回结果--->"+resultData.toString());
            return resultData;
        }
        resultData.setStatus(StatusCode.DEFEAT_STATUS);
        resultData.setMsg(StatusCode.DEFEAT_MSG);
        logger.info("查询失败");
        logger.info("返回结果--->"+resultData.toString());
        return resultData;
    }

    @RequestMapping(value = "/addPermission",method = RequestMethod.POST)
    public @ResponseBody
    ResultData addPermission(@RequestBody Map<String ,String > requestBody){
        String permissionName = requestBody.get("permission_name");
        String permissionEncoding = requestBody.get("permission_encoding");
        ResultData resultData = new ResultData();
        if(StringUtils.isBlank(permissionName)){
            resultData.setStatus(StatusCode.DEFEAT_STATUS);
            resultData.setMsg(StatusCode.DEFEAT_MSG);
            logger.info("参数permissionName--->为空");
            return resultData;

        }
        if(StringUtils.isBlank(permissionEncoding)){
            resultData.setStatus(StatusCode.DEFEAT_STATUS);
            resultData.setMsg(StatusCode.DEFEAT_MSG);
            logger.info("参数permissionEncoding--->为空");
            return resultData;
        }
        Permissions permissions = new Permissions();
        permissions.setPermissionName(permissionName);
        permissions.setPermissionEncoding(permissionEncoding);
        permissions.setIsDelete(0);
        int insertPermissionStatus = permissionService.addPermission(permissions);
        if(insertPermissionStatus>0){
            resultData.setData(insertPermissionStatus);
            resultData.setStatus(StatusCode.SUCCESS_STATUS);
            resultData.setMsg(StatusCode.SUCCESS_MSG);
            logger.info("添加成功--->权限添加");
            logger.info("返回结果--->"+resultData.toString());
            return resultData;
        }
        resultData.setStatus(StatusCode.DEFEAT_STATUS);
        resultData.setMsg(StatusCode.DEFEAT_MSG);
        logger.info("添加失败--->");
        logger.info("返回结果--->"+resultData.toString());
        return resultData;
    }

    @RequestMapping(value = "/deletePermissionById",method = RequestMethod.POST)
    public @ResponseBody
    ResultData deletePermissionById(@RequestBody Map<String,String> requestBody){
        String id = requestBody.get("id");
        ResultData resultData = new ResultData();
        if(StringUtils.isBlank(id)){
            resultData.setStatus(StatusCode.DEFEAT_STATUS);
            resultData.setMsg(StatusCode.DEFEAT_MSG);
            logger.info("删除参数--->id为空");
            logger.info("返回结果--->"+resultData.toString());
            return resultData;
        }
        int permissionId = Integer.parseInt(requestBody.get("id"));
        int deletePermissionStatus = permissionService.deletePermissionById(permissionId);
        if(deletePermissionStatus>0){
            resultData.setData(deletePermissionStatus);
            resultData.setStatus(StatusCode.SUCCESS_STATUS);
            resultData.setMsg(StatusCode.SUCCESS_MSG);
            logger.info("删除成功--->permissionId:"+permissionId+"已被删除");
            logger.info("返回结果--->"+resultData.toString());
            return resultData;
        }
        resultData.setStatus(StatusCode.DEFEAT_STATUS);
        resultData.setMsg(StatusCode.DEFEAT_MSG);
        logger.info("删除失败--->permissionId:"+permissionId+"未被删除");
        logger.info("返回结果--->"+resultData.toString());
        return resultData;
    }

    @RequestMapping(value = "/permissionStatusEdit",method = RequestMethod.POST)
    public @ResponseBody
    ResultData editPermission(@RequestBody Map<String,String> requestBody){
        String id = requestBody.get("id");
        String permissionName = requestBody.get("permission_name");
        String addTime = requestBody.get("add_time");
        String permissionEncoding = requestBody.get("permission_encoding");

        ResultData resultData = new ResultData();
        // id不能为空
        if(StringUtils.isBlank(id)){
            resultData.setStatus(StatusCode.DEFEAT_STATUS);
            resultData.setMsg(StatusCode.DEFEAT_MSG);
            logger.info("编辑失败--->id为空");
            logger.info("返回结果--->"+resultData.toString());
            return resultData;
        }
        // 实例赋值
        Permissions permissions = new Permissions();
        if(StringUtils.isNotBlank(permissionName)){
            permissions.setPermissionName(permissionName);
            logger.info("参数为空--->permissionName为空");
        }
        if(StringUtils.isNotBlank(addTime)){
            permissions.setAddTime(Timestamp.valueOf(addTime));
            logger.info("参数为空--->addTime为空");
        }
        if(StringUtils.isNotBlank(permissionEncoding)){
            permissions.setPermissionEncoding(permissionEncoding);
            logger.info("参数为空--->permissionEncoding为空");
        }
        int roleId = Integer.parseInt(id);
        permissions.setId(roleId);
        // 数据修改
        int deletePermissionStatus = permissionService.editPermissionInfo(permissions);
        if(deletePermissionStatus>0){
            resultData.setData(deletePermissionStatus);
            resultData.setStatus(StatusCode.SUCCESS_STATUS);
            resultData.setMsg(StatusCode.SUCCESS_MSG);
            logger.info("编辑成功--->");
            logger.info("返回结果--->"+resultData.toString());
            return resultData;
        }
        resultData.setStatus(StatusCode.DEFEAT_STATUS);
        resultData.setMsg(StatusCode.DEFEAT_MSG);
        logger.info("编辑失败--->");
        logger.info("返回结果--->"+resultData.toString());
        return resultData;
    }

}
