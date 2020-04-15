package cn.syp.sypuser.config;

import cn.syp.sypuser.auth.UserRealm;
import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.realm.Realm;
import org.apache.shiro.spring.LifecycleBeanPostProcessor;
import org.apache.shiro.spring.security.interceptor.AuthorizationAttributeSourceAdvisor;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.springframework.aop.framework.autoproxy.DefaultAdvisorAutoProxyCreator;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.DependsOn;
import org.springframework.web.filter.DelegatingFilterProxy;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * TODO 对shiro做的配置
 *  如: 登陆路径、
 *      权限验证、
 *      密码匹配等等
 *
 * @Author syp
 * @Date 2020/3/25 20:15
 * @Description
 */
@Configuration
public class ShiroConfig {

    /**
     * 授权过滤代理
     * @date 2020/3/25 22:39
     * @author syp
     * @params
     * @return
     */
    @Bean
    public FilterRegistrationBean delegatingFilterProxy() {
        // 过滤注册代理bean
        FilterRegistrationBean filterRegistrationBean = new FilterRegistrationBean();
        // 授权过略代理实例
        DelegatingFilterProxy proxy = new DelegatingFilterProxy();
        // 开启生命周期
        proxy.setTargetFilterLifecycle(true);
        // bean名称设置
        proxy.setTargetBeanName("shiroFilter");
        // 注册代理类
        filterRegistrationBean.setFilter(proxy);
        return filterRegistrationBean;
    }

    /**
     * shiro 过滤工厂bean  安全管理
     * @date 2020/3/25 22:42
     * @author syp
     * @params
     * @return
     */
    @Bean("shiroFilter")
    public ShiroFilterFactoryBean shirFilter(SecurityManager securityManager){
        // 过滤工厂
        ShiroFilterFactoryBean shiroFilterFactoryBean = new ShiroFilterFactoryBean();
        // 必须设置 SecurityManager
        shiroFilterFactoryBean.setSecurityManager(securityManager);
        // setLoginUrl 如果不设置值，默认会自动寻找Web工程根目录下的"/login.jsp"页面 或 "/login" 映射
        //shiroFilterFactoryBean.setLoginUrl("/login");
        // 设置成功跳转的页面
        //shiroFilterFactoryBean.setSuccessUrl("/");
        // 设置无权限时跳转的 url;
        //shiroFilterFactoryBean.setUnauthorizedUrl("/");

        // 设置拦截器
        Map<String, String> filterChainDefinitionMap = new LinkedHashMap<>();
        // 开放接口
        // filterChainDefinitionMap.put("/**", "anon");//放开全部
        filterChainDefinitionMap.put("/login", "anon");  //放开登录
        filterChainDefinitionMap.put("/tokenVeryFy", "anon");  //登录验证
        filterChainDefinitionMap.put("/*/get*", "anon"); //放开查询
        filterChainDefinitionMap.put("/upload/*", "anon"); //放开资源上传
        filterChainDefinitionMap.put("/search/*", "anon"); //放开资源搜索
        filterChainDefinitionMap.put("/user/*", "anon"); //放开普通用户操作
        filterChainDefinitionMap.put("/permission/*", "anon"); //放开普通用户操作
        filterChainDefinitionMap.put("/role/*", "anon"); //放开普通用户操作

        filterChainDefinitionMap.put("/swagger-ui.html", "anon"); //放开swagger
        filterChainDefinitionMap.put("/swagger-resources/**", "anon"); //放开swagger
        filterChainDefinitionMap.put("/v2/**", "anon"); //放开swagger
        filterChainDefinitionMap.put("/webjars/**", "anon"); //放开swagger

        filterChainDefinitionMap.put("/**", "authc");//其余接口一律拦截,这行代码必须放在所有权限设置的最后，不然都被拦截

        //配置shiro默认登录界面地址，前后端分离中登录界面跳转应由前端路由控制，后台仅返回json数据
        //shiroFilterFactoryBean.setLoginUrl("/unauth");

        shiroFilterFactoryBean.setFilterChainDefinitionMap(filterChainDefinitionMap);
        System.out.println("Shiro拦截器工厂类注入成功");
        return shiroFilterFactoryBean;
    }

    /*
    自定义身份认证realm
    必须写上这个类，并加上@Bean注解，目的是注入CustomRealm
    否则会影响CustomRealm类中其他类的依赖注入
     */
    @Bean
    public UserRealm customRealm(){
        UserRealm shiroRealm = new UserRealm();
        //shiroRealm.setCredentialsMatcher(hashedCredentialsMatcher());// 将md5密码比对器传给realm
        return  shiroRealm;
    }

    /*
    注入securityManager
     */
    @Bean
    public SecurityManager securityManager(){
        DefaultWebSecurityManager securityManager = new DefaultWebSecurityManager();
        //设置REALM
        securityManager.setRealm(customRealm());
        return securityManager;
    }

    /*
   开启注解支持
    */
    @Bean
    //@DependsOn({"lifecycleBeanPostProcessor"})
    public DefaultAdvisorAutoProxyCreator advisorAutoProxyCreator(){
        DefaultAdvisorAutoProxyCreator advisorAutoProxyCreator = new DefaultAdvisorAutoProxyCreator();
        advisorAutoProxyCreator.setProxyTargetClass(true);
        return advisorAutoProxyCreator;
    }
    @Bean
    public AuthorizationAttributeSourceAdvisor authorizationAttributeSourceAdvisor(){
        AuthorizationAttributeSourceAdvisor authorizationAttributeSourceAdvisor = new AuthorizationAttributeSourceAdvisor();
        authorizationAttributeSourceAdvisor.setSecurityManager(securityManager());
        return authorizationAttributeSourceAdvisor;
    }


}
