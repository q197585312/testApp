package gaming178.com.casinogame.Activity;

import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.view.View;

import gaming178.com.baccaratgame.R;
import gaming178.com.casinogame.Control.PageWidget;
import gaming178.com.casinogame.Control.PageWidgetT;

/**
 * 应用主界面
 * 
 * @author AigeStudio
 * @since 2014/12/15
 * @version 1.0.0
 * 
 */
public class PageCurlActivity extends Activity {


	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.gd_activity_curl);

//		twistPage();

		// curvePage();

//		 foldPage();

//		 turnPage();
		testView();
	}
	PageWidgetT pw;
	private void testView() {
		pw = (PageWidgetT) findViewById(R.id.gd__main1);
		pw.setCanNotFlip(false);
		pw.setImageRes( R.mipmap.gd_poker_bg, R.mipmap.gd_pk_2);


	}

	public void clickChange(View view) {
		pw.rotate();
	}
}