package com.example.viewpagerandtabdemo;

import java.util.ArrayList;
import java.util.List;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.OnPageChangeListener;

import com.actionbarsherlock.app.ActionBar;
import com.actionbarsherlock.app.ActionBar.Tab;
import com.actionbarsherlock.app.SherlockFragmentActivity;

public class MainActivity extends SherlockFragmentActivity implements ActionBar.TabListener, OnPageChangeListener{
	/**
	 * ����Tab��title
	 */
	private String [] mTabTitles;
	
	/**
	 * ViewPager���������
	 */
	private ViewPager mViewPager;
	
	/**
	 * װ��Fragment�����������ǵ�ÿһ�����涼��һ��Fragment
	 */
	private List<Fragment> mFragmentList;
	
	/**
	 * ActionBar���������
	 */
	private ActionBar mActionBar;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		//����Դ�ļ��ڻ�ȡTab��title
		mTabTitles = getResources().getStringArray(R.array.tab_title);
		mFragmentList =  new ArrayList<Fragment>();
		
		mViewPager = (ViewPager) findViewById(R.id.viewPager);
		//����Adapter
		mViewPager.setAdapter(new TabPagerAdapter(getSupportFragmentManager(), mFragmentList));
		//���ü���
		mViewPager.setOnPageChangeListener(this);
		
		
		//��ȡActionʵ������ʹ��getSupportActionBar()����
		mActionBar = getSupportActionBar();
		
//		//����Title
//		mActionBar.setDisplayShowTitleEnabled(false);
//		//����Home logo
//		mActionBar.setDisplayShowHomeEnabled(false);
		//����ActionBar�ĵ���ģʽΪTab
		mActionBar.setNavigationMode(ActionBar.NAVIGATION_MODE_TABS);
		
		
		//ΪActionBar���Tab������TabListener
		for(int i=0; i<mTabTitles.length; i++){
			 ActionBar.Tab tab = mActionBar.newTab();
			 tab.setText(mTabTitles[i]);
			 tab.setTabListener(this);
			 mActionBar.addTab(tab, i);
		}
		
		
		//��Fragment���뵽List�У�����Tab��title���ݸ�Fragment
		for(int i=0; i<mTabTitles.length; i++){
			Fragment fragment = new ItemFragment();
			Bundle args = new Bundle();
			args.putString("arg", mTabTitles[i]);
			fragment.setArguments(args);
			
			mFragmentList.add(fragment);
		}
		
	}
	
	

	@Override
	public void onTabSelected(Tab tab, FragmentTransaction ft) {
		//���ActionBar Tab��ʱ���л���ͬ��Fragment����
		mViewPager.setCurrentItem(tab.getPosition());
	}

	@Override
	public void onTabUnselected(Tab tab, FragmentTransaction ft) {

	}

	@Override
	public void onTabReselected(Tab tab, FragmentTransaction ft) {
		
	}
	
	
	@Override
	public void onPageScrollStateChanged(int arg0) {
		
	}

	@Override
	public void onPageScrolled(int arg0, float arg1, int arg2) {
		
	}

	@Override
	public void onPageSelected(int arg0) {
		//����ViewPager��ʱ���������Ӧ��ActionBar Tab��ѡ��
		mActionBar.setSelectedNavigationItem(arg0);
	}


}
