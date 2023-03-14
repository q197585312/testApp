package gaming178.com.casinogame.Bean

import android.graphics.PointF
import gaming178.com.baccaratgame.R

data class BetInfoData(
    var resText: Int = 0,
    var oddsType: String = "",
    var odds: Float = 1f,
    var textSize: Float = 40f,
    var textTypeSize: Float = 20f,
    var topCenter: PointF = PointF(0f, 0f),
    var bottomCenter: PointF = PointF(0f, 0f),
    var changeBg: Boolean = false,
    var changeBgStarColor: Int = -0Xfeca34,
    var changeBgEndColor: Int = -0XEA4335,
    var chips: Int = 0
) {
    fun addChips(i: Int) {
        chips += i
    }

    fun removeChips() {
        chips = 0
    }
}