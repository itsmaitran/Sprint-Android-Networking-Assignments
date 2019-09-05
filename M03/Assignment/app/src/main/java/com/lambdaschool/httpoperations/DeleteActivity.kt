package com.lambdaschool.httpoperations

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import com.lambdaschool.httpoperations.retrofit.JsonPlaceHolderAPI
import kotlinx.android.synthetic.main.activity_http.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class DeleteActivity : AppCompatActivity(), Callback<Void> {
    override fun onFailure(call: Call<Void>, t: Throwable) {
        t.printStackTrace()
        val response = "failure; ${t.printStackTrace()}"
        Toast.makeText(this, response, Toast.LENGTH_SHORT).show()
    }

    override fun onResponse(call: Call<Void>, response: Response<Void>) {
        if (response.isSuccessful) {
            result.text = "Successfully Deleted Employee"
            Toast.makeText(this, "Successfully Deleted Employee", Toast.LENGTH_SHORT).show()
        } else {
            result.text = "Failed to Delete the Employee"
            Toast.makeText(this, "Failed to Delete the Employee", Toast.LENGTH_SHORT).show()
        }
        progressBar.visibility = View.INVISIBLE
    }

    lateinit var employeesService: JsonPlaceHolderAPI

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_http)
        title = "HTTP API for DELETE"

        employeesService = JsonPlaceHolderAPI.Factory.create()
        deleteEmployee()
    }

    // Make a DELETE call to delete existing employee Steve, id = 1 when users click on DELETE button in MainActivity.
    private fun deleteEmployee() {
        employeesService.deleteEmployee("1").enqueue(this)
    }
}
