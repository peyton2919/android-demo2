package cn.peyton.android.demo.base;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * <span style="color:red;font: 16px/1.5 Tahoma,Helvetica,Arial,'宋体',sans-serif;">
 *     公共Fragment基类;
 *     CommonFrameFragment,ThirdpartyFragment,CustomFragment,OtherFragment
 *     都继承它
 * </span>
 * <pre>
 * 作者: <a href="http://www.peyton.cn">peyton</a>
 * 邮箱: <a href="mailto:fz2919@tom.com">fz2919@tom.com</a>
 * 创建时间: 2017-11-09 13:33
 * </pre>
 */

public abstract class BaseFragment extends Fragment {
    /**
     * 上下文
     */
    protected Context mContext;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mContext = getActivity();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return intiView();
    }

    /**
     * 强制子类重写,实现子类特有的UI
     * @return
     */
    protected abstract View intiView();

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        initData();
    }

    /**
     * <pre>
     *     当子类需要初始化数据,
     *     或者联网请求绑定数据,
     *     展示数据等可以重写这个方法
     * </pre>
     */
    protected void initData() {

    }
}
