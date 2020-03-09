package com.example.template.mvp.http.gson;

import com.example.template.mvp.base.BaseException;
import com.google.gson.Gson;
import com.google.gson.TypeAdapter;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

import okhttp3.ResponseBody;
import retrofit2.Converter;

/**
 * 功能描述：
 * Created by gfq on 2020/3/9.
 */
public class BaseResponseBodyConverter<T> implements Converter<ResponseBody, T> {
    private final Gson gson;
    private final TypeAdapter<T> adapter;

    BaseResponseBodyConverter(Gson gson, TypeAdapter<T> adapter) {
        this.gson = gson;
        this.adapter = adapter;
    }

    @Override
    public T convert(ResponseBody value) throws IOException {
        String jsonString = value.string();
        try {
            JSONObject object = new JSONObject(jsonString);
            int code = object.getInt("errorCode");
            if (0 != code) {
                String data;
                //错误信息
                if (code == -1001) {//失效
                    data = "登录失效，请重新登录";
                } else {
                    data = object.getString("errorMsg");
                }
                //异常处理
                throw new BaseException(code, data);
            }
            //正确返回整个json
            return adapter.fromJson(jsonString);

        } catch (JSONException e) {
            e.printStackTrace();
            //数据解析异常即json格式有变动
            throw new BaseException(BaseException.PARSE_ERROR_MSG);
        } finally {
            value.close();
        }
    }
}
