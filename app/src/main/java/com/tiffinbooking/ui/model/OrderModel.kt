package com.tiffinbooking.ui.model

data class OrderModel(
    var image: Int = 0,
    var name: String = "",
    var price: Int = 0,
    var mealTime: String = "",
    var mealType: String = "",
    var mealPlan: String = "",
    var duration: String = "",
    var quantity: Int = 0,
    var date: String = ""
) {
    fun getProductName() : String {
        return "Product Name : $name"
    }
    fun getProductPrice() : String {
        return "Total Price : $price Gbp"
    }
    fun getProductMealTime() : String {
        return "Meal Time : $mealTime"
    }
    fun getProductMealType() : String {
        return "Meal Type : $mealType"
    }
    fun getProductMealPlan() : String {
        return "Meal Plan : $mealPlan"
    }
    fun getProductDuration() : String {
        return "Duration : $duration"
    }
    fun getProductQuantity() : String {
        return "Quantity : $quantity"
    }
    fun getProductDate() : String {
        return "Date : $date"
    }
}