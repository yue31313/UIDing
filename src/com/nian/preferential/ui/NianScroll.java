package com.nian.preferential.ui;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.ScrollView;

public class NianScroll extends ScrollView{

	public NianScroll(Context context) {
		super(context);
		// TODO Auto-generated constructor stub
	}

	public NianScroll(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
		// TODO Auto-generated constructor stub
	}

	public NianScroll(Context context, AttributeSet attrs) {
		super(context, attrs);
		// TODO Auto-generated constructor stub
	}

	@Override
	protected void onOverScrolled(int scrollX, int scrollY, boolean clampedX,
			boolean clampedY) {
		// TODO Auto-generated method stub
		super.onOverScrolled(scrollX, scrollY, clampedX, clampedY);
	}

}
