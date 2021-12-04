package Presentacion

import com.example.marveltptaller.Modelo.Character
import android.graphics.Rect
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ItemDecoration
import com.example.marveltptaller.android.databinding.ActivityCharactersBinding
//import ar.edu.unlam.com.example.marveltptaller.android.marvelcharacters.databinding.ActivityCharactersBinding
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

class CharactersActivity : AppCompatActivity() {

    private lateinit var charactersAdapter: CharactersAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //val binding = ActivityMainBinding.inflate(layoutInflater)
        val binding = ActivityCharactersBinding.inflate(layoutInflater)
        //val binding =
        setContentView(binding.root)

        // Setup del listado
        charactersAdapter = CharactersAdapter()
        val verticalLayoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
        with(binding.charactersList) {
            this.adapter = charactersAdapter
            this.layoutManager = verticalLayoutManager
            this.addItemDecoration(VerticalSpaceItemDecoration(16))
        }

        // Listen to Retrofit response
        val viewModel = ViewModelProvider(this, CharactersViewModelFactory())[CharactersViewModel::class.java]
        lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.CREATED) {
                viewModel.screenState.collect {
                    when (it) {
                        ScreenState.Loading -> showLoading()
                        is ScreenState.ShowCharacters -> showCharacters(it.list)
                    }
                }
            }
        }
    }

    private fun showLoading() {

    }

    private fun showCharacters(list: List<Character>) {
        charactersAdapter.submitList(list)
    }

}

class VerticalSpaceItemDecoration(private val verticalSpaceHeight: Int) : ItemDecoration() {

    override fun getItemOffsets(outRect: Rect, view: View, parent: RecyclerView, state: RecyclerView.State) {
        outRect.bottom = verticalSpaceHeight
    }

}
