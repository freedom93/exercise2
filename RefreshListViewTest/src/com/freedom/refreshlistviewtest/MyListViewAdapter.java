package com.freedom.refreshlistviewtest;

import java.util.ArrayList;
import java.util.List;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

public class MyListViewAdapter extends BaseAdapter {

	private List<String> data = new ArrayList<String>();

	private Context mContext;

	public MyListViewAdapter(Context context, List<String> data) {
		mContext = context;
		this.data = data;
	}

	public void refreshData(List<String> data) {
		this.data = data;
		notifyDataSetChanged();

	}

	@Override
	public int getCount() {
		return data.size();
	}

	@Override
	public Object getItem(int pos) {
		return data.get(pos);
	}

	@Override
	public long getItemId(int arg0) {
		return 0;
	}

	@Override
	public View getView(int pos, View view, ViewGroup parent) {
		if (view == null) {
			view = LayoutInflater.from(mContext).inflate(
					R.layout.listview_item, null);
		}

		TextView textView = (TextView) view.findViewById(R.id.textView);
		textView.setText(data.get(pos));

		return view;
	}

}
