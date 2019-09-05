package com.lambdaschool.httpoperations

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.lambdaschool.httpoperations.model.Employee
import com.lambdaschool.httpoperations.retrofit.JsonPlaceHolderApi
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class HttpPutActivity : AppCompatActivity(), Callback<Employee> {
    override fun onFailure(call: Call<Employee>, t: Throwable) {
    }

    override fun onResponse(call: Call<Employee>, response: Response<Employee>) {
        response.body()?.let {
            Toast.makeText(this@HttpPutActivity, it.toString(), Toast.LENGTH_SHORT).show()
        }
    }


    lateinit var employeesService: JsonPlaceHolderApi

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_http_get)
        title = "Put Request: Update existing Employee Steve"

        // Create the API object
        employeesService = JsonPlaceHolderApi.Factory.create()
        updateEmployee()
    }

    private fun updateEmployee(){
        // Write the call to update an employee
        val employee = Employee ("Steve", 1, 25, "Engineer")
        employeesService.updateEmployee(employee).enqueue(this)
    }

}
