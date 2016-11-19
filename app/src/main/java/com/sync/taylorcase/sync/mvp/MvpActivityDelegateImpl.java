package com.sync.taylorcase.sync.mvp;


import android.os.Bundle;

public class MvpActivityDelegateImpl <V extends MvpView, P extends MvpPresenter<V>> implements MvpActivityDelegate<V, P> {

    private P presenter;
    private MvpDelegateCallback<V, P> mvpDelegateCallback;

    public MvpActivityDelegateImpl(MvpDelegateCallback<V, P> mvpDelegateCallback) {
        this.mvpDelegateCallback = mvpDelegateCallback;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        if (presenter == null) {
            presenter = createPresenter();
        }
        V view = mvpDelegateCallback.getMvpView();
        presenter.attachView(view);

        if (savedInstanceState != null) {
            presenter.restoreState(savedInstanceState);
        }
    }

    @Override
    public void onState() {

    }

    @Override
    public void onResume() {

    }

    @Override
    public void onPause() {

    }

    @Override
    public void onStop() {

    }

    @Override
    public void onDestroy() {
        presenter.detachView(mvpDelegateCallback.shouldRetainInstance());
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        presenter.saveState(outState);
    }

    @Override
    public P createPresenter() {
        return mvpDelegateCallback.createPresenter();
    }
}
