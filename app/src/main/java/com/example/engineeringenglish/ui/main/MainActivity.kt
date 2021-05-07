package com.example.engineeringenglish.ui.main

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.PersistableBundle
import androidx.appcompat.widget.Toolbar
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupWithNavController
import com.example.engineeringenglish.R
import com.example.engineeringenglish.service.model.AppPreferences
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    lateinit var toolbar: Toolbar

    @SuppressLint("ResourceAsColor")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        AppPreferences.init(this)

        toolbar = findViewById(R.id.toolbar)
        toolbar.setTitleTextColor(R.color.colorBlack)
        setSupportActionBar(toolbar)

       if (savedInstanceState == null){
           initNavigation()
       }
    }

    override fun onSaveInstanceState(outState: Bundle, outPersistentState: PersistableBundle) {
        super.onSaveInstanceState(outState, outPersistentState)
        initNavigation()
    }

    private fun initNavigation(){
        val navController = findNavController(R.id.nav_host_fragment)
        val appBarConfiguration = AppBarConfiguration(navGraph = navController.graph)
        toolbar.setupWithNavController(navController, appBarConfiguration)
    }
}