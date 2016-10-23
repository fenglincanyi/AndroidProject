package com.chinahr.android.common.http;

import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;
import retrofit2.http.Path;
import retrofit2.http.Query;
import retrofit2.http.Url;

/**
 * Created by liuzhao on 16/7/14.
 */
public interface ApiService {
	/**
     * 上传图片
     * @param cvId cvId
     */
    @Multipart
    @POST("cv/uploadPhoto")
    Call<PhotoBean> uploadCImage(@Part("cvId") RequestBody cvId, @Part MultipartBody.Part file);

    /**
     * 上传图片
     * @return
     */
    @Multipart
    @POST("buser/app/pic/upload")
    Call<PhotoBean> uploadBImage(@Part MultipartBody.Part file);

    /**
     * 下载apk (因为有两个路径，@url： 动态传入url解决)
     * @return
     */
    @GET
    Call<ResponseBody> downloadApk(@Url String url);
}