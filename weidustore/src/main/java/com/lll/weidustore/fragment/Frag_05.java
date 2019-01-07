package com.lll.weidustore.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.lll.weidustore.R;
import com.lll.weidustore.UserAddressActivity;
import com.lll.weidustore.UserCircleActivity;
import com.lll.weidustore.UserFootActivity;
import com.lll.weidustore.UserPersonActivity;
import com.lll.weidustore.UserWalletActivity;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

public class Frag_05 extends Fragment implements View.OnClickListener {

    @BindView(R.id.user_person)
    TextView userPerson;
    @BindView(R.id.user_circle)
    TextView userCircle;
    @BindView(R.id.user_foot)
    TextView userFoot;
    @BindView(R.id.user_wallet)
    TextView userWallet;
    @BindView(R.id.user_address)
    TextView userAddress;
    Unbinder unbinder;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.frag_05, container, false);
        unbinder = ButterKnife.bind(this, view);
        userPerson.setOnClickListener(this);
        userCircle.setOnClickListener(this);
        userFoot.setOnClickListener(this);
        userWallet.setOnClickListener(this);
        userAddress.setOnClickListener(this);
        return view;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.user_person:
                startActivity(new Intent(getActivity(),UserPersonActivity.class));
                break;
            case R.id.user_circle:
                startActivity(new Intent(getActivity(),UserCircleActivity.class));
                break;
            case R.id.user_foot:
                startActivity(new Intent(getActivity(),UserFootActivity.class));
                break;
            case R.id.user_wallet:
                startActivity(new Intent(getActivity(),UserWalletActivity.class));
                break;
            case R.id.user_address:
                startActivity(new Intent(getActivity(),UserAddressActivity.class));
                break;

        }
    }
}
