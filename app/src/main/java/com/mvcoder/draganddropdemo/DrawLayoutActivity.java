package com.mvcoder.draganddropdemo;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

/**
 * Created by 爱的LUICKY on 2017/8/7.
 */

public class DrawLayoutActivity extends AppCompatActivity {

    private DrawerLayout drawerLayout;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_drawerlayout);
        drawerLayout = (DrawerLayout) findViewById(R.id.drawlayout);
        initToolBar();
    }

    private void initToolBar() {
        TYToolBar toolbar = (TYToolBar) findViewById(R.id.tyToolBar);

    }
}
