@file:Suppress("DEPRECATION")

package com.example.movie.utils

import android.app.Activity
import android.app.ProgressDialog
import android.content.Context
import android.widget.Toast
import androidx.fragment.app.Fragment

private var progress:ProgressDialog? =null


fun Context.showToast(message:String){

    Toast.makeText(this,message,Toast.LENGTH_SHORT).show()
}

fun Context.showProgress(message:String){

    progress?.let { progress->

        if (!progress.isShowing) {
            progress.setMessage(message.ifEmpty { "please wait..." })
            progress.show()
        }
    }.run {

      progress = ProgressDialog(this@showProgress)
      progress?.setCancelable(false)
        if (!progress!!.isShowing) {
            progress!!.setMessage(message.ifEmpty { "please wait..." })
            progress!!.show()
        }
    }

}

fun Context.hideProgress(){


    progress?.let { progress->

        if(progress.isShowing)
            progress.cancel()
    }.run {

        progress = ProgressDialog(this@hideProgress)
        progress?.setCancelable(false)
    }




}


