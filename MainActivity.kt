package com.yourname.ioslauncherandroidtv


import android.content.Intent

import android.content.pm.PackageManager

import android.os.Bundle

import android.view.View

import androidx.appcompat.app.AppCompatActivity

import androidx.recyclerview.widget.GridLayoutManager

import androidx.recyclerview.widget.RecyclerView


class MainActivity : AppCompatActivity() {

    private lateinit var appList: ArrayList<AppItem>

    private lateinit var adapter: AppAdapter


    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_main)


        appList = ArrayList()

        loadInstalledApps()


        val recyclerView = findViewById<RecyclerView>(R.id.recyclerView)

        recyclerView.layoutManager = GridLayoutManager(this, 5)

        adapter = AppAdapter(this, appList)

        recyclerView.adapter = adapter

    }


    private fun loadInstalledApps() {

        val pm = packageManager

        val apps = pm.getInstalledApplications(PackageManager.GET_META_DATA)


        for (app in apps) {

            if (pm.getLaunchIntentForPackage(app.packageName) != null) {

                val label = pm.getApplicationLabel(app).toString()

                val icon = pm.getApplicationIcon(app)

                appList.add(AppItem(label, app.packageName, icon))

            }

        }

    }

}// MainActivity content
