package com.illegal.funime.data

sealed class DataResult<out T> {
    data class Error(val e :Exception) : DataResult<Nothing>()
    data class Success<T>(val data: T) : DataResult<T>()
    object Loading : DataResult<Nothing>()
}