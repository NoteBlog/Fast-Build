package cn.noteblog.library.mvp.main;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.Calendar;

/**
 * 将fragment作为view层的实现类，有两个原因
 * 其一，我们把activity作为一个全局控制类来创建对象，把fragment作为view，这样两者就能各司其职
 * 其二，因为fragment比较灵活，能够方便的处理界面适配的问题
 */
//public class MainFragment extends Fragment implements Contract.View {
//
//    private Contract.Presenter mPresenter;
//
//    public static MainFragment newInstance() {
//        return new MainFragment();
//    }
//
//    @Nullable
//    @Override
//    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
//        View view = new View(getContext());
//
//        // initViews();
//        mPresenter.loadPosts(Calendar.getInstance().getTimeInMillis(), false);
//
//        return view;
//    }
//
//    @Override
//    public void onResume() {
//        super.onResume();
//        mPresenter.start();// 初始化presenter
//    }
//
//    @Override
//    public void setPresenter(Contract.Presenter presenter) {
//        // 通过该方法，view获得了presenter得实例，从而可以调用presenter代码来处理业务逻辑
//        if (presenter != null)
//            this.mPresenter = presenter;
//    }
//
//    @Override
//    public void showError() {
//        // view.show错误页面
//    }
//
//    @Override
//    public void showLoading() {
//        // view.show加载页面
//    }
//
//    @Override
//    public void stopLoading() {
//        // view.stop加载页面
//    }
//
//    @Override
//    public void showResults(ArrayList<String> list) {
//
//    }
//
//    @Override
//    public void showNetworkError() {
//
//    }
//}
