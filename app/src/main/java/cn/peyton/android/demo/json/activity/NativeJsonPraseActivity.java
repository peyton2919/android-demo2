package cn.peyton.android.demo.json.activity;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import cn.peyton.android.demo.R;
import cn.peyton.android.demo.json.bean.DataInfo;
import cn.peyton.android.demo.json.bean.FilmInfo;
import cn.peyton.android.demo.json.bean.ShopCategory;

/**
 * <span style="color:red;font: 16px/1.5 Tahoma,Helvetica,Arial,'宋体',sans-serif;">
 * </span>
 * <pre>
 * 作者: <a href="http://www.peyton.cn">peyton</a>
 * 邮箱: <a href="mailto:fz2919@tom.com">fz2919@tom.com</a>
 * 创建时间: 2017-11-10 11:59
 * </pre>
 */

public class NativeJsonPraseActivity extends Activity implements View.OnClickListener{

    private TextView mTitle;
    private TextView mOriginal;
    private TextView mChange;
    private Button mToJavaObject;
    private Button mToJavaList;
    private Button mComplex;
    private Button mSpecial;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_native_json_prase);
        //初始化数据
        initView();
        //初始化监听
        initListener();

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btn_native_tojavaobject://json对象转java对象
                jsonToJavaobjectByNative();
                break;
            case R.id.btn_native_tojavalist://json对象转java集合
                jsonToJavaListByNative();
                break;
            case R.id.btn_native_complex://复杂json解析
                jsonToJavaOfComplex();
                break;
            case R.id.btn_native_special://特殊json解析
                jsonToJavaOfSpecial();
                break;
        }
    }

    /**
     * 特殊json解析
     */
    private void jsonToJavaOfSpecial() {
        String json ="{\n" +
                "\t\"code\":0,\n" +
                "\t\"list\":{\n" +
                "\t\t\"0\":{\n" +
                "\t\t\t\"aid\":\"6008965\",\n" +
                "\t\t\t\"author\":\"五福临门\",\n" +
                "\t\t\t\"coins\":111,\n" +
                "\t\t\t\"copyright\":\"Copy\",\n" +
                "\t\t\t\"create\":\"2017-10-01 20:20\"\n" +
                "\t\t},\n" +
                "\t\t\"1\":{\n" +
                "\t\t\t\"aid\":\"7008888\",\n" +
                "\t\t\t\"author\":\"东方一溅\",\n" +
                "\t\t\t\"coins\":999,\n" +
                "\t\t\t\"copyright\":\"Copy\",\n" +
                "\t\t\t\"create\":\"2015-01-01 11:11\"\n" +
                "\t\t}\n" +
                "\t}\n" +
                "}";
        FilmInfo filmInfo = new FilmInfo();
        try {
            JSONObject jsonObject = new JSONObject(json);
            int code = jsonObject.optInt("code");
            JSONObject list = jsonObject.optJSONObject("list");
            for (int i=0 ;i < list.length(); i++){
                JSONObject jsonObject1 = list.optJSONObject(i + "");
                if (null != jsonObject1){
                    String aid = jsonObject1.optString("aid");
                    String author = jsonObject1.optString("author");
                    int coins = jsonObject1.optInt("coins");
                    String copyright = jsonObject1.optString("copyright");
                    String create = jsonObject1.optString("create");

                    filmInfo.getList().add(new FilmInfo.FilmBean(aid,author,coins,copyright,create));
                }
            }


        } catch (JSONException e) {
            e.printStackTrace();
        }
        mOriginal.setText(json);
        mChange.setText(filmInfo.toString());
    }

    /**
     * 复杂json解析
     */
    private void jsonToJavaOfComplex() {
        String json ="{ \"rs_code\":\"1000\",\"data\":{\"count\":5," +
                "\"items\":[{\"id\":45,\"title\":\"\\u575a\\u679c\"}," +
                "{\"id\":132,\"title\":\"\\u7092\\u8d27\"},{\"id\":166,\"title\":\"\\u871c\\u996f\"}," +
                "{\"id\":195,\"title\":\"\\u679c\\u812f\"}]},\"rs_msg\":\"success\"}";
        //解析json数据
        //复杂json逐层解析
        //
        DataInfo dataInfo = new DataInfo();
        try {
            JSONObject jsonObject = new JSONObject(json);
            //第一层解析
            JSONObject data = jsonObject.optJSONObject("data");
            String rs_code = jsonObject.optString("rs_code");
            String rs_msg = jsonObject.optString("rs_msg");
            dataInfo.setRs_code(rs_code);
            dataInfo.setRs_msg(rs_msg);
            DataInfo.DataBean dataBean = new DataInfo.DataBean();
            dataInfo.setData(dataBean);

            //第二层解析
            int count = data.optInt("count");
            JSONArray items = data.getJSONArray("items");
            dataBean.setCount(count);

            List<DataInfo.DataBean.ItemsBean> itemsBean = new ArrayList<>();
            dataBean.setItems(itemsBean);
            //第三层解析 
            for (int i=0;i<items.length();i++){
                JSONObject jsonObject1 = items.optJSONObject(i);
                if (null != jsonObject1){
                    int id = jsonObject1.optInt("id");
                    String title = jsonObject1.optString("title");
                    DataInfo.DataBean.ItemsBean bean = new DataInfo.DataBean.ItemsBean();
                    bean.setId(id);
                    bean.setTitle(title);
                    itemsBean.add(bean);
                }
            }

        } catch (JSONException e) {
            e.printStackTrace();
        }
        mOriginal.setText("");
        mChange.setText("");
        mOriginal.setText(json);
        mChange.setText(dataInfo.toString());
    }

    /**
     * json格式字符串[] 解析java对象集合
     */
    private void jsonToJavaListByNative() {
        String json ="[{\n" +
                "    \"shopCategoryId\": 4,\n" +
                "    \"shopCategoryName\": \"休闲娱乐\",\n" +
                "    \"shopCategoryDesc\": \"休闲娱乐\",\n" +
                "    \"shopCategoryImg\": \"/upload/images/item/shopcategory/111.png\",\n" +
                "    \"priority\": 101\n" +
                "},{" +
            "    \"shopCategoryId\": 5,\n" +
                    "    \"shopCategoryName\": \"特价产品\",\n" +
                    "    \"shopCategoryDesc\": \"特价产品\",\n" +
                    "    \"shopCategoryImg\": \"/upload/images/item/shopcategory/551.png\",\n" +
                    "    \"priority\": 991\n" +
                    "}]";

        List<ShopCategory> shopCategories = new ArrayList<>();
        try {
            JSONArray jsonArray = new JSONArray(json);
            for (int i=0;i<jsonArray.length();i++){
                JSONObject jsonObject = jsonArray.getJSONObject(i);
                if (null != jsonObject){
                    int shopCategoryId = jsonObject.getInt("shopCategoryId");
                    String shopCategoryName = jsonObject.optString("shopCategoryName");
                    String shopCategoryDesc = jsonObject.optString("shopCategoryDesc");
                    String shopCategoryImg = jsonObject.optString("shopCategoryImg");
                    int priority = jsonObject.optInt("priority");
                    shopCategories.add(new ShopCategory(shopCategoryId,shopCategoryName,shopCategoryDesc,shopCategoryImg,priority));
                }
            }

        } catch (JSONException e) {
            e.printStackTrace();
        }

        mOriginal.setText("");
        mChange.setText("");
        mOriginal.setText(json);
        mChange.setText(shopCategories.toString());
    }

    /**
     * json解析成java
     */
    private void jsonToJavaobjectByNative() {
        String json ="{\n" +
                "    \"shopCategoryId\": 4,\n" +
                "    \"shopCategoryName\": \"休闲娱乐\",\n" +
                "    \"shopCategoryDesc\": \"休闲娱乐\",\n" +
                "    \"shopCategoryImg\": \"/upload/images/item/shopcategory/111.png\",\n" +
                "    \"priority\": 101\n" +
                "}";
        //解析json
        ShopCategory shopCategory = null;
        try {
            JSONObject jsonObject = new JSONObject(json);
            int shopCategoryId = jsonObject.getInt("shopCategoryId");
            String shopCategoryName = jsonObject.optString("shopCategoryName");
            String shopCategoryDesc = jsonObject.optString("shopCategoryDesc");
            String shopCategoryImg = jsonObject.optString("shopCategoryImg");
            int priority = jsonObject.optInt("priority");
            shopCategory = new ShopCategory(shopCategoryId,shopCategoryName,shopCategoryDesc,shopCategoryImg,priority);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        //显示json数据
        mOriginal.setText("");
        mChange.setText("");
        mOriginal.setText(json);
        mChange.setText(shopCategory.toString());
    }

    private void initListener() {
        mToJavaObject.setOnClickListener(this);
        mToJavaList.setOnClickListener(this);
        mComplex.setOnClickListener(this);
        mSpecial.setOnClickListener(this);
    }


    private void initView() {
        mTitle = (TextView) findViewById(R.id.tv_title);
        mTitle.setText("手动Json解析");
        mOriginal = (TextView) findViewById(R.id.tv_native_original);
        mChange = (TextView) findViewById(R.id.tv_native_changedata);
        mToJavaObject = (Button) findViewById(R.id.btn_native_tojavaobject);
        mToJavaList = (Button) findViewById(R.id.btn_native_tojavalist);
        mComplex = (Button) findViewById(R.id.btn_native_complex);
        mSpecial = (Button) findViewById(R.id.btn_native_special);
    }
}
