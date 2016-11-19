package com.sync.taylorcase.sync.mvp;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.ViewGroup;

public interface MvpFragmentDelegate <V extends MvpView, P extends MvpPresenter<V>> {

    void onAttach(Context context);

    void onCreate(Bundle savedInstanceState);

    void onCreateView(LayoutInflater inflater, ViewGroup parent, Bundle savedInstanceState);

    void onActivityCreated(Bundle savedInstanceState);

    void onStart();

    void onResume();

    void onPause();

    void onStop();

    void onDestroyView();

    void onDestroy();

    void onDetach();

    void onSaveInstanceState(Bundle outState);

    P createPresenter();
}
