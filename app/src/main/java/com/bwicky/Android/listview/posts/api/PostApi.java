package com.bwicky.Android.listview.posts.api;

import com.bwicky.Android.listview.posts.models.Post;

import retrofit2.Call;
import retrofit2.http.GET;

public interface PostApi {

    //  https://jsonplaceholder.typicode.com/

    @GET("/posts/1")
    Call<Post> getPost();
}
