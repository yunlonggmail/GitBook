package com.yunlong.gitbook.user.model

import java.lang.reflect.Field
import java.util.*

/**
 * Created by shiyunlong on 2017/7/5.
 * 日期
 */
class UserDates {
    /**
     * 创建日期
     */
    var created: Date? = null

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