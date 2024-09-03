package com.android.china.utils;

import java.io.IOException;

import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

/**
 * @Author Crwei
 * date 2023/8/27 21:24
 */

public class PostToken {
    static  final OkHttpClient HTTP_CLIENT = new OkHttpClient().newBuilder().build();
    public static Response getToken() throws IOException {
//        Token值的获取
        MediaType mediaType = MediaType.parse("application/json");
        RequestBody body = RequestBody.create(mediaType, "");
        Request request = new Request.Builder()
                .url("https://aip.baidubce.com/oauth/2.0/token?client_id=UyGwSQub0RTgLaghq8Yv4c0o&client_secret=1TE1YnRqOxMkriueCPqYEk4Iql7mGhYm&grant_type=client_credentials")
                .method("POST", body)
                .addHeader("Content-Type", "application/json")
                .addHeader("Accept", "application/json")
                .build();
        Response response = HTTP_CLIENT.newCall(request).execute();
        return response;
    }
}
