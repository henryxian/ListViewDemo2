package com.henryxian.listviewdemo2;

import android.app.ListActivity;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.GradientDrawable;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnLongClickListener;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.PopupMenu;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class Main extends ListActivity {

	/* Called when the activity is first created. */
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);
		String[] data = getResources().getStringArray(R.array.foodstuffs);
		ArrayAdapter<String> adapter = new ArrayAdapter<String>(
				this, 
				android.R.layout.simple_list_item_1, 
				data
		);
		setListAdapter(adapter);
		final ListView myList = getListView();
		myList.setFastScrollEnabled(true);
		myList.setOverScrollMode(AbsListView.OVER_SCROLL_IF_CONTENT_SCROLLS);
		myList.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view, int pos,
					long id) {
				// TODO Auto-generated method stub
				Toast.makeText(Main.this, 
						"You picked " + ((TextView)view).getText(), 
						Toast.LENGTH_LONG).show();
			}
			
		});
		myList.setTextFilterEnabled(true);
		
		Drawable d = new GradientDrawable(
				GradientDrawable.Orientation.BOTTOM_TOP, 
				new int[]{0x00ff00, 0x008888, 0x0000ff}
		);
		myList.setOverscrollHeader(d);
		
		View orderZone = findViewById(R.id.orderZone);
		orderZone.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Toast.makeText(Main.this, 
						"Your order will be ready soon", 
						Toast.LENGTH_LONG
			).show();
			}
		});
		
		OnLongClickListener longClickListener = new OnLongClickListener() {
			
			@Override
			public boolean onLongClick(View v) {
				// TODO Auto-generated method stub
				PopupMenu pm = new PopupMenu(Main.this, v);
				pm.getMenuInflater().inflate(R.menu.main, pm.getMenu());
				pm.show();
				return true;
			}
		};
		
		myList.setOnLongClickListener(longClickListener);
		orderZone.setOnLongClickListener(longClickListener);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

}
