package com.lll.weidustore;

import android.content.Intent;
import android.text.InputType;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.lll.weidustore.app.MyApp;
import com.lll.weidustore.bean.Result;
import com.lll.weidustore.bean.User;
import com.lll.weidustore.core.DataCall;
import com.lll.weidustore.core.WDActivity;
import com.lll.weidustore.core.exception.ApiException;
import com.lll.weidustore.presenter.LoginPresenter;
import com.lll.weidustore.untils.MD5Utils;
import com.lll.weidustore.untils.UIUtils;

import butterknife.BindView;

import butterknife.OnClick;


public class MainActivity extends WDActivity {

    @BindView(R.id.img_phone)
    ImageView imgPhone;
    @BindView(R.id.edit_phone)
    EditText editPhone;
    @BindView(R.id.img_pwd)
    ImageView imgPwd;
    @BindView(R.id.edit_pwd)
    EditText editPwd;
    @BindView(R.id.img_eye)
    ImageView imgEye;
    @BindView(R.id.ch_box)
    CheckBox chBox;
    @BindView(R.id.btn_reg)
    Button btnLoginReg;
    @BindView(R.id.btn_login)
    Button btnLogin;
    private LoginPresenter loginPresenter;
    private DaoSession daoSession;
    private UserDao userDao;

    @Override
    protected void initView() {
        loginPresenter = new LoginPresenter(new LoginCall());
        boolean remPas = MyApp.getShare().getBoolean("remPas",true);
        if (remPas){
            chBox.setChecked(true);
            editPhone.setText(MyApp.getShare().getString("mobile",""));
            editPwd.setText(MyApp.getShare().getString("pas",""));
        }
        daoSession = DaoMaster.newDevSession(this, UserDao.TABLENAME);
        userDao = daoSession.getUserDao();
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_main;
    }

    @Override
    protected void destoryData() {
        loginPresenter.unBind();
    }

    @OnClick({R.id.btn_login,R.id.btn_reg,R.id.img_eye,R.id.ch_box})
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btn_login:
                Toast.makeText(this, "11", Toast.LENGTH_SHORT).show();
                //登录
                String phone = editPhone.getText().toString();
                String pwd = editPwd.getText().toString();
                if (TextUtils.isEmpty(phone)){
                    Toast.makeText(this,"请输入正确的手机号",Toast.LENGTH_LONG).show();
                    return;
                }
                if (TextUtils.isEmpty(pwd)){
                    Toast.makeText(this,"请输入密码",Toast.LENGTH_LONG).show();
                    return;
                }
                if (chBox.isChecked()){
                    MyApp.getShare().edit().putString("mobile",phone)
                            .putString("pas",pwd).commit();
                }
                loginPresenter.reqeust(phone,MD5Utils.md5(pwd));
                break;
            case R.id.btn_reg:
                //注册
                startActivity(new Intent(MainActivity.this,RegActivity.class));
                break;
            case R.id.img_eye:
                if(editPwd.getInputType() == (InputType.TYPE_CLASS_TEXT|InputType.TYPE_TEXT_VARIATION_VISIBLE_PASSWORD)) {
                    editPwd.setInputType(InputType.TYPE_CLASS_TEXT|InputType.TYPE_TEXT_VARIATION_PASSWORD);

                }
                else {
                    editPwd.setInputType(InputType.TYPE_CLASS_TEXT|InputType.TYPE_TEXT_VARIATION_VISIBLE_PASSWORD);

                }
                break;
            case R.id.ch_box:
                MyApp.getShare().edit()
                        .putBoolean("remPas",chBox.isChecked()).commit();
                break;
        }
    }
    class LoginCall implements DataCall<Result<User>> {


        @Override
        public void success(Result<User> data) {
            if (data.getStatus().equals("0000")){
                boolean checked = chBox.isChecked();
                if(checked){
                    String name = editPhone.getText().toString();
                    String pwd = editPwd.getText().toString();
                    data.getResult().setStatus(1);
                    UserDao userInfoDao = DaoMaster.newDevSession(getBaseContext(), UserDao.TABLENAME).getUserDao();
                    userInfoDao.insertOrReplace(data.getResult());//保存用户数据
                }else{
                   userDao.deleteAll();
                }
                Intent intent = new Intent(MainActivity.this,HomeActivity.class);
                startActivity(intent);
                finish();

            }else{
                UIUtils.showToastSafe(data.getStatus()+"  "+data.getMessage());
            }
        }

        @Override
        public void fail(ApiException e) {

            UIUtils.showToastSafe(e.getCode()+"  "+e.getDisplayMessage());
        }
    }
}
