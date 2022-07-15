package com.example.myapprepositories.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.Menu
import androidx.appcompat.widget.SearchView
import com.example.myapprepositories.R
import com.example.myapprepositories.core.createDialog
import com.example.myapprepositories.core.createProgressDialog
import com.example.myapprepositories.core.hideSoftKeyboard
import com.example.myapprepositories.data.repositories.RepoRepository
import com.example.myapprepositories.databinding.ActivityMainBinding
import com.example.myapprepositories.presentation.MainViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

class MainActivity : AppCompatActivity(), SearchView.OnQueryTextListener {

    private val binding by lazy{ ActivityMainBinding.inflate(layoutInflater)}
    private val adapter by lazy { RepoListAdapter()}
    private val viewModel by viewModel<MainViewModel>()
    private val dialog  by lazy{ createProgressDialog()}

    companion object{
        private const val TAG = "TAG"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        setSupportActionBar(binding.toolbar)

        binding.rvRepos.adapter = adapter
        viewModel.getRepoList("dmmatano")

        viewModel.repos.observe(this){
            when(it){
                MainViewModel.State.Loading -> dialog.show()
                is MainViewModel.State.Error ->{
                    createDialog {
                        setMessage(it.error.message)
                    }.show()
                    dialog.dismiss()
                }
                is MainViewModel.State.Success ->{
                    dialog.dismiss()
                    adapter.submitList(it.list)
                }
            }
        }
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.main_menu, menu)
        val searchView = menu.findItem(R.id.action_search).actionView as SearchView
        searchView.setOnQueryTextListener(this)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onQueryTextSubmit(p0: String?): Boolean {
        p0?.let{
            viewModel.getRepoList(it)
        }
        binding.root.hideSoftKeyboard()
        return true
    }

    override fun onQueryTextChange(p0: String?): Boolean {
        Log.e(TAG, "onQueryTextChange: $p0" )
        return false
    }
}