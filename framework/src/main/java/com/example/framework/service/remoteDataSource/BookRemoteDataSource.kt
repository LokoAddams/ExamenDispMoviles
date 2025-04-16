package com.example.framework.service.remoteDataSource

import com.example.data.NetworkResult
import com.example.data.book.IBookRemoteDataSource
import com.example.domain.Libro
import com.example.framework.service.mappers.toModel
import com.example.framework.service.service.RetrofitBuilder

class BookRemoteDataSource(
    val retrofiService: RetrofitBuilder
): IBookRemoteDataSource {
    override suspend fun buscar(bookTitle: String): NetworkResult<List<Libro>> {
        println("Buscando libro con el título: $bookTitle")
        println(retrofiService.apiService.toString())
        val response = retrofiService.apiService.getLibros("the+lord+of+the+rings")
        println(response.toString())
        if(response.isSuccessful) {
            println("Respuesta exitosa")
            println(response.body()!!.libros.toString())
            val librosBuscados = response.body()?.libros?.filter { it.titulo == bookTitle }.orEmpty()
            if (librosBuscados.isEmpty()) {
                println("entreee")
                return NetworkResult.Error("No se encontraron libros")
            }
            return NetworkResult.Success(librosBuscados.map { it.toModel() })
        } else {
            return NetworkResult.Error(response.message())
        }
    }
    override suspend fun buscarPorId(id: String): NetworkResult<Libro> {
        val response = retrofiService.apiService.getLibros("the+lord+of+the+rings")
        if(response.isSuccessful) {
            println("Respuesta exitosa2")
            println(response.body()!!.libros.toString())
            val librosBuscados = response.body()?.libros?.find( { it.key == id })
            if (librosBuscados == null) {
                println("entreee2")
                return NetworkResult.Error("El libro" + id + " ya no se encuentra disponible")
            }
            return NetworkResult.Success(librosBuscados.toModel())
        } else {
            return NetworkResult.Error(response.message())
        }
    }
}