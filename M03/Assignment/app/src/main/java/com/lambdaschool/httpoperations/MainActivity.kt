package com.lambdaschool.httpoperations

import android.content.Context
import android.content.Intent
import android.net.ConnectivityManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.google.android.material.snackbar.Snackbar
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    // Add a function to check if the network is connected before taking the user to any of the above activities and handle the case of network unavailability by showing a Snackbar message to the user.
    private fun isNetworkConnected(): Boolean {
        val connectivityManager = getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val networkInfo = connectivityManager.activeNetworkInfo
        return networkInfo?.isConnected == true
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Each of these buttons take the user to a new Activity
        btn_get.setOnClickListener {
            if (!isNetworkConnected()) {
                Snackbar.make(it, "Please check your internet connection and try again", Snackbar.LENGTH_LONG).show()
            } else {
                startActivity(Intent(this@MainActivity, GetPickerActivity::class.java))
            }
        }

        btn_post.setOnClickListener {
            if (!isNetworkConnected()) {
                Snackbar.make(it, "Please check your internet connection and try again", Snackbar.LENGTH_LONG).show()
            } else {
                startActivity(Intent(this@MainActivity, PostActivity::class.java))
            }
        }

        btn_put.setOnClickListener {
            if (!isNetworkConnected()) {
                Snackbar.make(it, "Please check your internet connection and try again", Snackbar.LENGTH_LONG).show()
            } else {
                startActivity(Intent(this@MainActivity, PutActivity::class.java))
            }
        }

        btn_delete.setOnClickListener {
            if (!isNetworkConnected()) {
                Snackbar.make(it, "Please check your internet connection and try again", Snackbar.LENGTH_LONG).show()
            } else {
                startActivity(Intent(this@MainActivity, DeleteActivity::class.java))
            }
        }
    }
}
