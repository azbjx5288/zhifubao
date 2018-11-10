package com.ace.android.supervise.module.presenter;

import android.content.Context;

import com.ace.android.supervise.R;
import com.ace.android.supervise.module.model.repository.MainRepository;
import com.ace.android.supervise.module.view.IMainView;
import com.ace.android.supervise.utils.LogUtil;

import rx.Subscriber;
import rx.subscriptions.CompositeSubscription;

/**
 * Created by ace on 2018/10/21.
 */
public class MainPresenter implements BasePresenter<IMainView> {
    private IMainView mView;
    private MainRepository mMainRepository;
    private CompositeSubscription mCompositeSubscription;

    @Override
    public void attachView(IMainView view) {
        this.mView = view;
        mMainRepository = new MainRepository();
        mCompositeSubscription = new CompositeSubscription();
    }

    @Override
    public void detachView() {
        if (!mCompositeSubscription.isUnsubscribed()) {
            mCompositeSubscription.unsubscribe();
        }
        mMainRepository = null;
        mView = null;
    }

    public void checkService() {
            mView.showOpenServiceDialog();
    }


    public void checkPermissions(final Context context, String... permissions) {
        mCompositeSubscription.add(
                mMainRepository.checkPermissions(context, permissions)
                        .subscribe(new Subscriber<Boolean>() {
                            @Override
                            public void onCompleted() {
                                LogUtil.i("check permissions completed");
                            }

                            @Override
                            public void onError(Throwable e) {
                                mView.showErrorMessage(e.toString());
                            }

                            @Override
                            public void onNext(Boolean aBoolean) {
                                if(aBoolean){
                                    mView.checkService();
                                }else{
                                    mView.showErrorMessage(context.getResources().getString(R.string.no_permissions));
                                }
                            }
                        })
        );
    }
}
