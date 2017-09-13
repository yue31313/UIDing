package com.nian.preferential.smenu;

import java.util.Timer;
import java.util.TimerTask;

import android.app.Activity;
import android.graphics.PixelFormat;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.GestureDetector;
import android.view.GestureDetector.OnGestureListener;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnTouchListener;
import android.view.ViewGroup.LayoutParams;
import android.view.Window;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationSet;
import android.view.animation.AnimationUtils;
import android.widget.ImageButton;
import android.widget.ScrollView;
import android.widget.TextView;

import com.nian.preferential.R;
import com.nian.preferential.util.log;

public class BusinessInformation extends Activity implements OnTouchListener {

	public static final String TAG = "BusinessInformation";
	public static final int UP = 1;
	public static final int DOWN = 2;
	public static final int EACH =4 ;
	boolean isShow; // 判断优惠券提示是否显示
	
	View view;
	WindowManager windowManager;
	WindowManager.LayoutParams lp;
	ScrollView sv;
	TextView text;
	ImageButton img;
	GestureDetector mGestureDetector;
	int viewH;
	
	Handler handler = new Handler() {

		@Override
		public void handleMessage(Message msg) {
			switch (msg.what) {
			case UP:
				windowManager.updateViewLayout(view, lp);
				break;
            case DOWN:
				windowManager.updateViewLayout(view, lp);
				break;
			default:
				break;
			}
			
			super.handleMessage(msg);
		}
         
	};

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.businesse_information);
		initResourceRefs();

		windowManager.addView(view, lp);
		isShow = text.getVisibility() == View.VISIBLE;
		
		// 优惠券提示
		img.setOnClickListener(new ImageButton.OnClickListener() {

			@Override
			public void onClick(View v) {
				if (isShow) {
					text.setVisibility(View.GONE);
					img.setImageResource(R.drawable.arrow_down);
					isShow = false;
				} else {
					text.setVisibility(View.VISIBLE);
					img.setImageResource(R.drawable.arrow_up);
					isShow = true;
				}

			}
		});

	}
	
       /**
        *  初始化
        */
	private void initResourceRefs() {
		img = (ImageButton) findViewById(R.id.bus_info_detail_but);
		text = (TextView) findViewById(R.id.bus_info_explor);
		LayoutInflater inflater = LayoutInflater.from(this);
		view = inflater.inflate(R.layout.bus_info_windows, null);

		lp = new WindowManager.LayoutParams(LayoutParams.FILL_PARENT,
				LayoutParams.WRAP_CONTENT,
				WindowManager.LayoutParams.TYPE_APPLICATION,
				// 设置为无焦点状态
				WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE
						| WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS, // 没有边界
				// 半透明效果
				PixelFormat.TRANSLUCENT);
		lp.gravity = Gravity.BOTTOM;
        
        lp.windowAnimations = R.style.bus_view;
		sv = (ScrollView) findViewById(R.id.scrollView);
		sv.setOnTouchListener(this);
		
		windowManager = (WindowManager) getSystemService(WINDOW_SERVICE);
	   
	}


	@Override
	protected void onDestroy() {
		windowManager.removeView(view);
		super.onDestroy();
	}

	// scroll view 设置onTouch
	@Override
	public boolean onTouch(View v, MotionEvent event) {
		if(event.getAction()==MotionEvent.ACTION_MOVE){
			view.setVisibility(View.GONE);
		}else{
			view.setVisibility(View.VISIBLE);
		} 
		return false;
	}

}
