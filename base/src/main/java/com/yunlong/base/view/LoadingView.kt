package com.yunlong.base.view

import android.animation.ValueAnimator
import android.annotation.SuppressLint
import android.content.Context
import android.content.res.TypedArray
import android.graphics.*
import android.util.AttributeSet
import android.view.View
import com.yunlong.base.R
import com.yunlong.base.util.DisplayUtils
import android.view.animation.LinearInterpolator
import com.yunlong.base.util.StringUtils
import android.graphics.Shader
import android.graphics.BitmapShader
import android.graphics.RectF


/**
 * Created by shiyunlong on 2017/8/15.
 * 加载界面
 */
class LoadingView(context: Context?, var attrs: AttributeSet?, defStyleAttr: Int) : View(context, attrs, defStyleAttr) {

    constructor(context: Context?, attrs: AttributeSet?) : this(context, attrs, 0)

    constructor(context: Context?) : this(context, null)

    /**
     * 截取方式
     */
    val mPorterDuffXfermode: PorterDuffXfermode? = PorterDuffXfermode(PorterDuff.Mode.SRC_ATOP)
    /**
     * 文字
     */
    var mText: CharSequence? = "GitBook"
    /**
     * 文字字号
     */
    var mTextSize: Float = DisplayUtils.dp2px(1.0f);
    /**
     * 文字边框颜色
     */
    var mTextStrokeWidth: Float = DisplayUtils.dp2px(1.0f)
    /**
     * 顶部文字边框颜色
     */
    var mTopTextStrokeColor: Int = Color.BLACK
    /**
     * 顶部文字填充颜色
     */
    var mTopTextFillColor: Int = Color.BLACK
    /**
     * 底部文字边框颜色
     */
    var mBottomTextStrokeColor: Int = Color.BLACK
    /**
     * 底部文字填充颜色
     */
    var mBottomTextFillColor: Int = Color.BLACK
    /**
     * 文字边线画笔
     */
    var mTextStrokePaint: Paint? = null
    /**
     * 文字画笔
     */
    var mTextPaint: Paint? = null
    /**
     * 路径
     */
    var mPath: Path? = null
    /**
     * 区域宽度
     */
    var mWidth: Int = DisplayUtils.dp2px(1)
    /**
     * 区域高度
     */
    var mHeight: Int = DisplayUtils.dp2px(1)
    /**
     * 填充画笔
     */
    var mMiddlePaint: Paint? = null
    /**
     * 边线颜色
     */
    var mStrokeColor: Int = Color.TRANSPARENT
    /**
     * 边线宽度
     */
    var mStrokeWidth: Float = DisplayUtils.dp2px(1.0f)
    /**
     * 边线弧度
     */
    var mStrokeRadius: Float = 0.0f
    /**
     * 填充颜色
     */
    var mFillColor: Int = Color.BLACK
    /**
     * 图片
     */
    var mBitMap: Bitmap? = null
    /**
     * 画布，自定义画布
     */
    var mCanvas: Canvas? = null
    /**
     * 当前进度
     */
    var mCurrentPercent: Float = 0.0f
    /**
     * 动画时长
     */
    var mDuration: Long = 1000

    /**
     * 属性动画
     */
    var mValueAnimator: ValueAnimator? = null

    init {
        if (attrs != null) {
            val array: TypedArray? = context?.obtainStyledAttributes(attrs, R.styleable.LoadingView)

            mTopTextStrokeColor = array?.getColor(R.styleable.LoadingView_lv_top_text_stroke_color, Color.BLACK) as Int
            mTopTextFillColor = array.getColor(R.styleable.LoadingView_lv_top_text_fill_color, Color.BLACK)
            mBottomTextStrokeColor = array.getColor(R.styleable.LoadingView_lv_bottom_text_stroke_color, Color.BLACK)
            mBottomTextFillColor = array.getColor(R.styleable.LoadingView_lv_bottom_text_fill_color, Color.BLACK)

            mText = array.getText(R.styleable.LoadingView_lv_text)
            mTextSize = array.getDimension(R.styleable.LoadingView_lv_text_size, mTextSize)
            mTextStrokeWidth = array.getDimension(R.styleable.LoadingView_lv_text_stroke_width, mTextStrokeWidth)

            mStrokeWidth = array.getDimension(R.styleable.LoadingView_lv_stroke_width, mStrokeWidth)
            mStrokeRadius = array.getDimension(R.styleable.LoadingView_lv_stroke_radius, mStrokeRadius)
            mStrokeColor = array.getColor(R.styleable.LoadingView_lv_stroke_color, Color.TRANSPARENT)

            mFillColor = array.getColor(R.styleable.LoadingView_lv_fill_color, Color.BLACK)

            array.recycle()
        }

        mTextStrokePaint = Paint(Paint.ANTI_ALIAS_FLAG)
        mTextStrokePaint?.color = mBottomTextStrokeColor
        mTextStrokePaint?.style = Paint.Style.STROKE
        mTextStrokePaint?.isDither = true
        mTextStrokePaint?.xfermode = mPorterDuffXfermode

        mTextPaint = Paint(Paint.ANTI_ALIAS_FLAG)
        mTextPaint?.color = mBottomTextFillColor
        mTextPaint?.isDither = true
        mTextPaint?.xfermode = mPorterDuffXfermode

        mPath = Path()

        //中部画笔
        mMiddlePaint = Paint(Paint.ANTI_ALIAS_FLAG)
        mMiddlePaint?.style = Paint.Style.FILL
        mMiddlePaint?.color = mFillColor
        mMiddlePaint?.isDither = true

        mValueAnimator = ValueAnimator.ofFloat(0.0f, 1.0f)
        mValueAnimator?.duration = mDuration
        mValueAnimator?.interpolator = LinearInterpolator()
        mValueAnimator?.repeatCount = ValueAnimator.INFINITE
        mValueAnimator?.repeatMode = ValueAnimator.RESTART
        mValueAnimator?.addUpdateListener({ animation ->
            mCurrentPercent = animation.animatedFraction
            invalidate()
        })
        mValueAnimator?.start()
    }


    @SuppressLint("DrawAllocation")
    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        val width: Int = MeasureSpec.getSize(widthMeasureSpec)
        val height: Int = MeasureSpec.getSize(heightMeasureSpec)
        mWidth = width
        mHeight = height
        setMeasuredDimension(mWidth, mHeight)

        mBitMap = Bitmap.createBitmap(mWidth, mHeight, Bitmap.Config.ARGB_8888)
        mCanvas = Canvas(mBitMap)
    }

    override fun onDraw(canvas: Canvas?) {
        if (StringUtils.isEmpty(mText))
            return

        drawTopText(canvas)
        drawMiddleGraphics(canvas)
    }

    /**
     * 画上部文字
     */
    private fun drawTopText(canvas: Canvas?) {
        if (mTextStrokeWidth > 0 && mTopTextStrokeColor != Color.TRANSPARENT) {
            mTextStrokePaint?.textSize = mTextSize
            mTextStrokePaint?.color = mTopTextStrokeColor
            mTextStrokePaint?.strokeWidth = mTextStrokeWidth
            drawTextAtCenter(canvas, mTextStrokePaint, mText)
        }

        mTextPaint?.textSize = mTextSize
        mTextPaint?.color = mTopTextFillColor
        drawTextAtCenter(canvas, mTextPaint, mText)
    }


    /**
     * 画下部文字
     */
    private fun drawBottomText(canvas: Canvas?) {
        if (mTextStrokeWidth > 0 && mBottomTextStrokeColor != Color.TRANSPARENT) {
            mTextStrokePaint?.textSize = mTextSize
            mTextStrokePaint?.color = mBottomTextStrokeColor
            mTextStrokePaint?.strokeWidth = mTextStrokeWidth
            drawTextAtCenter(canvas, mTextStrokePaint, mText)
        }
        mTextPaint?.textSize = mTextSize
        mTextPaint?.color = mBottomTextFillColor
        drawTextAtCenter(canvas, mTextPaint, mText)
    }

    /**
     * 画中间步骤的图形，也就是下半部

     * @param canvas
     */
    private fun drawMiddleGraphics(canvas: Canvas?) {
        drawMiddleStroke(canvas)
        drawMiddleFill(canvas)
    }

    /**
     * 画边框
     */
    private fun drawMiddleStroke(canvas: Canvas?) {
        if (mStrokeColor == Color.TRANSPARENT || mStrokeWidth <= 0)
            return
        val r = Math.min(mWidth, mHeight) / 2
        mMiddlePaint?.color = mStrokeColor
        mMiddlePaint?.style = Paint.Style.STROKE
        mMiddlePaint?.strokeWidth = mStrokeWidth
        mMiddlePaint?.xfermode = mPorterDuffXfermode
        if (mWidth == mHeight) {
            canvas?.drawCircle(mWidth.toFloat() / 2, mHeight.toFloat() / 2, r - mStrokeWidth, mMiddlePaint)
        } else {
            canvas?.drawRoundRect(RectF(mStrokeWidth / 2, mStrokeWidth / 2, width - mStrokeWidth / 2, height - mStrokeWidth / 2), mStrokeRadius, mStrokeRadius, mMiddlePaint)
        }
        mMiddlePaint?.xfermode = null
    }

    /**
     * 画中间填充

     * @param canvas：画布
     */
    private fun drawMiddleFill(canvas: Canvas?) {
        val r = Math.min(mWidth, mHeight) / 2
        mMiddlePaint?.color = mFillColor
        mMiddlePaint?.style = Paint.Style.FILL
        mMiddlePaint?.shader = getBitmapShader()
        //圆向内缩的距离
        val dx = mStrokeWidth * 3 / 2

        if (mWidth == mHeight) {
            canvas?.drawCircle(mWidth.toFloat() / 2, mHeight.toFloat() / 2, r - dx, mMiddlePaint)
        } else {
            canvas?.drawRoundRect(RectF(mStrokeWidth / 2, mStrokeWidth / 2, width - mStrokeWidth / 2, height - mStrokeWidth / 2), mStrokeRadius, mStrokeRadius, mMiddlePaint)
        }

        mMiddlePaint?.shader = null
    }

    /**
     * 获取Bitmap的图形
     */
    private fun getBitmapShader(): BitmapShader {
        mBitMap?.eraseColor(Color.TRANSPARENT)//将bitMap填充成透明色
        mPath = getActionPath(mCurrentPercent)
        mCanvas?.drawPath(mPath, mMiddlePaint)
        drawBottomText(mCanvas)
        return BitmapShader(mBitMap, Shader.TileMode.CLAMP, Shader.TileMode.CLAMP)
    }

    /**
     * 获取ActionPath
     */
    private fun getActionPath(percent: Float): Path? {
        val path: Path? = Path()
        var x: Float = -(mWidth.toFloat())
        //当前x点坐标（根据动画进度水平推移，一个动画周期推移的距离为一个mWidth）
        x += percent * mWidth
        //波形的起点
        path?.moveTo(x, mHeight.toFloat() / 2)
        //控制点的相对宽度
        val quadWidth: Float = mWidth.toFloat() / 4
        //控制点的相对高度
        val quadHeight: Float = mHeight.toFloat() / 20 * 3
        //第一个周期
        path?.rQuadTo(quadWidth, quadHeight, quadWidth * 2, 0f)
        path?.rQuadTo(quadWidth, -quadHeight, quadWidth * 2, 0f)
        //第二个周期
        path?.rQuadTo(quadWidth, quadHeight, quadWidth * 2, 0f)
        path?.rQuadTo(quadWidth, -quadHeight, quadWidth * 2, 0f)
        //右侧的直线
        path?.lineTo(x + mWidth * 2, mHeight.toFloat())
        //下边的直线
        path?.lineTo(x, mHeight.toFloat())
        //自动闭合补出左边的直线
        path?.close()
        return path
    }

    /**
     * 在画板中间画APP

     * @param canvas：画布
     * *
     * @param textPaint：画笔
     * *
     * @param text：文字
     */
    private fun drawTextAtCenter(canvas: Canvas?, textPaint: Paint?, text: CharSequence?) {
        if (StringUtils.isEmpty(text)) {
            return
        }
        val rect = Rect(0, 0, mWidth, mHeight)
        textPaint?.textAlign = Paint.Align.CENTER
        val fontMetrics: Paint.FontMetrics? = textPaint?.fontMetrics

        val top: Float = fontMetrics?.top as Float
        val bottom: Float = fontMetrics.bottom

        val centerY = rect.centerY() - (top / 2 + bottom / 2).toInt()

        canvas?.drawText(text.toString(), rect.centerX().toFloat(), centerY.toFloat(), textPaint)
    }

    /**
     * 设置底部文字边框颜色

     * @param bottomTextStrokeColor：底部边框颜色
     */
    fun setBottomTextStrokeColor(bottomTextStrokeColor: Int) {
        this.mBottomTextStrokeColor = bottomTextStrokeColor
    }

    /**
     * 设置底部文字填充颜色

     * @param bottomTextFillColor：底部文字填充颜色
     */
    fun setBottomTextFillColor(bottomTextFillColor: Int) {
        this.mBottomTextFillColor = bottomTextFillColor
    }

    /**
     * 设置顶部文字颜色

     * @param topTextFillColor：顶部文字填充颜色
     */
    fun setTopTextFillColor(topTextFillColor: Int) {
        this.mTopTextFillColor = topTextFillColor
    }

    /**
     * 设置顶部文字边框颜色

     * @param topTextStrokeColor：顶部边框颜色
     */
    fun setTopTextStrokeColor(topTextStrokeColor: Int) {
        this.mTopTextStrokeColor = topTextStrokeColor
    }

    /**
     * 设置文字

     * @param text：文字
     */
    fun setText(text: CharSequence) {
        if (StringUtils.isEmpty(text))
            return
        this.mText = text
    }

    /**
     * 设置文字大小

     * @param mTextSize：文字大小
     */
    fun setTextSize(mTextSize: Float) {
        this.mTextSize = mTextSize
    }

    /**
     * 设置文字边框宽度

     * @param mTextStrokeWidth：边框宽度
     */
    fun setTextStrokeWidth(mTextStrokeWidth: Float) {
        this.mTextStrokeWidth = mTextStrokeWidth
    }

    /**
     * 设置边框颜色

     * @param mStrokeColor：圆边框颜色
     */
    fun setStrokeColor(mStrokeColor: Int) {
        this.mStrokeColor = mStrokeColor
    }

    /**
     * 设置填充颜色

     * @param mFillColor：填充颜色
     */
    fun setFillColor(mFillColor: Int) {
        this.mFillColor = mFillColor
    }

    /**
     * 设置时长

     * @param mDuration:时长
     */
    fun setDuration(mDuration: Long) {
        this.mDuration = mDuration
        mValueAnimator?.duration = mDuration
        mValueAnimator?.cancel()
        mValueAnimator?.start()
    }

    /**
     * 设置边框宽度

     * @param mStrokeWidth：边框宽度
     */
    fun setStrokeWidth(mStrokeWidth: Float) {
        this.mStrokeWidth = mStrokeWidth
    }

    /**
     * 设置边框宽度

     * @param radius：边框弧度
     */
    fun setStrokeRadius(radius: Float) {
        this.mStrokeRadius = radius
    }
}