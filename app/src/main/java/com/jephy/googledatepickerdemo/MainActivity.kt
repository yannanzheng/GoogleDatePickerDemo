package com.jephy.googledatepickerdemo

import android.content.Context
import android.os.Build
import android.os.Bundle
import android.widget.Button
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*
import java.text.SimpleDateFormat
import java.util.*

class MainActivity : AppCompatActivity() {


    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        bt_birth_date.setOnClickListener {birthDayView ->


           pickBirthday(this, Calendar.getInstance(), object :MyDatePickerDialog.OnDateSelectedListener {
               override fun onDateSelected(date: Date) {
                   (birthDayView as Button).text = parseTimeToString(date.time, "yyyy/MM/dd")
               }
           })

        }
    }

    fun pickBirthday(context: Context, initCalendar: Calendar?, listener: MyDatePickerDialog.OnDateSelectedListener) {
        val birthDayPicker = MyDatePickerDialog(
            context,
            initCalendar,
            listener
        ).apply {
            setMaxDate(Calendar.getInstance())
        }
        birthDayPicker.show()
    }

    fun parseTimeToString(time: Long, pattern: String): String {
        try {
            return SimpleDateFormat(pattern, Locale.getDefault()).format(Date(time))
        } catch (e: Exception) {
            e.printStackTrace()
        }
        return ""
    }
}
