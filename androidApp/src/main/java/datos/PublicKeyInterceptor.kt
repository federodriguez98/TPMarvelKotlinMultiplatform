package datos

import okhttp3.Interceptor
import okhttp3.Response

class PublicKeyInterceptor : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val keys = Keys()
        val request = chain.request()
        val url = request.url

        //val newUrl = url.newBuilder().addQueryParameter("apikey", BuildConfig.PUBLIC_KEY as String?).build()
        val newUrl = url.newBuilder().addQueryParameter("apikey",keys.kPublic as String?).build()
        return chain.proceed(request.newBuilder().url(newUrl).build())
    }
}