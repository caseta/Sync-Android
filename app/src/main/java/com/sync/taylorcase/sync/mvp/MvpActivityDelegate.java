package com.sync.taylorcase.sync.mvp;

import android.os.Bundle;

public interface MvpActivityDelegate <V extends MvpView, P extends MvpPresenter<V>> {

    void onCreate(Bundle savedInstanceState);

    void onState();

    void onResume();

    void onPause();

    void onStop();

    void onDestroy();

    void onSaveInstanceState(Bundle outState);

    P createPresenter();
}
