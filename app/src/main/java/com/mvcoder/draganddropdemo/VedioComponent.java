package com.mvcoder.draganddropdemo;

import android.content.ClipData;
import android.content.Context;
import android.content.Intent;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Point;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.support.annotation.AttrRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.DragEvent;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import static android.R.attr.start;

/**
 * Created by 爱的LUICKY on 2017/8/6.
 */

public class VedioComponent extends FrameLayout implements View.OnDragListener {

    private ImageView iv;
    private ImageView snapshot;
    private TextView tv;
    private float lastX;
    private float lastY;
    private float currentX;
    private float currentY;
    private float initX;
    private float initY;
    private boolean isDrag = false;
    private String label="";
    private boolean interuptScroll = false;

    //触摸点移动的阈值，超过它就开启拖拉事件
    private final int TOUCH_RAGNE = 8;
    //触摸点移动的速度阈值，超过它就开启拖拉事件
    private final int TOUCH_VELOCITY = 20;

    public VedioComponent(@NonNull Context context) {
        super(context);
    }

    public VedioComponent(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        initView(context);
    }

    public VedioComponent(@NonNull Context context, @Nullable AttributeSet attrs, @AttrRes int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    private void initView(Context context){
        LayoutInflater.from(context).inflate(R.layout.view_video,this);
        iv = (ImageView) findViewById(R.id.iv);
        snapshot = (ImageView) findViewById(R.id.snapshot);
        tv = (TextView) findViewById(R.id.tv);
        setOnDragListener(this);
    }

    public void setLabel(String label){
        this.label = label;
        if(label.equals("in")){
            interuptScroll = true;
            tv.setText("in设备");
        }else{
            tv.setText("out设备");
        }
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        if(interuptScroll && !isDrag) {
            int action = event.getAction();
            currentX = event.getX();
            currentY = event.getY();
            switch (action) {
                case MotionEvent.ACTION_DOWN:
                    initX = currentX;
                    initY = currentY;
                    break;
                case MotionEvent.ACTION_MOVE:
                    double aX = Math.pow(Math.abs(currentX - initX),2);
                    double aY = Math.pow(Math.abs(currentY - initY),2);
                    if(Math.sqrt(aX+aY) > TOUCH_RAGNE){
                        isDrag = true;
                        startDrag(currentX,currentY);
                        initX = 0;
                        initY = 0;
                        lastX = 0;
                        lastY = 0;
                        currentX = 0;
                        currentY = 0;
                        return true;
                    }
                    break;
            }
            lastX = currentX;
            lastY = currentY;
            return true;
        }
        return super.onTouchEvent(event);
    }

    private void startDrag(float x,float y) {
        Uri uri = Uri.parse("http://www.baidu.com");
        ClipData data = ClipData.newRawUri("out",uri);
        startDrag(data,new MyShadowBuilder(snapshot),null,0);
    }

    class MyShadowBuilder extends DragShadowBuilder {

        private Drawable mShadow;

        public MyShadowBuilder(View view){
            super(view);
            mShadow = new ColorDrawable(Color.BLUE);
        }

        @Override
        public void onProvideShadowMetrics(Point outShadowSize, Point outShadowTouchPoint) {
            int width,height;
            width = getView().getWidth();
            height = getView().getHeight();
            mShadow.setBounds(0,0,width,height);
            outShadowSize.set(width,height);
            outShadowTouchPoint.set(width/2,height/2);
            //super.onProvideShadowMetrics(outShadowSize, outShadowTouchPoint);
        }

        @Override
        public void onDrawShadow(Canvas canvas) {
            mShadow.draw(canvas);
           // super.onDrawShadow(canvas);
        }
    }

    @Override
    public boolean onDrag(View v, DragEvent event) {
        int action =  event.getAction();
        switch (action){
            case DragEvent.ACTION_DRAG_STARTED:
                if(event.getClipDescription().getLabel().equals(label)){
                    snapshot.setImageDrawable(new ColorDrawable(Color.argb(50,0,255,0)));
                    return true;
                }
                return false;
            case DragEvent.ACTION_DRAG_ENTERED:
                snapshot.setImageDrawable(new ColorDrawable(Color.argb(50,255,0,255)));
                break;
            case DragEvent.ACTION_DRAG_LOCATION:
                break;
            case DragEvent.ACTION_DRAG_EXITED:
                snapshot.setImageDrawable(new ColorDrawable(Color.argb(50,0,255,0)));
                break;
            case DragEvent.ACTION_DROP:
                ClipData data = event.getClipData();
                ClipData.Item item = data.getItemAt(0);
                Toast.makeText(getContext(),item.getUri().toString(),Toast.LENGTH_LONG).show();
                Intent intent = new Intent(Intent.ACTION_VIEW);
                intent.setData(item.getUri());
                getContext().startActivity(intent);
                return true;
            case DragEvent.ACTION_DRAG_ENDED:
                isDrag = false;
                snapshot.setImageDrawable(new ColorDrawable(Color.argb(0,0,0,0)));
                break;
        }
        return true;
    }
}
