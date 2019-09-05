package com.lambdaschool.servicesandbroadcasts

import android.app.Service
import android.content.Intent
import android.os.IBinder
import android.util.Log
import androidx.localbroadcastmanager.content.LocalBroadcastManager
import com.lambdaschool.servicesandbroadcasts.adapter.NetworkAdapter
import java.lang.UnsupportedOperationException

class LargeImageDownloadService : Service() {

    companion object {
        const val FILE_DOWNLOADED_ACTION = "com.lambdaschool.servicesandbroadcasts.FILE_DOWNLOADED"
        const val DOWNLOADED_IMAGE = "downloadedImage"
        const val BITMAP_WIDTH = "BITMAP_WIDTH"
        const val BITMAP_HEIGHT = "BITMAP_HEIGHT"
    }

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        Log.i("LargeImageDownload", "Started")

        Thread(Runnable {
            val bitmap = NetworkAdapter.getBitmapFromURL("https://imgur.com/gallery/vjXpIbW")

            val intent = Intent(FILE_DOWNLOADED_ACTION).apply {
                putExtra(DOWNLOADED_IMAGE, bitmap)
            }

            LocalBroadcastManager.getInstance(this).sendBroadcast(intent)
            stopSelf()
        }).start()

        return super.onStartCommand(intent, flags, startId)
    }

    override fun onCreate() {
        super.onCreate()
        Log.i("LargeImageDownload", "Created")
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.i("LargeImageDownload", "Destroyed")
    }

    override fun onBind(p0: Intent?): IBinder? {
        throw UnsupportedOperationException()
    }
}