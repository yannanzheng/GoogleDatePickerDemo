
package com.jephy.googledatepickerdemo

import android.app.Dialog
import android.content.Context
import android.view.Gravity
import android.view.Window
import android.view.WindowManager.LayoutParams
import kotlinx.android.synthetic.main.dialog_my_date_picker.*
import java.util.*


class MyDatePickerDialog(
    context: Context,
    calendar: Calendar?,
    private val listener: OnDateSelectedListener
) : Dialog(context) {
    init {

        val window: Window = this.window!!
        window.requestFeature(Window.FEATURE_NO_TITLE)
        setContentView(R.layout.dialog_my_date_picker)

        window.decorView.setPadding(0, 0, 0, 0)
        val lp: LayoutParams = window.attributes
        lp.windowAnimations = R.style.MyDatePickerDialogAnimation
        lp.width = LayoutParams.MATCH_PARENT
        lp.height = LayoutParams.WRAP_CONTENT
        lp.gravity = Gravity.BOTTOM
        window.attributes = lp
        window.setBackgroundDrawableResource(android.R.color.white)

        btn_ok.setOnClickListener {
            date_picker.let {
                val calendar = Calendar.getInstance().apply {
                    clear()
                    set(Calendar.YEAR,it.year)
                    set(Calendar.MONTH, it.month)
                    set(Calendar.DAY_OF_MONTH,it.dayOfMonth)
                }
                listener.onDateSelected(Date(calendar.timeInMillis))
                dismiss()
            }
        }

        btn_cancel.setOnClickListener {
            dismiss()
        }
        val initCalendar = calendar?: Calendar.getInstance()
        date_picker.init(
            initCalendar.get(Calendar.YEAR),
            initCalendar.get(Calendar.MONTH),
            initCalendar.get(Calendar.DAY_OF_MONTH),
            null
        )
    }

    fun setMinDate(minDate: Calendar) {
        date_picker.minDate = minDate.timeInMillis
    }

    fun setMaxDate(maxDate: Calendar) {
        date_picker.maxDate = maxDate.timeInMillis
    }

    interface OnDateSelectedListener {
        fun onDateSelected(date: Date)
    }

}