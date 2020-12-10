package com.pdtrung.news.di

import com.pdtrung.news.ui.ListFragment
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class FragmentBuildersModule {
    @ContributesAndroidInjector
    abstract fun contributeThemeFragment(): ListFragment
}