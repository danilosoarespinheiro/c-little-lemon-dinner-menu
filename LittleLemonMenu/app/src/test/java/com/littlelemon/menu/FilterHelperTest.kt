package com.littlelemon.menu

import org.junit.Assert.assertEquals
import org.junit.Test

class FilterHelperTest {

    class FilterHelperTest {
        @Test
        fun filterProducts_filterTypeDessert_croissantReturned() {
            val sampleProductsList = mutableListOf(
                ProductItem(
                    title = "Black tea",
                    price = 3.00,
                    category = "Drinks",
                    R.drawable.black_tea
                ),
                ProductItem(
                    title = "Croissant",
                    price = 7.00,
                    category = "Dessert",
                    R.drawable.croissant
                ),
                ProductItem(
                    title = "Bouillabaisse",
                    price = 20.00,
                    category = "Food",
                    R.drawable.bouillabaisse
                )
            )
            val expectation = mutableListOf(
                ProductItem(
                    title = "Croissant",
                    price = 7.00,
                    category = "Dessert",
                    R.drawable.croissant
                )
            )
            val result = FilterHelper().filterProducts(FilterType.Dessert, sampleProductsList)
            assertEquals(result, expectation)
        }
    }
}