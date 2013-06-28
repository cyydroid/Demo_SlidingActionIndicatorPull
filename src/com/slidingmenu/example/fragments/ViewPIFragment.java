package com.slidingmenu.example.fragments;

import java.util.ArrayList;

import com.slidingmenu.example.FragmentChangeActivity;
import com.slidingmenu.example.R;
import com.slidingmenu.lib.SlidingMenu;

import com.viewpagerindicator.TabPageIndicator;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;

public class ViewPIFragment extends Fragment {
//	public ViewPIFragment(Context context){
//		this.context=context;
//	}
	private static final String[] CONTENT = new String[] { "Recent", "Artists", "Albums", "Songs", "Playlists" };
    
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		View v=inflater.inflate(R.layout.simple_tabs, null);
		//this.getFragmentManager();
		FragmentPagerAdapter adapter = new ColorPagerAdapter(this.getFragmentManager());

        ViewPager pager = (ViewPager)v.findViewById(R.id.pager);
        pager.setAdapter(adapter);

        TabPageIndicator indicator = (TabPageIndicator)v.findViewById(R.id.indicator);
        indicator.setViewPager(pager);		
        
        indicator.setOnPageChangeListener(new OnPageChangeListener() {
			@Override
			public void onPageScrollStateChanged(int arg0) { }

			@Override
			public void onPageScrolled(int arg0, float arg1, int arg2) { }

			@Override
			public void onPageSelected(int position) {
				//System.out.println("position___"+position);
				Log.i("viewpager", "position___"+position);
				switch (position) {
				case 0:
					if (getActivity() == null)
						return;
					if (getActivity() instanceof FragmentChangeActivity) {
						FragmentChangeActivity fca = (FragmentChangeActivity) getActivity();
						fca.getSlidingMenu().setTouchModeAbove(SlidingMenu.TOUCHMODE_FULLSCREEN);
					} 
					
					break;
				default:
					if (getActivity() == null)
						return;
					if (getActivity() instanceof FragmentChangeActivity) {
						FragmentChangeActivity fca = (FragmentChangeActivity) getActivity();
						fca.getSlidingMenu().setTouchModeAbove(SlidingMenu.TOUCHMODE_MARGIN);
					}
					break;
				}
			}

		});
		return v;
	}
	class ColorPagerAdapter extends FragmentPagerAdapter {
		
		private ArrayList<Fragment> mFragments;

		private final int[] COLORS = new int[] {
			R.color.red,
			R.color.green,
			R.color.blue,
			R.color.white,
			R.color.black
		};
		
		public ColorPagerAdapter(FragmentManager fm) {
			super(fm);
			mFragments = new ArrayList<Fragment>();
			for (int color : COLORS)
				mFragments.add(new ColorFragment(color));
		}

		@Override
		public int getCount() {
			return mFragments.size();
		}

		@Override
		public Fragment getItem(int position) {
			return mFragments.get(position);
		}
		@Override
        public CharSequence getPageTitle(int position) {
            return CONTENT[position % CONTENT.length].toUpperCase();
        }
//		public int getIconResId(int index) {
//	          return ICONS[index];
//	        }
//
//	    
	}
}
