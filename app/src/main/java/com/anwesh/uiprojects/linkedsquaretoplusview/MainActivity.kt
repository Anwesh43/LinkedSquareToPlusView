package com.anwesh.uiprojects.linkedsquaretoplusview

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import com.anwesh.uiprojects.squaretoplusview.SquareToPlusView

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        SquareToPlusView.create(this)
    }
}
