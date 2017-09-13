package com.nian.preferential.smenu;

import android.app.Activity;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.Toast;

import com.nian.preferential.R;
import com.nian.preferential.util.NianUtil;

public class TwoDimensionalCode extends Activity{

	Button scanBut;
	CheckBox notiCheckBox;
	SharedPreferences share;
	Editor editor;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.two_dimensional_code);
		initRescourceRefs();
		
		scanBut.setOnClickListener(new Button.OnClickListener() {
			
			@Override
			public void onClick(View v) {
			  Toast.makeText(TwoDimensionalCode.this, "扫， 扫你妹呀， 没有这个功能。。", Toast.LENGTH_SHORT).show();
				
			}
		});
		notiCheckBox.setOnCheckedChangeListener(new CheckBox.OnCheckedChangeListener() {
			
			@Override
			public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
				if(isChecked){
					editor.putBoolean("TWO_DIM", isChecked);
				}else{
					editor.putBoolean("TWO_DIM", isChecked);
				}
				editor.commit();
			}
		});
	}
	private void initRescourceRefs(){
		scanBut = (Button)findViewById(R.id.two_scan_but);
		notiCheckBox = (CheckBox)findViewById(R.id.two_noti_checkbox);
		share = getSharedPreferences(NianUtil.TWO_SHARW_TAG, 0);
		editor = share.edit();
	}

}
