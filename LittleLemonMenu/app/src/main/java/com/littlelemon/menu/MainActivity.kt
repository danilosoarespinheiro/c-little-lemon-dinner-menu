package com.littlelemon.menu

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.core.view.MenuCompat
import com.littlelemon.menu.FilterType.All
import com.littlelemon.menu.FilterType.Dessert
import com.littlelemon.menu.FilterType.Drinks
import com.littlelemon.menu.FilterType.Food
import com.littlelemon.menu.ProductActivity.Companion.KEY_CATEGORY
import com.littlelemon.menu.ProductActivity.Companion.KEY_IMAGE
import com.littlelemon.menu.ProductActivity.Companion.KEY_PRICE
import com.littlelemon.menu.ProductActivity.Companion.KEY_TITLE
import com.littlelemon.menu.SortType.Alphabetically
import com.littlelemon.menu.SortType.PriceAsc
import com.littlelemon.menu.SortType.PriceDesc
import com.littlelemon.menu.ProductsWarehouse.productsList
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.update

class MainActivity : ComponentActivity() {

    private val productsState: MutableStateFlow<Products> = MutableStateFlow(Products(productsList))

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent { InitUI() }
    }

    @Composable
    fun InitUI() {
        val products by productsState.collectAsState()
        ProductsGrid(products = products, startProductActivity = this::startProductActivity)
    }

    private fun startProductActivity(context: Context, productItem: ProductItem) {
        val intent = Intent(context, ProductActivity::class.java).apply {
            this.putExtra(KEY_TITLE, productItem.title)
            this.putExtra(KEY_PRICE, productItem.price)
            this.putExtra(KEY_CATEGORY, productItem.category)
            this.putExtra(KEY_IMAGE, productItem.image)
        }
        context.startActivity(intent)
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.products_menu, menu)
        MenuCompat.setGroupDividerEnabled(menu, true)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.groupId == R.id.sorting) {
            val type = when (item.itemId) {
                R.id.sort_a_z -> Alphabetically
                R.id.sort_price_asc -> PriceAsc
                R.id.sort_price_desc -> PriceDesc
                else -> Alphabetically
            }
            productsState.update { Products(SortHelper().sortProducts(type, productsList)) }
        } else if (item.groupId == R.id.filter) {
            val type = when (item.itemId) {
                R.id.filter_all -> All
                R.id.filter_drinks -> Drinks
                R.id.filter_food -> Food
                R.id.filter_dessert -> Dessert
                else -> All
            }
            productsState.update {
                Products(FilterHelper().filterProducts(type, productsList))
            }
        }
        return true
    }
}