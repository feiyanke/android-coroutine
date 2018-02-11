package io.koto.anco

import android.os.Bundle
import android.support.design.widget.Snackbar
import android.support.v7.app.AppCompatActivity
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.view.View

import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.coroutines.experimental.CommonPool
import kotlinx.coroutines.experimental.android.UI
import kotlinx.coroutines.experimental.launch
import kotlinx.coroutines.experimental.newFixedThreadPoolContext
import kotlinx.coroutines.experimental.withContext
import okhttp3.OkHttpClient
import okhttp3.Request
import org.jetbrains.anko.coroutines.experimental.asReference
import org.jetbrains.anko.coroutines.experimental.bg
import org.jetbrains.anko.doAsyncResult
import java.util.concurrent.Executors



class MainActivity : CoroutineAppCompatActivity() {

    override suspend fun onCreateAsync(savedInstanceState: Bundle?) {
        setContentView(R.layout.activity_main)

        whenClick(button) {
            Log.e("yfei", "1: $thread")
            try {
                val response = OkHttpClient()
                        .newCall(Request.Builder()
                                .url("https://www.baidu.com/")
                                .build())
                        .aresponse()
                Log.e("yfei", "2: $thread")
//                text.text = withContext(CommonPool) { response.body()!!.string()}
                text.text = response.body()!!.astring()
            } catch (e:Exception) {
                Log.e("yfei", "3: $thread")
                e.printStackTrace()
                text.text = e.message
            }
        }
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.menu_main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        return when (item.itemId) {
            R.id.action_settings -> true
            else -> super.onOptionsItemSelected(item)
        }
    }
}
