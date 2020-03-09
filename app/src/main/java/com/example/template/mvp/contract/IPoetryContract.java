package com.example.template.mvp.contract;

import com.example.template.mvp.base.BaseView;
import com.example.template.mvp.entity.PoetryEntity;

import io.reactivex.Observable;

/**
 * 功能描述：诗歌的接口管理器
 * Created by gfq on 2020/3/9.
 */
public class IPoetryContract {
    public interface IPoetryModel {
        /**
         * 得到诗歌
         *
         * @return 诗歌
         */
        Observable<PoetryEntity> getPoetry();
    }

    public interface IPoetryPresenter {
        void getPoetry();
    }

    public interface IPoetryView extends BaseView {
        /**
         * @param author 作者
         */
        void searchSuccess(String author);
    }

}
