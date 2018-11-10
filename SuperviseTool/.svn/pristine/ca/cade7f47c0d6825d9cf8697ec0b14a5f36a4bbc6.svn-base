package com.ace.android.supervise.module.model.repository;

import android.content.Context;

import com.tbruyelle.rxpermissions.RxPermissions;

import rx.Observable;

/**
 * Created by ace on 2018/10/22.
 */

public class MainRepository {
    public Observable<Boolean> checkPermissions(Context context, String[] permissions) {
        return RxPermissions.getInstance(context).request(permissions);
    }
}
