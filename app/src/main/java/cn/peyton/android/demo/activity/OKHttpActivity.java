package cn.peyton.android.demo.activity;

import android.app.Activity;
import android.os.Bundle;
import android.os.Environment;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.FileCallBack;
import com.zhy.http.okhttp.callback.StringCallback;

import java.io.File;
import java.io.IOException;

import cn.peyton.android.demo.R;
import okhttp3.Call;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

/**
 * <span style="color:red;font: 16px/1.5 Tahoma,Helvetica,Arial,'宋体',sans-serif;">
 *     OKHttp
 * </span>
 * <pre>
 * 作者: <a href="http://www.peyton.cn">peyton</a>
 * 邮箱: <a href="mailto:fz2919@tom.com">fz2919@tom.com</a>
 * 创建时间: 2017-11-09 16:01
 * </pre>
 */

public class OKHttpActivity extends Activity implements View.OnClickListener{

    private static final String TAG = OKHttpActivity.class.getSimpleName();
    /** get请求 */
    private static final int GET = 1;
    /** post请求 */
    private static final int POST = 2;
    private Button btn_get_post;
    private Button btn_post;
    private Button btn_get_okhttputils;
    private Button btn_downloadfile;

    private ProgressBar mProgressBar;
    private TextView tv_result;

    private OkHttpClient client = new OkHttpClient();
    /** okhttp POST请求*/
    private static final MediaType JSON = MediaType.parse("application/json;charset=utf-8");
    /**
     * 原生的okhttp
     */
    private Handler handler = new Handler(){

        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what){
                case GET:
                //获取数据
                tv_result.setText((String)msg.obj);
                break;
                case  POST:
                tv_result.setText((String)msg.obj);
                break;
            }
        }
    };


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_okhttp);
        btn_get_post = (Button) findViewById(R.id.btn_get_post);
        btn_post = (Button) findViewById(R.id.btn_post);
        tv_result = (TextView) findViewById(R.id.tv_result);
        btn_get_okhttputils = (Button) findViewById(R.id.btn_get_okhttputils);
        btn_downloadfile = (Button) findViewById(R.id.btn_downloadfile);
        mProgressBar = (ProgressBar) findViewById(R.id.pb_progressBar);

        //设置点击事件
        btn_get_post.setOnClickListener(this);
        btn_get_okhttputils.setOnClickListener(this);
        btn_downloadfile.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btn_get_post://使用原生okhttp请求网络数据
                tv_result.setText("");
                getDataFromGet();
                break;
            case R.id.btn_post://使用原生okhttp请求网络数据
                tv_result.setText("");
                getDataFromPost();
                break;
            case  R.id.btn_get_okhttputils:
                tv_result.setText("");
                getDataOKHttpUtils();
                break;
            case R.id.btn_downloadfile:
                downloadFile();
                break;

        }
    }

    /**
     * 使用get网络请求数据
     */
    private void getDataFromGet(){
        new Thread(){
            @Override
            public void run() {
                super.run();
                try {
                    String result = get("http://www.fj167.com/frontend/listmainpageinfo");
                    Log.e(TAG , result);
                    Message msg = Message.obtain();
                    msg.what = GET;
                    msg.obj = result;
                    handler.sendMessage(msg);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }.start();

    }

    private void getDataFromPost(){
        new Thread(){
            @Override
            public void run() {
                super.run();
                try {
                    String result = post("http://www.fj167.com/frontend/listmainpageinfo","");
                    Log.e(TAG , result);
                    Message msg = Message.obtain();
                    msg.what = POST;
                    msg.obj = result;
                    handler.sendMessage(msg);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }.start();

    }

    /**
     *  <p>get请求 </p>
     * @param url 网络地址
     * @return
     * @throws IOException
     */
    private String get(String url) throws IOException{
        Request request = new Request.Builder()
                .url(url)
                .build();
        Response response = client.newCall(request).execute();
        return response.body().string();
    }

    /**
     * <p>POST请求</p>
     * @param url
     * @param json
     * @return
     * @throws IOException
     */
    private String post(String url ,String json) throws IOException{
        RequestBody body = RequestBody.create(JSON,json);
        Request request = new Request.Builder()
                .url(url)
                .post(body)
                .build();
        Response response = client.newCall(request).execute();
        return response.body().string();
    }

    public void getDataOKHttpUtils(){
        String url = "http://www.fj167.com/frontend/listmainpageinfo";
        OkHttpUtils
                .get()
                .url(url)
                .id(100)
                .build()
                .execute(new MyStringCallback());
    }

    /**
     * 内部类okhttputils的get请求,回调方法
     */
    public  class  MyStringCallback extends StringCallback{

        @Override
        public void onError(Call call, Exception e, int id) {
            e.printStackTrace();
            tv_result.setText("onError: " + e.getMessage());
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
            tv_result.setText("onResponse: " + response);
            switch (id){
                case 100:
                    Toast.makeText(OKHttpActivity.this,"http",Toast.LENGTH_SHORT).show();
                    break;
                case 101:
                    Toast.makeText(OKHttpActivity.this,"https",Toast.LENGTH_SHORT).show();
                    break;
            }
        }

        @Override
        public void inProgress(float progress, long total, int id) {
            Log.e(TAG,"inProgress: " + progress);
        }
    }

    /**
     * 使用okhttp-utils下载大文件 https://cdn.mysql.com//Downloads/MySQLInstaller/mysql-installer-community-5.6.38.0.msi
     */
    public void downloadFile()
    {
        String url = "http://sw.bos.baidu.com/sw-search-sp/software/cc9b2e050d0bf/BaiduNetdisk_5.7.1.1.exe";
        OkHttpUtils//
                .get()//
                .url(url)//
                .build()//
                .execute(new FileCallBack(Environment.getExternalStorageDirectory().getAbsolutePath(), "baidunet.exe")//
                {

                    @Override
                    public void onBefore(Request request, int id)
                    {
                    }

                    @Override
                    public void inProgress(float progress, long total, int id)
                    {
                        mProgressBar.setProgress((int) (100 * progress));
                        Log.e(TAG, "inProgress :" + (int) (100 * progress));
                    }

                    @Override
                    public void onError(Call call, Exception e, int id)
                    {
                        Log.e(TAG, "onError :" + e.getMessage());
                    }

                    @Override
                    public void onResponse(File file, int id)
                    {
                        Log.e(TAG, "onResponse :" + file.getAbsolutePath());
                    }
                });
    }
}
