package xiaochen.common;

import org.apache.commons.lang3.RandomStringUtils;

public class CommonResult {
    private int code;
    private String msg;
    private String data;

    public CommonResult(int code, String msg, String data) {
        this.code = code;
        this.msg = msg;
        this.data = data;
    }

    public CommonResult(String msg) {
        this.msg = msg;
        this.code = 200;
        this.data = RandomStringUtils.randomNumeric(6);
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }
}
