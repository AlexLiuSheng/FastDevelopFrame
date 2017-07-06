package com.allenliu.fastdevelopframe;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.GET;

/**
 * Created by allenliu on 2017/7/6.
 */

public interface ApiService {
    @GET("alexliusheng/fastdevelopframe")
    Call<ResponseBody>testHttp();
}
