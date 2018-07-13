package android.lzj.com.lazyfragment.Base;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by lenovo on 2018/7/13.
 */

public abstract class BaseLazyFragment extends Fragment {
    private static final String TAG = "BaseLazyFragment";
    private boolean isFirstVisible = true;
    private boolean isFirstInvisible = true;
    private boolean isFirstResume = true;

    private boolean isViewCreated;
    private boolean isUIVisible;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.d(TAG, "onCreate: ");
    }

    @Override
    public void onResume() {
        super.onResume();
        Log.d(TAG, "onResume: ");
        if (isFirstResume) {
            //第一次进入，setUserVisibleHint  执行，防止重复
            isFirstResume = false;
            return;
        }
        //解锁时执行
        if (getUserVisibleHint()) {
            onUserVisible();
        }
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        Log.d(TAG, "onCreateView: ");
        if (getLayoutId() != 0) {
            return inflater.inflate(getLayoutId(), container, false);
        } else {
            return super.onCreateView(inflater, container, savedInstanceState);
        }
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        isViewCreated = true;
        initView();
        lazyLoad();
        Log.d(TAG, "onViewCreated: ");
    }

    protected abstract void initView();

    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        Log.d(TAG, "setUserVisibleHint: " + isVisibleToUser);
        if (isVisibleToUser) {
            isUIVisible = true; //当前fragment可见
            if (isFirstVisible) {
                //如果是第一次可见，则进行懒加载
                isFirstVisible = false;
                lazyLoad();
            } else {
                //不是第一次可见，则调用onUserVisible()
                onUserVisible();
                Log.d(TAG, "setUserVisibleHint: ");
            }
        } else {
            isUIVisible = false;
            if (isFirstInvisible) {
                isFirstInvisible = false;
                //第一次不可见
//                onFirstUserInvisible();
            } else {
                //非第一次不可见
//                onUserInvisible();
            }
        }
    }

    @Override
    public void onHiddenChanged(boolean hidden) {
        super.onHiddenChanged(hidden);
        Log.d(TAG, "onHiddenChanged: " + hidden);
    }

    @Override
    public void onPause() {
        super.onPause();
        //解锁时执行
        Log.d(TAG, "onPause: ");
    }


    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.d(TAG, "onDestroy: ");
    }

    private void lazyLoad() {
        if (isViewCreated && isUIVisible) { //需要进行双重判断，避免因为setUserVisibleHint先于onViewCreaetd调用时，出现空指针
            onFirstUserVisible();  //进行初次可见时的加载
            Log.d(TAG, "lazyLoad: ");
        }
    }

    /**
     * 第一次可见执行初始化请求数据
     */
    protected abstract void onFirstUserVisible();

    /**
     * 执行刷新操作
     */
    protected abstract void onUserVisible();

    protected abstract int getLayoutId();
}
