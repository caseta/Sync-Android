package com.sync.taylorcase.sync.Login;

import com.sync.taylorcase.sync.mvp.MvpPresenter;
import com.sync.taylorcase.sync.mvp.MvpView;

public interface LoginContract {

    public interface LoginView extends MvpView {

    }

    public interface LoginPresenter extends MvpPresenter<LoginView> {

    }

}
