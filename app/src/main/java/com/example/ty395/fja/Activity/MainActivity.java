package com.example.ty395.fja.Activity;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.design.widget.TabLayout;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Toast;

import com.example.ty395.fja.Fragment.Fragment1;
import com.example.ty395.fja.Fragment.Fragment2;
import com.example.ty395.fja.Fragment.Fragment3;
import com.example.ty395.fja.Fragment.Fragment4;
import com.example.ty395.fja.Item.BackPressCloseHandler;
import com.example.ty395.fja.R;

public class MainActivity extends AppCompatActivity implements View.OnClickListener, NavigationView.OnNavigationItemSelectedListener {

    Toolbar toolbar;
    Fragment1 fragment1;
    Fragment2 fragment2;
    Fragment3 fragment3;
    Fragment4 fragment4;
    private Animation fab_open, fab_close;
    private Boolean isFabOpen = false;
    private FloatingActionButton fab, fab1,fab2;
    BackPressCloseHandler backPressCloseHandler;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        //액션바 기본 타이틀 보여지지 않게
        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayShowTitleEnabled(false);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeAsUpIndicator(R.drawable.ic_baseline_menu_24px);
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
        backPressCloseHandler = new BackPressCloseHandler(this);
        //Fragment : 탭 클릭시 보여줄 화면들
        fragment1 = new Fragment1();
        fragment2 = new Fragment2();
        fragment3 = new Fragment3();
        fragment4 = new Fragment4();
        fab_open = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.fab_open);
        fab_close = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.fab_close);
        fab = (FloatingActionButton) findViewById(R.id.fab);
        fab1 = (FloatingActionButton) findViewById(R.id.fab1);
        fab2=findViewById(R.id.fab2);
        fab1.setOnClickListener(this);
        fab.setOnClickListener(this);
        fab2.setOnClickListener(this);
        getSupportFragmentManager().beginTransaction().replace(R.id.container, fragment1).commit();
        //기본으로 첫번째 Fragment를 보여지도록 설정


        //TabLayout에 Tab 3개 추가
        final TabLayout tabs = (TabLayout) findViewById(R.id.tabs);
        tabs.addTab(tabs.newTab().setText("평가"));
        tabs.addTab(tabs.newTab().setText("토론"));
        tabs.addTab(tabs.newTab().setText("패셔니스타"));
        tabs.addTab(tabs.newTab().setText("마이페이지"));

        //탭 선택리스너
        tabs.setOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            //탭선택시
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                int position = tab.getPosition();

                Fragment selected = null;
                if (position == 0) {
                    selected = fragment1;
                } else if (position == 1) {
                    selected = fragment2;
                } else if (position == 2) {
                    selected = fragment3;
                } else if (position == 3) {
                    selected = fragment4;
                }
                getSupportFragmentManager().beginTransaction().replace(R.id.container, selected).commit();
            }

            //탭선택해제시
            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            //선탭된탭을 다시 클릭시
            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });
    }


    @Override
    public void onClick(View v) {
        int id = v.getId();
        switch (id) {
            case R.id.fab:
                anim();
                break;
            case R.id.fab1:
                anim();
                Toast.makeText(getApplicationContext(), "평가 글쓰기 화면으로 이동합니다", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(getApplicationContext(), WriteActivity.class);
                startActivity(intent);
                finish();
                break;
            case R.id.fab2:
                anim();
                Toast.makeText(getApplicationContext(), "토론 글쓰기 화면으로 이동합니다", Toast.LENGTH_SHORT).show();
                Intent intent2 = new Intent(getApplicationContext(), WriteActivity.class);
                startActivity(intent2);
                finish();
                break;
        }
    }

    public void anim() {
        if (isFabOpen) {
            fab1.startAnimation(fab_close);
            fab1.setClickable(false);
            fab2.setAnimation(fab_close);
            fab2.setClickable(false);
            isFabOpen = false;
        } else {
            fab1.startAnimation(fab_open);
            fab1.setClickable(true);
            fab2.setAnimation(fab_open);
            fab2.setClickable(true);
            isFabOpen = true;
        }
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            backPressCloseHandler.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.side_menu, menu);
        return true;
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.nav_weather) {
            SettingDialog settingDialog = new SettingDialog(MainActivity.this);
            settingDialog.Date();
        }
        if (id == R.id.nav_write){
            Intent intent=new Intent(MainActivity.this,WriteMenuActivity.class);
            startActivity(intent);
        }
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

}