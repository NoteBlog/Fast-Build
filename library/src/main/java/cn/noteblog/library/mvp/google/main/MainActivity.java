package cn.noteblog.library.mvp.google.main;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

/**
 * activity在项目中是一个全局的控制者，负责创建view以及presenter实例，并将二者联系起来
 */
public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Create the view
        MainFragment mainFragment = MainFragment.newInstance();// 这里是将fragment作为view的具体实现类

        // Create the presenter
        new MainPresenter(mainFragment);// 创建presenter
    }
}
