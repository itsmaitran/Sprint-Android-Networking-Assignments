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

class GetActivity : AppCompatActivity(), Callback<List<Employee>> {
    override fun onFailure(call: Call<List<Employee>>, t: Throwable) {
        t.printStackTrace()
        val response = "failure; ${t.printStackTrace()}"
        Toast.makeText(this@GetActivity, response, Toast.LENGTH_SHORT).show()
    }

    override fun onResponse(call: Call<List<Employee>>, response: Response<List<Employee>>) {
        if (response.isSuccessful) {
            val employeeList = response.body()
            result.setMovementMethod(ScrollingMovementMethod())
        } else {
            val response = "response not successful; ${response.errorBody().toString()}"
            Toast.makeText(this@GetActivity, response, Toast.LENGTH_SHORT).show()
        }
    }

    // Create a lateinit variable for JsonPlaceHolderAPI.
    lateinit var employeesService: JsonPlaceHolderAPI

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_http)

        // Get an instance to the JsonPlaceHolderAPI and create a lateinit variable for it.
        employeesService = JsonPlaceHolderAPI.Factory.create()
    }

    // Make a simple GET call to get all employees when users click on the first button in the GETPickerActivity.
    private fun getEmployees() {
        employeesService.getEmployees().enqueue(this)
    }

    // Make a GET call with employee id "2" as path parameter when users click on the second button in the GETPickerActivity.
    private fun getEmployeeById() {
        employeesService.getEmployeesById("2").enqueue(this)
    }

    // Make a GET call with employee age - 45 as query parameter when users click on the third button in the GETPickerActivity.
    private fun getEmployeeByAge() {
        employeesService.getEmployeesByAge("45").enqueue(this)
    }
}
