package com.stalary.usercenter.data.dto;

import com.stalary.usercenter.data.ResultEnum;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * ResponseMessage
 *
 * @author lirongqian
 * @since 2018/03/24
 */
@Data
@AllArgsConstructor
@ApiModel("返回对象")
public class ResponseMessage {

    /**
     * 状态码
     */
    @ApiModelProperty("状态码")
    private int code;

    /**
     * 信息
     */
    @ApiModelProperty("信息")
    private String msg;

    /**
     * 是否成功
     */
    @ApiModelProperty("是否成功")
    private boolean success;

    /**
     * 数据
     */
    @ApiModelProperty("数据")
    private Object data;

    public ResponseMessage(int code, String msg, boolean success) {
        this.code = code;
        this.msg = msg;
        this.success = success;
    }

    public static ResponseMessage enumError(ResultEnum resultEnum) {
        return new ResponseMessage(resultEnum.getCode(), resultEnum.getMsg(), false);
    }

    public static ResponseMessage error(int code, String msg) {
        return new ResponseMessage(code, msg, false);
    }

    public static ResponseMessage successMessage(Object data) {
        return new ResponseMessage(0, "success", true, data);
    }

    public static ResponseMessage successMessage() {
        return new ResponseMessage(0, "success", true, null);
    }

    public static ResponseMessage failedMessage(String message) {
        return new ResponseMessage(1, message, false, null);
    }

    public static ResponseMessage failedMessage() {
        return new ResponseMessage(1, "failed", false, null);
    }

}