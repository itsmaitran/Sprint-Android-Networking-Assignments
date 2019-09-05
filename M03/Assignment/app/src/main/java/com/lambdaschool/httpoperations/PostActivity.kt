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

class PostActivity : AppCompatActivity(), Callback<Employee> {
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
        title = "HTTP API for POST"

        employeesService = JsonPlaceHolderAPI.Factory.create()
        addNewEmployee()
    }

    // Make a POST call to add a new employee with details age = 30, id = 7, name = David, title = intern when users click on the Post button on MainActivity.
    private fun addNewEmployee() {
        val newEmployee = Employee("David", 7, 30, "Intern")
        employeesService.addNewEmployee(newEmployee).enqueue(this)
    }
}
