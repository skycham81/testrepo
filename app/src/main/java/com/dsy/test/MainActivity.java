package com.dsy.test;

import android.content.res.Configuration;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.NavigationView;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener,
        MainFragment.OnFragmentInteractionListener , FragmentExample1.OnFragmentInteractionListener,
        FragmentExample2.OnFragmentInteractionListener {

    private Toolbar mToolbar;
    private ActionBarDrawerToggle mDrawerToggle;
    private DrawerLayout mDrawerLayout;

    private boolean mToolbarInitialized;

    int mSortMode = -1;

    private MainFragment mMainFragment = null;

    private final DrawerLayout.DrawerListener mDrawerListener = new DrawerLayout.DrawerListener() {
        @Override
        public void onDrawerClosed(View drawerView) {

        }

        @Override
        public void onDrawerStateChanged(int newState) {
            if (mDrawerToggle != null) mDrawerToggle.onDrawerStateChanged(newState);
        }

        @Override
        public void onDrawerSlide(View drawerView, float slideOffset) {
            if (mDrawerToggle != null) mDrawerToggle.onDrawerSlide(drawerView, slideOffset);
        }

        @Override
        public void onDrawerOpened(View drawerView) {
            if (mDrawerToggle != null) mDrawerToggle.onDrawerOpened(drawerView);
            if (getSupportActionBar() != null) getSupportActionBar()
                    .setTitle(R.string.app_name);
        }
    };

    private final FragmentManager.OnBackStackChangedListener mBackStackChangedListener =
            new FragmentManager.OnBackStackChangedListener() {
                @Override
                public void onBackStackChanged() {
                    updateDrawerToggle();
                }
            };

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mToolbar = (Toolbar) findViewById(R.id.toolbar);
        if (mToolbar == null) {
            throw new IllegalStateException("Layout is required to include a Toolbar with id " + "'toolbar'");
        }
        mToolbar.inflateMenu(R.menu.main);

        mDrawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (mDrawerLayout != null) {
            NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
            if (navigationView == null) {
                throw new IllegalStateException("Layout requires a NavigationView " + "with id 'nav_view'");
            }
            navigationView.setNavigationItemSelectedListener(this);

            // Create an ActionBarDrawerToggle that will handle opening/closing of the drawer:
            mDrawerToggle = new ActionBarDrawerToggle(this, mDrawerLayout,
                    mToolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
            mDrawerLayout.setDrawerListener(mDrawerListener);

            setSupportActionBar(mToolbar);
            updateDrawerToggle();
        } else {
            setSupportActionBar(mToolbar);
        }

        mToolbarInitialized = true;

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

    }

    @Override
    protected void onStart() {
        super.onStart();
        if(mMainFragment==null)
            mMainFragment = (MainFragment) getSupportFragmentManager().findFragmentById(R.id.fragment_container);
        if (!mToolbarInitialized) {
            throw new IllegalStateException("You must run super.initializeToolbar at " +
                    "the end of your onCreate method");
        }
    }

    @Override
    protected void onPostCreate(Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        if (mDrawerToggle != null) {
            mDrawerToggle.syncState();
        }
    }

    @Override
    public void onResume() {
        super.onResume();
        // Whenever the fragment back stack changes, we may need to update the
        // action bar toggle: only top level screens show the hamburger-like icon, inner
        // screens - either Activities or fragments - show the "Up" icon instead.
        getSupportFragmentManager().addOnBackStackChangedListener(mBackStackChangedListener);
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        if (mDrawerToggle != null) {
            mDrawerToggle.onConfigurationChanged(newConfig);
        }
    }

    @Override
    public void onPause() {
        super.onPause();
        getSupportFragmentManager().removeOnBackStackChangedListener(mBackStackChangedListener);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        super.onCreateOptionsMenu(menu);
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (mDrawerToggle != null && mDrawerToggle.onOptionsItemSelected(item)) {
            return true;
        }
        // If not handled by drawerToggle, home needs to be handled by returning to previous
        if (item != null && item.getItemId() == android.R.id.home) {
            onBackPressed();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onBackPressed() {
        // If the drawer is open, back will close it
        if (mDrawerLayout != null && mDrawerLayout.isDrawerOpen(GravityCompat.START)) {
            mDrawerLayout.closeDrawers();
            return;
        }
        // Otherwise, it may return to the previous fragment stack
        FragmentManager fragmentManager = getSupportFragmentManager();
        if (fragmentManager.getBackStackEntryCount() > 0) {
            fragmentManager.popBackStack();
        } else {
            // Lastly, it will rely on the system behavior for back
            super.onBackPressed();
        }
    }

    @Override
    public void setTitle(CharSequence title) {
        super.setTitle(title);
        mToolbar.setTitle(title);
    }

    @Override
    public void setTitle(int titleId) {
        super.setTitle(titleId);
        mToolbar.setTitle(titleId);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_camera) {
            // Handle the camera action
            replaceFragment(FragmentExample1.class.getSimpleName());
        } else if (id == R.id.nav_gallery) {
            replaceFragment(FragmentExample2.class.getSimpleName());
        } else if (id == R.id.nav_slideshow) {
            replaceFragment(FragmentExample1.class.getSimpleName());
        } else if (id == R.id.nav_manage) {
            replaceFragment(FragmentExample2.class.getSimpleName());
        } else if (id == R.id.nav_share) {
            replaceFragment(FragmentExample1.class.getSimpleName());
        } else if (id == R.id.nav_send) {
            replaceFragment(FragmentExample2.class.getSimpleName());
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    protected void updateDrawerToggle() {
        if (mDrawerToggle == null) {
            return;
        }
        boolean isRoot = getSupportFragmentManager().getBackStackEntryCount() == 0;
        mDrawerToggle.setDrawerIndicatorEnabled(isRoot);
        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayShowHomeEnabled(!isRoot);
            getSupportActionBar().setDisplayHomeAsUpEnabled(!isRoot);
            getSupportActionBar().setHomeButtonEnabled(!isRoot);
        }
        if (isRoot) {
            mDrawerToggle.syncState();
        }
    }

    // This method is specified as an onClick handler in the menu xml and will
    // take precedence over the Activity's onOptionsItemSelected method.
    // See res/menu/actions.xml for more info.
    public void onSort(MenuItem item) {
        mSortMode = item.getItemId();
        // Request a call to onPrepareOptionsMenu so we can change the sort icon
        invalidateOptionsMenu();
    }

    public void replaceFragment(String fragmentName){
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();

        Fragment fragment = null;
        if(fragmentName.equals(FragmentExample1.class.getSimpleName())){
            fragment = FragmentExample1.newInstance(FragmentExample1.class.getName(),"");
        }else if(fragmentName.equals(FragmentExample2.class.getSimpleName())){
            fragment = FragmentExample2.newInstance(FragmentExample2.class.getName(),"");
        }

        //백스택 제일위에 있는 프레그먼트를 없앤다.
        //fragmentManager.popBackStack();
        //백스택에 특정 이름으로 등록된 프레그먼트를 없앤다.
        //fragmentManager.popBackStack(FragmentExample2.class.getSimpleName(), FragmentManager.POP_BACK_STACK_INCLUSIVE);
        //백스택에 있는 모든 프레그먼트를 없앤다.
        fragmentManager.popBackStackImmediate(null, FragmentManager.POP_BACK_STACK_INCLUSIVE);

        fragmentTransaction.setCustomAnimations(R.anim.slide_out_left, R.anim.slide_in_right, R.anim.slide_in_up, R.anim.slide_out_down);

        fragmentTransaction.replace(R.id.fragment_container, fragment);
        fragmentTransaction.addToBackStack(null);
        fragmentTransaction.commit();
    }

    //백스택에 있는 모든 프레그먼트를 없앤다.
    private void clearBackStack(){
        for(int i=0; i<getSupportFragmentManager().getBackStackEntryCount(); i++){
            getSupportFragmentManager().popBackStack();
        }
    }

    @Override
    public void onFragmentInteraction(Uri uri) {

    }
}
