package com.pdtrung.news.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.pdtrung.news.api.Status
import com.pdtrung.news.databinding.FragmentListBinding
import com.pdtrung.news.di.Injectable
import com.pdtrung.news.di.injectViewModel
import com.pdtrung.news.util.ConnectivityUtil
import kotlinx.android.synthetic.main.fragment_list.*
import javax.inject.Inject

class ListFragment : Fragment(), Injectable {

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory
    private lateinit var viewModel: NewsViewModel
    private lateinit var binding: FragmentListBinding
    private var isConnected : Boolean = true
    val newsViewModel : NewsViewModel by viewModels { viewModelFactory }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        binding = FragmentListBinding.inflate(inflater, container, false)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = injectViewModel(viewModelFactory)
        isConnected = ConnectivityUtil.isConnected(context)
        if (!isConnected)
            Toast.makeText(context?.applicationContext,"No internet connection!",Toast.LENGTH_SHORT).show()

        val adapter = NewsAdapter()
        binding.rvNewsList.adapter = adapter
        subscribeUI(adapter)

    }

    private fun subscribeUI(adapter: NewsAdapter) {
        val data = viewModel.newsList(isConnected)
        data?.networkState?.observe(viewLifecycleOwner, Observer {
            when(it.status) {
                Status.RUNNING -> {
                    progressBar.visibility = View.VISIBLE
                }
                Status.FAILED -> {
                    progressBar.visibility = View.GONE
                    // Handle fail state
                }
                Status.SUCCESS -> {
                    progressBar.visibility = View.GONE
                }
            }
        })
        data?.pagedList?.observe(viewLifecycleOwner, Observer {
            adapter.submitList(it)
        })
    }
}