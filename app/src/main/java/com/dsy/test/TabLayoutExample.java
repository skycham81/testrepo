package com.dsy.test;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;

/**
 * Created by new on 2016-01-18.
 */
public class TabLayoutExample  extends AppCompatActivity{
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        overridePendingTransition(0, 0);

        setContentView(R.layout.tablayout_example);

        ViewPager viewPager = (ViewPager) findViewById(R.id.viewpager);
        if (viewPager != null) {
            setupViewPager(viewPager);
        }
        TabLayout tabLayout = (TabLayout) findViewById(R.id.tabs);
        tabLayout.setupWithViewPager(viewPager);

    }

    @Override
    protected void onDestroy() {
        overridePendingTransition(0, 0);
        super.onDestroy();
    }

    private void setupViewPager(ViewPager viewPager) {
        Adapter adapter = new Adapter(getSupportFragmentManager());
        viewPager.setAdapter(adapter);
    }

    static class Adapter extends FragmentPagerAdapter {
        final int mMaxSize = 3;
        public Adapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            Fragment fragment = new TabLayoutFragment();
            Bundle b = new Bundle();
            switch(position){
                case 0:
                    b.putInt("image_id", R.drawable.image1);
                    break;
                case 1:
                    b.putInt("image_id", R.drawable.image2);
                    break;
                case 2:
                    b.putInt("image_id", R.drawable.image3);
                    break;
                default:
                    break;
            }

            fragment.setArguments(b);
            //tlf.setBackGroundImage(R.drawable.image1);
            return fragment;
        }

        @Override
        public int getCount() {
            return mMaxSize;
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return "Category" + (position+1);
        }
    }
}
