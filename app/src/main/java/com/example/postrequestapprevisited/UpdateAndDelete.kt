package com.example.postrequestapprevisited

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_update_and_delete.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class UpdateAndDelete : AppCompatActivity() {
    private lateinit var etId : EditText
    private lateinit var etName: EditText
    private lateinit var etLocation: EditText
    private lateinit var btDelete: Button
    private lateinit var btUpdate: Button


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_update_and_delete)
        etId = findViewById(R.id.id)
        etName = findViewById(R.id.name)
        etLocation = findViewById(R.id.location)
        btDelete = findViewById(R.id.delete)
        btUpdate = findViewById(R.id.update)


        btUpdate.setOnClickListener {
            if (etId.text.isNotEmpty() && etName.text.isNotEmpty() && etLocation.text.isNotEmpty()) {
                updateUser()
                val intent = Intent(this, MainActivity::class.java)
                startActivity(intent)
            }
        }
        btDelete.setOnClickListener {
            deleteUser()
            val intent = Intent(this,MainActivity::class.java)
            startActivity(intent)
        }


    }
    private fun updateUser(){

        val apiInterface = APIClient().getClient()?.create(APIInterface::class.java)

        apiInterface!!.updateUser(
            etId.text.toString().toInt(),
            UsersInfo(

                etName.text.toString(),
                etLocation.text.toString(),
                etId.text.toString().toInt(),
            )
        ).enqueue(object : Callback<UsersInfo> {
            override fun onResponse(call: Call<UsersInfo>, response: Response<UsersInfo>) {
                Toast.makeText(applicationContext, "User Updated!", Toast.LENGTH_LONG).show()
                recreate()
            }

            override fun onFailure(call: Call<UsersInfo>, t: Throwable) {
                Log.d("MAIN", "Something went wrong!")
            }

        })
    }
    private fun deleteUser(){
        val apiInterface = APIClient().getClient()?.create(APIInterface::class.java)
        apiInterface?.deleteUser(etId.text.toString().toInt())?.enqueue(object:Callback<Void>{
            override fun onResponse(call: Call<Void>, response: Response<Void>) {
                Toast.makeText(applicationContext, "User Deleted!", Toast.LENGTH_LONG).show()
                recreate()
            }

            override fun onFailure(call: Call<Void>, t: Throwable) {
                Log.d("MAIN", "Something went wrong!")
            }
        })

    }
}