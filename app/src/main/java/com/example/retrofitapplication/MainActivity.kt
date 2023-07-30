package com.example.retrofitapplication

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.core.view.isVisible
import androidx.recyclerview.widget.LinearLayoutManager
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import com.example.retrofitapplication.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    val baseUrl: String = "https://jsonplaceholder.typicode.com"

    lateinit var mainBinding: ActivityMainBinding

    var postsList: ArrayList<Posts> = ArrayList<Posts>()

    lateinit var postsAdapter: PostsAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mainBinding = ActivityMainBinding.inflate(layoutInflater)
        val view = mainBinding.root
        setContentView(view)

        mainBinding.recycleView.layoutManager = LinearLayoutManager(this)

        showPosts()
    }

    fun showPosts() {
        val retrofit = Retrofit.Builder()
            .baseUrl(baseUrl)
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        val retrofitApi: RetrofitApi = retrofit.create(RetrofitApi::class.java)

        val call = retrofitApi.getAllPosts()

        call.enqueue(object : Callback<List<Posts>>{
            override fun onResponse(call: Call<List<Posts>>, response: Response<List<Posts>>) {
                if (response.isSuccessful) {
                    postsList = response.body() as ArrayList<Posts>

                    postsAdapter = PostsAdapter(postsList)

                    mainBinding.recycleView.adapter = postsAdapter

                    mainBinding.progressBar.isVisible = false
                    mainBinding.recycleView.isVisible = true
                }
            }

            override fun onFailure(call: Call<List<Posts>>, t: Throwable) {
                Toast.makeText(applicationContext, t.localizedMessage, Toast.LENGTH_SHORT).show()
            }
        })

    }
}