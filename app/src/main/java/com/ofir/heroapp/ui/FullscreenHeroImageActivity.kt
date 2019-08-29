package com.ofir.heroapp.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.ofir.heroapp.R
import kotlinx.android.synthetic.main.activity_fullscreen_hero_image.*

class FullscreenHeroImageActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_fullscreen_hero_image)
        fullscreen_hero_image.setImageResource(R.drawable.default_fullscreen)
    }
}
