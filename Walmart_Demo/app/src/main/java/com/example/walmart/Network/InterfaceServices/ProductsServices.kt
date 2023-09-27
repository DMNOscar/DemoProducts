package com.example.walmart.Network.InterfaceServices


import com.example.walmart.Models.Product
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Header

interface ProductsServices {
    @GET("products")
    fun getProductsList(): Call<List<Product>>


}