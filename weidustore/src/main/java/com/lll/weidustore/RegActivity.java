package com.lll.weidustore;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.InputType;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.lll.weidustore.bean.Result;
import com.lll.weidustore.bean.User;
import com.lll.weidustore.core.DataCall;
import com.lll.weidustore.core.exception.ApiException;
import com.lll.weidustore.presenter.RegPresenter;
import com.lll.weidustore.untils.MD5Utils;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import io.reactivex.functions.Consumer;

public class RegActivity extends AppCompatActivity {

    @BindView(R.id.edit_reg_phone)
    EditText editregPhone;
    @BindView(R.id.edit_reg_pwd)
    EditText editregPwd;
    @BindView(R.id.edit_reg_yz)
    EditText editregYz;
    @BindView(R.id.btn_getsafe)
    Button btnGetsafe;
    @BindView(R.id.img_reg_eye)
    ImageView imgregEye;
    @BindView(R.id.reg_login)
    Button btnLoginReg;
    @BindView(R.id.btn_zc)
    Button btnzc;
    private RegPresenter regPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reg);
        ButterKnife.bind(this);
        regPresenter = new RegPresenter(new RegCall());
    }
    @OnClick({R.id.btn_getsafe,R.id.img_reg_eye,R.id.reg_login,R.id.btn_zc})
    public void onClick(View v){
        switch (v.getId()){
            case R.id.btn_getsafe:
                editregYz.setText("12345");
                break;
            case R.id.img_reg_eye:
                if(editregPwd.getInputType() == (InputType.TYPE_CLASS_TEXT|InputType.TYPE_TEXT_VARIATION_VISIBLE_PASSWORD)) {
                    editregPwd.setInputType(InputType.TYPE_CLASS_TEXT|InputType.TYPE_TEXT_VARIATION_PASSWORD);

                }
                else {
                    editregPwd.setInputType(InputType.TYPE_CLASS_TEXT|InputType.TYPE_TEXT_VARIATION_VISIBLE_PASSWORD);

                }
                break;
            case R.id.reg_login:
                finish();
                break;
            case R.id.btn_zc:
                String regphone = editregPhone.getText().toString();
                String regpwd = editregPwd.getText().toString();
                regPresenter.reqeust(regphone,MD5Utils.md5(regpwd));
                break;
        }
    }
     class RegCall implements DataCall<Result> {


         @Override
         public void success(Result data) {
             Toast.makeText(RegActivity.this, data.getMessage(), Toast.LENGTH_SHORT).show();
         }

         @Override
         public void fail(ApiException e) {
            Toast.makeText(RegActivity.this,e.getMessage(),Toast.LENGTH_SHORT).show();
         }
     }
}
