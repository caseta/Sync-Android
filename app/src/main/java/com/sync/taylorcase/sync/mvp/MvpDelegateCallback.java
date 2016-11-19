package com.sync.taylorcase.sync.mvp;

public interface MvpDelegateCallback <V extends MvpView, P extends MvpPresenter<V>> {

    P createPresenter();

    V getMvpView();

    boolean shouldRetainInstance();
}
