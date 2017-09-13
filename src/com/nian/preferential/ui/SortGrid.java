package com.nian.preferential.ui;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.GridView;
/**
 * 重写 GridView 可以避免显示不全
 * @author yuhaiyang
 *
 */
public class SortGrid extends GridView {

	public SortGrid(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
		// TODO Auto-generated constructor stub
	}

	public SortGrid(Context context, AttributeSet attrs) {
		super(context, attrs);
		// TODO Auto-generated constructor stub
	}

	public SortGrid(Context context) {
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
