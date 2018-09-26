package com.hzit.bean;

import java.util.List;

/**
 * 为了 table封装数据
 */
public class ResponseData {

    private Integer code=0;
    private String msg;
    private Integer count;
    private List<Object> data;


    /**
     * 成功
     * @param count
     * @param data
     * @return
     */
    public static ResponseData success(Integer count,List<Object> data){

        ResponseData responseData = new ResponseData();
        responseData.setCode(0);
        responseData.setMsg("");
        responseData.setCount(count);
        responseData.setData(data);
        return  responseData;
    }

    /**
     * 失败返回的对象
     * @param msg
     * @return
     */
    public static ResponseData fail(String msg){
        ResponseData responseData = new ResponseData();
        responseData.setCode(500);
        responseData.setMsg(msg);
        responseData.setCount(0);
        responseData.setData(null);
        return  responseData;
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

    public Integer getCount() {
        return count;
    }

    public void setCount(Integer count) {
        this.count = count;
    }

    public List<Object> getData() {
        return data;
    }

    public void setData(List<Object> data) {
        this.data = data;
    }
}
