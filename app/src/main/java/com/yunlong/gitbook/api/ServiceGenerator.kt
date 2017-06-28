package com.yunlong.gitbook.api

import android.content.Context
import com.facebook.stetho.okhttp3.StethoInterceptor
import com.google.gson.GsonBuilder
import com.yunlong.gitbook.BuildConfig
import com.yunlong.gitbook.auth.model.AccessToken
import okhttp3.*
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

/**
 * Created by shiyunlong on 2017/6/27.
 * 服务生产者
 */
class ServiceGenerator {
    companion object {
        /**
         * 最新的Token
         */
        var mLastToken: String? = null
        /**
         * 缓存
         */
        var mCache: Cache? = null
        /**
         * 请求
         */
        var mRetrofit: Retrofit? = null
        /**
         * 超时
         */
        val TIME_OUT: Long = 20

        /**
         * 初始化
         */
        fun init(context: Context) {
            if (mCache != null)
                throw IllegalStateException("Retrofit cache already initialized.")
            mCache = Cache(context.cacheDir, 20 * 1024 * 1024)
        }

        /**
         * 创建Service
         */
        fun createService(serviceClass: Class<*>, token: AccessToken?): Any? {
            val currentToken = token?.access_token ?: ""
            if (mRetrofit == null || currentToken != mLastToken) {

                mLastToken = currentToken

                val httpClientBuilder: OkHttpClient.Builder = OkHttpClient.Builder()
                        .connectTimeout(TIME_OUT, TimeUnit.SECONDS)
                        .writeTimeout(TIME_OUT, TimeUnit.SECONDS)
                        .readTimeout(TIME_OUT, TimeUnit.SECONDS)
                        .retryOnConnectionFailure(true)

                httpClientBuilder.addInterceptor(Interceptor { chain: Interceptor.Chain? ->
                    val original: Request? = chain?.request()
                    val requestBuilder: Request.Builder? = original?.newBuilder()
                            ?.addHeader("Accept", "application/json")
                            ?.addHeader("Authorization", token?.token_type + " " + mLastToken)
                            ?.method(original.method(), original.body())

                    val request: Request? = requestBuilder?.build()
                    chain?.proceed(request)
                }).cache(mCache)

                if (BuildConfig.DEBUG)
                    httpClientBuilder.addInterceptor(StethoInterceptor())

                val retrofitBuilder: Retrofit.Builder = Retrofit.Builder()
                        .baseUrl(ApiConstants.GIT_BOOK_BASE_API)
                        .addConverterFactory(GsonConverterFactory.create())
                        .addCallAdapterFactory(RxJava2CallAdapterFactory.create())

                val httpClient: OkHttpClient = httpClientBuilder.build()
                mRetrofit = retrofitBuilder.client(httpClient).build()
            }
            return mRetrofit?.create(serviceClass);
        }


    }
}