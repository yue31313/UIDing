package com.nian.preferential.ui;

import java.util.Timer;
import java.util.TimerTask;

import android.app.TabActivity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.view.WindowManager.LayoutParams;
import android.widget.PopupWindow;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TabHost;
import android.widget.Toast;

import com.nian.preferential.HomeActivity;
import com.nian.preferential.MoreActivity;
import com.nian.preferential.NearByAcitivity;
import com.nian.preferential.R;
import com.nian.preferential.SortActivity;
import com.nian.preferential.util.NianUtil;
import com.nian.preferential.util.log;

public class TabShow extends TabActivity {
	private final static String TAG = "TabShow";
	private TabHost mHost;
	private RadioGroup tabItems;
	
	// MineTab 是 “我的” 这一选项 的显示
	private MineTab mineTab;
	private PopupWindow minePop;
	private RadioButton mineBut;
	private static  boolean FINISH = false;
    boolean isShow;
	Toast backToast;
	 public void onCreate(Bundle savedInstanceState) {
	    super.onCreate(savedInstanceState);
	    requestWindowFeature(Window.FEATURE_NO_TITLE);
	    setContentView(R.layout.tabshow);
	    initResourceRefs();
	    initSettings();
	   
	 }
	 private void initResourceRefs(){
		  mHost = getTabHost();
		  
		  mHost.addTab(mHost.newTabSpec("HOME").setIndicator("HOME")
		    		.setContent(new Intent(this , HomeActivity.class)));
		    
		  mHost.addTab(mHost.newTabSpec("NEAR").setIndicator("NEAR")
		    		.setContent(new Intent(this , NearByAcitivity.class)));
		    
		  mHost.addTab(mHost.newTabSpec("SORT").setIndicator("SORT")
		    		.setContent(new Intent(this , SortActivity.class)));  
			    
		  mHost.addTab(mHost.newTabSpec("MORE").setIndicator("MORE")
		    		.setContent(new Intent(this , MoreActivity.class)));
		   
		  tabItems = (RadioGroup)findViewById(R.id.tab_items);
		  mineBut = (RadioButton)findViewById(R.id.tab_item_mine);
		  mineTab = new MineTab(this);
	 }
	 
	 private void initSettings(){
		 
		 
		 tabItems.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
				
				@Override
				public void onCheckedChanged(RadioGroup group, int checkedId) {
					// TODO Auto-generated method stub
					
					
					switch(checkedId){
					
					 case R.id.tab_item_home :
						 mHost.setCurrentTabByTag("HOME");
						 break;
					 case R.id.tab_item_nearby :
						 mHost.setCurrentTabByTag("NEAR");
						 break;
					 case R.id.tab_item_sort :
						 mHost.setCurrentTabByTag("SORT");
						 break;			
					 case R.id.tab_item_more :
						 mHost.setCurrentTabByTag("MORE");
						 break;
					
					}
					
					mineTab.dismissMine(true);
				}
			});
		 
		 mineBut.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				log.d(TAG,"mineButOnclick  " + minePop);
				if (minePop == null){
				  mineTab.showMine();
				}else {
				  mineTab.dismissMine(false);
				}
			}
		});
	 }
	 
	 /**
	  *  popWindow
	  * @author yuhaiyang
	  *
	  */
	  
	 public class MineTab {
		 private Context mContext;
		 
		 private View popView,patentView;
		 public MineTab(Context context){
			 mContext = context;
			 initRes();
		 }
		 private void initRes(){
			 LayoutInflater inflater = LayoutInflater.from(mContext);
			 popView = inflater.inflate(R.layout.mine, null);
			 patentView = inflater.inflate(R.layout.tabshow, null);

		 }
		 
		 public int getId(String tag){
			 if (tag.equals("HOME")){
				 return R.id.tab_item_home;
			 }else if(tag.equals("NEAR")){
				 return R.id.tab_item_nearby;
			 }else if (tag.equals("SORT")){
				 return R.id.tab_item_sort ;
			 }else if (tag.equals("MORE")){
				 return R.id.tab_item_more ;
			 }else{
				 return -1;
			 }
		 }
		 /**
		  * 显示 popWindow
		  */
		 public void showMine(){
			// if(!minePop.isShowing()){
			
			 minePop = new PopupWindow(popView, LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);
			 int y =tabItems.getHeight()  ;
			 int x = mineBut.getLeft() -  mineBut.getWidth()/2;
			 Log.i("nian","top == " + y + " , left == " + minePop.getWidth());
			 
			 minePop.showAtLocation(patentView, Gravity.BOTTOM | Gravity.LEFT, x, y);
			
			 //minePop.showAsDropDown(patentView, x, y);
			 // 以下代码是 popwindow可以自动消失的 代码
//			 minePop.setBackgroundDrawable(new BitmapDrawable());
//			 minePop.setOutsideTouchable(true);
//			 minePop.setOnDismissListener(new PopupWindow.OnDismissListener() {
//					
//					@Override
//					public void onDismiss() {
//						
//					    minePop =null;
//						String tag = mHost.getCurrentTabTag();
//						tabItems.check(getId(tag));
//						
//					}
//				});
			   
                
			// }
		 } 
		 
		 /**
		  *  消失对话框
		  * @param isRa 判断是否是点击的 radioButton 如果是 不需要自己去切换
		  */
		 public void dismissMine(boolean isRa){
			 if(minePop!=null && minePop.isShowing())
				minePop.dismiss();
			    minePop =null;
			    if(!isRa){
			      String tag = mHost.getCurrentTabTag();
				  tabItems.check(getId(tag));
			    }
		  }
	
	 }




     /**
      *  退出消失
      */
	@Override
	public boolean dispatchKeyEvent(KeyEvent event) {
    		if(event.getAction() == KeyEvent.ACTION_DOWN
				&& event.getKeyCode() == KeyEvent.KEYCODE_BACK) {
			
				 if(minePop!=null && minePop.isShowing()){
					 mineTab.dismissMine(false);
				 }else if (!FINISH){
					 backToast = Toast.makeText(this, "再按一次退出程序", Toast.LENGTH_SHORT);
					 backToast.show();
					 FINISH = true;
					 new Timer().schedule(new TimerTask() {
						
						@Override
						public void run() {
							 FINISH = false;
							
						}
					}, 2000);
				 }else {
					 return super.dispatchKeyEvent(event);
				 }
		      return true; 
	     } 
		return super.dispatchKeyEvent(event);
	}

	
	

	@Override
	protected void onPause() {
		if(FINISH){
		   backToast.cancel();	
		   FINISH = false;
		 }
		 mineTab.dismissMine(false);
		super.onPause();
	}

	@Override
	public boolean onTouchEvent(MotionEvent event) {
		mineTab.dismissMine(false);
		return super.onTouchEvent(event);
	}
}
