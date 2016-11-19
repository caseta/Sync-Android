package com.sync.taylorcase.sync.mvp;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import java.lang.ref.WeakReference;

public abstract class BasePresenter <V extends MvpView> implements MvpPresenter<V> {

    private WeakReference<V> viewReference;

    @Override public void attachView(@NonNull V attachView) {
        viewReference = new WeakReference<>(attachView);
    }

    @Override public void saveState(Bundle outState) {

    }

    @Override public void restoreState(Bundle inState) {

    }

    @Nullable
    public V getView() {
        return viewReference == null ? null : viewReference.get();
    }

    @Override public void detachView(boolean retainInstance) {
        if (viewReference != null) {
            viewReference.clear();
            viewReference = null;
        }
    }

    protected boolean isViewPresent() {
        return viewReference != null && viewReference.get() != null;
    }
}
