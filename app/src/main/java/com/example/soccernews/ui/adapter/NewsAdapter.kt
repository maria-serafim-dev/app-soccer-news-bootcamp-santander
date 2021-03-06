package com.example.soccernews.ui.adapter

import android.content.Context
import android.content.Intent
import android.net.Uri
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.soccernews.R
import com.example.soccernews.databinding.NewsItemBinding
import com.example.soccernews.domain.News

class NewsAdapter (private val favoriteListener: (News) -> Unit) : ListAdapter<News, NewsAdapter.ViewHolder>(NewsCallback()) {

    private lateinit var binding: NewsItemBinding


    inner class ViewHolder (private val binding: NewsItemBinding) : RecyclerView.ViewHolder (binding.root) {
        fun onBind(news: News, context: Context, favoriteClickListener: (News) -> Unit, position: Int){
            binding.tvTitle.text = news.title
            binding.tvDescription.text = news.description
            Glide.with(context).load(news.image).into(binding.ivThumbnail)

            binding.btOpenLink.setOnClickListener {
                val queryUrl: Uri = Uri.parse(news.link)
                val intent = Intent(Intent.ACTION_VIEW, queryUrl)
                context.startActivity(intent)
            }

            binding.ivShare.setOnClickListener {
                val intent = Intent(Intent.ACTION_SEND)
                intent.type = "text/pain"
                intent.putExtra(Intent.EXTRA_SUBJECT, news.title)
                intent.putExtra(Intent.EXTRA_TEXT, news.link)
                context.startActivity(Intent.createChooser(intent, "Share via"))
            }

            binding.ivFavorite.setOnClickListener{
                news.favorite = !news.favorite
                favoriteClickListener(news)
                notifyItemChanged(position)
            }

            if (news.favorite) binding.ivFavorite.setColorFilter(ContextCompat.getColor(context, R.color.pink_500)) else binding.ivFavorite.setColorFilter(ContextCompat.getColor(context, R.color.gray))
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        binding = NewsItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val news = getItem(position)
        holder.onBind(news, holder.itemView.context, favoriteListener, position)
    }

}

class NewsCallback : DiffUtil.ItemCallback<News>() {
    override fun areItemsTheSame(oldItem: News, newItem: News): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: News, newItem: News): Boolean {
        return oldItem == newItem
    }
}
