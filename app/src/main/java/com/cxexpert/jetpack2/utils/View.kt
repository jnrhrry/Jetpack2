package com.cxexpert.jetpack2.utils

import android.view.View
import android.widget.ProgressBar

fun ProgressBar.show(){
    visibility = View.VISIBLE
}

fun ProgressBar.gone(){
    visibility = View.GONE
}