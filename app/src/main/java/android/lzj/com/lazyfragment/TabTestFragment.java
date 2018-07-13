package android.lzj.com.lazyfragment;

import android.lzj.com.lazyfragment.Base.BaseTablayout;
import android.support.v4.app.Fragment;

/**
 * Created by lenovo on 2018/7/13.
 */

public class TabTestFragment extends BaseTablayout {
    String[] mTitle={"未完成考试", "已完成考试"};
    Fragment[] fragments={new Fragment1(),new Fragment2()};
    @Override
    protected void setData() {
        tabTitle=mTitle;
        mFragement=fragments;
    }
}
