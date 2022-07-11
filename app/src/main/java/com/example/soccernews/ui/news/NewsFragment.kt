package com.example.soccernews.ui.news

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.soccernews.NewsApplication
import com.example.soccernews.R
import com.example.soccernews.databinding.FragmentNewsBinding
import com.example.soccernews.domain.News
import com.example.soccernews.ui.adapter.NewsAdapter
import com.google.android.material.snackbar.Snackbar

class NewsFragment : Fragment() {


    private var _binding: FragmentNewsBinding? = null

    private val newsViewModel : NewsViewModel by viewModels {
        NewsViewModelFactory((activity?.application as NewsApplication).repository)
    }

    private val adapter : NewsAdapter by lazy {
        NewsAdapter{ news ->
            saveNews(news)
        }
    }

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
        observeChangesSwipe()
        swipeRefreshListener()
    }

    private fun observeChangesSwipe() {
        newsViewModel.state.observe(viewLifecycleOwner) {
            when (it) {
                State.DOING -> binding.srlNews.isRefreshing = true
                State.DONE -> binding.srlNews.isRefreshing = false
                State.ERROR -> {
                    binding.srlNews.isRefreshing = false
                    Snackbar.make(binding.srlNews, getString(R.string.text_error_network), Snackbar.LENGTH_LONG).show()
                }
                else -> binding.srlNews.isRefreshing = false
            }
        }
    }

    private fun swipeRefreshListener() {
        binding.srlNews.setOnRefreshListener {
            newsViewModel.getNews()
        }
    }

    private fun setLayoutManager() {
        binding.rvNews.layoutManager = LinearLayoutManager(context)
    }

    private fun observeChanges() {
        binding.rvNews.adapter = adapter

        newsViewModel.news.observe(viewLifecycleOwner) { listNewsRemote ->
            newsViewModel.newsRoom.observe(viewLifecycleOwner) { listNewsRoom ->
                checkFavorite(listNewsRemote, listNewsRoom)
                adapter.submitList(listNewsRemote)
            }
        }
    }

    private fun checkFavorite(
        listNewsRemote: MutableList<News>,
        listNewsRoom: List<News>
    ) {
        listNewsRemote.forEach { newRemote ->
            val result = listNewsRoom.any { listRoom -> listRoom.id == newRemote.id }
            newRemote.favorite = result
        }
    }

    private fun saveNews(news: News){
        newsViewModel.saveNews(news)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}