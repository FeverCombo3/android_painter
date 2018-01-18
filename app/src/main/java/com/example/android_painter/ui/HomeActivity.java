package com.example.android_painter.ui;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.widget.RadioGroup;

import com.example.android_painter.R;
import com.example.android_painter.ui.base.BaseActivity;
import com.example.android_painter.ui.fragment.ApplyFragment;
import com.example.android_painter.ui.fragment.ExploreFragment;
import com.example.android_painter.ui.fragment.HomeFragment;
import com.example.android_painter.ui.fragment.MoreFragment;
import com.example.android_painter.util.FragmentTag;
import com.example.android_painter.util.LogUtil;


public class HomeActivity extends BaseActivity implements
        RadioGroup.OnCheckedChangeListener {

    private FragmentManager mFragmentManager;
    private RadioGroup mRadioGroup;
    private Fragment mHomeFragment, mExploreFragment,
            mApplyFragment, mMoreFragment, mCurrFragment;

    @Override
    protected Object setLayout() {
        return R.layout.activity_main;
    }

    @Override
    protected void initParamsAndViews() {
        mFragmentManager = getSupportFragmentManager();
    }

    @Override
    protected void initViews() {
        mRadioGroup = (RadioGroup) findViewById(R.id.rg_tab);
        mRadioGroup.setOnCheckedChangeListener(this);
    }

    @Override
    protected void onStart() {
        super.onStart();
        switchFragment(FragmentTag.HOME);
    }

    /**
     * 切换Fragment
     *
     * @param tag
     */
    private void switchFragment(FragmentTag tag) {
        switch (tag) {
            case HOME:
                if (mHomeFragment == null) {
                    mHomeFragment = HomeFragment.newInstance();
                }
                showSelectedFragment(mHomeFragment, tag);
                break;
            case EXPLORE:
                if (mExploreFragment == null) {
                    mExploreFragment = ExploreFragment.newInstance();
                }
                showSelectedFragment(mExploreFragment, tag);
                break;
            case APPLY:
                if (mApplyFragment == null) {
                    mApplyFragment = ApplyFragment.newInstance();
                }
                showSelectedFragment(mApplyFragment, tag);
                break;
            case MORE:
                if (mMoreFragment == null) {
                    mMoreFragment = MoreFragment.newInstance();
                }
                showSelectedFragment(mMoreFragment, tag);
                break;
            default:
                break;
        }
    }

    /**
     * 展示Fragment
     *
     * @param fragment
     * @param tag
     */
    private void showSelectedFragment(Fragment fragment, FragmentTag tag) {
        if (fragment == null)
            return;

        if (fragment.isAdded()) {
            mFragmentManager.beginTransaction()
                    .show(fragment)
                    .hide(mCurrFragment)
                    .commit();
        } else {
            mFragmentManager.beginTransaction()
                    .add(R.id.container, fragment, tag.name())
                    .commit();

            if (mCurrFragment != null) {
                mFragmentManager.beginTransaction()
                        .hide(mCurrFragment)
                        .commit();
            }
        }
        mCurrFragment = fragment;
    }


    /**
     * 切换RadioGroup:实现界面的切换
     */
    @Override
    public void onCheckedChanged(RadioGroup group, int checkedId) {
        switch (checkedId) {
            case R.id.rb_home://首页
                switchFragment(FragmentTag.HOME);
                break;
            case R.id.rb_explore://探索
                switchFragment(FragmentTag.EXPLORE);
                break;
            case R.id.rb_apply://应用
                switchFragment(FragmentTag.APPLY);
                break;
            case R.id.rb_more://更多
                switchFragment(FragmentTag.MORE);
                break;
            default:
                break;
        }
    }

    @Override
    protected void onDestroy() {
        LogUtil.logIWithTime("主页：onDestroy");
        super.onDestroy();
    }
}
