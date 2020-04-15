package cn.syp.sypcommons.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * TODO
 *
 * @Author syp
 * @Date 2020/3/22 23:07
 * @Description
 */
@ResponseStatus(value = HttpStatus.FORBIDDEN,reason = "非法请求！")
public class ResponseException extends RuntimeException {
    public ResponseException(String msg){
        super(msg);
    }

}
