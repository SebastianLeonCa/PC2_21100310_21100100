package dev.sebastianleon.firebase.service

import dev.sebastianleon.firebase.model.RegisterModel
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.PUT
import retrofit2.http.Path

interface RegisterService {
    @GET("products")
    fun getProducts(): Call<List<RegisterModel>>

    @GET("products/{id}")
    fun getProductById(@Path("id") id: Int): Call<RegisterModel>

    // Create POST products
    @POST("products")
    fun createProduct(@Body product: RegisterModel): Call<RegisterModel>

    // Update PUT products
    @PUT("products/{id}")
    fun updateProduct(@Path("id") id: Int, product: RegisterModel): Call<RegisterModel>

    // Delete DELETE products
    @DELETE("products/{id}")
    fun deleteProduct(@Path("id") id: Int): Call<Void>
}