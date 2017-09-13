package com.nian.preferential.util;

import android.app.Activity;
import android.content.Context;
import android.graphics.Rect;
import android.util.DisplayMetrics;

public class NianUtil {
	public static final String TWO_SHARW_TAG = "com.nian.preferential";
	/**
	 * ��ȡ��Ļ�ĳߴ�
	 * @param context
	 * @return
	 */
	public static int[] getScreenSize(Context context) {
		DisplayMetrics dm = new DisplayMetrics();
		dm = context.getResources().getDisplayMetrics();
		int[] size = { dm.widthPixels, dm.heightPixels };
		return size;
	}
	
	/**
	 * ��ȡ״̬���ĸ߶�
	 * @param activity
	 * @return
	 */
	public static int getStatusBarH(Activity activity){
		Rect frame = new Rect();  
		activity.getWindow().getDecorView().getWindowVisibleDisplayFrame(frame);  
		return frame.top;
	}
}
