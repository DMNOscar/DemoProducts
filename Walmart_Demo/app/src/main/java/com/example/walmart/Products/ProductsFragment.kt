package com.example.walmart.Products

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.room.Room
import com.example.walmark.R
import com.example.walmark.databinding.FragmentProductsBinding
import com.example.walmart.DataBase.AppDatabase
import com.example.walmart.Models.Product
import com.example.walmart.Products.Adapter.ProductsAdaptersList
import com.example.walmart.Products.ViewModel.ProductsFragmentViewModel
import com.example.walmart.Utiis.Utils
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext


class ProductsFragment : Fragment() {

    private lateinit var _binding: FragmentProductsBinding
    private val binding get() = _binding
    private lateinit var database: AppDatabase
    private val viewModel = ProductsFragmentViewModel()
    private val CODE_OK = 200
    private val CODE_LOAD = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {

        _binding = FragmentProductsBinding.inflate(inflater, container, false)
        database = AppDatabase.getDataBase(requireContext())

        lifecycleScope.launch {
            val productCount = withContext(Dispatchers.IO) {
                database.productDao().getProductCount()
            }

            if (productCount == 0) {
                initObserverHome()

            } else {
                val products = withContext(Dispatchers.IO) {
                    database.productDao().getAllProducts()
                }
                binding.loader.visibility = View.GONE
                loadListProducts(products)
            }
        }

        return _binding.root
    }


    private fun initObserverHome() {

        lifecycleScope.launchWhenStarted {
            viewModel.productsFragmenState.collect { data ->

                when (data.codeResponse) {

                    CODE_LOAD ->{
                        binding.loader.visibility = View.VISIBLE
                    }
                    CODE_OK -> {
                        loadListProducts(data.productsList)
                        if (!data.productsList.isNullOrEmpty()) {
                            database.productDao().insertAllProducts(data.productsList)
                        }
                        binding.loader.visibility = View.GONE
                    }

                    else ->{
                        binding.loader.visibility = View.GONE

                        context?.let {
                            Utils.createCustomSnackbar(binding.rcvProducts,"Failed to load the list of products","Loading error",
                                it
                            )
                        }
                    }
                }
            }
        }
    }
    private fun loadListProducts(list:List<Product>){

        binding.rcvProducts.layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
        binding.rcvProducts.adapter = context?.let { ProductsAdaptersList(list, it) }

    }

}