package com.example.soccernews.ui.news

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.soccernews.MainActivity
import com.example.soccernews.databinding.FragmentNewsBinding
import com.example.soccernews.domain.News
import com.example.soccernews.ui.adapter.NewsAdapter
import com.example.soccernews.ui.adapter.NewsListener

class NewsFragment : Fragment() {


    private var _binding: FragmentNewsBinding? = null
    private val newsViewModel : NewsViewModel by viewModels()

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

        setLayoutManager()
        observeChanges()
    }



    private fun setLayoutManager() {
        binding.rvNews.layoutManager = LinearLayoutManager(context)
    }

    private fun observeChanges() {
        val activity: MainActivity = activity as MainActivity

        newsViewModel.news.observe(viewLifecycleOwner) {
            binding.rvNews.adapter = NewsAdapter(it, object : NewsListener {
                override fun onFavorite(news: News) {
                    activity.db.newsDao().save(news)
                }
            })
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}