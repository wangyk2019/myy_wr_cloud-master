package com.moyuan.cloud.exception;


import com.moyuan.cloud.pojo.JsonResult;

/**
 * 结果异常，会被 ExceptionHandler 捕捉并返回给前端
 *
 * @author Chery
 * @date 2017/3/28
 */
public class ResultException extends RuntimeException {

    private JsonResult resultCode;

    public ResultException(JsonResult resultCode) {
        super(resultCode.getMsg());
        this.resultCode = resultCode;
    }

    public JsonResult getResultCode() {
        return resultCode;
    }
}
