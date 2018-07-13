package android.lzj.com.lazyfragment;

import android.lzj.com.lazyfragment.Base.BaseLazyFragment;
import android.util.Log;

/**
 * Created by lenovo on 2018/7/13.
 */

public class Fragment1 extends BaseLazyFragment {
    private static final String TAG = "Fragment1";
    @Override
    protected void initView() {
        Log.d(TAG, "initView: ");
    }

    @Override
    protected void onFirstUserVisible() {
        Log.d(TAG, "onFirstUserVisible: ");
    }

    @Override
    protected void onUserVisible() {
        Log.d(TAG, "onUserVisible: ");
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_layout;
    }
}
