package com.littlelemon.menu

import com.littlelemon.menu.FilterType.All
import com.littlelemon.menu.FilterType.Dessert
import com.littlelemon.menu.FilterType.Drinks
import com.littlelemon.menu.FilterType.Food

class FilterHelper {//TODO create a FilterHelperTest and write a unit test for filterProducts

    fun filterProducts(type: FilterType, productsList: List<ProductItem>): List<ProductItem> {
        return when (type) {
            All -> productsList
            Dessert -> productsList.productItemFilter("Dessert")
            Drinks -> productsList.productItemFilter("Drinks")
            Food -> productsList.productItemFilter("Food")
        }
    }

    private fun List<ProductItem>.productItemFilter(filter: String) =
        filter { productItem -> productItem.category == filter }
}