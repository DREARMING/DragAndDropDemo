package com.mvcoder.draganddropdemo;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class MainActivity extends AppCompatActivity {

    private VedioComponent in;
    private VedioComponent out;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        in = (VedioComponent) findViewById(R.id.in1);
        in.setLabel("in");
        out = (VedioComponent) findViewById(R.id.out1);
        out.setLabel("out");
    }
}
