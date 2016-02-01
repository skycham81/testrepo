package com.dsy.test;

import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.design.widget.Snackbar;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

public class MainActivity extends AppCompatActivity implements View.OnClickListener, NavigationView.OnNavigationItemSelectedListener {

    int mSortMode = -1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        overridePendingTransition(0, 0);

        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);

        setSupportActionBar(toolbar);

        findViewById(R.id.fab).setOnClickListener(this);
        findViewById(R.id.go_tablelayout).setOnClickListener(this);
        findViewById(R.id.go_viewpager_for_pageapater).setOnClickListener(this);
        findViewById(R.id.go_achartengine_example).setOnClickListener(this);
        findViewById(R.id.go_mpandroidchart_example1).setOnClickListener(this);
        findViewById(R.id.go_mpandroidchart_example2).setOnClickListener(this);

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);

        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawer, toolbar,
                R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_camera) {
            // Handle the camera action
        } else if (id == R.id.nav_gallery) {

        } else if (id == R.id.nav_slideshow) {

        } else if (id == R.id.nav_manage) {

        } else if (id == R.id.nav_share) {

        } else if (id == R.id.nav_send) {

        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    @Override
    public boolean onPrepareOptionsMenu(Menu menu) {
        if (mSortMode != -1) {
            Drawable icon = menu.findItem(mSortMode).getIcon();
            menu.findItem(R.id.action_sort).setIcon(icon);
        }
        return super.onPrepareOptionsMenu(menu);
    }

    // This method is specified as an onClick handler in the menu xml and will
    // take precedence over the Activity's onOptionsItemSelected method.
    // See res/menu/actions.xml for more info.
    public void onSort(MenuItem item) {
        mSortMode = item.getItemId();
        // Request a call to onPrepareOptionsMenu so we can change the sort icon
        invalidateOptionsMenu();
    }

    @Override
    public void onClick(View v) {
        Intent intent;
        switch (v.getId()){
            case R.id.fab:
                Snackbar.make(v, "Replace with your own action", Snackbar.LENGTH_LONG).setAction("Action", null).show();
                break;
            case R.id.go_tablelayout:
                intent = new Intent(v.getContext(), TabLayoutExample.class);
                startActivity(intent);
                break;
            case R.id.go_viewpager_for_pageapater:
                intent = new Intent(v.getContext(), ViewPagerExample.class);
                startActivity(intent);
                break;
            case R.id.go_achartengine_example:
                intent = new Intent(v.getContext(), AChartEngineExample.class);
                startActivity(intent);
                break;
            case R.id.go_mpandroidchart_example1:
                intent = new Intent(v.getContext(), MPAndroidChartExample1.class);
                startActivity(intent);
                break;
            case R.id.go_mpandroidchart_example2:
                intent = new Intent(v.getContext(), MPAndroidChartExample2.class);
                startActivity(intent);
                break;
        }
    }
}
