package com.lambdaschool.httpoperations

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.method.ScrollingMovementMethod
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
        Toast.makeText(this@DeleteActivity, response, Toast.LENGTH_SHORT).show()
    }

    override fun onResponse(call: Call<Void>, response: Response<Void>) {
        if (response.isSuccessful) {
            val employeeList = response.body()
            result.setMovementMethod(ScrollingMovementMethod())
        } else {
            val response = "response not successful; ${response.errorBody().toString()}"
            Toast.makeText(this@DeleteActivity, response, Toast.LENGTH_SHORT).show()
        }
    }

    lateinit var employeesService: JsonPlaceHolderAPI

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_http)

        employeesService = JsonPlaceHolderAPI.Factory.create()
        deleteEmployee()
    }

    // Make a DELETE call to delete existing employee Steve, id = 1 when users click on DELETE button in MainActivity.
    private fun deleteEmployee() {
        employeesService.deleteEmployee("1").enqueue(this)
    }
}
