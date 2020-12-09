package com.pdtrung.news.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.pdtrung.news.databinding.FragmentDetailBinding
import com.pdtrung.news.di.Injectable

class DetailFragment : Fragment(), Injectable {
    //  private val args : NewsDetailFragmentArgs by navArgs()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val binding = FragmentDetailBinding.inflate(inflater, container, false)

        binding.ivBack.setOnClickListener {
            activity?.supportFragmentManager?.popBackStack()
        }

        /*val newsModel = args.newsItem
        binding.newsDetail = newsModel*/

        return binding.root
    }
}