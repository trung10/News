package com.pdtrung.news

import android.app.Activity
import android.app.Application
import com.facebook.stetho.Stetho
import com.pdtrung.news.di.AppInjector
import dagger.android.DispatchingAndroidInjector
import dagger.android.HasActivityInjector
import javax.inject.Inject

class NewsApplication : Application(), HasActivityInjector {

    @Inject
    lateinit var dispatchingAndroidInjector: DispatchingAndroidInjector<Activity>

    override fun onCreate() {
        super.onCreate()
        if (BuildConfig.DEBUG) Stetho.initializeWithDefaults(this)

        AppInjector.init(this)
    }

    override fun activityInjector() = dispatchingAndroidInjector
}