package com.android.china.retrofit;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * @Author Crwei
 * date 2023/9/11 19:19
 */

public class RetrofitClient {
    private static final String BASE_URL = "https://aip.baidubce.com/";
    private static Retrofit retrofit;
    public static Retrofit getClient() {
        if (retrofit == null) {
            // 创建 Retrofit 实例
            retrofit = new Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }
        return retrofit;
    }
    public static <T> T createService(Class<T> serviceClass) {
        // 创建指定接口的实例
        return getClient().create(serviceClass);
    }
}
