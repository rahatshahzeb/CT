package com.android.task.binding

import android.view.View
import androidx.databinding.BindingAdapter

object BindingAdapters {

    /**
     * A Binding Adapter that is called whenever the value of the attribute `visibleGone`
     * changes.
     */
    @JvmStatic
    @BindingAdapter("visibleGone")
    fun setGone(view: View, show: Boolean) {
        view.visibility = if (show) {
            View.VISIBLE
        } else {
            View.GONE
        }

        if (show) {
            view.animate().alpha(1.0f)
        } else {
            view.animate().alpha(0.0f)
        }
    }

}