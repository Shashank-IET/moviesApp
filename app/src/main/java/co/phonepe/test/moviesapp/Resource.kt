package co.phonepe.test.moviesapp

data class Resource<out T>(val status: Status, val data: T?, val exception: Exception? = null) {
    companion object {
        fun <T> success(data: T?): Resource<T> {
            return Resource(Status.SUCCESS, data, null)
        }

        fun <T> error(exception: Exception? = null, data: T? = null): Resource<T> {
            return Resource(Status.ERROR, data, exception = exception)
        }

        fun <T> loading(data: T? = null): Resource<T> {
            return Resource(Status.LOADING, data, null)
        }
    }
}

enum class Status {
    SUCCESS,
    ERROR,
    LOADING
}
