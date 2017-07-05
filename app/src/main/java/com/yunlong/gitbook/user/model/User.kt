package com.yunlong.gitbook.user.model

import com.google.gson.annotations.SerializedName
import java.lang.reflect.Field

/**
 * Created by shiyunlong on 2017/7/5.
 * 用户信息
 */
class User {
    /**
     * ID
     */
    var id: String = ""
    /**
     * 类型
     */
    var type: String = ""
    /**
     * 用户名称
     */
    var username: String = ""
    /**
     * 名称
     */
    var name: String = ""
    /**
     * 位置
     */
    var location: String = ""
    /**
     * 网址
     */
    var website: String = ""
    /**
     * 座右铭，小传
     */
    var bio: String = ""
    /**
     * 邮箱
     */
    var email: String = ""
    /**
     * 验证过
     */
    var verified: Boolean = false
    /**
     * 锁定
     */
    var locked: Boolean = false
    /**
     * 站点管理
     */
    @SerializedName("site_admin")
    var siteAdmin: Boolean = false
    /**
     * Url集合
     */
    var urls: UserUrls? = null
    /**
     * 权限
     */
    var permissions: UserPermissions? = null
    /**
     * 用户日期
     */
    var dates: UserDates? = null
    /**
     * 数量，未知具体表示
     */
    //    var counts: String
    /**
     * GitBub信息
     */
    var github: UserGithub? = null
    /**
     * 用户计划
     */
    var plan: UserPlan? = null
    /**
     * 用户验证信息
     */
    var auth: UserAuth? = null
    /**
     * Token
     */
    var token: String = ""

    override fun toString(): String {
        val sb: StringBuilder? = StringBuilder()
        val fields: Array<Field> = this.javaClass.declaredFields
        sb?.append("{\n")
        for (field in fields) {
            field.isAccessible = true
            sb?.append(field.name)
            sb?.append(":")
            sb?.append(field.get(this))
            sb?.append("\n")
        }
        sb?.append("}")
        return sb.toString()
    }
}