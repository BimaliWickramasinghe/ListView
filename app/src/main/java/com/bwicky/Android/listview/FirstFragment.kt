package com.bwicky.Android.listview

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.bwicky.Android.listview.posts.api.PostApi
import com.bwicky.Android.listview.posts.models.Post
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit


/**
 * A simple [Fragment] subclass as the default destination in the navigation.
 */
class FirstFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val retrofit = Retrofit.Builder()
            .baseUrl("https://jsonplaceholder.typicode.com/")
            .build()

        var postApi= retrofit.create(PostApi::class.java)
        var postCall = postApi.post;
        postCall.enqueue(object : Callback<Post> {
            override fun onFailure(call: Call<Post>, t: Throwable) {

            }

            override fun onResponse(call: Call<Post>, response: Response<Post>) {
                var title = response.body()!!.title
                Log.d("FirstFragment",title)
            }
        })

        return inflater.inflate(R.layout.fragment_first, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        view.findViewById<Button>(R.id.button_first).setOnClickListener {
            findNavController().navigate(R.id.action_FirstFragment_to_SecondFragment)
        }
    }
}