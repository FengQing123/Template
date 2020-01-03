package com.example.mvp.search;

import com.example.mvp.base.present.AbstractPresenter;
import com.example.mvp.base.view.AbstractView;
import com.example.mvp.core.bean.main.collect.FeedArticleData;
import com.example.mvp.core.bean.main.collect.FeedArticleListData;

/**
 * 功能描述：
 * Created by gfq on 2020/1/3.
 */
public interface SearchListContract {

    interface View extends AbstractView {

        /**
         * Show search list
         *
         * @param feedArticleListData FeedArticleListData
         */
        void showSearchList(FeedArticleListData feedArticleListData);

        /**
         * Show collect article data
         *
         * @param position            Position
         * @param feedArticleData     FeedArticleData
         * @param feedArticleListData FeedArticleListData
         */
        void showCollectArticleData(int position, FeedArticleData feedArticleData, FeedArticleListData feedArticleListData);

        /**
         * Show cancel collect article data
         *
         * @param position            Position
         * @param feedArticleData     FeedArticleData
         * @param feedArticleListData FeedArticleListData
         */
        void showCancelCollectArticleData(int position, FeedArticleData feedArticleData, FeedArticleListData feedArticleListData);

    }

    interface Presenter extends AbstractPresenter<View> {

        /**
         * 搜索
         *
         * @param page        page
         * @param k           POST search key
         * @param isShowError If show error
         */
        void getSearchList(int page, String k, boolean isShowError);

        /**
         * Add collect article
         *
         * @param position        Position
         * @param feedArticleData FeedArticleData
         */
        void addCollectArticle(int position, FeedArticleData feedArticleData);

        /**
         * Cancel collect article
         *
         * @param position        Position
         * @param feedArticleData FeedArticleData
         */
        void cancelCollectArticle(int position, FeedArticleData feedArticleData);

    }
}
