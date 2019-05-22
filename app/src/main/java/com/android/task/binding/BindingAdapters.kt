package com.android.task.binding

import android.view.View
import androidx.databinding.BindingAdapter

object BindingAdapters {

    /**
     * A Binding Adapter that is called whenever the value of the attribute `visibleInvisible`
     * changes.
     */
    @JvmStatic
    @BindingAdapter("visibleInvisible")
    fun setInvisible(view: View, show: Boolean) {
        view.visibility = if (show) View.VISIBLE else View.INVISIBLE
    }

    /**
     * A Binding Adapter that is called whenever the value of the attribute `visibleGone`
     * changes.
     */
    @JvmStatic
    @BindingAdapter("visibleGone")
    fun setGone(view: View, show: Boolean) {
        view.visibility = if (show) View.VISIBLE else View.GONE
    }

}