package com.mutkuensert.androidphase

import androidx.compose.runtime.Composable

sealed class Phase<T>(
    open val data: T? = null,
    open val message: String?,
    open val error: Throwable? = null
) {

    class Standby<T>(
        override val data: T? = null,
        override val message: String? = null,
        override val error: Throwable? = null
    ) : Phase<T>(data, message, error)

    class Loading<T>(
        override val data: T? = null,
        override val message: String? = null,
        override val error: Throwable? = null
    ) : Phase<T>(data, message, error)

    class Success<T>(
        override val data: T? = null,
        override val message: String? = null,
        override val error: Throwable? = null
    ) : Phase<T>(data, message, error)

    class Error<T>(
        override val data: T? = null,
        override val message: String? = null,
        override val error: Throwable? = null
    ) : Phase<T>(data, message, error)

    /**
     * @param onSuccessWithData It is invoked if data is not null.
     */
    fun execute(
        onStandby: Phase<T>.() -> Unit = {},
        onLoading: Phase<T>.() -> Unit = {},
        onSuccess: Phase<T>.() -> Unit = {},
        onSuccessWithData: (Phase<T>.(data: T) -> Unit)? = null,
        onError: Phase<T>.() -> Unit = {}
    ) {
        when (this) {
            is Standby -> onStandby.invoke(this)

            is Loading -> onLoading.invoke(this)

            is Success -> {
                if (onSuccessWithData == null) {
                    onSuccess.invoke(this)
                } else {
                    if (data != null) {
                        onSuccessWithData.invoke(this, data!!)
                    }
                }
            }

            is Error -> onError.invoke(this)
        }
    }

    /**
     * @param onSuccessWithData It is invoked if data is not null.
     */
    @Composable
    fun Execute(
        onStandby: @Composable Phase<T>.() -> Unit = {},
        onLoading: @Composable Phase<T>.() -> Unit = {},
        onSuccess: @Composable Phase<T>.() -> Unit = {},
        onSuccessWithData: (@Composable Phase<T>.(data: T) -> Unit)? = null,
        onError: @Composable Phase<T>.() -> Unit = {}
    ) {
        when (this) {
            is Standby -> onStandby.invoke(this)
            is Loading -> onLoading.invoke(this)
            is Success -> {
                if (onSuccessWithData == null) {
                    onSuccess.invoke(this)
                } else {
                    if (data != null) {
                        onSuccessWithData.invoke(this, data!!)
                    }
                }
            }

            is Error -> onError.invoke(this)
        }
    }
}