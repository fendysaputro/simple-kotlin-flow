package id.phephen.kotlinflow.presentation

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.core.view.isVisible
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import id.phephen.kotlinflow.R
import id.phephen.kotlinflow.databinding.ActivityMainBinding
import id.phephen.kotlinflow.network.Status
import id.phephen.kotlinflow.viewmodel.CommentViewModel
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {

    private lateinit var viewModel: CommentViewModel
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        viewModel = ViewModelProvider(this)[CommentViewModel::class.java]

        binding.button.setOnClickListener {
            if (binding.searchEditText.text.isNullOrEmpty()) {
                Toast.makeText(this, "Query can't be empty", Toast.LENGTH_SHORT).show()
            } else {
                viewModel.getNewComment(binding.searchEditText.text.toString().toInt())
            }
        }

        lifecycleScope.launch {
            viewModel.commentState.collect {
                when(it.status) {
                    Status.LOADING -> {
                        binding.progressBar.isVisible = true
                    }
                    Status.SUCCESS -> {
                        binding.progressBar.isVisible = false
                        it.data?.let { comment ->
                            binding.commentIdTextview.text = comment.id.toString()
                            binding.commentTextview.text = comment.comment
                            binding.nameTextview.text = comment.name
                            binding.emailTextview.text = comment.email
                        }
                    }
                    else -> {
                        binding.progressBar.isVisible = false
                        Toast.makeText(this@MainActivity, "${it.message}", Toast.LENGTH_SHORT).show()
                    }
                }
            }
        }

    }
}