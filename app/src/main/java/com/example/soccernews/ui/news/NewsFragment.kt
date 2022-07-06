package com.example.soccernews.ui.news

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.soccernews.databinding.FragmentNewsBinding
import com.example.soccernews.ui.adapter.NewsAdapter

class NewsFragment : Fragment() {

    private var _binding: FragmentNewsBinding? = null


    private val binding get() = _binding!!


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentNewsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val newsViewModel = ViewModelProvider(this)[NewsViewModel::class.java]
        binding.rvNews.layoutManager = LinearLayoutManager(context)
        newsViewModel.news.observe(viewLifecycleOwner) {
            binding.rvNews.adapter = NewsAdapter(it)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}