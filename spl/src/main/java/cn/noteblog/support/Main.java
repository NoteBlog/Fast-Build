package cn.noteblog.support;

import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import butterknife.ButterKnife;
import butterknife.InjectView;
import cn.noteblog.library.widget.navigationbar.BottomNavigation;

public class Main extends AppCompatActivity implements BottomNavigation.OnTabSelectedListener {

    @InjectView(R.id.bottom_navigation_bar)
    BottomNavigation navigation;

    private FragmentManager mFragmentManager;
    private FragmentTest mFragmentHome;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        /* 测试代码区 */
        setContentView(R.layout.activity_main);

        ButterKnife.inject(this);
        init();
    }

    private void init() {
        navigation
                .setMode(BottomNavigation.MODE_CLASSIC)
                .addItem(new BottomNavigation.BottomNavigationItem(R.mipmap.ic_home, "首页").setActiveColor(R.color.colorPrimary))
                .addItem(new BottomNavigation.BottomNavigationItem(R.mipmap.ic_favorite, "兴趣").setActiveColor(R.color.colorPrimary))
                .addItem(new BottomNavigation.BottomNavigationItem(R.mipmap.ic_music, "音乐").setActiveColor(R.color.colorPrimary))
                .addItem(new BottomNavigation.BottomNavigationItem(R.mipmap.ic_video, "视频").setActiveColor(R.color.colorPrimary))
                .setTabSelectedListener(this)
                .initialise();

        // 获取碎片管理器
        mFragmentManager = getSupportFragmentManager();
        // 开启一个Fragment事务
        FragmentTransaction transaction = mFragmentManager.beginTransaction();
        mFragmentHome = new FragmentTest();
        Bundle bundle = new Bundle();
        bundle.putInt("args", 0);
        mFragmentHome.setArguments(bundle);
        transaction.add(R.id.main_content, mFragmentHome);
        transaction.commit();
    }

    @Override
    public void onTabSelected(int position) {
        FragmentTransaction transaction = mFragmentManager.beginTransaction();
        // 隐藏掉所有的Fragment
        hideFragments(transaction);
        Bundle bundle = new Bundle();
        switch (position) {
            case 0:
                transaction.show(mFragmentHome);
                break;
            case 1:
                mFragmentHome = new FragmentTest();
                bundle.putInt("args", 1);
                mFragmentHome.setArguments(bundle);
                transaction.add(R.id.main_content, mFragmentHome);
                break;
            case 2:
                mFragmentHome = new FragmentTest();
                bundle.putInt("args", 2);
                mFragmentHome.setArguments(bundle);
                transaction.add(R.id.main_content, mFragmentHome);
                break;
            case 3:
                mFragmentHome = new FragmentTest();
                bundle.putInt("args", 3);
                mFragmentHome.setArguments(bundle);
                transaction.add(R.id.main_content, mFragmentHome);
                break;
        }
        transaction.commit();
    }

    /**
     * 隐藏所有fragment
     */
    private void hideFragments(FragmentTransaction transaction) {
        if (mFragmentHome != null) {
            transaction.hide(mFragmentHome);
        }
    }

    @Override
    public void onTabUnselected(int position) {

    }

    @Override
    public void onTabReselected(int position) {
        FragmentTransaction transaction = mFragmentManager.beginTransaction();
        // 隐藏掉所有的Fragment
        hideFragments(transaction);
        switch (position) {
            case 0:
                transaction.show(mFragmentHome);
                break;
            case 1:
                transaction.show(mFragmentHome);
                break;
            case 2:
                transaction.show(mFragmentHome);
                break;
            case 3:
                transaction.show(mFragmentHome);
                break;
        }
        transaction.commit();
    }
}
