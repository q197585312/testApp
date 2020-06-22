package gaming178.com.casinogame.Activity;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.Window;

import gaming178.com.baccaratgame.R;
import gaming178.com.casinogame.Util.AppConfig;
import gaming178.com.mylibrary.allinone.util.AppTool;
import gaming178.com.mylibrary.base.component.BaseActivity;

public class HomeGuideActivity extends Activity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView( R.layout.activity_guide);
        findViewById(R.id.fl_guide_parent).setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v)
            {
                AppTool.saveObjectData(HomeGuideActivity.this, AppConfig.ACTION_KEY_Guide,true);
                finish();
            }
        });
    }

}