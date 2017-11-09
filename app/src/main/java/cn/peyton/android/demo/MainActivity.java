package cn.peyton.android.demo;

import android.os.Bundle;
import android.support.annotation.IdRes;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.widget.RadioGroup;

import java.util.ArrayList;
import java.util.List;

import cn.peyton.android.demo.base.BaseFragment;
import cn.peyton.android.demo.fragment.CommonFrameFragment;
import cn.peyton.android.demo.fragment.CustomFragment;
import cn.peyton.android.demo.fragment.OtherFragment;
import cn.peyton.android.demo.fragment.ThirdPartyFragment;

/**
 * <pre>
 * </pre>
 * 作者: peyton
 * 邮箱: <a href="mailto:fz2919@tom.com">fz2919@tom.com</a>
 * 创建时间: 2017-11-09
 */
public class MainActivity  extends FragmentActivity{

    private RadioGroup mRg_main;
    private List<BaseFragment> mBaseFragment;
    /** 选中Fragment位置 */
    private int position;
    /** 上次切换的Fragment*/
    private Fragment mContent;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //初始化view
        initView();
        //初始化Fragment
        initFragment();
        //设置RadioGroup监听
        setListener();
    }

    /**
     *
     */
    private void setListener() {
        mRg_main.setOnCheckedChangeListener(new MyOnCheckedChangeListener());
        //设置默认选中常用框架
        mRg_main.check(R.id.rb_common_frame);
    }

    private void initFragment() {
        mBaseFragment = new ArrayList<>();
        mBaseFragment.add(new CommonFrameFragment()); //常用框架
        mBaseFragment.add(new ThirdPartyFragment());//第三方框架
        mBaseFragment.add(new CustomFragment());//自定义框架
        mBaseFragment.add(new OtherFragment());//其它框架
    }

    private void initView() {
        setContentView(R.layout.activity_main);
        mRg_main = (RadioGroup) findViewById(R.id.rg_main);

    }

    /**
     * 内部 RadioGroup监听
     */
    class MyOnCheckedChangeListener implements RadioGroup.OnCheckedChangeListener{

        @Override
        public void onCheckedChanged(RadioGroup group, @IdRes int checkedId) {
            switch (checkedId){
                case  R.id.rb_common_frame:
                    position = 0;
                    break;
                case  R.id.rb_thirdparty:
                    position = 1;
                    break;
                case  R.id.rb_custom:
                    position = 2;
                    break;
                case  R.id.rb_other:
                    position = 3;
                    break;
                default:
                    position = 0;
                    break;
            }
            //根据位置得到相应的Fragment
            BaseFragment to = getFragment();
            //替换
            switchFragment(mContent,to);
        }
    }

    /**
     *
     * @param from 刚显示的Fragment,就要被隐藏了
     * @param to 马上要切换到的Fragment,就要被显示
     */
    private void switchFragment(Fragment from,Fragment to) {
        if (from != to){
            mContent = to;
            FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
            //才切换
            //判断有没有被添加
            if (!to.isAdded()){ //没有被添加
                if (null != from){
                    ft.hide(from);
                }
                if (null != to){
                    ft.add(R.id.fl_content,to).commit();
                }

            }else { //to已经被添加
                if (null != from){
                    ft.hide(from);
                }
                if (null != to){
                    ft.show(to).commit();
                }

            }

        }

    }

//    private void switchFragment(BaseFragment fragment) {
//        //得到FragmentManager
//        FragmentManager fm = getSupportFragmentManager();
//        //开启事务
//        FragmentTransaction transaction = fm.beginTransaction();
//        //替换
//        transaction.replace(R.id.fl_content,fragment);
//        //提交事务
//        transaction.commit();
//    }

    private BaseFragment getFragment() {
        return  mBaseFragment.get(position);
    }

}
