package com.example.flixstermovie

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }
    class MainActivity : AppCompatActivity() {

        private lateinit var movieApiService: MovieApiService
        private lateinit var movieAdapter: MovieAdapter

        override fun onCreate(savedInstanceState: Bundle?) {
            super.onCreate(savedInstanceState)
            setContentView(R.layout.activity_main)

            movieApiService = MovieApiService(this)

            val recyclerView = findViewById<RecyclerView>(R.id.recyclerView)
            recyclerView.layoutManager = LinearLayoutManager(this)

            movieApiService.getNowPlayingMovies { movies ->
                movies?.let {
                    movieAdapter = MovieAdapter(it)
                    recyclerView.adapter = movieAdapter
                } ?: run {
                    // Handle error or show empty state
                }
            }
        }
    }

}