package com.lambdaschool.androidthreadsassignment

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.ProgressBar
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        tv_primes.visibility = View.GONE
        toggleProgressBar()


        val primeNumbers = primes().take(16000).joinToString(", ")
        tv_primes.text = "Primes: $primeNumbers"
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

    fun toggleProgressBar() {
        if (progressBar.visibility == ProgressBar.VISIBLE) {
            progressBar.visibility = ProgressBar.GONE
        } else {
            progressBar.visibility = ProgressBar.VISIBLE
        }
    }
}
