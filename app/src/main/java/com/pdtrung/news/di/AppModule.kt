package com.pdtrung.news.di

import com.pdtrung.news.NewsApplication
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
interface AppModule {
    @ContributesAndroidInjector(modules = [ActivityModule::class])
    abstract fun assaa(): NewsApplication
}