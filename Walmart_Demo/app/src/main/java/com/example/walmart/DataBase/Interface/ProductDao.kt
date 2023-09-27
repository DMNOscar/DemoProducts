package com.example.walmart.DataBase.Interface

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.walmart.Models.Product

@Dao
interface ProductDao {

    @Query("SELECT COUNT(*) FROM products")
    fun getProductCount(): Int

    @Query("SELECT * FROM products")
    fun getAllProducts(): List<Product>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertProduct(product: Product)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
     fun insertAllProducts(products: List<Product>)
}
