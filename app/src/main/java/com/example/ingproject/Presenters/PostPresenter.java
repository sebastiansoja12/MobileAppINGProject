package com.example.ingproject.Presenters;

import com.example.ingproject.Models.Post;
import com.example.ingproject.Models.User;
import com.example.ingproject.Contracts.PostContract;

public class PostPresenter implements PostContract.presenter, PostContract.GetPostIntractor.OnFinishedListener {


    private PostContract.MainView mainView;
    private PostContract.GetPostIntractor getNoticeIntractor;

    public PostPresenter(PostContract.MainView mainView, PostContract.GetPostIntractor getNoticeIntractor) {
        this.mainView = mainView;
        this.getNoticeIntractor = getNoticeIntractor;

    }


    @Override
    public void onDestroy() {

        mainView = null;

    }

    @Override
    public void onRefreshButtonClick() {

        if(mainView != null){
            mainView.showProgress();
        }
        getNoticeIntractor.getNoticeArray(this);
    }

    @Override
    public void requestDataFromServer() {
        getNoticeIntractor.getNoticeArray(this);

    }


    @Override
    public void onFinishedUser(User[] noticeUser) {
        if(mainView != null){
            mainView.setDataToListView(noticeUser);
            mainView.hideProgress();
        }
    }

    @Override
    public void onFinished(Post[] post) {
        if(mainView != null){
            mainView.setDataToListView(post);
            mainView.hideProgress();
        }
    }

    @Override
    public void onFailure(Throwable t) {
        if(mainView != null){
            mainView.onResponseFailure(t);
            mainView.hideProgress();
        }
    }


}
