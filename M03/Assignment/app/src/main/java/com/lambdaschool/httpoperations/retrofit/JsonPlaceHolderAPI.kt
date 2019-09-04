package com.lambdaschool.httpoperations.retrofit

import com.lambdaschool.httpoperations.model.Employee
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.*
import java.util.concurrent.TimeUnit

// Create an interface that has all the endpoints that you will be hitting for responses.
interface JsonPlaceHolderAPI {

    // HTTP Operations Using Retrofit
    // HTTP API for GET
    // Simple GET
    @GET("employees")
    fun getEmployees() : Call<List<Employee>>

    // GET - Path Parameter
    @GET("employees/{id}")
    fun getEmployeesById(@Path("id") employeeId: String) : Call<List<Employee>>

    // GET - QUERY Parameter
    @GET("employees")
    fun getEmployeesByAge(@Query("age") employeeAge: String) : Call<List<Employee>>

    // HTTP API for POST
    @POST("employees")
    fun addNewEmployee(@Body employee: Employee) : Call<Employee>

    // HTTP API for PUT
    @PUT("employees")
    fun updateEmployee(@Body employee: Employee) : Call<Employee>

    // HTTP API FOR DELETE
    @DELETE("employees/{id}")
    fun deleteEmployee(@Path("id") employeeId: String) : Call<Void>


    // Create a Factory class that will serve as a Helper class to retrieve the retrofit object.
    class Factory {

        companion object {

            // The BASE_URL, depends on the backend.
            private const val BASE_URL = "https://demo8143297.mockable.io"

            fun create(): JsonPlaceHolderAPI {

                // Http logging (will see the results in logcat).
                val logger = HttpLoggingInterceptor().apply {
                    level = HttpLoggingInterceptor.Level.BASIC
                    level = HttpLoggingInterceptor.Level.BODY
                }

                // okHttp timeout settings.
                val okHttpClient = OkHttpClient.Builder()
                    .addInterceptor(logger)
                    .retryOnConnectionFailure(false)
                    .readTimeout(10, TimeUnit.SECONDS)
                    .connectTimeout(15, TimeUnit.SECONDS)
                    .build()

                // The retrofit builder takes in the okHttpClient, the BASE_URL and the Gson converter factory that converts json from the backend into JAVA/Kotlin objects for us.
                val retrofit = Retrofit.Builder()
                    .client(okHttpClient)
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build()

                return retrofit.create(JsonPlaceHolderAPI::class.java)

            }
        }
    }
}