package com.yunlong.gitbook.api

/**
 * Created by shiyunlong on 2017/6/26.
 * API
 */
class ApiConstants {
    companion object {
        /**
         * BaseAPI
         */
        const val GIT_BOOK_BASE_API: String = "https://api.gitbook.com"
        /**
         * 客户端ID，在GitBook后台查看
         */
        const val GIT_BOOK_CLIENT_ID: String = "59477aeae9e0490011064ac9"
        /**
         * 客户端Secret，在GitBook后台查看
         */
        const val GIT_BOOK_CLIENT_SECRET: String = "262d437c-4978-4503-8cae-b6ee881743de";
        /**
         * 获取交换码的API
         * 此API的参数
         * client_id:必须，客户端ID，在GitBook的后台查看
         * redirect_uri：必须，回调URL，GET_GIT_BOOK_AUTH_AUTHORIZE_CALL_BACK
         * response_type：必须，返回类型：默认值：code
         */
        const val GET_GIT_BOOK_AUTH_AUTHORIZE: String = GIT_BOOK_BASE_API + "/oauth/authorize"
        /**
         * 回调URL
         */
        const val GIT_BOOK_AUTH_AUTHORIZE_REDIRECT_URI: String = "https://github.com/yunlonggmail"
        /**
         * 获取AccessToken的API
         * 此API的参数
         * client_id：必须，客户端ID，
         * client_secret：必须，客户端Secret
         * code：必须，交换码，通过GET_GIT_BOOK_AUTH_AUTHORIZE获得的
         * grant_type：必须，默认值authorization_code
         */
        const val POST_GIT_BOOK_AUTH_ACCESS_TOKEN = GIT_BOOK_BASE_API + "/oauth/access_token"
        /**
         * 账号信息
         * header中需要加入Token：
         * Authorization:Bearer ${AccessToken}
         */
        const val GET_GIT_BOOK_ACCOUNT_INFO = GIT_BOOK_BASE_API + "/account"
        /**
         * 作者信息
         */
        const val GET_GIT_BOOK_AUTHOR_INFO = GIT_BOOK_BASE_API + "/author/{username}"
    }

}