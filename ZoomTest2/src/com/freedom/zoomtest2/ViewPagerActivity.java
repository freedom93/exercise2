package com.freedom.zoomtest2;

import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewGroup.LayoutParams;

public class ViewPagerActivity extends Activity {
	
	private int[] bitmapIds = new int[] {
			R.drawable.picture1,R.drawable.picture2, R.drawable.picture3,
			R.drawable.picture4,R.drawable.picture5, R.drawable.picture6,
			R.drawable.picture1,R.drawable.picture2, R.drawable.picture3,
			R.drawable.picture4,R.drawable.picture5, R.drawable.picture6
	};

	private ViewPager mViewPager;
	private List<Bitmap> bitmapList = new ArrayList<Bitmap>();
	private int index = 0;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		mViewPager = new HackyViewPager(this);
		setContentView(mViewPager);
		for(int j=0; j< bitmapIds.length; j++){
			Bitmap bitmap = null;
			bitmap = BitmapFactory.decodeResource(getResources(), bitmapIds[j]);
			bitmapList.add(bitmap);
		}
		
		mViewPager.setAdapter(new SamplePagerAdapter(bitmapList, index));
	}

	static class SamplePagerAdapter extends PagerAdapter 
	{
		private List<Bitmap> bitmapList = new ArrayList<Bitmap>();
		private int index = 0;
		
		public SamplePagerAdapter(List<Bitmap> bitmapList, int index) 
		{
			this.bitmapList = bitmapList;
			this.index = index;
		}

		@Override
		public int getCount() {
			return bitmapList.size();
		}

		@Override
		public View instantiateItem(ViewGroup container, int position) {
			PhotoView photoView = new PhotoView(container.getContext());
			photoView.setImageBitmap(bitmapList.get(position));

			// Now just add PhotoView to ViewPager and return it
			container.addView(photoView, LayoutParams.MATCH_PARENT,
					LayoutParams.MATCH_PARENT);

			return photoView;
		}

		@Override
		public void destroyItem(ViewGroup container, int position, Object object) {
			container.removeView((View) object);
		}

		@Override
		public boolean isViewFromObject(View view, Object object) {
			return view == object;
		}

	}

}
