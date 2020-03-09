//package com.example.template.mvp.view;
//
//import android.view.View;
//import android.widget.Button;
//import android.widget.LinearLayout;
//import android.widget.TextView;
//import android.widget.Toast;
//
//import com.example.template.R;
//import com.example.template.app.BaseApplication;
//import com.example.template.mvp.base.BaseMvpActivity;
//import com.example.template.mvp.contract.IPoetryContract;
//import com.example.template.mvp.presenter.PoetryPresenter;
//
//import butterknife.BindView;
//import butterknife.OnClick;
//
///**
// * 功能描述：
// * Created by gfq on 2020/3/9.
// */
//public class PoetryActivity extends BaseMvpActivity<PoetryActivity, PoetryPresenter> implements IPoetryContract.IPoetryView {
//
//    @BindView(R.id.btn_get_poetry)
//    Button btnGetPoetry;
//    @BindView(R.id.tv_poetry_author)
//    TextView tvPoetryAuthor;
//    @BindView(R.id.btn_goto_fragment)
//    Button btnGotoFragment;
//    @BindView(R.id.layout_fragment)
//    LinearLayout ll;
//
//    @Override
//    protected void initViews() {
//
//    }
//
//    @Override
//    protected int getLayoutId() {
//        return R.layout.activity_poetry;
//    }
//
//    @Override
//    protected PoetryPresenter createPresenter() {
//        return PoetryPresenter.getInstance();
//    }
//
//    @Override
//    public void searchSuccess(String author) {
//        tvPoetryAuthor.setText(author);
//    }
//
//    @Override
//    public void showProgressDialog() {
//
//    }
//
//    @Override
//    public void hideProgressDialog() {
//
//    }
//
//    @Override
//    public void onError(String result) {
//        Toast.makeText(BaseApplication.getContext(), result, Toast.LENGTH_SHORT).show();
//    }
//
//
//    @OnClick({R.id.btn_get_poetry, R.id.btn_goto_fragment})
//    public void onViewClicked(View view) {
//        switch (view.getId()) {
//            case R.id.btn_get_poetry:
//                getPresenter().getPoetry();
//                break;
//            case R.id.btn_goto_fragment:
//                startFragment(R.id.layout_fragment, new PoetryFragment());
//                break;
//            default:
//                break;
//        }
//    }
//}
