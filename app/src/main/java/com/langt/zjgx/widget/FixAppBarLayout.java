package com.langt.zjgx.widget;

import android.content.Context;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.CoordinatorLayout;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.widget.OverScroller;

import java.lang.reflect.Field;

import static android.view.MotionEvent.ACTION_DOWN;

/**
 * Created by Hao on 2019/7/22.
 * Describe解决AppBarLayout滑动时抖动问题
 */
public class FixAppBarLayout extends AppBarLayout.Behavior {

    private static final String TAG = "AppBarLayoutBehavior";

    public FixAppBarLayout() {
        super();
    }

    public FixAppBarLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    public boolean onInterceptTouchEvent(CoordinatorLayout parent, AppBarLayout child, MotionEvent ev) {
        if (ev.getAction() == ACTION_DOWN) {
            Object scroller = getSuperSuperField(this, "mScroller");
            if (scroller != null && scroller instanceof OverScroller) {
                OverScroller overScroller = (OverScroller) scroller;
                overScroller.abortAnimation();
            }
        }

        return super.onInterceptTouchEvent(parent, child, ev);
    }

    private Object getSuperSuperField(Object paramClass, String paramString) {
        Field field = null;
        Object object = null;
        try {
            field = paramClass.getClass().getSuperclass().getSuperclass().getDeclaredField(paramString);
            field.setAccessible(true);
            object = field.get(paramClass);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return object;
    }
}