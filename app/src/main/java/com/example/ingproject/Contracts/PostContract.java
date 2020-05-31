package com.example.ingproject.Contracts;

import com.example.ingproject.Models.Post;
import com.example.ingproject.Models.User;

public interface PostContract {
    interface presenter{

        void onDestroy();

        void onRefreshButtonClick();

        void requestDataFromServer();

    }

    /**
     * showProgress() and hideProgress() would be used for displaying and hiding the progressBar
     * while the setDataToRecyclerView and onResponseFailure is fetched from the GetNoticeInteractorImpl class
     **/
    interface MainView {

        void showProgress();

        void hideProgress();

        void setDataToListView(User[] noticeUser);

        void setDataToListView(Post[] noticePost);

        void onResponseFailure(Throwable throwable);

    }

    /**
     * Intractors are classes built for fetching data from your database, web services, or any other data source.
     **/
    interface GetPostIntractor {

        interface OnFinishedListener {

            void onFinishedUser(User[] noticeUser);

            void onFinished(Post[] noticePost);

            void onFailure(Throwable t);

        }

        void getNoticeArray(OnFinishedListener onFinishedListener);
    }
}
