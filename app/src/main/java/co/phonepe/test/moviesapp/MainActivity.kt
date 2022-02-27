package co.phonepe.test.moviesapp

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import android.view.Menu
import android.view.MenuItem
import androidx.activity.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import co.phonepe.test.moviesapp.data.remote.models.DataPacket
import co.phonepe.test.moviesapp.databinding.ActivityMainBinding
import co.phonepe.test.moviesapp.extensions.gone
import co.phonepe.test.moviesapp.extensions.visible
import co.phonepe.test.moviesapp.ui.MoviesListAdapter
import co.phonepe.test.moviesapp.ui.MoviesListAdapterContract

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private val mViewModel: MainViewModel by viewModels()
    private val movieData: ArrayList<DataPacket> = ArrayList()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setSupportActionBar(binding.toolbar)
        setupUi()
        setUpData()
    }

    private fun setUpData() {
        mViewModel.getMoviesData().observe(this){
            when(it.status){
                Status.SUCCESS -> {
                    binding.content.loader.gone()
                    movieData.clear()
                    movieData.addAll(it.data!!)
                    binding.content.rvData.adapter?.notifyDataSetChanged()
                }
                Status.ERROR -> TODO()
                Status.LOADING -> binding.content.loader.visible()
            }
        }
    }

    private fun setupUi() {
        binding.content.rvData.layoutManager = LinearLayoutManager(this)
        binding.content.rvData.adapter = MoviesListAdapter(movieData, object : MoviesListAdapterContract{
            override fun onClickAddToPlayList(videoId: Int) {
                TODO()
            }
        })

        binding.fab.setOnClickListener { view ->
            mViewModel.getPlayLists().observe(this){
            }
        }
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.menu_main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.action_settings -> true
            else -> super.onOptionsItemSelected(item)
        }
    }
}