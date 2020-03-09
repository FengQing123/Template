package com.example.template.mvp.view;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;

import com.example.template.R;
import com.example.template.app.BaseApplication;
import com.example.template.mvp.base.BaseFragment;
import com.example.template.mvp.contract.IPoetryContract;
import com.example.template.mvp.presenter.PoetryPresenter;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * 功能描述：
 * Created by gfq on 2020/3/9.
 */
public class PoetryFragment extends BaseFragment<PoetryPresenter> implements IPoetryContract.IPoetryView {

    @BindView(R.id.btn_get_poetry)
    Button btnGetPoetry;
    @BindView(R.id.tv_poetry_author)
    TextView tvPoetryAuthor;

    @Override
    public View initView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_poetry, container, false);
    }

    @Override
    public PoetryPresenter initPresenter() {
        return PoetryPresenter.getInstance();
    }


    @Override
    public void showProgressDialog() {

    }

    @Override
    public void hideProgressDialog() {

    }

    @Override
    public void onError(String result) {
        Toast.makeText(BaseApplication.getContext(), result, Toast.LENGTH_SHORT).show();

    }

    @OnClick(R.id.btn_get_poetry)
    public void onViewClicked() {
        getPresenter().getPoetry();
    }

    @Override
    public void searchSuccess(String author) {
        tvPoetryAuthor.setText(author);
    }
}

