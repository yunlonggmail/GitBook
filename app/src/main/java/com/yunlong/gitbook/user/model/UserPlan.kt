package com.yunlong.gitbook.user.model

import java.lang.reflect.Field

/**
 * Created by shiyunlong on 2017/7/5.
 * 用户的计划，应该是用户的套餐之类
 */
class UserPlan {
    /**
     * ID
     */
    var id: String = ""
    /**
     * 私有书籍
     */
    var privateBooks: UserPlanBooks? = null
    /**
     * 合作中
     */
    var collaborators: UserPlanBooks? = null

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

    /**
     * 用户私有书籍
     */
    class UserPlanBooks {
        /**
         * 最大值
         */
        var max: Int = 0
        /**
         * 数量
         */
        var count: Int = 0
        /**
         * 使用
         */
        var usage: Int = 0
        /**
         * 剩下的
         */
        var remaining: Int = 0

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

}