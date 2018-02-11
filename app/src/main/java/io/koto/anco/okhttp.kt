package io.koto.anco

import kotlinx.coroutines.experimental.CommonPool
import kotlinx.coroutines.experimental.suspendCancellableCoroutine
import kotlinx.coroutines.experimental.withContext
import okhttp3.Call
import okhttp3.Callback
import okhttp3.Response
import okhttp3.ResponseBody
import java.io.IOException

/**
 * Created by Administrator on 2018/2/11.
 */

suspend fun awaitCallback(block:(Callback)->Unit) : Response {
    return suspendCancellableCoroutine {
        block(object : Callback {
            override fun onFailure(call: Call, e: IOException) {
                it.resumeWithException(e)
            }

            override fun onResponse(call: Call, response: Response) {
                it.resume(response)
            }
        })
    }
}

suspend fun Call.aresponse():Response
        = net { awaitCallback { enqueue(it) } }

suspend fun ResponseBody.astring():String = net { string() }
