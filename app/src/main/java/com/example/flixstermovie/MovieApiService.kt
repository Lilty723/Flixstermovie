package com.example.flixstermovie

class MovieApiService(private val context: Context) {
    private val baseUrl = "https://api.themoviedb.org/3/"
    private val apiKey = "a07e22bc18f5cb106bfe4cc1f83ad8ed"

    fun getNowPlayingMovies(callback: (List<Movie>?) -> Unit) {
        val url = "$baseUrl/movie/now_playing?api_key=$apiKey"
        val request = JsonObjectRequest(Request.Method.GET, url, null,
            { response ->
                val movies = parseMovies(response)
                callback(movies)
            },
            { error ->
                Log.e("MovieApiService", "Error fetching movies: ${error.message}")
                callback(null)
            }
        )
        Volley.newRequestQueue(context).add(request)
    }

    private fun parseMovies(response: JSONObject): List<Movie> {
        val movies = mutableListOf<Movie>()
        val resultsArray = response.getJSONArray("results")
        for (i in 0 until resultsArray.length()) {
            val movieObject = resultsArray.getJSONObject(i)
            val title = movieObject.getString("title")
            val description = movieObject.getString("overview")
            val posterPath = movieObject.getString("poster_path")
            val movie = Movie(title, description, posterPath)
            movies.add(movie)
        }
        return movies
    }
}
