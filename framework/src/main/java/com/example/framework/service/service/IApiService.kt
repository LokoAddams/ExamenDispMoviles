package com.example.framework.service.service

import com.example.framework.service.dto.ListaLibrosDto
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface IApiService {
    @GET("/search.json")
    suspend fun getLibros(@Query("q") query: String): Response<ListaLibrosDto>
}
