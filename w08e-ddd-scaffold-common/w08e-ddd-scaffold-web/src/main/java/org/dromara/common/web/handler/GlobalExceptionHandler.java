package org.dromara.common.web.handler;

import cn.hutool.core.util.ObjectUtil;
import cn.hutool.http.HttpStatus;
import com.w08e.common.core.domain.result.Result;
import com.w08e.common.core.enums.ResultCodeEnum;
import com.w08e.common.core.exception.ServiceException;
import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * 全局异常处理器
 *
 * @author Lion Li
 */
@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler {

    /**
     * 请求方式不支持
     */
    @ExceptionHandler(HttpRequestMethodNotSupportedException.class)
    public Result<Void> handleHttpRequestMethodNotSupported(HttpRequestMethodNotSupportedException e,
                                                            HttpServletRequest request) {
        String requestURI = request.getRequestURI();
        log.error("请求地址'{}',不支持'{}'请求", requestURI, e.getMethod());
        return Result.fail(HttpStatus.HTTP_BAD_METHOD, e.getMessage());
    }

    /**
     * 业务异常
     */
    @ExceptionHandler(ServiceException.class)
    public Result<Void> handleServiceException(ServiceException e, HttpServletRequest request) {
        log.error(e.getMessage());
        Integer code = e.getCode();
        return ObjectUtil.isNotNull(code) ? Result.fail(code, e.getMessage()) : Result.fail(ResultCodeEnum.ILLEGAL_TOKEN);
    }

//    /**
//     * 业务异常
//     */
//    @ExceptionHandler(BaseException.class)
//    public R<Void> handleBaseException(BaseException e, HttpServletRequest request) {
//        log.error(e.getMessage());
//        return R.fail(e.getMessage());
//    }
//
//    /**
//     * 请求路径中缺少必需的路径变量
//     */
//    @ExceptionHandler(MissingPathVariableException.class)
//    public R<Void> handleMissingPathVariableException(MissingPathVariableException e, HttpServletRequest request) {
//        String requestURI = request.getRequestURI();
//        log.error("请求路径中缺少必需的路径变量'{}',发生系统异常.", requestURI);
//        return R.fail(String.format("请求路径中缺少必需的路径变量[%s]", e.getVariableName()));
//    }
//
//    /**
//     * 请求参数类型不匹配
//     */
//    @ExceptionHandler(MethodArgumentTypeMismatchException.class)
//    public R<Void> handleMethodArgumentTypeMismatchException(MethodArgumentTypeMismatchException e, HttpServletRequest request) {
//        String requestURI = request.getRequestURI();
//        log.error("请求参数类型不匹配'{}',发生系统异常.", requestURI);
//        return R.fail(String.format("请求参数类型不匹配，参数[%s]要求类型为：'%s'，但输入值为：'%s'", e.getName(), e.getRequiredType().getName(), e.getValue()));
//    }
//
//    /**
//     * 找不到路由
//     */
//    @ExceptionHandler(NoHandlerFoundException.class)
//    public R<Void> handleNoHandlerFoundException(NoHandlerFoundException e, HttpServletRequest request) {
//        String requestURI = request.getRequestURI();
//        log.error("请求地址'{}'不存在.", requestURI);
//        return R.fail(HttpStatus.HTTP_NOT_FOUND, e.getMessage());
//    }
//
//    /**
//     * 拦截未知的运行时异常
//     */
//    @ExceptionHandler(RuntimeException.class)
//    public R<Void> handleRuntimeException(RuntimeException e, HttpServletRequest request) {
//        String requestURI = request.getRequestURI();
//        log.error("请求地址'{}',发生未知异常.", requestURI, e);
//        return R.fail(e.getMessage());
//    }
//
//    /**
//     * 系统异常
//     */
//    @ExceptionHandler(Exception.class)
//    public R<Void> handleException(Exception e, HttpServletRequest request) {
//        String requestURI = request.getRequestURI();
//        log.error("请求地址'{}',发生系统异常.", requestURI, e);
//        return R.fail(e.getMessage());
//    }
//
//    /**
//     * 自定义验证异常
//     */
//    @ExceptionHandler(BindException.class)
//    public R<Void> handleBindException(BindException e) {
//        log.error(e.getMessage());
//        String message = StreamUtils.join(e.getAllErrors(), DefaultMessageSourceResolvable::getDefaultMessage, ", ");
//        return R.fail(message);
//    }
//
//    /**
//     * 自定义验证异常
//     */
//    @ExceptionHandler(ConstraintViolationException.class)
//    public R<Void> constraintViolationException(ConstraintViolationException e) {
//        log.error(e.getMessage());
//        String message = StreamUtils.join(e.getConstraintViolations(), ConstraintViolation::getMessage, ", ");
//        return R.fail(message);
//    }
//
//    /**
//     * 自定义验证异常
//     */
//    @ExceptionHandler(MethodArgumentNotValidException.class)
//    public R<Void> handleMethodArgumentNotValidException(MethodArgumentNotValidException e) {
//        log.error(e.getMessage());
//        String message = e.getBindingResult().getFieldError().getDefaultMessage();
//        return R.fail(message);
//    }

}
