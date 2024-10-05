package ru.easycode.zerotoheroandroidtdd

import java.net.UnknownHostException

interface Repository {

    suspend fun load(): LoadResult

    class Base(
        private val service: SimpleService,
        private val url: String
    ) : Repository {
        override suspend fun load(): LoadResult {
            return try {
                val response = service.fetch(url)
                LoadResult.Success(data = response)
            } catch (e: Throwable) {
                LoadResult.Error(noConnection = e is UnknownHostException)
            }
        }
    }
}
