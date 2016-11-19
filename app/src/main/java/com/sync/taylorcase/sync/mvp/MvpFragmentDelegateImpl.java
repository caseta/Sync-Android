package com.sync.taylorcase.sync.mvp;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.ViewGroup;

public class MvpFragmentDelegateImpl <V extends MvpView, P extends MvpPresenter<V>> implements MvpFragmentDelegate<V, P> {

    private P presenter;
    private MvpDelegateCallback<V, P> mvpDelegateCallback;

    public MvpFragmentDelegateImpl(MvpDelegateCallback<V, P> delegateCallback) {
        this.mvpDelegateCallback = delegateCallback;
    }

    @Override
    public void onAttach(Context context) {

    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        if (presenter == null) {
            presenter = createPresenter();
        }
        if (savedInstanceState != null) {
            presenter.restoreState(savedInstanceState);
        }
    }

    @Override
    public void onCreateView(LayoutInflater inflater, ViewGroup parent, Bundle savedInstanceState) {
        V mvpView = mvpDelegateCallback.getMvpView();
        presenter.attachView(mvpView);
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {

    }

    @Override
    public void onStart() {

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
    public void onDestroyView() {

    }

    @Override
    public void onDestroy() {
        presenter.detachView(mvpDelegateCallback.shouldRetainInstance());
    }

    @Override
    public void onDetach() {

    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        if (outState != null) {
            presenter.saveState(outState);
        }
    }

    @Override
    public P createPresenter() {
        return mvpDelegateCallback.createPresenter();
    }
}
