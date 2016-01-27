package com.dsy.test;

import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.support.design.widget.TabLayout;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.Toast;

/**
 * Created by new on 2016-01-19.
 */
public class ViewPagerExample extends AppCompatActivity {
    private int mItemIndex = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.viewpager_example);

        final SwipeRefreshLayout swipeView = (SwipeRefreshLayout) findViewById(R.id.swipe);

        final ViewPager viewPager = (ViewPager) findViewById(R.id.viewpager);
        if (viewPager != null) {
            setupViewPager(viewPager);
        }
        TabLayout tabLayout = (TabLayout) findViewById(R.id.tabs);
        tabLayout.setupWithViewPager(viewPager);
        tabLayout.setOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                viewPager.setCurrentItem(tab.getPosition(), true);
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });

        swipeView.setColorSchemeResources (
            R.color.color_scheme_1_1, R.color.color_scheme_1_2,
            R.color.color_scheme_1_3, R.color.color_scheme_1_4);

        swipeView.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                swipeView.setRefreshing(true);

                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        Toast.makeText(getApplicationContext(), "refresh!!", Toast.LENGTH_SHORT).show();
                        mItemIndex++;
                        if(mItemIndex>2) mItemIndex = 0;
                        viewPager.setCurrentItem(mItemIndex);
                        swipeView.setRefreshing(false);
                    }
                }, 1000);
            }
        });
    }

    private void setupViewPager(ViewPager viewPager) {
        Adapter adapter = new Adapter(this, 3);
        viewPager.setAdapter(adapter);
        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {

            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });

    }

    static class Adapter extends PagerAdapter {
        int mPageCount = 0;
        Context mContext;
        LayoutInflater mLayoutInflater;

        public Adapter(Context context, int pageCount) {
            super();
            mContext = context;
            mLayoutInflater = LayoutInflater.from(context);
            mPageCount = pageCount;
        }

        @Override
        public int getCount() {
            return mPageCount;
        }

        @Override
        public boolean isViewFromObject(View view, Object object) {
            return view == object;
        }

        @Override
        public Object instantiateItem(ViewGroup container, int position) {
            FrameLayout fl = (FrameLayout) mLayoutInflater.inflate(R.layout.tablayout_fragment, null);
            fl.setBackgroundResource(R.drawable.image1+position);
            container.addView(fl);
            return fl;
            //return super.instantiateItem(container, position);
        }

        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {
            container.removeView((View) object);
            //super.destroyItem(container, position, object);
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return "page" + (position + 1);
        }
    }
}
