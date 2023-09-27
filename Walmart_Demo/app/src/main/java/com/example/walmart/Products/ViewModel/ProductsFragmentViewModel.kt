package com.example.walmart.Products.ViewModel


import androidx.lifecycle.ViewModel
import com.example.walmart.Network.CallServices
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow

import kotlinx.coroutines.launch


import androidx.lifecycle.viewModelScope
import com.example.walmart.Models.Product
import kotlinx.coroutines.flow.update
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class ProductsFragmentViewModel() : ViewModel() {

    private val _productsFragmen = MutableStateFlow(ProductsUiState())
    val productsFragmenState: StateFlow<ProductsUiState> = _productsFragmen.asStateFlow()
    var productsList: List<Product> = arrayListOf()


    data class ProductsUiState(
        var productsList: List<Product> = arrayListOf(),
        var codeResponse: Int = 0
    )

    init {
        getProductsList()
    }

    fun getProductsList() {

        viewModelScope.launch(Dispatchers.IO) {
            CallServices.callProucts().getProductsList()
                .enqueue(object : Callback<List<Product>> {
                    override fun onResponse(
                        call: Call<List<Product>>,
                        response: Response<List<Product>>
                    ) {

                        if (response.code().equals(200)) {

                            productsList = response.body() as ArrayList<Product>

                            _productsFragmen.update {
                                it.copy(
                                    productsList= productsList,
                                    codeResponse = response.code()
                                )
                            }

                        } else {
                            _productsFragmen.update {
                                it.copy(
                                    codeResponse = response.code()
                                )
                            }
                        }
                    }

                    override fun onFailure(call: Call<List<Product>>, t: Throwable) {
                        _productsFragmen.update {
                            it.copy(
                                codeResponse = 400
                            )
                        }
                    }

                })
        }
    }

}