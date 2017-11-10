package cn.peyton.android.demo.okhttp.activity;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;

import cn.peyton.android.demo.R;
import okhttp3.Call;
import okhttp3.Request;

import static android.content.ContentValues.TAG;

/**
 * <span style="color:red;font: 16px/1.5 Tahoma,Helvetica,Arial,'宋体',sans-serif;">
 *     在列表中请求图片
 * </span>
 * <pre>
 * 作者: <a href="http://www.peyton.cn">peyton</a>
 * 邮箱: <a href="mailto:fz2919@tom.com">fz2919@tom.com</a>
 * 创建时间: 2017-11-10 10:38
 * </pre>
 */

public class OKHttpListActivity extends Activity{

    private ListView mListView;
    private ProgressBar mProgressBar;
    private TextView mTextView;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_okhttplist);
        //初始化数据
        initView();
        //获取数据
        getDataFromNet();
    }

    private void getDataFromNet() {

        String url = "http://www.fj167.com/frontend/listmainpageinfo";
        OkHttpUtils
                .post()
                .url(url)
                .id(100)
                .build()
                .execute(new MyStringCallback());
    }

    /**
     * 初始化数据
     */
    private void initView() {
        mListView = (ListView) findViewById(R.id.lv_listview);
        mProgressBar = (ProgressBar) findViewById(R.id.pb_okhpptutils_list);
        mTextView = (TextView) findViewById(R.id.tv_okhttputils_list);
    }

    /**
     * 内部类okhttputils的get请求,回调方法
     */
    public  class  MyStringCallback extends StringCallback {

        @Override
        public void onError(Call call, Exception e, int id) {
            e.printStackTrace();
            mTextView.setText("onError: " + e.getMessage());
        }

        @Override
        public void onBefore(Request request, int id) {
            setTitle("loading...");
        }

        @Override
        public void onAfter(int id) {
            setTitle("Sample-okhttp");
        }

        @Override
        public void onResponse(String response, int id) {
            Log.e(TAG,"onResponse:complete;");
            mTextView.setText("onResponse: " + response);
            switch (id){
                case 100:
                    Toast.makeText(OKHttpListActivity.this,"http",Toast.LENGTH_SHORT).show();
                    break;
                case 101:
                    Toast.makeText(OKHttpListActivity.this,"https",Toast.LENGTH_SHORT).show();
                    break;
            }
        }

        @Override
        public void inProgress(float progress, long total, int id) {
            Log.e(TAG,"inProgress: " + progress);
        }
    }
}
