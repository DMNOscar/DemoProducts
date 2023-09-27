package com.example.walmart.Network

import com.example.walmart.Network.InterfaceServices.ProductsServices


object CallServices {
     fun callProucts() = RetrofitClient.getRetrofit().create(
          ProductsServices::class.java
     )
}