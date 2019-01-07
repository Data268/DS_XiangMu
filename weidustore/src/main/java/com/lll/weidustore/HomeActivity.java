package com.lll.weidustore;

import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.RadioGroup;

import com.lll.weidustore.fragment.Frag_01;
import com.lll.weidustore.fragment.Frag_02;
import com.lll.weidustore.fragment.Frag_03;
import com.lll.weidustore.fragment.Frag_04;
import com.lll.weidustore.fragment.Frag_05;

import butterknife.BindView;
import butterknife.ButterKnife;

public class HomeActivity extends AppCompatActivity {

    @BindView(R.id.fragment)
    FrameLayout fragment;
    @BindView(R.id.radioGroup)
    RadioGroup radioGroup;
    private FragmentManager manager;
    private FragmentTransaction transaction;
    private Frag_01 frag_01;
    private Frag_02 frag_02;
    private Frag_03 frag_03;
    private Frag_04 frag_04;
    private Frag_05 frag_05;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        ButterKnife.bind(this);
        //获取事务管理者
        manager = getSupportFragmentManager();
        //开启事务
        transaction = manager.beginTransaction();
        //添加Fragment
        frag_01 = new Frag_01();
        frag_02 = new Frag_02();
        frag_03 = new Frag_03();
        frag_04 = new Frag_04();
        frag_05 = new Frag_05();
        transaction.add(R.id.fragment, frag_01).show(frag_01);
        transaction.add(R.id.fragment, frag_02).hide(frag_02);
        transaction.add(R.id.fragment, frag_03).hide(frag_03);
        transaction.add(R.id.fragment, frag_04).hide(frag_04);
        transaction.add(R.id.fragment, frag_05).hide(frag_05);
        //提交事务
        transaction.commit();
        //默认第一个选中
        radioGroup.check(radioGroup.getChildAt(0).getId());
        //切换按钮改变页面状态
        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                FragmentTransaction transaction1 = manager.beginTransaction();
                switch (checkedId){
                    case R.id.radio_home:
                        transaction1.show(frag_01).hide(frag_02).hide(frag_03).hide(frag_04).hide(frag_05);
                        break;
                    case R.id.radio_ufo:
                        transaction1.show(frag_02).hide(frag_01).hide(frag_03).hide(frag_04).hide(frag_05);
                        break;
                    case R.id.radio_car:
                        transaction1.show(frag_03).hide(frag_02).hide(frag_01).hide(frag_04).hide(frag_05);
                        break;
                    case R.id.radio_ben:
                        transaction1.show(frag_04).hide(frag_02).hide(frag_03).hide(frag_01).hide(frag_05);
                        break;
                    case R.id.radio_user:
                        transaction1.show(frag_05).hide(frag_02).hide(frag_03).hide(frag_04).hide(frag_01);
                        break;
                }
                transaction1.commit();
            }
        });
    }
}
