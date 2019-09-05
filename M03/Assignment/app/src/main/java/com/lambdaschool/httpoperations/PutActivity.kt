package com.lambdaschool.httpoperations

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import com.lambdaschool.httpoperations.model.Employee
import com.lambdaschool.httpoperations.retrofit.JsonPlaceHolderAPI
import kotlinx.android.synthetic.main.activity_http.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class PutActivity : AppCompatActivity(), Callback<Employee> {
    override fun onFailure(call: Call<Employee>, t: Throwable) {
        t.printStackTrace()
        val response = "failure; ${t.printStackTrace()}"
        Toast.makeText(this, response, Toast.LENGTH_SHORT).show()
    }

    override fun onResponse(call: Call<Employee>, response: Response<Employee>) {
        response.body()?.let {
            result.text = it.toString()
            Toast.makeText(this, it.toString(), Toast.LENGTH_SHORT).show()
            progressBar.visibility = View.INVISIBLE
        }
    }

    lateinit var employeesService: JsonPlaceHolderAPI

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_http)
        title = "HTTP API for PUT"

        employeesService = JsonPlaceHolderAPI.Factory.create()
        updateEmployee()
    }

    // Make a PUT call to update existing employee Steve's title, with details age = 25, id = 1, name = Steve and title = Principal Engineer.
    private fun updateEmployee() {
        val toUpdateEmployee = Employee("Steve", 1, 25, "Principal Engineer")
        employeesService.updateEmployee(toUpdateEmployee).enqueue(this)
    }
}
