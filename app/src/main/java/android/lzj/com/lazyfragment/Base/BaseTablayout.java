package android.lzj.com.lazyfragment.Base;

import android.app.Fragment;
import android.graphics.Color;
import android.lzj.com.lazyfragment.R;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.widget.TextView;

/**
 * Created by lenovo on 2018/7/13.
 */

public class BaseTablayout extends BaseLazyFragment {
    TabLayout mTablayout;

    ViewPager mViewPage;

    public String[] tabTitle = null;
    public android.support.v4.app.Fragment[] mFragement = null;

    @Override
    protected void onFirstUserVisible() {

    }

    @Override
    protected void initView() {
        setData();
        mViewPage= (ViewPager) getView().findViewById(R.id.viewpage);
        mTablayout= (TabLayout) getView().findViewById(R.id.tablayout);
        mViewPage.setOffscreenPageLimit(1);
        setupViewPager(mViewPage);
        mTablayout.setupWithViewPager(mViewPage);

        try {
            setupTabIcons();
        } catch (Exception e) {
            e.printStackTrace();
        }


        mViewPage.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                mViewPage.setCurrentItem(position, false);

            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
    }

    @Override
    protected void onUserVisible() {

    }

    @Override
    protected int getLayoutId() {
        return R.layout.layout_tab;
    }

    private void setupTabIcons() {

        for (int i = 0; i < tabTitle.length; i++) {
            mTablayout.getTabAt(i).setCustomView(prepareTabView(i));
        }
    }

    private View prepareTabView(int pos) {
        View view = getActivity().getLayoutInflater().inflate(R.layout.viewpage_layout, null);
        TextView mTvTitle = (TextView) view.findViewById(R.id.tv_title);
        mTvTitle.setTextColor(Color.parseColor("#ba1003"));
        mTvTitle.setText(tabTitle[pos]);
        return view;
    }

    private void setupViewPager(ViewPager viewPager) {
        ViewPagerAdapter adapter = new ViewPagerAdapter(getFragmentManager());
        if (tabTitle.length == mFragement.length) {
            for (int i = 0; i < tabTitle.length; i++) {
                adapter.addFragment(mFragement[i], tabTitle[i]);
            }
        }
        viewPager.setAdapter(adapter);
    }

    protected void setData() {
    }
}
