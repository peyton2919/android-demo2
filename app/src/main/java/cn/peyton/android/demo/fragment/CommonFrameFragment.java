package cn.peyton.android.demo.fragment;

import android.graphics.Color;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import cn.peyton.android.demo.R;
import cn.peyton.android.demo.adapter.CommonFrameFragmentAdapter;
import cn.peyton.android.demo.base.BaseFragment;

/**
 * <span style="color:red;font: 16px/1.5 Tahoma,Helvetica,Arial,'宋体',sans-serif;">
 *     常用框架Fragment
 * </span>
 * <pre>
 * 作者: <a href="http://www.peyton.cn">peyton</a>
 * 邮箱: <a href="mailto:fz2919@tom.com">fz2919@tom.com</a>
 * 创建时间: 2017-11-09 13:59
 * </pre>
 */

public class CommonFrameFragment extends BaseFragment{

    private static final String TAG = CommonFrameFragment.class.getSimpleName();

    private ListView mListView;

    private  String[] datas;

    private CommonFrameFragmentAdapter adapter ;

    @Override
    protected View intiView() {
        Log.e(TAG,"常用框架Fragment页面初始化...");
       View view = View.inflate(mContext, R.layout.fragment_common_frame,null);
        mListView = (ListView) view.findViewById(R.id.listview);
        //设置点击事件
        mListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String data = datas[position];
                Toast.makeText(mContext,"data == " + data,Toast.LENGTH_LONG).show();
            }
        });
        return view;
    }

    @Override
    protected void initData() {
        super.initData();
        //准备数据
        datas = new String[]{"OKHttp","xUtils3","Retrofit2","Fresco","Glide","greenDao",
                "RxJava","volley","Gson","FastJson","picasso","evenBUs","jcvideoplayer",
                "pulltorefresh","Expandablelistview","UniversalVideoView","......"};
        //设置适配器
        adapter = new CommonFrameFragmentAdapter(mContext,datas);
        mListView.setAdapter(adapter);
    }
}
