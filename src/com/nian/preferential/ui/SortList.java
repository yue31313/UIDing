package com.nian.preferential.ui;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.ListView;
/**
 * 重写 GridView 可以避免显示不全
 * @author yuhaiyang
 *
 */
public class SortList extends ListView {

	public SortList(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
		// TODO Auto-generated constructor stub
	}

	public SortList(Context context, AttributeSet attrs) {
		super(context, attrs);
		// TODO Auto-generated constructor stub
	}

	public SortList(Context context) {
		super(context);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {

		int expandSpec = MeasureSpec.makeMeasureSpec(Integer.MAX_VALUE >> 2,
				MeasureSpec.AT_MOST);
		super.onMeasure(widthMeasureSpec, expandSpec);
	}
}
