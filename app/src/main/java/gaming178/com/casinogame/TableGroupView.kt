package gaming178.com.casinogame

import android.content.Context
import android.graphics.*
import android.util.AttributeSet
import android.widget.FrameLayout
import androidx.core.content.ContextCompat
import gaming178.com.baccaratgame.R


class TableGroupView(context: Context, attrs: AttributeSet) : FrameLayout(context, attrs) {
    companion object {
        const val test = "loanType"
        const val LOAN_TITLE = "loanTitle"
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


    private val topHorizontal = 655 / 915f
    private val bottomHorizontal = 742 / 915f
    private val top_bottom_ratio = topHorizontal / bottomHorizontal
    private val bottomSpace = 7 / 915f
    private val topSpace = bottomSpace * top_bottom_ratio
    private val numHorizontal = 6
    private val topEach = (topHorizontal - topSpace * (numHorizontal - 1)) / numHorizontal
    private val bottomEach = (bottomHorizontal - bottomSpace * (numHorizontal - 1)) / numHorizontal
    private val topPadding = (1 - topHorizontal) / 2
    private val bottomPadding = (1 - bottomHorizontal) / 2
    private val verticalSpace = 5 / 440f
    private val topY = 274 / 440f
    private val bottomY = 376 / 440f
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

    val camera = Camera()
    val matrixPath = Matrix()

    init {
        setWillNotDraw(false)
    }

    override fun onSizeChanged(w: Int, h: Int, oldw: Int, oldh: Int) {
        super.onSizeChanged(w, h, oldw, oldh)
        initPtah()
        initPaint()
    }

    val mapData = mutableMapOf<Path, BetInfoData>()

    var mPaint = Paint()

    fun initPaint() {

        mPaint = Paint()
        mPaint.isAntiAlias = true
        mPaint.strokeWidth = 3f
        mPaint.style = Paint.Style.STROKE
        mPaint.textSize = 40f
        val colors = intArrayOf(
            ContextCompat.getColor(context, R.color.gold),
            ContextCompat.getColor(context, R.color.white),
            ContextCompat.getColor(context, R.color.gold),
            ContextCompat.getColor(context, R.color.white),
            ContextCompat.getColor(context, R.color.gold),
            ContextCompat.getColor(context, R.color.white),
            ContextCompat.getColor(context, R.color.gold),

            )
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
        mPaint.shader = linearGradient


    }


    private fun initPtah() {

        setPath(playPath, 1, BetInfoData(R.string.player, "1:1", 1f,))
        setPath(bankerPath, 6, BetInfoData(R.string.banker, "1:0.95", 0.95f,))

        setMiddlePathUp(playLiePath, 2, BetInfoData(R.string.nplayer, "2:7", 7 / 2f,))
        setMiddlePathDown(playPairPath, 2, BetInfoData(R.string.cplayer, "1:11", 11f,))


        setMiddlePathUp(anyPairPath, 3, BetInfoData(R.string.anypairs, "1:5", 5f,))
        setMiddlePathDown(tiePath, 3, BetInfoData(R.string.tie, "1:8", 8f,))

        setMiddlePathUp(perfectPairPath, 4, BetInfoData(R.string.perfectpair, "1:25", 25f,))
        setMiddlePathDown(lucky6Path, 4, BetInfoData(R.string.lucky6, "1:12/1:20", 0f,))

        setMiddlePathUp(bankerLiePath, 5, BetInfoData(R.string.nbanker, "2:7", 7 / 2f,))
        setMiddlePathDown(bankerPairPath, 5, BetInfoData(R.string.cbanker, "1:11", 11f,))

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
    fun setQuadPath(
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
        matrix.reset();
        camera.translate(10f, 50f, -180f);
        camera.getMatrix(matrix);
        canvas.concat(matrix);
        canvas.drawCircle(60f, 60f, 60f, mPaint);

        drawDataPath(canvas, playPath)
        drawDataPath(canvas, playLiePath)
        drawDataPath(canvas, playPairPath)
        drawDataPath(canvas, anyPairPath)
        drawDataPath(canvas, tiePath)
        drawDataPath(canvas, perfectPairPath)
        drawDataPath(canvas, lucky6Path)
        drawDataPath(canvas, bankerLiePath)
        drawDataPath(canvas, bankerPairPath)
        drawDataPath(canvas, bankerPath)

    }

    private fun drawDataPath(canvas: Canvas, path: Path) {
        val betInfoData = mapData[path] ?: return
        println("testData:" + betInfoData)
        canvas.drawPath(path, mPaint)

        val string = betInfoData.resText.let { context.getString(it) }

        val textPoint = betInfoData.let { verticalCenter(it, 0.4f) }
        val typePoint = betInfoData.let { verticalCenter(it, 0.8f) }

        val stringWidth = mPaint.measureText(string)
        val oddsTypeWidth = mPaint.measureText(betInfoData.oddsType)

        val xText = (textPoint.x - stringWidth / 2)
        val yText = (textPoint.y)

        val xType = (typePoint.x - oddsTypeWidth / 2)
        val yType = (typePoint.y)
        canvas.drawText(string, xText, yText, mPaint)
        canvas.drawText(betInfoData.oddsType, xType, yType, mPaint)

    }

}

