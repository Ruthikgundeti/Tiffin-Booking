package com.tiffinbooking.ui.extension

import android.content.Context
import android.widget.Toast
import androidx.compose.ui.res.stringResource
import com.tiffinbooking.R
import com.tiffinbooking.ui.model.DataModel
import java.util.regex.Pattern

/**
 * for check null case
 */
fun checkNull(text: String): Boolean {
    return !(text.trim() == "" || text.trim() == null || text.trim() == "null" || text.trim()
        .isEmpty())
}

/**
 * for show toast message
 */
fun Context.showMessage(message:String) {
    Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
}

/**
 * list data
 */
fun Context.tiffinData() : ArrayList<DataModel> {
    val list = ArrayList<DataModel>()
    list.apply {
        add(DataModel(image = R.drawable.ic_idli,name = "Idli", detail = getString(R.string.idli), price = 150.00))
        add(DataModel(image = R.drawable.ic_chapatti,name = "Chapatti", detail = getString(R.string.chapatti), price = 140.00))
        add(DataModel(image = R.drawable.ic_poori,name = "Poori", detail = getString(R.string.poori), price = 140.00))
        add(DataModel(image = R.drawable.ic_rice,name = "Rice Roti", detail = getString(R.string.rice), price = 130.00))
        add(DataModel(image = R.drawable.ic_pasta,name = "Pasta", detail = getString(R.string.pasta), price = 150.00))
        add(DataModel(image = R.drawable.parotta,name = "Parotta", detail = getString(R.string.parotta), price = 100.00))
        add(DataModel(image = R.drawable.ic_rava,name = "Rava Dosa", detail = getString(R.string.rava_dosa), price = 160.00))
    }
    return list
}

/**
 * check email validation
 */
fun emailValidation(email: String): Boolean {
    val emailPattern = ("^(([\\w-]+\\.)+[\\w-]+|([a-zA-Z]{1}|[\\w-]{2,}))@"
            + "((([0-1]?[0-9]{1,2}|25[0-5]|2[0-4][0-9])\\.([0-1]?"
            + "[0-9]{1,2}|25[0-5]|2[0-4][0-9])\\."
            + "([0-1]?[0-9]{1,2}|25[0-5]|2[0-4][0-9])\\.([0-1]?"
            + "[0-9]{1,2}|25[0-5]|2[0-4][0-9])){1}|"
            + "([a-zA-Z]+[\\w-]+\\.)+[a-zA-Z]{2,4})$")
    val pattern = Pattern.compile(emailPattern)
    val matcher = pattern.matcher(email)

    return !matcher.matches()
}