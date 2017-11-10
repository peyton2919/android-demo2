package cn.peyton.android.demo.json.activity;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.alibaba.fastjson.JSON;

import java.util.ArrayList;
import java.util.List;

import cn.peyton.android.demo.R;
import cn.peyton.android.demo.json.bean.ShopInfo;

/**
 * <span style="color:red;font: 16px/1.5 Tahoma,Helvetica,Arial,'宋体',sans-serif;">
 *     fastjson 转换
 * </span>
 * <pre>
 * 作者: <a href="http://www.peyton.cn">peyton</a>
 * 邮箱: <a href="mailto:fz2919@tom.com">fz2919@tom.com</a>
 * 创建时间: 2017-11-10 18:39
 * </pre>
 */
public class FastJsonActivity extends Activity implements View.OnClickListener{

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
        setContentView(R.layout.activity_fastjson);
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
            case R.id.btn_fastjson_tojavaobject:
                jsonToJavaObjectByFastJson();
                break;
            case R.id.btn_fastjson_tojavalist:
                jsonToJavaListByFastJson();
                break;
            case R.id.btn_fastjson_tojsonobject:
                javaToJsonObjectByFastJson();
                break;
            case R.id.btn_fastjson_tojsonlist:
                javaToJsonListByFastJson();
                break;
        }
    }

    /**
     * java 集合点转 json 字符串
     */
    private void javaToJsonListByFastJson() {
        //java数据
        List<ShopInfo> shopInfos = new ArrayList<>();
        shopInfos.add(new ShopInfo(56,"baidu","http://baidu.com/shio.png",99.99));
        shopInfos.add(new ShopInfo(69,"google","http://google.com/gole.png",88.88));
        shopInfos.add(new ShopInfo(56,"sina","http://sina.com/soa.png",77.77));
        //解析java 数据
        String json = JSON.toJSONString(shopInfos);
        //展示数据
        mOriginal.setText(shopInfos.toString());
        mChange.setText(json);
    }
    /**
     * java 对象转 json 字符串
     */
    private void javaToJsonObjectByFastJson() {
        //java数据
        ShopInfo shopInfo = new ShopInfo(25, "judy", "http://baidu.com/adaf.png", 45.6);
        //解析java 数据
        String json = JSON.toJSONString(shopInfo);
        //展示数据
        mOriginal.setText(shopInfo.toString());
        mChange.setText(json);
    }
    /**
     * json 转 java点集合
     */
    private void jsonToJavaListByFastJson() {
        //json数据
        String json = "[{\"id\":2,\"imagePath\":\"test.jpg\",\"name\":\"大虾\",\"price\":12.3}," +
                "{\"id\":5,\"imagePath\":\"javae.jpg\",\"name\":\"java大虾\",\"price\":99.5}]";
        //解析json数据
        List<ShopInfo> shopInfos = JSON.parseArray(json, ShopInfo.class);
        //展示数据
        mOriginal.setText(json);
        mChange.setText(shopInfos.toString());
    }
    /**
     * json转java 对象
     */
    private void jsonToJavaObjectByFastJson() {
        //json数据
        String json = "{\"id\":2,\"imagePath\":\"test.jpg\",\"name\":\"大虾\",\"price\":12.3}";
        //解析json数据
        ShopInfo shopInfo = JSON.parseObject(json, ShopInfo.class);
        //展示数据
        mOriginal.setText(json);
        mChange.setText(shopInfo.toString());
    }


    /**
     * 初始化控件
     */
    private void initView() {
        mTitle = (TextView) findViewById(R.id.tv_title);
        mTitle.setText("FastJson解析");
        mOriginal = (TextView) findViewById(R.id.tv_fastjson_original);
        mChange = (TextView) findViewById(R.id.tv_fastjson_changedata);
        mToJavaObject = (Button) findViewById(R.id.btn_fastjson_tojavaobject);
        mToJavaList = (Button) findViewById(R.id.btn_fastjson_tojavalist);
        mToJsonObject = (Button) findViewById(R.id.btn_fastjson_tojsonobject);
        mToJsonList = (Button) findViewById(R.id.btn_fastjson_tojsonlist);
    }
}
