package com.lambdaschool.httpoperations

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.method.ScrollingMovementMethod
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
        Toast.makeText(this@PostActivity, response, Toast.LENGTH_SHORT).show()
    }

    override fun onResponse(call: Call<Employee>, response: Response<Employee>) {
        if (response.isSuccessful) {
            val employeeList = response.body()
            result.setMovementMethod(ScrollingMovementMethod())
        } else {
            val response = "response not successful; ${response.errorBody().toString()}"
            Toast.makeText(this@PostActivity, response, Toast.LENGTH_SHORT).show()
        }
    }

    lateinit var employeesService: JsonPlaceHolderAPI

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_http)

        employeesService = JsonPlaceHolderAPI.Factory.create()
        addNewEmployee()
    }

    // Make a POST call to add a new employee with details age = 30, id = 7, name = David, title = intern when users click on the Post button on MainActivity.
    private fun addNewEmployee() {
        val newEmployee = Employee("David", 7, 30, "Intern")
        employeesService.addNewEmployee(newEmployee).enqueue(this)
    }
}
