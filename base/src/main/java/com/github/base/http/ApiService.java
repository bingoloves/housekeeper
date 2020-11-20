package com.github.base.http;

import com.github.base.bean.UploadBean;

import java.util.List;
import io.reactivex.Observable;
import okhttp3.MultipartBody;
import okhttp3.ResponseBody;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;
import retrofit2.http.Query;
import retrofit2.http.Streaming;
import retrofit2.http.Url;

public interface ApiService {
     String HOST = "https://iam.myhuaweicloud.com";
     String BASE_URL = HOST + "/";
     /********************************* 示例代码 ****************************************/
    @GET("http://v.juhe.cn/toutiao/index?key=238ca532342eb15fb820f1f7fe08c2d6")
    Observable<BaseResponse> testApi(@Query("type") String type);

    /**
     * 上传多张图片
     * @param files
     * @return
     */
    @Multipart
    @POST(Api.UPLOAD_TRACE)
    Observable<BaseResponse<UploadBean>> uploadImages(@Part() List<MultipartBody.Part> files);

    /**
     * 文件下载
     * @param url
     * @return
     */
    @Streaming
    @GET
    Observable<ResponseBody> download(@Url String url);

    /********************************* 项目代码 ****************************************/
    /**
     * 授权接口
     * @return
     */
    @POST("v3/auth/tokens")
    Observable<BaseResponse> auth();
}