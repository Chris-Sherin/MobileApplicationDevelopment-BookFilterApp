package com.example.bookfilterapp

import retrofit2.http.GET

interface HttpApiService {
    @GET("books")
    suspend fun getFilter(): List<FilterBook>
}