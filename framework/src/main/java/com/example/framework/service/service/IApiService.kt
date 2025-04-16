package com.example.framework.service.service

import com.example.framework.service.dto.LibroByIdDto
import com.example.framework.service.dto.ListaLibrosDto
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query
import retrofit2.http.Url

interface IApiService {
    @GET("/search.json")
    suspend fun getLibros(@Query("q") query: String): Response<ListaLibrosDto>

    @GET
    suspend fun getLibroById(@Url query: String): Response<LibroByIdDto>
}
