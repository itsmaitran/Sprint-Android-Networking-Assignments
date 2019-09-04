package com.lambdaschool.basicandroidnetworking

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.lambdaschool.basicandroidnetworking.model.OceaniaCountry
import com.lambdaschool.basicandroidnetworking.retrofit.OceaniaCountriesRetriever
import retrofit2.Callback
import kotlinx.android.synthetic.main.activity_main.*
import retrofit2.Call
import retrofit2.Response

class MainActivity : AppCompatActivity(), Callback<List<OceaniaCountry>> {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        btn_fetch_countries.setOnClickListener {
            OceaniaCountriesRetriever().getOceaniaCountries().enqueue(this)
        }
    }

    override fun onResponse(
        call: Call<List<OceaniaCountry>>,
        response: Response<List<OceaniaCountry>>
    ) {
        if (response.isSuccessful) {
            val oceaniaCountryList = response.body()
            tv_countries.text = oceaniaCountryList.toString()
        } else {
            val response = "response not successful; ${response.errorBody().toString()}"
            Toast.makeText(this@MainActivity, response, Toast.LENGTH_SHORT).show()
        }
    }

    override fun onFailure(call: Call<List<OceaniaCountry>>, t: Throwable) {
        t.printStackTrace()
        val response = "failure; ${t.printStackTrace()}"
        Toast.makeText(this@MainActivity, response, Toast.LENGTH_SHORT).show()
    }
}
