package com.mutkuensert.androidphase

import androidx.compose.runtime.Composable

sealed class Phase<T : Any> {

    class Standby<T : Any>(
        val data: T? = null,
        val message: String? = null,
        val error: Throwable? = null
    ) : Phase<T>()

    class Loading<T : Any>(
        val data: T? = null,
        val message: String? = null,
        val error: Throwable? = null
    ) : Phase<T>()

    class Success<T : Any>(
        val data: T,
        val message: String? = null,
        val error: Throwable? = null
    ) : Phase<T>()

    class Error<T : Any>(
        val data: T? = null,
        val message: String? = null,
        val error: Throwable? = null
    ) : Phase<T>()

    fun execute(
        onStandby: Phase<T>.() -> Unit = {},
        onLoading: Phase<T>.() -> Unit = {},
        onSuccess: Phase<T>.() -> Unit = {},
        onError: Phase<T>.() -> Unit = {}
    ) {
        when (this) {
            is Standby -> onStandby.invoke(this)
            is Loading -> onLoading.invoke(this)
            is Success -> onSuccess.invoke(this)
            is Error -> onError.invoke(this)
        }
    }

    @Composable
    fun Execute(
        onStandby: @Composable Standby<T>.() -> Unit = {},
        onLoading: @Composable Loading<T>.() -> Unit = {},
        onSuccess: @Composable Success<T>.() -> Unit = {},
        onError: @Composable Error<T>.() -> Unit = {}
    ) {
        when (this) {
            is Standby -> onStandby.invoke(this)
            is Loading -> onLoading.invoke(this)
            is Success -> onSuccess.invoke(this)
            is Error -> onError.invoke(this)
        }
    }
}