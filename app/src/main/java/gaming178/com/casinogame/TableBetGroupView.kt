package gaming178.com.casinogame

import android.content.Context
import android.graphics.Canvas
import android.graphics.Paint
import android.graphics.Path
import android.util.AttributeSet
import android.widget.FrameLayout
import androidx.core.content.ContextCompat
import gaming178.com.baccaratgame.R

class TableBetGroupView(context: Context, attrs: AttributeSet?) : FrameLayout(context, attrs) {
    val topProportion=0.622f
    val bottomProportion=0.854f

    val bottomLeftProportion=80/915f
    val bottomRightProportion=202/915f
    val topLeftProportion=130/915f
    val topRightProportion=231/915f

    val top_bottom=(100)/115f
    val horizontal_space_bottom=6/915f
    val horizontal_space_top=6/915*top_bottom
    val topHorizontal=655/915f


    var paint = Paint()
    private var playPath  =  Path()
    private var playLeiPath  =  Path()
    private var playParPath  =  Path()
    override fun onSizeChanged(w: Int, h: Int, oldw: Int, oldh: Int) {
        super.onSizeChanged(w, h, oldw, oldh)
        initPath()
        setWillNotDraw(false);

    }
    init {
        paint.style=Paint.Style.STROKE
        paint.color=ContextCompat.getColor(context,R.color.gold)
        paint.strokeJoin = Paint.Join.MITER
        paint.strokeWidth=10f
    }
    private fun initPath() {
        playPath.moveTo(topLeftProportion*width, topProportion*height)
        playPath.lineTo(topRightProportion*width, topProportion*height);
        playPath.lineTo(bottomRightProportion*width,bottomProportion*height );
        playPath.lineTo(bottomLeftProportion*width,bottomProportion*height );
        playPath.close()

        playLeiPath.moveTo((topRightProportion+horizontal_space_top)*width, topProportion*height)
        playLeiPath.lineTo(topRightProportion*width, topProportion*height);
        playLeiPath.lineTo(bottomRightProportion*width,bottomProportion*height );
        playLeiPath.lineTo(bottomLeftProportion*width,bottomProportion*height );
        playLeiPath.close()

    }


    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)
        canvas.drawPath(playPath,paint)
    }




}