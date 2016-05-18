package com.ppandroid.whitefm;

import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import com.antonioleiva.mvpexample.app.R;
import com.ppandroid.whitefm.base.AC_Base;
import com.ppandroid.whitefm.base.AC_ContentFG;
import com.ppandroid.whitefm.home.FG_OkHttpTest;
import com.ppandroid.whitefm.utils.toast.ToastUtil;

import butterknife.Bind;
import butterknife.OnClick;

/**
 * Created by yeqinfu on 16-4-2.
 */
public class AC_Main extends AC_Base    implements NavigationView.OnNavigationItemSelectedListener{
    @Bind(R.id.tv_show)
    TextView tv_show;

    @Override
    public int getActivityLayout() {
        return R.layout.ac_main;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
       // setContentView(R.layout.ac_main);
        setTitle("toolbartitle");
        tv_show.setText("after");
        ToastUtil.toast(AC_Main.this,tv_show.getText()+"");

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

     // startActivity(AC_ContentFG.createIntent(AC_Main.this, FG_MainMusic.class.getName(), "", null));
    }

    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        return false;
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

    @OnClick(R.id.bt_to_fg)
    public void onClick(View v){
        switch (v.getId()){
            case R.id.bt_to_fg:
                startActivity(AC_ContentFG.createIntent(AC_Main.this, FG_OkHttpTest.class.getName(),"okHTTPTEST",null));
                break;
            default:
                break;
        }

    }
}
