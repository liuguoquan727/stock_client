package com.michaelliu.kotlin.module.repository.content;

import com.mdroid.PausedHandler;
import com.michaelliu.kotlin.base.AppBaseFragmentPresenter;
import com.michaelliu.kotlin.base.AppBaseView;
import com.michaelliu.kotlin.model.RepositoryModel
import com.trello.rxlifecycle2.LifecycleProvider;
import com.trello.rxlifecycle2.android.FragmentEvent;

/**
 * Description:
 *
 * Created by liuguoquan on 2017/11/6 10:35.
 */

interface RepositoryContract {
    interface IRepositoryView : AppBaseView<IRepositoryPresenter> {
        fun showSuccess(items: List<RepositoryModel.RepositoryItem>)
        fun showError(error: String)
    }

    abstract class IRepositoryPresenter(provider: LifecycleProvider<FragmentEvent>,
                                        handler: PausedHandler) : AppBaseFragmentPresenter<IRepositoryView>(provider, handler) {
        abstract fun query(condition: RepositoryCondition)
    }
}
