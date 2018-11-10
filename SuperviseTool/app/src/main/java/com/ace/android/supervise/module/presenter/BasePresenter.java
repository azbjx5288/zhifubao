package com.ace.android.supervise.module.presenter;

import com.ace.android.supervise.module.view.IBaseView;

/**
 * Created by ace on 2018/10/21.
 */

public interface BasePresenter<T extends IBaseView> {
    void attachView(T view);
    void detachView();
}
