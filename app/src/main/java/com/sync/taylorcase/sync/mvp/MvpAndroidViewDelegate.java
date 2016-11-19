package com.sync.taylorcase.sync.mvp;

import android.os.Parcelable;

public interface MvpAndroidViewDelegate <V extends MvpView, P extends MvpPresenter<V>> {

    void onAttachedToWindow();

    void onDetachedFromWindow();

    Parcelable onSaveInstanceState();

    void onRestoreInstanceState(Parcelable parcel);

    P createPresenter();
}
