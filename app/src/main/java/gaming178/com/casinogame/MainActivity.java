package gaming178.com.casinogame;

import android.app.Activity;
import android.os.Bundle;

import gaming178.com.baccaratgame.R;



public class MainActivity extends Activity {



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.gd_include_baccarat_bet_table);
//        gridView = (GridView) findViewById(R.id.grid);
//        LayoutInflater.from(this).inflate(R.layout.gd_activity_baccarat_bet_game)

    }
/**
 *   ---setContentView()：getWindow().setContentView()
 *   ---phoneWindow():DecorView:FrameLayout
 *   ---mContentParent = generateLayout(mDecor):ViewGroup
 *      ->requestFeature()设 状态栏导航栏等等
 *   ---mLayoutInflater.inflate(layoutResID, mContentParent);
 *
 * */
    /*view
    measure  ：测量
    、layout ：摆放子控件
     draw    : 绘制
    * viewGroup


    * */
}
