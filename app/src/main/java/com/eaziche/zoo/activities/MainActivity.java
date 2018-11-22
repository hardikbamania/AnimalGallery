package com.eaziche.zoo.activities;

import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;

import com.eaziche.zoo.R;
import com.eaziche.zoo.fragments.ExibitFragment;
import com.eaziche.zoo.fragments.GalleryFragment;
import com.eaziche.zoo.fragments.ZooMapFragment;

import static com.eaziche.zoo.fragments.GalleryFragment.MY_FRAGMENT_TAG;


public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener ,FragmentManager.OnBackStackChangedListener {

    Toolbar toolbar;
    DrawerLayout drawer;
    ActionBarDrawerToggle toggle;
    NavigationView navigationView;
    String mCurrentFragment =null;
    FragmentTransaction transaction;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initializeToolBarAndDrawer();
        initialFragment();
        getSupportFragmentManager().addOnBackStackChangedListener(this);
    }

    private void initialFragment() {
        transaction = getSupportFragmentManager().beginTransaction();
        transaction.add(R.id.content_main, ExibitFragment.newInstance());
        transaction.addToBackStack(null);
        transaction.commit();
        mCurrentFragment = "EXIBIT";
    }

    private void initializeToolBarAndDrawer() {
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);


        drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);


    }

    @Override
    public void onBackPressed() {

        drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }

        if (getSupportFragmentManager().getBackStackEntryCount() > 0) {
            Log.i("MainActivity", "popping backstack");
            getSupportFragmentManager().popBackStack();
        } else {
            this.finish();
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

        if (id == R.id.nav_exibit && !mCurrentFragment.equalsIgnoreCase("EXIBIT")) {
            transaction = getSupportFragmentManager().beginTransaction();
            transaction.replace(R.id.content_main, ExibitFragment.newInstance());
            transaction.addToBackStack(null);
            transaction.commit();
            mCurrentFragment = "EXIBIT";
        } else if (id == R.id.nav_gallery) {
            transaction = getSupportFragmentManager().beginTransaction();
            transaction.replace(R.id.content_main, GalleryFragment.newInstance(),MY_FRAGMENT_TAG);
            transaction.addToBackStack(null);
            transaction.commit();
            mCurrentFragment = "GALLARY";
        } else if (id == R.id.nav_map) {
            transaction = getSupportFragmentManager().beginTransaction();
            transaction.replace(R.id.content_main, ZooMapFragment.getInstance());
            transaction.addToBackStack(null);
            transaction.commit();
            mCurrentFragment = "Map";
        }

        drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    @Override
    public void onBackStackChanged() {
        getFragmentManager().popBackStackImmediate();
    }
}
