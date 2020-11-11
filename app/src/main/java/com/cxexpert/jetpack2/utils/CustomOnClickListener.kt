package com.cxexpert.jetpack2.utils

import android.view.View

class CustomOnClickListener (private val onItemClickCallback: OnItemClickCallback): View.OnClickListener{
    interface OnItemClickCallback {
        fun onItemClicked(v: View)
    }

    override fun onClick(p0: View) {
        onItemClickCallback.onItemClicked(p0)
    }
}