package com.bwicky.Android.listview

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.bwicky.Android.listview.posts.api.PostApi
import com.bwicky.Android.listview.posts.models.Post
import com.google.gson.GsonBuilder
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


/**
 * A simple [Fragment] subclass as the default destination in the navigation.
 */
class FirstFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        Log.i("FirstFragment","OnCreateView")


        return inflater.inflate(R.layout.fragment_first, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val gson = GsonBuilder()
                .create()
        // Inflate the layout for this fragment
        val retrofit = Retrofit.Builder()
                .baseUrl("https://jsonplaceholder.typicode.com/")
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build()

        var postApi= retrofit.create(PostApi::class.java)
        var postCall = postApi.post;
        postCall.enqueue(object : Callback<Post> {
            override fun onFailure(call: Call<Post>, t: Throwable) {
                Log.d("FirstFragment","Error")
            }

            override fun onResponse(call: Call<Post>, response: Response<Post>) {
                var title = response.body()!!.title
                Log.d("FirstFragment","Title is" +title)
                view.findViewById<TextView>(R.id.title_textview).text = title
            }
        })

    }
}