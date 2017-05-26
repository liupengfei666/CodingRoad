package com.lewis.codingrode;

import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.NavigationView;
import android.support.design.widget.Snackbar;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import com.lewis.codingrode.activity.BottomNavigationBarActivity;
import com.lewis.codingrode.activity.QRCodeSelectActivity;
import com.lewis.codingrode.activity.RecyclerViews;
import com.lewis.codingrode.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener, View.OnClickListener {

    private ActivityMainBinding mMainBinding;
    private NavigationView mNavigationView;
    private DrawerLayout mDrawerLayout;
    private FloatingActionButton mFab;
    private Button mBottomNavigationBar, mQRCode;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mMainBinding = DataBindingUtil.setContentView(this, R.layout.activity_main);
        initView();
        setListener();
    }

    private void setListener() {
        mNavigationView.setNavigationItemSelectedListener(this);
        mFab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Snackbar.make(v, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
        mBottomNavigationBar.setOnClickListener(this);
        mQRCode.setOnClickListener(this);
        mMainBinding.mainLayout.contentMain.btnRecyclerView.setOnClickListener(this);
    }

    private void initView() {
        Toolbar mToolBar = mMainBinding.mainLayout.toolbar;
        mNavigationView = mMainBinding.navigationView;
        mDrawerLayout = mMainBinding.drawerLayout;
        mFab = mMainBinding.mainLayout.fab;
        mBottomNavigationBar = mMainBinding.mainLayout.contentMain.btnBottomNavigationBar;
        mQRCode = mMainBinding.mainLayout.contentMain.btnQrcode;

        mToolBar.setTitle("示例代码入口");
        setSupportActionBar(mToolBar);
        ActionBarDrawerToggle mDrawerToggle = new ActionBarDrawerToggle(this, mDrawerLayout, mToolBar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        mDrawerLayout.addDrawerListener(mDrawerToggle);
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.nav_camera:

                break;
            case R.id.nav_gallery:

                break;
            case R.id.nav_slideshow:

                break;
            case R.id.nav_manage:

                break;
            case R.id.nav_share:

                break;
            case R.id.nav_send:

                break;
        }
        mDrawerLayout.closeDrawer(GravityCompat.START);
        return true;
    }

    @Override
    public void onBackPressed() {
        if (mDrawerLayout.isDrawerOpen(GravityCompat.START)) {
            mDrawerLayout.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_bottom_navigation_bar:
                startActivity(new Intent(this, BottomNavigationBarActivity.class));
                break;
            case R.id.btn_qrcode:
                startActivity(new Intent(this, QRCodeSelectActivity.class));
                break;
            case R.id.btn_recyclerView:
                startActivity(new Intent(this, RecyclerViews.class));
                break;
        }
    }
}
