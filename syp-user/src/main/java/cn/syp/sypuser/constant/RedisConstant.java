package cn.syp.sypuser.constant;

/**
 * TODO redis相关的常量
 *
 * @Author syp
 * @Date 2020/3/26 1:34
 * @Description
 */
public class RedisConstant {
    /*redis缓存 键的加密组队参数*/
    public static final String REDIS_WHACCOUNT_USER_KEY_PREFIX = "SYP";
    /*设置最大存活时间*/
    public static final long REDIS_SESSION_MAX_ACTIVE = 20L;
    /*redis过期*/
    public static final String REDIS_SESSION_DIE_MSG = "session过期";
    /*状态码*/
    public static final int REDIS_SESSION_DIE_STATUS = 444;

}
