package com.itboy.dj.examtool.api;

import android.util.Log;

import java.io.Serializable;

/**
 * Created by Jam on 16-8-12
 * Description:
 */
public class BaseModel<T> implements Serializable {
    public String result;
    public T response;


    public boolean success(){
        Log.d("BaseModel", ".equals(result):" + "fail".equals(result));
        return "fail".equals(result);
    }

    @Override
    public String toString() {
        return "BaseModel{" +
                "result='" + result + '\'' +
                ", response=" + response +
                '}';
    }
}
