package com.masamtechnologies.androidsimpleloadingview

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.view.View
import android.widget.FrameLayout
import kotlinx.android.synthetic.main.loading_view.view.*
import pl.droidsonroids.gif.GifDrawable

class SimpleLoadingView(context: Context, attrs: AttributeSet?) : FrameLayout(context, attrs) {

    val isBusy:Boolean get() =  counter > 0
    private var counter:Int = 0
    init {
        LayoutInflater.from(context).inflate(R.layout.loading_view, this, true)
        visibility = View.GONE
    }

    fun start(message: String? = "Loading...") {
        counter++
        if(visibility == View.GONE) {
            gif().start()
            visibility = View.VISIBLE
        }
        loading_text.text = message
    }

    fun stop(status: Boolean? = true,message:String? = null) {
        counter--
        if (counter <= 0)
            stopInternal()
    }

    private fun stopInternal() {
        if (gif().isRunning) {
            gif().stop()
            gif ().seekToFrame(Integer.MAX_VALUE)
        }
        loading_text.text = ""
        visibility = View.GONE
    }

    private fun gif(): GifDrawable = (gif_image_view.drawable as GifDrawable)

    fun reset() {
        counter = 0
        stopInternal()
    }
}
