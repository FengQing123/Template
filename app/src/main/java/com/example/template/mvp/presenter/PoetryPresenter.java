//package com.example.template.mvp.presenter;
//
//import android.util.Log;
//
//import com.example.template.mvp.base.BasePresenter;
//import com.example.template.mvp.contract.IPoetryContract;
//import com.example.template.mvp.entity.PoetryEntity;
//import com.example.template.mvp.model.PoetryModel;
//import com.example.template.mvp.util.RxJavaUtil;
//
//import io.reactivex.Observable;
//import io.reactivex.Observer;
//import io.reactivex.disposables.Disposable;
//import io.reactivex.functions.Consumer;
//
///**
// * 功能描述：
// * Created by gfq on 2020/3/9.
// */
//public class PoetryPresenter extends BasePresenter<IPoetryContract.IPoetryView> implements IPoetryContract.IPoetryPresenter {
//
//    private static final String TAG = "PoetryPresenter";
//
//    private PoetryEntity mPoetryEntity;
//    private PoetryModel mPoetryModel;
//
//    private PoetryPresenter() {
//        mPoetryModel = PoetryModel.getInstance();
//    }
//
//    public static PoetryPresenter getInstance() {
//        return Inner.instance;
//    }
//
//    private static class Inner {
//        private static final PoetryPresenter instance = new PoetryPresenter();
//    }
//
//    /**
//     * 得到诗歌
//     */
//    @Override
//    public void getPoetry() {
//        Observable observable = mPoetryModel.getPoetry().doOnSubscribe(new Consumer<Disposable>() {
//            @Override
//            public void accept(Disposable disposable) throws Exception {
//                addDisposable(disposable);
//            }
//        });
//        observable = RxJavaUtil.toSubscribe(observable);
//        observable.subscribe(new Observer<PoetryEntity>() {
//            @Override
//            public void onSubscribe(Disposable d) {
//            }
//
//            @Override
//            public void onNext(PoetryEntity poetryEntity) {
//                mPoetryEntity = poetryEntity;
//            }
//
//            @Override
//            public void onError(Throwable e) {
//                getMvpView().onError(e.getMessage());
//                Log.d(TAG, "onError: " + e.getMessage());
//            }
//
//            @Override
//            public void onComplete() {
//                if (mPoetryEntity != null) {
//                    getMvpView().searchSuccess(mPoetryEntity.getAuthor());
//                }
//            }
//        });
//
//    }
//}
