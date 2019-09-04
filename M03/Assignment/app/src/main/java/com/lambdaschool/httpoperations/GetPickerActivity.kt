package com.lambdaschool.httpoperations

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_get_picker.*

class GetPickerActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_get_picker)

        // Each of these buttons take the user to a new Activity.
        btn_get_simple.setOnClickListener {
            val getSimpleIntent = Intent(this@GetPickerActivity, GetActivity::class.java)
            startActivity(getSimpleIntent)
        }

        btn_get_path.setOnClickListener {
            val getPathIntent = Intent(this@GetPickerActivity, GetActivity::class.java)
            startActivity(getPathIntent)
        }

        btn_get_query.setOnClickListener {
            val getQueryIntent = Intent(this@GetPickerActivity, GetActivity::class.java)
            startActivity(getQueryIntent)
        }
    }
}
