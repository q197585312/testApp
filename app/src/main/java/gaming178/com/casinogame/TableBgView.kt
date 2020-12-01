package gaming178.com.casinogame

import android.content.Context
import android.graphics.*
import android.graphics.Typeface.BOLD
import android.util.AttributeSet
import android.view.MotionEvent
import android.view.View
import androidx.core.content.ContextCompat
import androidx.core.graphics.drawable.toBitmap
import gaming178.com.baccaratgame.R


@Suppress("DEPRECATED_IDENTITY_EQUALS")
class TableBgView(context: Context, attrs: AttributeSet) : View(context, attrs) {
    companion object {
        const val bigSize = 80f
        const val middleSize = 40f
        const val smallSize = 25f
        const val chipWidth = 90
        const val chipHeight = 90
        const val chipSpace = 15
        const val rotateXDeg = 11.9f

    }

    val playPath = Path()
    val playLiePath: Path = Path()
    val playPairPath = Path()
    val anyPairPath = Path()
    val tiePath = Path()
    val perfectPairPath = Path()
    val lucky6Path = Path()

    val bankerLiePath = Path()
    val bankerPairPath = Path()
    val bankerPath = Path()

    val mCamera = Camera()
    val mMatrix = Matrix()


    private val topHorizontal = 742 / 915f
    private val bottomHorizontal = 742 / 915f
    private val top_bottom_ratio = topHorizontal / bottomHorizontal
    private val bottomSpace = 5 / 915f * bottomHorizontal
    private val topSpace = bottomSpace * top_bottom_ratio
    private val numHorizontal = 6
    private val topEach = (topHorizontal - topSpace * (numHorizontal - 1)) / numHorizontal
    private val bottomEach = (bottomHorizontal - bottomSpace * (numHorizontal - 1)) / numHorizontal
    private val topPadding = (1 - topHorizontal) / 2
    private val bottomPadding = (1 - bottomHorizontal) / 2
    private val topY = 274 / 440f
    private val bottomY = 376 / 440f
    private val verticalSpace = 15 / 440f * (bottomY - topY)
    private val verticalEach = (bottomY - topY - verticalSpace) / 2
    private val middleUpY = topY + verticalEach
    private val middleDownY = middleUpY + verticalSpace


    fun topLeftX_N(n: Int) = topPadding + (n - 1) * (topEach + topSpace)

    fun topRightX_N(n: Int) = topLeftX_N(n) + topEach

    fun bottomLeftX_N(n: Int) = bottomPadding + (n - 1) * (bottomEach + bottomSpace)

    fun bottomRightX_N(n: Int) = bottomLeftX_N(n) + bottomEach


    fun middleUpLeftX_N(n: Int) =
        bottomLeftX_N(n) + (topLeftX_N(n) - bottomLeftX_N(n)) * (verticalEach + verticalSpace) / (bottomY - topY)

    fun middleUpRightX_N(n: Int) =
        bottomRightX_N(n) + (topRightX_N(n) - bottomRightX_N(n)) * (verticalEach + verticalSpace) / (bottomY - topY)

    fun middleDownLeftX_N(n: Int) =
        bottomLeftX_N(n) + (topLeftX_N(n) - bottomLeftX_N(n)) * (verticalEach - verticalSpace) / (bottomY - topY)

    fun middleDownRightX_N(n: Int) =
        bottomRightX_N(n) + (topRightX_N(n) - bottomRightX_N(n)) * (verticalEach - verticalSpace) / (bottomY - topY)

    var mBitmap10: Bitmap? = null
    var mBitmapRotate10: Bitmap? = null

    init {
        setWillNotDraw(false)
        mBitmap10 =
            ContextCompat.getDrawable(context, R.mipmap.gd_chip10)?.toBitmap(chipWidth, chipHeight)
        mBitmapRotate10 = ContextCompat.getDrawable(context, R.mipmap.gd_chip10_show)
            ?.toBitmap(chipWidth, chipHeight / 2)

    }

    private val removeRectF = RectF()
    override fun onSizeChanged(w: Int, h: Int, oldw: Int, oldh: Int) {
        super.onSizeChanged(w, h, oldw, oldh)
        initPtah()
        initPaint()
        removeRectF.set(0f, (bottom - 200).toFloat(), 200f, bottom.toFloat())

    }

    val mapData = mutableMapOf<Path, BetInfoData>()

    val mPaint = Paint()
    val pathBgPaint = Paint()


    fun initPaint() {
        val colors = intArrayOf(
            ContextCompat.getColor(context, R.color.yellow_white_stroke),
            ContextCompat.getColor(context, R.color.yellow_stroke),
            ContextCompat.getColor(context, R.color.yellow_white_stroke),
            ContextCompat.getColor(context, R.color.yellow_stroke),
            ContextCompat.getColor(context, R.color.yellow_white_stroke),
            ContextCompat.getColor(context, R.color.yellow_stroke)
        )
        val colorsPath = intArrayOf(
            ContextCompat.getColor(context, R.color.green_table_dark),
            ContextCompat.getColor(context, R.color.green_table_light),
            ContextCompat.getColor(context, R.color.green_table_dark)

        )
        setPaint(mPaint, colors, Paint.Style.STROKE)
        setPaint(pathBgPaint, colorsPath, Paint.Style.FILL)


    }

    private fun setPaint(paint: Paint, colors: IntArray, style: Paint.Style) {

        paint.isAntiAlias = true
        paint.strokeWidth = 5f
        paint.style = style
        paint.textSize = 40f
        paint.typeface = Typeface.DEFAULT_BOLD


        val linearGradient =
            LinearGradient(
                0f,
                0f,
                width.toFloat(),
                0f,
                colors,
                null,
                Shader.TileMode.CLAMP
            )
        paint.shader = linearGradient
    }


    private fun initPtah() {


        setPath(playPath, 1, BetInfoData(R.string.player, "1:1", 1f, bigSize, middleSize))
        setPath(bankerPath, 6, BetInfoData(R.string.banker, "1:0.95", 0.95f, bigSize, middleSize))

        setMiddlePathUp(
            playLiePath,
            2,
            BetInfoData(R.string.nplayer, "2:7", 7 / 2f, middleSize, smallSize)
        )
        setMiddlePathDown(
            playPairPath,
            2,
            BetInfoData(R.string.cplayer, "1:11", 11f, middleSize, smallSize)
        )


        setMiddlePathUp(
            anyPairPath,
            3,
            BetInfoData(R.string.anypairs, "1:5", 5f, middleSize, smallSize)
        )
        setMiddlePathDown(tiePath, 3, BetInfoData(R.string.tie, "1:8", 8f, middleSize, smallSize))

        setMiddlePathUp(
            perfectPairPath,
            4,
            BetInfoData(R.string.perfectpair, "1:25", 25f, middleSize, smallSize)
        )
        setMiddlePathDown(
            lucky6Path,
            4,
            BetInfoData(R.string.lucky6, "1:12/1:20", 0f, middleSize, smallSize)
        )

        setMiddlePathUp(
            bankerLiePath,
            5,
            BetInfoData(R.string.nbanker, "2:7", 7 / 2f, middleSize, smallSize)
        )
        setMiddlePathDown(
            bankerPairPath,
            5,
            BetInfoData(R.string.cbanker, "1:11", 11f, middleSize, smallSize)
        )

    }

    private fun setPath(
        path: Path,
        n: Int,
        betInfoData: BetInfoData
    ) {
        setQuadPath(
            betInfoData, path, topLeftX_N(n) * width,
            topY * height, topRightX_N(n) * width,
            topY * height, bottomRightX_N(n) * width,
            bottomY * height, bottomLeftX_N(n) * width, bottomY * height
        )

    }


    private fun setMiddlePathUp(
        path: Path,
        n: Int,
        betInfoData: BetInfoData
    ) {
        setQuadPath(
            betInfoData, path, topLeftX_N(n) * width,
            topY * height, topRightX_N(n) * width,
            topY * height, middleUpRightX_N(n) * width,
            middleUpY * height, middleUpLeftX_N(n) * width, middleUpY * height
        )
    }

    private fun setMiddlePathDown(
        path: Path,
        n: Int,
        betInfoData: BetInfoData
    ) {
        setQuadPath(
            betInfoData,
            path, middleDownLeftX_N(n) * width, middleDownY * height,
            middleDownRightX_N(n) * width, middleDownY * height,
            bottomRightX_N(n) * width, bottomY * height,
            bottomLeftX_N(n) * width, bottomY * height
        )
    }

    val quadRatio = 0.1f //四条边开始画贝塞尔曲线的比例
    private fun setQuadPath(
        data: BetInfoData,
        path: Path,
        x0: Float,
        y0: Float,
        x1: Float,
        y1: Float,
        x2: Float,
        y2: Float,
        x3: Float,
        y3: Float
    ) {
        path.reset()
        path.moveTo(x3 + (x0 - x3) * (1 - quadRatio), y0 + (y3 - y0) * quadRatio)
        path.quadTo(x0, y0, x0 + (x1 - x0) * quadRatio, y0)
        path.lineTo(x0 + (x1 - x0) * (1 - quadRatio), y1)
        path.quadTo(x1, y1, x1 + (x2 - x1) * quadRatio, y1 + (y2 - y1) * quadRatio)
        path.lineTo(x1 + (x2 - x1) * (1 - quadRatio), y1 + (y2 - y1) * (1 - quadRatio))
        path.quadTo(x2, y2, x3 + (x2 - x3) * (1 - quadRatio), y2)
        path.lineTo(x3 + (x2 - x3) * quadRatio, y3)
        path.quadTo(x3, y3, x3 + (x0 - x3) * quadRatio, y0 + (y3 - y0) * (1 - quadRatio))
        path.close()

        var topCenter = PointF(x0 + (x1 - x0) * 0.5f, y0)
        var bottomCenter = PointF(x3 + (x2 - x3) * 0.5f, y3)
        data.topCenter = topCenter
        data.bottomCenter = bottomCenter
        mapData.put(path, data)
    }

    private fun verticalCenter(dataBetInfoData: BetInfoData, ratio: Float) =
        PointF(
            dataBetInfoData.bottomCenter.x + (dataBetInfoData.topCenter.x - dataBetInfoData.bottomCenter.x) * (1 - ratio),
            dataBetInfoData.topCenter.y + (dataBetInfoData.bottomCenter.y - dataBetInfoData.topCenter.y) * ratio
        )

    //画
    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)
        mMatrix.reset()
        canvas.save()
        mCamera.save()

        val pivotXf = width / 2f
        val pivotYf = height * bottomY
        mCamera.rotateX(rotateXDeg)
        mCamera.getMatrix(mMatrix)
        mCamera.restore()
        mMatrix.preTranslate(-pivotXf, -pivotYf);
        mMatrix.postTranslate(pivotXf, pivotYf);
        //将矩阵作用于整个canvas
        canvas.concat(mMatrix);

        drawRemoveBtn(canvas)

        mapData.forEach { it ->
            drawTable(it.key, canvas, it.value)
        }
        canvas.restore()
        mapData.forEach { it ->
            drawChip(canvas, it.value)
        }

    }

    private fun drawRemoveBtn(canvas: Canvas) {
        canvas.drawRoundRect(removeRectF, 5f, 5f, mPaint)
        val string = "清除"
        val stringWidth = mPaint.measureText(string)
        val xText = (removeRectF.centerX() - stringWidth / 2)
        val yText = (removeRectF.centerY())
        canvas.drawText(string, xText, yText, mPaint)
    }


    private fun drawTable(
        path: Path,
        canvas: Canvas,
        betInfoData: BetInfoData
    ) {
        if (betInfoData.changeBg) {
            val verticalCenter = verticalCenter(betInfoData, 0.5f)
            var mRadialGradient =
                RadialGradient(
                    verticalCenter.x,
                    verticalCenter.y,
                    width / 2f,
                    betInfoData.changeBgStarColor,
                    betInfoData.changeBgEndColor,
                    Shader.TileMode.REPEAT
                )
            pathBgPaint.shader = mRadialGradient
            canvas.drawPath(path, pathBgPaint)

        } else {
            val colorsPath = intArrayOf(
                ContextCompat.getColor(context, R.color.green_table_dark),
                ContextCompat.getColor(context, R.color.green_table_light),
                ContextCompat.getColor(context, R.color.green_table_dark)

            )
            val linearGradient =
                LinearGradient(
                    0f,
                    0f,
                    width.toFloat(),
                    0f,
                    colorsPath,
                    null,
                    Shader.TileMode.CLAMP
                )
            pathBgPaint.shader = linearGradient
            canvas.drawPath(path, pathBgPaint)
        }

        canvas.drawPath(path, mPaint)
        val string = betInfoData.resText.let { context.getString(it) }

        val textPoint = verticalCenter(betInfoData, 0.4f)
        val typePoint = verticalCenter(betInfoData, 0.8f)

        mPaint.textSize = betInfoData.textSize
        val stringWidth = mPaint.measureText(string)
        val xText = (textPoint.x - stringWidth / 2)
        val yText = (textPoint.y)
        canvas.drawText(string, xText, yText, mPaint)

        mPaint.textSize = betInfoData.textTypeSize
        val oddsTypeWidth = mPaint.measureText(betInfoData.oddsType)
        val xType = (typePoint.x - oddsTypeWidth / 2)
        val yType = (typePoint.y)
        canvas.drawText(betInfoData.oddsType, xType, yType, mPaint)
    }

    private fun drawChip(
        canvas: Canvas,
        betInfoData: BetInfoData
    ) {

        val typePoint = verticalCenter(betInfoData, 0.5f)
        val n = betInfoData.chips / 10

        var chipX = (typePoint.x - chipWidth / 2)
        for (index in 1..n) {
            var chipY = typePoint.y - index * chipSpace
            canvas.drawBitmap(mBitmapRotate10, chipX, chipY, mPaint)
        }
    }

    private val re = Region()

    override fun onTouchEvent(event: MotionEvent): Boolean {

        //------关键部分 判断点是否在 一个闭合的path内--------//

        //------关键部分 判断点是否在 一个闭合的path内--------//
        val r = RectF()
        if (event.action === MotionEvent.ACTION_DOWN) {


            mapData.map {
                //计算控制点的边界
                it.key.computeBounds(r, true)
                //设置区域路径和剪辑描述的区域
                re.setPath(
                    it.key,
                    Region(r.left.toInt(), r.top.toInt(), r.right.toInt(), r.bottom.toInt())
                )
                //在封闭的path内返回true 不在返回false
                it.value.changeBg = re.contains(event.x.toInt(), event.y.toInt())
                if (it.value.changeBg)
                    it.value.addChips(10)
                if (removeRectF.contains(event.x, event.y)) {
                    it.value.removeChips()
                }
            }

            invalidate()
        }

        return super.onTouchEvent(event)

    }

}

