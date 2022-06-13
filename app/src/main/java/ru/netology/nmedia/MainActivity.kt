package ru.netology.nmedia

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import ru.netology.nmedia.databinding.ActivityMainBinding
import ru.netology.nmedia.viewmodel.PostViewModel

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val viewModel: PostViewModel by viewModels()
        viewModel.data.observe(this) { post ->
            with(binding) {
                tvAuthor.text = post.author
                tvContent.text = post.content
                tvPublished.text = post.published
                tvLike.text = post.likes.toString()
                tvShare.text = post.shares.toString()
                ibLike.setImageResource(
                    if (post.likedByMe) R.drawable.ic_baseline_favorited_border_24
                    else R.drawable.ic_baseline_favorite_border_24
                )
                tvLike.text = totalCount(post.likes)
                tvShare.text = totalCount(post.shares)
                }
            }

            binding.ibLike.setOnClickListener {
                viewModel.like()
            binding.ibShare.setOnClickListener {
                viewModel.share()
            }
        }
    }
}