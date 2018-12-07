package ru.dogwalk.progressbardog

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.view.View
import android.widget.LinearLayout
import android.widget.ProgressBar

class ProgressBarDog(context: Context?, attrs: AttributeSet?) : LinearLayout(context, attrs) {
    private var pbDog: ProgressBar? = null
    private var llContent: LinearLayout? = null

    init {
        val view = LayoutInflater.from(getContext()).inflate(R.layout.progress_bar, this)
        pbDog = view.findViewById(R.id.progress_bar)
        llContent = view.findViewById(R.id.ll_content)
    }

    fun startProgress() {
        if (pbDog != null) {
            llContent?.visibility = View.VISIBLE
            pbDog?.visibility = View.VISIBLE
        }
    }

    fun stopProgress() {
        if (pbDog != null) {
            llContent?.visibility = View.GONE
            pbDog?.visibility = View.INVISIBLE
        }
    }
}