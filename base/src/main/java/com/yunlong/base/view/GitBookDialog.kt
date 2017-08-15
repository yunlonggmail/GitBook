package com.yunlong.base.view

import android.content.Context
import android.view.Gravity
import android.app.Dialog
import android.view.View
import android.widget.TextView
import com.yunlong.base.R


/**
 * Created by shiyunlong on 2017/8/15.
 * GitBookDialog
 */
class GitBookDialog : Dialog {

    constructor(context: Context?) : super(context)

    constructor(context: Context?, themeResId: Int) : super(context, themeResId)

    /**
     * 展示
     * @param message:消息
     * @param cancelable：是否可以取消
     */
    fun show(message: CharSequence?, cancelable: Boolean): GitBookDialog {
        val dialog: GitBookDialog = GitBookDialog(context, R.style.GitBook_Progress)
        dialog.setTitle("")
        dialog.setContentView(R.layout.d_loading)
        val tvMessage: TextView = dialog.findViewById(R.id.tv_message) as TextView
        if (message?.isEmpty() as Boolean) {
            tvMessage.visibility = View.GONE
        } else {
            tvMessage.text = message
        }
        // 按返回键是否取消
        dialog.setCancelable(cancelable)
        dialog.window.attributes.gravity = Gravity.CENTER
        val lp = dialog.window.attributes
        // 设置背景层透明度
        lp.dimAmount = 0.2f
        dialog.window.attributes = lp
        dialog.show()
        return dialog
    }

    /**
     * 给Dialog设置提示信息

     * @param message
     */
    fun setMessage(message: CharSequence?) {
        val tvMessage: TextView = findViewById(R.id.tv_message) as TextView
        if (message != null && message.isNotEmpty()) {
            tvMessage.visibility = View.VISIBLE
            tvMessage.text = message
            tvMessage.invalidate()
        }
    }
}
