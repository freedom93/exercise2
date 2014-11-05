package com.freedom.refreshlistviewtest;

import java.util.Date;
import java.util.LinkedList;

import android.app.Activity;
import android.os.AsyncTask;
import android.os.Bundle;
import android.widget.TextView;

public class MainActivity extends Activity implements
		RefreshListView.IOnRefreshListener, RefreshListView.IOnLoadMoreListener {

	private RefreshListView mListView;
	private TextView lv_time;
	private LinkedList<String> data;
	private MyListViewAdapter adapter;
	private RefreshDataAsynTask mRefreshAsynTask;
	private LoadMoreDataAsynTask mLoadMoreAsynTask;

	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		// requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.activity_main);

		initView();

		initData();
	}

	public void initView() {
		mListView = (RefreshListView) findViewById(R.id.listView);
		lv_time = (TextView)findViewById(R.id.head_lastUpdatedTextView);
		lv_time.setText(new Date().toLocaleString());
	}

	public void initData() {

		data = new LinkedList<String>();
		String string = "";
		for (int i = 0; i < 4; i++) {
			string = "TestListviewRefresh" + i;
			data.addFirst(string);
		}

		adapter = new MyListViewAdapter(this, data);

		mListView.setAdapter(adapter);
		mListView.setOnRefreshListener(this);
		mListView.setOnLoadMoreListener(this);

	}

	@Override
	public void OnRefresh() {

		mRefreshAsynTask = new RefreshDataAsynTask();
		mRefreshAsynTask.execute();
	}

	@Override
	public void OnLoadMore() {

		mLoadMoreAsynTask = new LoadMoreDataAsynTask();
		mLoadMoreAsynTask.execute();
	}

	private int index = 3;

	class RefreshDataAsynTask extends AsyncTask<Void, Void, Void> {

		@Override
		protected Void doInBackground(Void... arg0) {

			try {
				Thread.sleep(2000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}

			index++;
			data.addFirst("TestListviewRefreshAdd" + index);

			return null;
		}

		@Override
		protected void onPostExecute(Void result) {

			adapter.refreshData(data);
			mListView.onRefreshComplete();
		}

	}

	private int pos = 0;

	class LoadMoreDataAsynTask extends AsyncTask<Void, Void, Void> {

		@Override
		protected Void doInBackground(Void... arg0) {

			try {
				Thread.sleep(2000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}

			pos++;
			data.add("TestListviewRefreshMore" + pos);

			return null;
		}

		@Override
		protected void onPostExecute(Void result) {

			adapter.refreshData(data);

			if (pos > 5) {
				mListView.onLoadMoreComplete(true);
			} else {
				mListView.onLoadMoreComplete(false);
			}

		}

	}

}