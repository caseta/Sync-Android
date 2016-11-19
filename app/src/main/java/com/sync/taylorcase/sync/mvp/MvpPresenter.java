package com.sync.taylorcase.sync.mvp;

import android.os.Bundle;
import android.support.annotation.NonNull;

public interface MvpPresenter <V extends MvpView> {

    void attachView(@NonNull V attachView);

    void saveState(Bundle outState);

    void restoreState(Bundle inState);

    void detachView(boolean retainInstance);
}
