package com.michaelliu.kotlin.module.repository.content

import com.mdroid.PausedHandler
import com.mdroid.lib.core.rxjava.PausedHandlerScheduler
import com.michaelliu.kotlin.model.RepositoryModel
import com.michaelliu.kotlin.module.repository.content.RepositoryContract.IRepositoryPresenter
import com.michaelliu.kotlin.network.Api
import com.trello.rxlifecycle2.LifecycleProvider
import com.trello.rxlifecycle2.android.FragmentEvent
import com.trello.rxlifecycle2.android.FragmentEvent.DESTROY_VIEW
import io.reactivex.schedulers.Schedulers

/**
 * Description:
 *
 * Created by liuguoquan on 2017/11/6 10:42.
 */
class RepositoryPresenter(provider: LifecycleProvider<FragmentEvent>,
                          handler: PausedHandler) : IRepositoryPresenter(provider, handler) {


    override fun query(condition: RepositoryCondition) {
        Api.getSearchApi().search(condition.q, condition.sort, condition.order, condition.page,
                condition.limit)
                .subscribeOn(Schedulers.io()).observeOn(PausedHandlerScheduler.from(mHandler))
                .compose(mProvider.bindUntilEvent(DESTROY_VIEW))
                .subscribe({ model: RepositoryModel? ->
                    run {
                        mView.showSuccess(model?.items!!)
                    }
                },
                        { error: Throwable -> run { mView.showError(error.message!!) } })
    }
}