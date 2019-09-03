package com.lambdaschool.androidthreadsassignment

import android.os.AsyncTask
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.ProgressBar
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    // Create a new inner class (class inside of another class, your main activity in this case) which extends AsyncTask.
    inner class MyAsyncTask : AsyncTask<Unit, Int, String>() {
        // Insert Override methods for onPreExecute, onPostExecute, onCancelled, doInBackground. This will generate methods with the proper signatures for the types you gave in the class signature.
        // In onPreExecute, hide your TextView and display the ProgressBar with your helper method.
        override fun onPreExecute() {
            super.onPreExecute()
            tv_primes.visibility = View.INVISIBLE
            toggleProgressBar()
        }

        // In onPostExecute, perform tasks you would do after the thread completes (hide the progress bar and update the text in the field. This value will be what you returned in the doInBackground method.
        override fun onPostExecute(result: String?) {
            super.onPostExecute(result)
            tv_primes.visibility = View.VISIBLE
            tv_primes.text = "Primes: $result"
            toggleProgressBar()

        }

        // In onCancelled will allow you to release any resources before this AsyncTask is canceled (close a file, close a database, etc.)
        override fun onCancelled() {
            super.onCancelled()
            Log.i("Lifecycle", "onCancelled")
        }

        // In doInBackground, perform the time-consuming portion of your task on a background Thread, and you won't be able to access any views in the UI from it. Move your code for prime number generation here, and return the appended string of prime numbers. This will be passed into the onPostExecute method.
        override fun doInBackground(vararg p0: Unit?): String {
            val primeNumbers = primes().take(16000).joinToString(", ")
            return primeNumbers
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        btn_generate.setOnClickListener {
            MyAsyncTask().execute()
        }

        btn_cancel.setOnClickListener {
            tv_primes.visibility = View.VISIBLE
            progressBar.visibility = View.INVISIBLE
            MyAsyncTask().cancel(true)
        }
    }

    //Add a helper method to your MainActivity for generating prime numbers:
    fun primes(): Sequence<Long> {
        var i = 0L
        return sequence {
            generateSequence { i++ }
                .filter { n -> n > 1 && ((2 until n).none { i -> n % i == 0L }) }
                .forEach { yield(it) }
        }
    }

    // Write a method in your Activity for toggling your ProgressBar from INVISIBLE TO VISIBLE and vice-versa.
    fun toggleProgressBar() {
        if (progressBar.visibility == ProgressBar.VISIBLE) {
            progressBar.visibility = ProgressBar.INVISIBLE
        } else {
            progressBar.visibility = ProgressBar.VISIBLE
        }
    }

    override fun onStop() {
        MyAsyncTask().cancel(true)
        Log.i("Lifecycle", "onStop")
        super.onStop()

    }
}
