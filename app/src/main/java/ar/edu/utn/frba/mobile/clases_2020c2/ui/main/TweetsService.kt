package ar.edu.utn.frba.mobile.clases_2020c2.ui.main

import retrofit2.Call
import retrofit2.http.GET

interface TweetsService {
    @GET("list")
    fun getTweets(): Call<GetTweetsResponse>
}
