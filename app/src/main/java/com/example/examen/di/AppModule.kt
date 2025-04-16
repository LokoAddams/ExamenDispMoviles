package com.example.examen.di


import android.content.Context
import com.example.data.book.BookRepository
import com.example.data.book.IBookRemoteDataSource
import com.example.data.persistence.ILocalDataSource
import com.example.framework.service.persistence.LocalDataSourceImpl
import com.example.framework.service.remoteDataSource.BookRemoteDataSource
import com.example.framework.service.service.RetrofitBuilder
import com.example.usecases.SearchBooksUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun providerSearchBooksUseCase(bookRepository: BookRepository, @ApplicationContext context: Context) : SearchBooksUseCase {
        return SearchBooksUseCase(bookRepository)
    }

    @Provides
    @Singleton
    fun providerSearchBookRepository(remoteDataSource: IBookRemoteDataSource,
                                     localDataSource: ILocalDataSource
    ) : BookRepository {
        return BookRepository(remoteDataSource,localDataSource)
    }

    @Provides
    @Singleton
    fun provideMovieIBookRemoteDataSource(retrofit:RetrofitBuilder ): IBookRemoteDataSource {
        return BookRemoteDataSource(retrofit)
    }

    @Provides
    @Singleton
    fun provideLocalDataSource(@ApplicationContext context: Context): ILocalDataSource {
        return LocalDataSourceImpl(context)
    }

    @Provides
    @Singleton
    fun providerRetrofitBuilder(@ApplicationContext context: Context) : RetrofitBuilder {
        return RetrofitBuilder(context)
    }
}