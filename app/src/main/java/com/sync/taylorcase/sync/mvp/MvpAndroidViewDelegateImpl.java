package com.sync.taylorcase.sync.mvp;

import android.os.Parcelable;

public class MvpAndroidViewDelegateImpl <V extends MvpView, P extends MvpPresenter<V>> implements MvpAndroidViewDelegate<V, P> {

    private P presenter;
    private final MvpDelegateCallback<V, P> mvpDelegateCallback;

    public MvpAndroidViewDelegateImpl(MvpDelegateCallback<V, P> mvpDelegateCallback) {
        this.mvpDelegateCallback = mvpDelegateCallback;
    }

    @Override
    public void onAttachedToWindow() {
        if (presenter == null) {
            presenter = createPresenter();
        }
        presenter.attachView(mvpDelegateCallback.getMvpView());
    }

    @Override
    public void onDetachedFromWindow() {
        presenter.detachView(mvpDelegateCallback.shouldRetainInstance());
    }

    @Override
    public Parcelable onSaveInstanceState() {
        return null;
    }

    @Override
    public void onRestoreInstanceState(Parcelable parcel) {

    }

    @Override
    public P createPresenter() {
        return mvpDelegateCallback.createPresenter();
    }
}
