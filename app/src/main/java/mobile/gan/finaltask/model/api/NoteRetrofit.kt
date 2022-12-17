package mobile.gan.finaltask.model.api

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object NoteRetrofit {
    fun getRetrofit() =
        Retrofit.Builder()
            .baseUrl("https://jsonplaceholder.typicode.com/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(NoteApi::class.java)
}