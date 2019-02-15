package meiyu.core.http;


import java.net.CookieHandler;
import java.net.CookieManager;
import java.security.cert.CertificateException;
import java.util.concurrent.TimeUnit;

import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSocketFactory;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;
import meiyu.core.Constant;

import meiyu.core.module.response.ArticleListModel;

import okhttp3.JavaNetCookieJar;
import okhttp3.OkHttpClient;
import retrofit2.Retrofit;

import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;

import retrofit2.converter.gson.GsonConverterFactory;




/**
 * Created by ${guodandan} on 2017/9/27.
 */

public class ApiClient {

    public static String TAG = "retrofit";
    private final int DEFAULT_TIMEOUT = 50;
    public Retrofit mRetrofit;
    public ApiService apiService;

    public ApiClient() {
        try {
            final TrustManager[] trustAllCerts = new TrustManager[]{new X509TrustManager() {
                @Override
                public void checkClientTrusted(
                        java.security.cert.X509Certificate[] chain,
                        String authType) throws CertificateException {
                }

                @Override
                public void checkServerTrusted(
                        java.security.cert.X509Certificate[] chain,
                        String authType) throws CertificateException {
                }

                @Override
                public java.security.cert.X509Certificate[] getAcceptedIssuers() {
                    return new java.security.cert.X509Certificate[0];
                }
            }};
            final SSLContext sslContext = SSLContext.getInstance("TLS");
            sslContext.init(null, trustAllCerts,
                    new java.security.SecureRandom());
            final SSLSocketFactory sslSocketFactory = sslContext
                    .getSocketFactory();


            CookieHandler cookieHandler = new CookieManager();
            OkHttpClient.Builder httpClientBuilder = new OkHttpClient.Builder()
                    .cookieJar(new JavaNetCookieJar(cookieHandler))
                    .sslSocketFactory(sslSocketFactory)
                    .hostnameVerifier(org.apache.http.conn.ssl.SSLSocketFactory.ALLOW_ALL_HOSTNAME_VERIFIER)
                    .connectTimeout(DEFAULT_TIMEOUT, TimeUnit.SECONDS)
                    .readTimeout(DEFAULT_TIMEOUT, TimeUnit.SECONDS)
                    .writeTimeout(DEFAULT_TIMEOUT, TimeUnit.SECONDS)

                    .addInterceptor(new SignInterceptor())
                    .addInterceptor(new LoggerInterceptor("YHJS_LOGGER", true));

            String url = Constant.BASE_URL;
            mRetrofit = new Retrofit.Builder()
                    .client(httpClientBuilder.build())
                    .addConverterFactory(GsonConverterFactory.create())
                    .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                    .baseUrl(url)
                    .build();

            apiService = mRetrofit.create(ApiService.class);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }


    private static class SingleHolder {
        private static final ApiClient INSTANCE = new ApiClient();
    }

    public static ApiClient getInstance() {
        return SingleHolder.INSTANCE;
    }


    /**
     * 获取版本信息
     *
     * @param subscriber
     */
    public void getArticleList(int page,ResponseSubscriberTwo<ArticleListModel> subscriber) {
//        apiService.getArticleList(page)
//                .subscribeOn(Schedulers.io())
//                .unsubscribeOn(Schedulers.io())
//                .observeOn(AndroidSchedulers.mainThread())
//                .subscribe(subscriber);
    }





}
