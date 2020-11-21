import android.content.Context
import android.graphics.Point
import android.graphics.PointF
import android.util.AttributeSet
import android.view.ViewGroup

class BaseGroupView(context: Context?, attrs: AttributeSet?) : ViewGroup(context, attrs) {
    override fun onLayout(changed: Boolean, l: Int, t: Int, r: Int, b: Int) {
        TODO("Not yet implemented")
    }

    fun addChildView() {

    }

    fun getCenterPoint(): Point {

        return Point(right - left, top - bottom)
    }

}