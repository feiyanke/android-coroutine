package io.koto.anco

import android.view.View
import kotlinx.coroutines.experimental.*
import kotlinx.coroutines.experimental.android.UI
import kotlin.coroutines.experimental.CoroutineContext

/**
 * Created by Administrator on 2018/2/11.
 */
val thread:Thread get() = Thread.currentThread()
fun ui(start: CoroutineStart = CoroutineStart.DEFAULT,
       parent: Job? = null,
       block: suspend CoroutineScope.() -> Unit): Job = launch(UI, start, parent, block)

suspend fun <T> net(start: CoroutineStart = CoroutineStart.DEFAULT,
            block: suspend () -> T):T = withContext(CommonPool, start, block)

fun whenClick(view: View, block: suspend CoroutineScope.(View) -> Unit) {
    view.setOnClickListener { v -> ui { block(v) } }
}