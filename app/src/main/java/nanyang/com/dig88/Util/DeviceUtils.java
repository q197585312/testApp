package nanyang.com.dig88.Util;

import android.app.Activity;
import android.content.Context;
import android.util.DisplayMetrics;
import android.view.Display;

import java.lang.reflect.Method;

public class DeviceUtils {

	@SuppressWarnings("deprecation")
	public static int getScreenWidth(Activity ctx) {
		int width;
		Display display = ctx.getWindowManager().getDefaultDisplay();
		try {
			Method mGetRawW = Display.class.getMethod("getRawWidth");
			width = (Integer) mGetRawW.invoke(display);
		} catch (Exception e) {
			width = display.getWidth();
		}
		return width;
	}

	@SuppressWarnings("deprecation")
	public static int getScreenHeight(Activity ctx) {
		int height;
		Display display = ctx.getWindowManager().getDefaultDisplay();
		try {
			Method mGetRawH = Display.class.getMethod("getRawHeight");
			height = (Integer) mGetRawH.invoke(display);
		} catch (Exception e) {
			height = display.getHeight();
		}
		return height;
	}

	public static double getScreenPhysicalSize(Activity ctx) {
		DisplayMetrics dm = new DisplayMetrics();
		ctx.getWindowManager().getDefaultDisplay().getMetrics(dm);
		double diagonalPixels = Math.sqrt(Math.pow(dm.widthPixels, 2) + Math.pow(dm.heightPixels, 2));
		return diagonalPixels / (160 * dm.density);
	}
	/**
	 * 根据手机的分辨率从 dp 的单位 转成为 px(像素)
	 */
	public static int dip2px(Context context, float dpValue) {
		final float scale = context.getResources().getDisplayMetrics().density;
		return (int) (dpValue * scale + 0.5f);
	}

	/**
	 * 根据手机的分辨率从 px(像素) 的单位 转成为 dp
	 */
	public static int px2dip(Context context, float pxValue) {
		final float scale = context.getResources().getDisplayMetrics().density;
		return (int) (pxValue / scale + 0.5f);
	}
	/**
	 * 获取手机大小（分辨率）
	 *
	 * @param activity
	 * @return
	 */
	public static DisplayMetrics getScreenPix(Activity activity) {
		// DisplayMetrics 一个描述普通显示信息的结构，例如显示大小、密度、字体尺寸
		DisplayMetrics displaysMetrics = new DisplayMetrics();
		// 获取手机窗口的Display 来初始化DisplayMetrics 对象
		// getManager()获取显示定制窗口的管理器。
		// 获取默认显示Display对象
		// 通过Display 对象的数据来初始化一个DisplayMetrics 对象
		activity.getWindowManager().getDefaultDisplay()
				.getMetrics(displaysMetrics);
		return displaysMetrics;
	}
}
