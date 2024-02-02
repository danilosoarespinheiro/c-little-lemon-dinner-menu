package com.littlelemon.menu

import com.littlelemon.menu.SortType.Alphabetically
import com.littlelemon.menu.SortType.PriceAsc
import com.littlelemon.menu.SortType.PriceDesc

class SortHelper {

    fun sortProducts(type: SortType, productsList: List<ProductItem>): List<ProductItem> {
        return when (type) {
            Alphabetically -> productsList.sortAlphabetically()
            PriceAsc -> productsList.sortPriceAsc()
            PriceDesc -> productsList.sortPriceDesc()
        }
    }

    private fun List<ProductItem>.sortAlphabetically() = sortedBy { it.title }

    private fun List<ProductItem>.sortPriceAsc() = sortedBy { it.price }

    private fun List<ProductItem>.sortPriceDesc() = sortedByDescending { it.price }
}