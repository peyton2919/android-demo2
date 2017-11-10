package cn.peyton.android.demo.json.activity;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.util.ArrayList;
import java.util.List;

import cn.peyton.android.demo.R;
import cn.peyton.android.demo.json.bean.ShopInfo;

/**
 * <span style="color:red;font: 16px/1.5 Tahoma,Helvetica,Arial,'宋体',sans-serif;">
 *     Gson 转换
 * </span>
 * <pre>
 * 作者: <a href="http://www.peyton.cn">peyton</a>
 * 邮箱: <a href="mailto:fz2919@tom.com">fz2919@tom.com</a>
 * 创建时间: 2017-11-10 15:16
 * </pre>
 */
public class GsonActivity extends Activity implements View.OnClickListener{

    private TextView mTitle;
    private TextView mOriginal;
    private TextView mChange;
    private Button mToJavaObject;
    private Button mToJavaList;
    private Button mToJsonObject;
    private Button mToJsonList;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gson);
        //初始化数据
        initView();
        //设置监听
        mToJavaObject.setOnClickListener(this);
        mToJavaList.setOnClickListener(this);
        mToJsonObject.setOnClickListener(this);
        mToJsonList.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case  R.id.btn_gson_tojavaobject: //json转java
                jsonToJavaObjectByGson();
                break;
            case  R.id.btn_gson_tojavalist:
                jsonToJavaListByGson();
                break;
            case  R.id.btn_gson_tojsonobject:
                javaToJsonObjectByGson();
                break;
            case  R.id.btn_gson_tojsonlist:
                javaToJsonListByGson();
                break;
        }
    }

    /**
     * java集合转为json数组
     */
    private void javaToJsonListByGson() {
        //java数据
        List<ShopInfo> shopInfos = new ArrayList<>();
        shopInfos.add(new ShopInfo(56,"baidu","http://baidu.com/shio.png",99.99));
        shopInfos.add(new ShopInfo(69,"google","http://google.com/gole.png",88.88));
        shopInfos.add(new ShopInfo(56,"sina","http://sina.com/soa.png",77.77));
        //解析java 数据
        Gson gson = new Gson();
        String json = gson.toJson(shopInfos);
        //展示数据
        mOriginal.setText(shopInfos.toString());
        mChange.setText(json);
    }

    /**
     * java对象转json对象
     */
    private void javaToJsonObjectByGson() {
        //java数据
        ShopInfo judy = new ShopInfo(25, "judy", "http://baidu.com/adaf.png", 45.6);
        //解析java 数据
        Gson gson = new Gson();
        String s = gson.toJson(judy);
        //展示数据
        mOriginal.setText(judy.toString());
        mChange.setText(s);
    }

    /**
     * json数组转java集合
     */
    private void jsonToJavaListByGson() {
        //json数据
        String json = "[{\"id\":2,\"imagePath\":\"test.jpg\",\"name\":\"大虾\",\"price\":12.3}," +
                "{\"id\":5,\"imagePath\":\"javae.jpg\",\"name\":\"java大虾\",\"price\":99.5}]";
        //解析json数据
        Gson gson = new Gson();
        Object obj = gson.fromJson(json, new TypeToken<List<ShopInfo>>() {}.getType());
        //展示数据
        mOriginal.setText(json);
        mChange.setText(obj.toString());
    }

    /**
     * json对象 转java对象
     */
    private void jsonToJavaObjectByGson() {
        //json数据
        String json = "{\"id\":2,\"imagePath\":\"test.jpg\",\"name\":\"大虾\",\"price\":12.3}";
        //解析json数据
        Gson gson = new Gson();
        ShopInfo shopInfo = gson.fromJson(json, ShopInfo.class);
        //展示数据
        mOriginal.setText(json);
        mChange.setText(shopInfo.toString());
    }

    /**
     * 初始化控件
     */
    private void initView() {
        mTitle = (TextView) findViewById(R.id.tv_title);
        mTitle.setText("Gson解析");
        mOriginal = (TextView) findViewById(R.id.tv_gson_original);
        mChange = (TextView) findViewById(R.id.tv_gson_changedata);
        mToJavaObject = (Button) findViewById(R.id.btn_gson_tojavaobject);
        mToJavaList = (Button) findViewById(R.id.btn_gson_tojavalist);
        mToJsonObject = (Button) findViewById(R.id.btn_gson_tojsonobject);
        mToJsonList = (Button) findViewById(R.id.btn_gson_tojsonlist);
    }
}
