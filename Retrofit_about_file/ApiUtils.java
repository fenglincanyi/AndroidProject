package com.chinahr.android.common.http;

import android.text.TextUtils;

import com.chinahr.android.common.instance.SelectClientInstance;
import com.chinahr.android.common.pushpoint.pbi.PBINetCollection;
import com.chinahr.android.common.sharedpref.ShareConstant;
import com.chinahr.android.common.sharedpref.ShareLite;
import com.chinahr.android.common.utils.SPUtil;
import com.chinahr.android.m.constant.HttpUrlConst;
import com.chinahr.android.m.constant.Switch;
import com.chinahr.android.m.main.MyApp;
import com.chinahr.android.m.util.Constants;
import com.chinahr.android.m.util.DeviceUtil;
import com.chinahr.android.m.util.LogUtil;

import java.io.IOException;
import java.net.URLEncoder;
import java.util.concurrent.TimeUnit;

import okhttp3.HttpUrl;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.fastjson.FastJsonConverterFactory;
import retrofit2.converter.scalars.ScalarsConverterFactory;

/**
 * 封装Retrofit的工具类
 * <p/>
 * Created by liuzhao on 16/2/24.
 */
public class ApiUtils {
    /**
     * 不同域名对应的BaseUrl；
     */
    public static final String APPCONFING_DOMAIN = "http://appconfig.chinahr.com/";
    public static final String QYAPI_DOMAIN = "http://qy.api.chinahr.com/";//求职者端的职位相关的接口
    public static final String MYAPI_DOMAIN = "http://my.api.chinahr.com/";//求职者端的简历相关的接口
    public static final String PASSPORTAPP_DOMAIN = "http://passport.app.chinahr.com/";//APP登录相关的接口
    public static final String QY_DOMAIN = "http://qy.app.chinahr.com/";//企业端职位和简历相关的接口
    public static final String GET_KEYWORD_DOMAIN = "http://suggest.chinahr.com/";//获取热刺联想相关的接口
    public static final String DOWNLOAD_APK = "http://download.chinahr.com/";//apk 下载（最原始链接）


    private static ApiService qyApiService;
    private static ApiService myApiService;
    private static ApiService appConfigService;
    private static ApiService passPortAppService;
    private static ApiService qyDomainService;
    private static ApiService getKeywordDomainService;
    private static ApiService getDownloadApkService;

    private static int TIME_OUT_CONNECT = 10;//连接超时时间
    private static int TIME_OUT_READ = 30;//读取超时时间
    private static int TIME_OUT_WRITE = 120;//写入超时时间

    private static int TIME_OUT_READ_FILE = 60;//文件读写超时
    private static int TIME_OUT_WRITE_FIlE = 60;

    /**
     * http://qy.api.chinahr.com/
     *
     * @return
     */
    public static ApiService    getQyApiService() {
        if (Switch.IFSINGLERETROFIT ) {
            if (qyApiService == null) {
                synchronized (ApiUtils.class) {
                    if (qyApiService == null) {
                        LogUtil.i("lz", "走了getQyApiService");
                        qyApiService = getRetrofitClient(QYAPI_DOMAIN, null).create(ApiService.class);
                    }
                }
            }
        } else {
            qyApiService = getRetrofitClient(QYAPI_DOMAIN, null).create(ApiService.class);
        }
        return qyApiService;
    }

    /**
     * http://my.api.chinahr.com
     *
     * @return
     */
    public static ApiService getMyApiService() {
        if (Switch.IFSINGLERETROFIT ) {
            if (myApiService == null) {
                synchronized (ApiUtils.class) {
                    if (myApiService == null) {
                        LogUtil.i("lz", "走了getMyApiService");
                        myApiService = getRetrofitClient(MYAPI_DOMAIN, null).create(ApiService.class);
                    }
                }
            }
        } else {
            myApiService = getRetrofitClient(MYAPI_DOMAIN, null).create(ApiService.class);
        }
        return myApiService;
    }

    public static ApiService getAppConfigService() {
        if (Switch.IFSINGLERETROFIT ) {
            if (appConfigService == null) {
                synchronized (ApiUtils.class) {
                    if (appConfigService == null) {
                        LogUtil.i("lz", "走了getAppConfigService");
                        appConfigService = getRetrofitClient(APPCONFING_DOMAIN, null).create(ApiService.class);
                    }
                }
            }
        } else {
            appConfigService = getRetrofitClient(APPCONFING_DOMAIN, null).create(ApiService.class);
        }
        return appConfigService;
    }

    public static ApiService getPassPortService() {
        if (Switch.IFSINGLERETROFIT ) {
            if (passPortAppService == null) {
                synchronized (ApiUtils.class) {
                    if (passPortAppService == null) {
                        LogUtil.i("lz", "走了getPassPortService");
                        passPortAppService = getRetrofitClient(PASSPORTAPP_DOMAIN, null).create(ApiService.class);
                    }
                }
            }
        }else{
            passPortAppService = getRetrofitClient(PASSPORTAPP_DOMAIN, null).create(ApiService.class);
        }
        return passPortAppService;
    }

    public static ApiService getQyDomainService() {
        if (Switch.IFSINGLERETROFIT ) {
            if (qyDomainService == null) {
                synchronized (ApiUtils.class) {
                    if (qyDomainService == null) {
                        LogUtil.i("lz", "走了getQyDomainService");
                        qyDomainService = getRetrofitClient(QY_DOMAIN, null).create(ApiService.class);
                    }
                }
            }
        }else{
            qyDomainService = getRetrofitClient(QY_DOMAIN, null).create(ApiService.class);
        }
        return qyDomainService;
    }

    public static ApiService getGetKeywordDomainService() {
        if (Switch.IFSINGLERETROFIT ) {
            if (getKeywordDomainService == null) {
                synchronized (ApiUtils.class) {
                    if (getKeywordDomainService == null) {
                        getKeywordDomainService = getRetrofitClient(GET_KEYWORD_DOMAIN, null).create(ApiService.class);
                    }
                }
            }
        }else{
            getKeywordDomainService = getRetrofitClient(GET_KEYWORD_DOMAIN, null).create(ApiService.class);
        }
        return getKeywordDomainService;
    }

    public static ApiService getDownloadApkService(FileCallBack callBack) {
        if (Switch.IFSINGLERETROFIT) {
            if (getDownloadApkService == null) {
                synchronized (ApiUtils.class) {
                    if (getDownloadApkService == null) {
                        getDownloadApkService = getRetrofitClient(DOWNLOAD_APK, callBack).create(ApiService.class);
                    }
                }
            }
        }else{
            getDownloadApkService = getRetrofitClient(DOWNLOAD_APK, callBack).create(ApiService.class);
        }
        return getDownloadApkService;
    }

    /**
     * 获取RetrofitClient
     *
     * @param baseUrl baseUrl
     * @param callBack callBack  null:普通请求, nonnull:文件相关
     * @return Retrofit
     */
    public static Retrofit getRetrofitClient(String baseUrl, final FileCallBack callBack) {
//        LogUtil.i("lz", "getRetrofitClient");

        //统一添加log的过滤器；
        HttpLoggingInterceptor logInterceptor = new HttpLoggingInterceptor();
        logInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);

        //统一添加Header信息的过滤器；
        Interceptor headerInterceptor = new Interceptor() {
            @Override
            public okhttp3.Response intercept(Chain chain) throws IOException {
                Request original = chain.request();

                //
                HttpUrl modifiedUrl = original.url().newBuilder().
                        addQueryParameter("name", "liuzhao").build();

                // 添加 统一的Header
                Request.Builder requestBuilder = original.newBuilder()
                        .header("versionCode", "Android_" + DeviceUtil.getVersionCode())
                        .header("versionName", "Android_" + DeviceUtil.getVersionName(MyApp.getContext()))
                        .header("UMengChannel", Constants.UMengChannel)
                        .header("uid", SPUtil.getUId())
                        .header("Cookie", SelectClientInstance.getSelectClientInstance().isSelectJobClient() ? "PPS=" + SPUtil.getPPS() : "bps=" + SPUtil.getBps())
                        .header("appSign", DeviceUtil.getAppSign(MyApp.getContext()))
                        .header("deviceID", DeviceUtil.getIMEI(MyApp.getContext()))
                        .header("deviceName", URLEncoder.encode(android.os.Build.MODEL.replaceAll(" ", ""), "UTF-8"))
                        .header("role", SPUtil.getRole())
                        .header("deviceModel", URLEncoder.encode(android.os.Build.MODEL.replaceAll(" ", ""), "UTF-8"))//备注okhttp不支持追传汉子，需要编码下。
                        .header("deviceVersion", android.os.Build.VERSION.RELEASE)
                        .header("pushVersion", "52")
                        .header("platform","Android-"+android.os.Build.VERSION.SDK_INT)
                        .header("User-Agent", "ChinaHrAndroid"+DeviceUtil.getVersionName(MyApp.getContext()))
                        .header("extion", TextUtils.isEmpty(Constants.API_EXTION) ? "" :Constants.API_EXTION)
                        .header("pbi", PBINetCollection.getCollection())
                        .header("Brand", URLEncoder.encode(android.os.Build.BRAND, "UTF-8"))//备注okhttp不支持追传汉子，需要编码下。
                        .method(original.method(), original.body());


                Request request = requestBuilder.build();

                if (callBack != null) {
                    okhttp3.Response originalResponse = chain.proceed(request);
                    // 文件下载时，传入自定义FiLeResponseBody
                    return originalResponse.newBuilder()
                            .body(new FileResponseBody(originalResponse.body(), callBack))
                            .build();
                }

                return chain.proceed(request);
            }
        };

        OkHttpClient httpClient;
        Retrofit retrofit;

        if (callBack != null) {// 文件相关
            httpClient = new OkHttpClient.Builder()
                    .connectTimeout(TIME_OUT_CONNECT, TimeUnit.SECONDS)//超时时间
                    .writeTimeout(TIME_OUT_WRITE_FIlE, TimeUnit.SECONDS)
                    .readTimeout(TIME_OUT_READ_FILE, TimeUnit.SECONDS)
                    .retryOnConnectionFailure(true)//重试机制
                    .addInterceptor(headerInterceptor)//请求拦截器
                    .build();
            retrofit = new Retrofit.Builder()
                    .baseUrl(baseUrl)
                    .addConverterFactory(FastJsonConverterFactory.create())//使用fastjson解析数据。
                    .addCallAdapterFactory(RxJavaCallAdapterFactory.create())//添加对RxJava的支持
                    .addConverterFactory(ScalarsConverterFactory.create())
                    .client(httpClient)//添加统一的Header和打印Logger的时候需要使用过滤器，需要使用到OKHttpClient。必须要注意添加的方式，不同版本（早期版本）不一样。
                    .build();
        } else {// 普通请求
            httpClient = new OkHttpClient.Builder()
                    .connectTimeout(TIME_OUT_CONNECT, TimeUnit.SECONDS)//超时时间
                    .writeTimeout(TIME_OUT_WRITE, TimeUnit.SECONDS)
                    .readTimeout(TIME_OUT_READ, TimeUnit.SECONDS)
                    .retryOnConnectionFailure(true)//重试机制
                    .addInterceptor(headerInterceptor)//请求拦截器
                    .addInterceptor(logInterceptor)//Log拦截器
                    .build();
            retrofit = new Retrofit.Builder()
                    .baseUrl(baseUrl)
                    .addConverterFactory(FastJsonConverterFactory.create())//使用fastjson解析数据。
                    .addCallAdapterFactory(RxJavaCallAdapterFactory.create())//添加对RxJava的支持
                    .addConverterFactory(ScalarsConverterFactory.create())
                    .client(httpClient)//添加统一的Header和打印Logger的时候需要使用过滤器，需要使用到OKHttpClient。必须要注意添加的方式，不同版本（早期版本）不一样。
                    .build();
        }

        return retrofit;
    }
}
