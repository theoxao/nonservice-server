package com.theoxao.common;

/**
 * @author theo
 * @date 2019/5/23
 */
public class CommonResult {

    private Integer code = 200;
    private String msg = null;
    private Object data;

    public CommonResult() {
    }

    public CommonResult(Object data) {
        this.data = data;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }
}
