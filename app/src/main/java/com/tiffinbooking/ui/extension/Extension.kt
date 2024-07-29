package com.tiffinbooking.ui.extension

import android.app.DatePickerDialog
import android.content.Context
import android.widget.DatePicker
import android.widget.Toast
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import com.tiffinbooking.R
import com.tiffinbooking.ui.model.DataModel
import java.util.*
import java.util.regex.Pattern
import kotlin.collections.ArrayList

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
        add(DataModel(type = "Veg", image = R.drawable.ic_idli,name = "Idli", detail = getString(R.string.idli), priceStandard = 150, priceMini = 140))
        add(DataModel(type = "Veg", image = R.drawable.ic_chapatti,name = "Chapatti", detail = getString(R.string.chapatti), priceStandard = 140, priceMini = 130))
        add(DataModel(type = "Veg", image = R.drawable.ic_poori,name = "Poori", detail = getString(R.string.poori), priceStandard = 140, priceMini = 130))
        add(DataModel(type = "Veg", image = R.drawable.ic_rice,name = "Rice Roti", detail = getString(R.string.rice), priceStandard = 130, priceMini = 120))
        add(DataModel(type = "Veg", image = R.drawable.ic_pasta,name = "Pasta", detail = getString(R.string.pasta), priceStandard = 150, priceMini = 140))
        add(DataModel(type = "Veg", image = R.drawable.parotta,name = "Parotta", detail = getString(R.string.parotta), priceStandard = 100, priceMini = 90))
        add(DataModel(type = "Veg", image = R.drawable.ic_rava,name = "Rava Dosa", detail = getString(R.string.rava_dosa), priceStandard = 160, priceMini = 150))
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

@Composable
fun Context.DatePickerDialogBox(
    onDateSelect: (String) -> Unit
) {
    val year: Int
    val month: Int
    val day: Int
    val mCalendar = Calendar.getInstance()
    year = mCalendar.get(Calendar.YEAR)
    month = mCalendar.get(Calendar.MONTH)
    day = mCalendar.get(Calendar.DAY_OF_MONTH)

    mCalendar.time = Date()
    val mDatePickerDialog = DatePickerDialog(
        this,
        { _: DatePicker, mYear: Int, mMonth: Int, mDayOfMonth: Int ->
            onDateSelect("$mDayOfMonth/${mMonth + 1}/$mYear")
        }, year, month, day
    )
    mDatePickerDialog.show()
}