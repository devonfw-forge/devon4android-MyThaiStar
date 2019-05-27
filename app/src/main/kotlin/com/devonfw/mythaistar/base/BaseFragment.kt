package com.devonfw.mythaistar.base

import android.app.Activity
import android.app.Fragment
import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.devonfw.mythaistar.common.ViewBinder
import com.devonfw.mythaistar.navigation.Navigator
import retrofit2.Retrofit
import javax.inject.Inject

typealias Screen = BaseFragment<*, *, *>

abstract class BaseFragment<in C : Any, V, P  : BasePresenter<V>>  : Fragment() {
    val id = javaClass.toString()

    val LOG_TAG = "Fragment State"


    @Inject protected lateinit var presenter: P

    @Inject protected lateinit var navigator: Navigator
    @Inject protected lateinit var retrofit: Retrofit
    @Suppress("UNCHECKED_CAST") // It is ok due to type erasure.
    private val baseActivity by lazy(LazyThreadSafetyMode.NONE) {
        activity as BaseActivity<C>
    }

    protected abstract val layoutId: Int


    protected abstract val ui: V

    //For Apis below 23
    override fun onAttach(activity: Activity?) {
        inject(baseActivity.component)
        super.onAttach(activity)
    }

    override fun onAttach(context: Context?) {
        Log.i(LOG_TAG, "onAttach")
        inject(baseActivity.component)
        super.onAttach(context)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, inState: Bundle?): View {
        Log.i(LOG_TAG, "onCreateView")
        return inflater.inflate(layoutId, container, false)
    }


    override fun onViewCreated(view: View, inState: Bundle?) {
        Log.i(LOG_TAG, "onViewCreated in BaseFragment")
        super.onViewCreated(view, inState)
        presenter.bindView(ui)
    }

    override fun onDestroyView() {
        Log.i(LOG_TAG, "onDestroyView in BaseFragment")
        presenter.unbindView(ui)
        ViewBinder.reset(this)
        super.onDestroyView()
    }

    open fun handleBackButton() = listOf(1, 2).isEmpty()

    abstract fun inject(component: C)

}
