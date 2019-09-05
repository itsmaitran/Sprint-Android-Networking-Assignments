package com.lambdaschool.httpoperations.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import com.lambdaschool.httpoperations.R
import com.lambdaschool.httpoperations.model.Employee
import com.lambdaschool.httpoperations.retrofit.JsonPlaceHolderAPI
import kotlinx.android.synthetic.main.activity_http.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class GetActivity : AppCompatActivity(), Callback<List<Employee>> {

    // Create a lateinit variable for JsonPlaceHolderAPI.
    lateinit var employeesService: JsonPlaceHolderAPI

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_http)

        // Get an instance to the JsonPlaceHolderAPI and create a lateinit variable for it.
        employeesService = JsonPlaceHolderAPI.Factory.create()

        val type = intent.getStringExtra("get")
        if (type == "simple") {
            title = "GET - Simple Request"
            getEmployees()
        } else if (type == "path") {
            title = "GET - Path Parameter"
            getEmployeeById("2")
        }
        else {
            title = "GET - Query Parameter"
            getEmployeeByAge("45")
        }
    }

    // Make a simple GET call to get all employees when users click on the first button in the GETPickerActivity.
    private fun getEmployees(){
        employeesService.getEmployees().enqueue(this)
    }

    // Make a GET call with employee id "2" as path parameter when users click on the second button in the GETPickerActivity.
    private fun getEmployeeById(employeeId: String){
        employeesService.getEmployeesById(employeeId).enqueue(this)
    }

    // Make a GET call with employee age - 45 as query parameter when users click on the third button in the GETPickerActivity.
    private fun getEmployeeByAge(age: String){
        employeesService.getEmployeesByAge(age).enqueue(this)
    }

    override fun onFailure(call: Call<List<Employee>>, t: Throwable) {
        Toast.makeText(this, "Failed", Toast.LENGTH_SHORT).show()
    }

    override fun onResponse(call: Call<List<Employee>>, response: Response<List<Employee>>) {
        response.body()?.let {
            result.text = it.toString()
            Toast.makeText(this, it.toString(), Toast.LENGTH_SHORT).show()
            progressBar.visibility = View.INVISIBLE
        }
    }
}
