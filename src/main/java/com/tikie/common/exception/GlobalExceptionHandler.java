package com.tikie.common.exception;

import com.tikie.common.JsonResult;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @author TiKie
 * @desc    @ControllerAdvice + @ExceptionHandler 进行全局的 Controller 层异常处理,
 *          对于 Interceptor（拦截器）层的异常，Spring 框架层的异常，就无能为力了;
 *          service
 *          <p>
 *              @Service
                public class DogService {
                    @Transactional
                    public Dog update(Dog dog){
                        // some database options

                        // 模拟狗狗新名字与其他狗狗的名字冲突
                        BSUtil.isTrue(false, "狗狗名字已经被使用了...");

                        // update database dog info

                        return dog;
                    }
                }
 *          </p>
 *          BSUtil
 *          <p>
 *              public static void isTrue(boolean expression, String error){
                    if(!expression) {
                        throw new BusinessException(error);
                    }
                }
 *          </p>
 *          或者
 *          <p>
 *              if(true){
                    throw new BusinessException(SysErrorEnums.EMPTY_PARAME);
                }
 *          </p>
 * @date 2018/11/3
 */
@ControllerAdvice
public class GlobalExceptionHandler {
    private static final Logger log = LoggerFactory.getLogger(GlobalExceptionHandler.class);

    /**
     * 处理所有不可知的异常
     * @param e
     * @return
     */
    @ExceptionHandler(value = Exception.class)
    @ResponseBody
    public JsonResult handleException(Exception e){
        log.info("操作失败！");
        return new JsonResult(0, e.getMessage());
    }

    /**
     * 处理所有业务异常
     * @param e
     * @return
     */
    @ExceptionHandler(value = BusinessException.class)
    @ResponseBody
    public JsonResult handleBusinessException(BusinessException e){
        log.info(e.getMessage(), e);
        return new JsonResult(e.getIErrorCode().getErrorCode(), e.getMessage());
    }
}
