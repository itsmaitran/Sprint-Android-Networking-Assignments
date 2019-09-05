package com.lambdaschool.httpoperations.activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.lambdaschool.httpoperations.R
import kotlinx.android.synthetic.main.activity_get_picker.*

class GetPickerActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_get_picker)
        title = "HTTP API for GET"

        // Each of these buttons take the user to a new Activity.
        btn_get_simple.setOnClickListener {
            val getSimpleIntent = Intent(this@GetPickerActivity, GetActivity::class.java)
            getSimpleIntent.putExtra("get", "simple")
            startActivity(getSimpleIntent)
        }

        btn_get_path.setOnClickListener {
            val getPathIntent = Intent(this@GetPickerActivity, GetActivity::class.java)
            getPathIntent.putExtra("get", "path")
            startActivity(getPathIntent)
        }

        btn_get_query.setOnClickListener {
            val getQueryIntent = Intent(this@GetPickerActivity, GetActivity::class.java)
            getQueryIntent.putExtra("get", "query")
            startActivity(getQueryIntent)
        }
    }
}
