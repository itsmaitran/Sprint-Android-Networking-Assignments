package com.lambdaschool.servicesandbroadcasts.ui

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import android.graphics.Bitmap
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.localbroadcastmanager.content.LocalBroadcastManager
import com.lambdaschool.servicesandbroadcasts.LargeImageDownloadService
import com.lambdaschool.servicesandbroadcasts.R
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    private lateinit var imageDownloadReceiver: BroadcastReceiver

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        btn_download.setOnClickListener {
            val serviceIntent = Intent(this, LargeImageDownloadService::class.java)
            // To scale the image to your ImageView, you should pass them to the Service via the Intent.
            serviceIntent.putExtra(LargeImageDownloadService.BITMAP_WIDTH, iv_downloaded_image.width)
            serviceIntent.putExtra(LargeImageDownloadService.BITMAP_HEIGHT, iv_downloaded_image.height)
            this.startService(serviceIntent)
        }

        imageDownloadReceiver = object : BroadcastReceiver() {
            override fun onReceive(context: Context?, intent: Intent?) {
                if (intent?.action == LargeImageDownloadService.FILE_DOWNLOADED_ACTION) {
                    val bitmap = intent.getParcelableExtra<Bitmap>(LargeImageDownloadService.DOWNLOADED_IMAGE)
                    iv_downloaded_image.setImageBitmap(bitmap)
                }
            }
        }

        val intentFilter = IntentFilter().apply {
            addAction(LargeImageDownloadService.FILE_DOWNLOADED_ACTION)
        }

        LocalBroadcastManager.getInstance(this).registerReceiver(imageDownloadReceiver, intentFilter)
    }

    override fun onDestroy() {
        super.onDestroy()
        unregisterReceiver(imageDownloadReceiver)
    }
}
