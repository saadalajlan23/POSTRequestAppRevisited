package com.example.postrequestapprevisited


import retrofit2.Call
import retrofit2.http.*
import java.sql.RowId

interface APIInterface {
    @GET("test/")
    fun getAll(): Call<Users>

    @POST("test/")
    fun addUser(@Body userData: UsersInfo): Call<UsersInfo>
    @PUT("test/{id}")
    fun updateUser(@Path("id")id: Int,@Body userData: UsersInfo): Call<UsersInfo>
    @DELETE("test/{id}")
    fun deleteUser(@Path("id")id: Int): Call<Void>
}