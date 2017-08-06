package com.mvcoder.draganddropdemo;

import android.content.Context;
import android.content.DialogInterface;
import android.support.annotation.Nullable;
import android.support.v7.widget.Toolbar;
import android.util.AttributeSet;
import android.view.View;
import android.widget.TextView;

/**
 * Created by 爱的LUICKY on 2017/8/7.
 */

public class TYToolBar extends Toolbar {

    private TextView tyMainTitle;
    private TextView tySubTitle;
    private TextView tyRightText;
    private TextView tyLeftText;
    private OnClickListener righListener;
    private OnClickListener leftListener;

    public TYToolBar(Context context) {
        super(context, null);
    }

    public TYToolBar(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs, 0);
    }

    public TYToolBar(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onFinishInflate() {
        super.onFinishInflate();
        tyMainTitle = (TextView) findViewById(R.id.tyMainTitle);
        tySubTitle = (TextView) findViewById(R.id.tySubtitle);
        tyLeftText = (TextView) findViewById(R.id.tyLeftText);
        tyRightText = (TextView) findViewById(R.id.tyRightText);

        tyLeftText.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                if(leftListener != null){
                    leftListener.onClick(v);
                }
            }
        });

        tyRightText.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                if(righListener != null){
                    righListener.onClick(v);
                }
            }
        });
    }

    public void setTyMainTitle(String mainTitle) {
        tyMainTitle.setText(mainTitle);
    }

    public void setTySubTitle(String subTitle) {
        tySubTitle.setText(subTitle);
        tySubTitle.setText(View.VISIBLE);
    }

    public void setTyRightText(String rightText) {
        tyRightText.setText(rightText);
        tyRightText.setVisibility(View.VISIBLE);
    }

    public void setTyRightListener(OnClickListener listener) {
        this.righListener = listener;
    }

    public void setLeftListener(OnClickListener listener){
        this.leftListener = listener;
    }



}
