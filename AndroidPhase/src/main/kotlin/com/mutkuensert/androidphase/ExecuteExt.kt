package com.mutkuensert.androidphase

fun <T : Any> Phase<T>.execute(
    onStandby: (T?) -> Unit = {},
    onLoading: (T?) -> Unit = {},
    onSuccess: (T) -> Unit = {},
    onError: (Phase.Error<T>) -> Unit = {}
) {
    when (this) {
        is Phase.Standby -> onStandby.invoke(data)
        is Phase.Loading -> onLoading.invoke(data)
        is Phase.Success -> onSuccess.invoke(data)
        is Phase.Error -> onError.invoke(this)
    }
}