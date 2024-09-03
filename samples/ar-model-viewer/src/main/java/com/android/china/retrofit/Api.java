package com.android.china.retrofit;

import com.android.china.model.tokenBean;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.Headers;
import retrofit2.http.POST;

public interface Api {
    //post请求，如果有参数需要添加 @FormUrlEncoded注解，即和@Field配合使用
    @FormUrlEncoded
    @Headers({"Content-Type:application/json","Accept:application/json"})
    @POST("oauth/2.0/token?")
    Call<tokenBean> postDataCall(
            @Field("grant_type")String type,
            @Field("client_id") String id,
            @Field("client_secret") String cline_secret);
}