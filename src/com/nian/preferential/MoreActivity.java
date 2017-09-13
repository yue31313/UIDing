package com.nian.preferential;

import android.app.Activity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;

import com.nian.preferential.ui.SortList;

public class MoreActivity extends Activity {
      
	SortList moreList;
	HoldView hold = new HoldView();
	MoreAdapter moreAda;
	int moreDate[] = {R.string.more_system , R.string.more_person ,R.string.more_mine , R.string.more_new_version}; 
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.more);
		initResourceRefs();
	}
	
	private void initResourceRefs(){
		moreList = (SortList)findViewById(R.id.more_list);
		moreAda = new MoreAdapter();
		moreList.setAdapter(moreAda);
	}

	private class MoreAdapter extends BaseAdapter{

		@Override
		public int getCount() {
			// TODO Auto-generated method stub
			return moreDate.length;
		}

		@Override
		public Object getItem(int position) {
			// TODO Auto-generated method stub
			return position;
		}

		@Override
		public long getItemId(int position) {
			// TODO Auto-generated method stub
			return position;
		}

		@Override
		public View getView(int position, View convertView, ViewGroup parent) {
			
			if(convertView == null){
				LayoutInflater inflater = LayoutInflater.from(MoreActivity.this);
				convertView = inflater.inflate(R.layout.more_list_item, null);
				hold.bu = (Button)convertView.findViewById(R.id.more_item_bu);
				convertView.setTag(hold);
			}else{
				hold = (HoldView) convertView.getTag();
			}
			  hold.bu.setText(moreDate[position]);
			
			return convertView;
		}
		
	}
	class HoldView {
		Button bu;
	}
}
