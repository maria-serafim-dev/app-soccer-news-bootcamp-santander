package com.example.soccernews.ui.favorites

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.soccernews.MainActivity
import com.example.soccernews.databinding.FragmentFavoritesBinding
import com.example.soccernews.domain.News
import com.example.soccernews.ui.adapter.NewsAdapter
import com.example.soccernews.ui.adapter.NewsListener

class FavoritesFragment : Fragment() {

    private var _binding: FragmentFavoritesBinding? = null

    private val binding get() = _binding!!


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {


        _binding = FragmentFavoritesBinding.inflate(inflater, container, false)
        return binding.root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setLayoutManager()
        loadFavoriteNews()
    }


    private fun setLayoutManager() {
        binding.rvFavorites.layoutManager = LinearLayoutManager(context)
    }

    private fun loadFavoriteNews() {
        val activity: MainActivity = activity as MainActivity
        val favoriteNews = activity.db.newsDao().loadFavoriteNews()

        binding.rvFavorites.adapter = NewsAdapter(favoriteNews, object : NewsListener {
            override fun onFavorite(news: News) {
                activity.db.newsDao().save(news)
                loadFavoriteNews()
            }
        })
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}