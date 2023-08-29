package com.mutkuensert.androidphase

sealed class Phase<T : Any> {

    class Standby<T : Any>(
        val data: T? = null
    ) : Phase<T>()

    class Loading<T : Any>(
        val data: T? = null
    ) : Phase<T>()

    class Success<T : Any>(
        val data: T
    ) : Phase<T>()

    class Error<T : Any>(
        val data: T? = null,
        val message: String? = null,
        val error: Throwable? = null
    ) : Phase<T>()
}
