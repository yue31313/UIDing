package com.nian.preferential;

import java.util.Timer;
import java.util.TimerTask;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Window;

import com.nian.preferential.ui.TabShow;

public class MissPreferentialActivity extends Activity {
	public static final String TAG = "nian"; 
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.main);
        new Timer().schedule(new TimerTask() {
			
			@Override
			public void run() {
				
				Intent intent = new Intent(MissPreferentialActivity.this , TabShow.class);
				MissPreferentialActivity.this.startActivity(intent);
				MissPreferentialActivity.this.finish();
			}
		}, 2000);
    }
}