package com.app.mappingsg;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;

import com.app.mappingsg.fragments.MenuPageFragment;
import com.jeremyfeinstein.slidingmenu.lib.SlidingMenu;
import com.jeremyfeinstein.slidingmenu.lib.app.SlidingFragmentActivity;

public class BaseActivity extends SlidingFragmentActivity{
	 // =========================================================================
	 // TODO Variables
	 // =========================================================================
		private int pageTitleRes;
		protected Fragment oFrag;
		
		public BaseActivity(int titleRes){
			pageTitleRes = titleRes;
		}

		@SuppressLint("NewApi")
		@Override
		public void onCreate(Bundle savedInstanceState) {
			super.onCreate(savedInstanceState);
			
			setTitle(pageTitleRes);
			//
			setBehindContentView(R.layout.frame_menu);
			
			if(savedInstanceState == null){
				
				FragmentTransaction ft = this.getSupportFragmentManager().beginTransaction();
				oFrag = new MenuPageFragment();
				
				ft.replace(R.id.frame_menu, oFrag);
				ft.commit();
			} else {
				oFrag = (Fragment)getSupportFragmentManager().findFragmentById(R.id.frame_menu);
			}
			// customize the Sliding Menu
			 SlidingMenu sm = getSlidingMenu();
			 sm.setShadowWidthRes(R.dimen.shadow_width);
			 sm.setShadowDrawable(R.drawable.shadow);
			 sm.setBehindOffsetRes(R.dimen.slidingmenu_offset);
			 sm.setFadeDegree(0.35f);
			 sm.setTouchModeAbove(SlidingMenu.TOUCHMODE_FULLSCREEN);
			 
			 getActionBar().setDisplayHomeAsUpEnabled(true);
		}
}
