package cn.noteblog.support;

import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import butterknife.ButterKnife;
import butterknife.InjectView;
import cn.noteblog.library.widget.navigationbar.NavigationBar;

public class Main extends AppCompatActivity implements NavigationBar.OnTabSelectedListener {

    @InjectView(R.id.bottom_navigation_bar)
    NavigationBar navigation;

    private FragmentManager mFragmentManager;
    private Fragment_0 mFragment0;
    private Fragment_1 mFragment1;
    private Fragment_2 mFragment2;
    private Fragment_3 mFragment3;

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
                .setMode(NavigationBar.MODE_CLASSIC)
                .addItem(new NavigationBar.BottomNavigationItem(R.mipmap.ic_home, "首页").setActiveColor(R.color.colorPrimary))
                .addItem(new NavigationBar.BottomNavigationItem(R.mipmap.ic_favorite, "兴趣").setActiveColor(R.color.colorPrimary))
                .addItem(new NavigationBar.BottomNavigationItem(R.mipmap.ic_music, "音乐").setActiveColor(R.color.colorPrimary))
                .addItem(new NavigationBar.BottomNavigationItem(R.mipmap.ic_video, "视频").setActiveColor(R.color.colorPrimary))
                .setTabSelectedListener(this)
                .initialise();

        // 获取碎片管理器
        mFragmentManager = getSupportFragmentManager();
        // 开启一个Fragment事务
        FragmentTransaction transaction = mFragmentManager.beginTransaction();
        mFragment0 = new Fragment_0();
        Bundle bundle = new Bundle();
        bundle.putInt("args", 0);
        mFragment0.setArguments(bundle);
        transaction.add(R.id.main_content, mFragment0);
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
                transaction.show(mFragment0);
                break;
            case 1:
                mFragment1 = new Fragment_1();
                bundle.putInt("args", 1);
                mFragment1.setArguments(bundle);
                transaction.add(R.id.main_content, mFragment1);
                break;
            case 2:
                mFragment2 = new Fragment_2();
                bundle.putInt("args", 2);
                mFragment2.setArguments(bundle);
                transaction.add(R.id.main_content, mFragment2);
                break;
            case 3:
                mFragment3 = new Fragment_3();
                bundle.putInt("args", 3);
                mFragment3.setArguments(bundle);
                transaction.add(R.id.main_content, mFragment3);
                break;
        }
        transaction.commit();
    }

    /**
     * 隐藏所有fragment
     */
    private void hideFragments(FragmentTransaction transaction) {
        if (mFragment0 != null) {
            transaction.hide(mFragment0);
        }
        if (mFragment1 != null) {
            transaction.hide(mFragment1);
        }
        if (mFragment2 != null) {
            transaction.hide(mFragment2);
        }
        if (mFragment3 != null) {
            transaction.hide(mFragment3);
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
                transaction.show(mFragment0);
                break;
            case 1:
                transaction.show(mFragment1);
                break;
            case 2:
                transaction.show(mFragment2);
                break;
            case 3:
                transaction.show(mFragment3);
                break;
        }
        transaction.commit();
    }
}
