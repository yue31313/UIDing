package com.nian.preferential.smenu;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.GridView;
import android.widget.ListView;
import android.widget.TextView;

import com.nian.preferential.HomeActivity;
import com.nian.preferential.R;

public class ChooseCity extends Activity {

	private static final String TAG = "ChooseCity";
	
	TextView topText;
	Intent gIntent;
	ListView listView;
	LayoutInflater inflater;
	View headView;
	Button headItemBut;
	ChooseAdapter coAdapter;
	
	String DATA[][] = new String [][] {
		/*eg  A , ����*/	
		{"B","����"},
		{"C","�ɶ�","����","����","��ɳ","����"},
		{"D","����"},
		{"F","����"},
		{"G","����","����"},
		{"H","����","������","�Ϸ�","����"},
		{"J","����"},
		{"K","����"},
		{"L","����"},
		{"N","�Ͼ�","�ϲ�","����","����"},
		{"Q","�ൺ"},
		{"S","�Ϻ�","����","����","����","ʯ��ׯ"},
		{"T","���","̫ԭ"},
		{"W","�人","����","����"},
		{"X","����","����"},
		{"Y","��̨","����"},
		{"Z","�麣","֣��"},
	
	};
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		super.onCreate(savedInstanceState);
		setContentView(R.layout.choose_city);
		initResourceRefs();
		initSettings();
	}
	private void initResourceRefs(){
		
		topText = (TextView)findViewById(R.id.choose_top_tital);
		listView = (ListView)findViewById(R.id.choose_city);
		gIntent = getIntent();
		inflater = LayoutInflater.from(this);
		headView = inflater.inflate(R.layout.choose_city_headview, null);
		
		headItemBut = (Button)headView.findViewById(R.id.choose_headview_but);
		coAdapter = new ChooseAdapter();
	}
	private void initSettings(){
		final String city = gIntent.getStringExtra("NowCity");
		topText.setText(getString(R.string.choose_city_text, city));
		
		listView.addHeaderView(headView);
		headItemBut.setText(getString(R.string.choose_current_city, getString(R.string.home_location_default)/*��ɶ�λϵͳ���л�*/));
		listView.setAdapter(coAdapter);
		headView.setClickable(true);
		headView.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				Intent intent = new Intent(ChooseCity.this ,HomeActivity.class);
				ChooseCity.this.setResult(RESULT_CANCELED);;
				ChooseCity.this.finish();
			}
		});
	}
	@Override
	protected void onDestroy() {
		// �˳���ʱ�� ����һ��ֵ
		setResult(RESULT_CANCELED);
		super.onDestroy();
	}
    // listView �� adapter
	private class ChooseAdapter extends BaseAdapter{

		@Override
		public int getCount() {
			// TODO Auto-generated method stub
			return DATA.length;
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
			ChooseCityListItem item ;
			if(convertView == null){
				item = new ChooseCityListItem(ChooseCity.this);
				convertView = item.getView();
				convertView.setTag(item);
			}else{
				item =(ChooseCityListItem)convertView.getTag();
			}
			 item.bind(getData(position));
			return convertView;
		}
		 
		public String[] getData(int position){
			String []a = new String[DATA[position].length];;
			for(int i=0 ;i<DATA[position].length ;i++){
				a[i]=DATA[position][i];
			}
			return a;
		}
	}
	
	/**
	 *
	 * item
	 */
	public class ChooseCityListItem {
		
		public static final String TAG = "ChooseCityListItem";
		private Context mContext;
		private String[] mData;
		private TextView text;
		private GridView grid;
		ChooseItemAdapter ciAdapter;
		View mView;

		public ChooseCityListItem(Context context) {
			mContext = context;
			init();
		}

		public View getView(){
			return mView;
		}

		public void bind(String[]data){
			mData = data;
			if(ciAdapter == null){
				ciAdapter = new ChooseItemAdapter();
				grid.setAdapter(ciAdapter);
			}else{
				ciAdapter.notifyDataSetChanged();
			}
			text.setText(mData[0]);
		}

		public void init(){
			LayoutInflater inflater = LayoutInflater.from(mContext);
			mView =inflater.inflate(R.layout.choose_city_list_item, null);
			text = (TextView)mView.findViewById(R.id.choose_list_item_text);
			grid = (GridView)mView.findViewById(R.id.choose_list_item_grid);
			
		}
		// listView �е�  GridView �� adapter
		private class ChooseItemAdapter extends BaseAdapter{

			@Override
			public int getCount() {
				// TODO Auto-generated method stub
				return mData.length-1;
			}

			@Override
			public Object getItem(int position) {
				// TODO Auto-generated method stub
				return position+1;
			}

			@Override
			public long getItemId(int position) {
				// TODO Auto-generated method stub
				return position+1;
			}

			@Override
			public View getView(int position, View convertView, ViewGroup parent) {
				final TextView text ;
				if(convertView == null){
					text = new TextView(mContext);
					text.setTextColor(Color.BLACK);
					text.setGravity(Gravity.CENTER_HORIZONTAL);
					text.setTextSize(20);
					text.setClickable(true);
					text.setOnClickListener(new TextView.OnClickListener() {
						
						@Override
						public void onClick(View v) {
							String city= text.getText().toString();
							Intent intent = new Intent(ChooseCity.this ,HomeActivity.class);
							intent.putExtra("Choose_city", city);
							ChooseCity.this.setResult(RESULT_OK, intent);
							ChooseCity.this.finish();
						}
					});
				}else{
					text = (TextView)convertView ;
				}
     
	            text.setText(mData[position+1]);	      
				return text;
			}
		 
		  private void setTextSeting(){
			  
		  }
			
		}

	}

}
