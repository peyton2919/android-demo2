package cn.peyton.android.demo.fragment;

import android.graphics.Color;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.widget.TextView;

import cn.peyton.android.demo.base.BaseFragment;

/**
 * <span style="color:red;font: 16px/1.5 Tahoma,Helvetica,Arial,'宋体',sans-serif;">
 *     其它框架Fragment
 * </span>
 * <pre>
 * 作者: <a href="http://www.peyton.cn">peyton</a>
 * 邮箱: <a href="mailto:fz2919@tom.com">fz2919@tom.com</a>
 * 创建时间: 2017-11-09 13:59
 * </pre>
 */

public class OtherFragment extends BaseFragment{

    private static final String TAG = OtherFragment.class.getSimpleName();
    private TextView textView;

    @Override
    protected View intiView() {
        Log.e(TAG,"其它框架Fragment页面初始化...");
        textView = new TextView(mContext);
        textView.setTextSize(20);
        textView.setGravity(Gravity.CENTER);
        textView.setTextColor(Color.RED);
        return textView;
    }

    @Override
    protected void initData() {
        super.initData();
        textView.setText("其它框架页面");
    }
}
