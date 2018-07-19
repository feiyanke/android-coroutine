package io.koto.anco

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import kotlinx.coroutines.experimental.runBlocking
import org.jetbrains.anko.coroutines.experimental.asReference

open class CoroutineAppCompatActivity : AppCompatActivity() {

    protected val thisActivity = this.asReference()

    open suspend fun onCreateAsync(savedInstanceState: Bundle? = null) {}
    open suspend fun onStartAsync() {}
    open suspend fun onRestartAsync() {}
    open suspend fun onResumeAsync() {}
    open suspend fun onPauseAsync() {}
    open suspend fun onStopAsync() {}
    open suspend fun onDestroyAsync() {}

    final override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        ui { onCreateAsync(savedInstanceState) }
    }

    final override fun onStart()  = ui { onStartAsync() }


    final override fun onRestart() {
        super.onRestart()
        ui { onRestartAsync() }
    }

    final override fun onResume() {
        super.onResume()
        ui { onResumeAsync() }
    }

    final override fun onPause() {
        super.onPause()
        ui { onPauseAsync() }
    }

    final override fun onStop() {
        super.onStop()
        ui { onStopAsync() }
    }

    final override fun onDestroy() {
        super.onDestroy()
        ui { onDestroyAsync() }
    }
}