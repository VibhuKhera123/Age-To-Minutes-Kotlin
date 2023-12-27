package com.example.age_to_minuts

import android.app.DatePickerDialog
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Locale

class MainActivity : AppCompatActivity() {

    private var selectedDate : TextView? = null
    private var timeView : TextView? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val btnDatePicker: Button = findViewById(R.id.btnDatePicker)
        selectedDate = findViewById(R.id.selectedDate)
        timeView = findViewById(R.id.dateInTime)
        btnDatePicker.setOnClickListener{
            selectDate()
        }
    }

    fun selectDate(){
        val myCylinder = Calendar.getInstance()
        val year = myCylinder.get(Calendar.YEAR)
        val month = myCylinder.get(Calendar.MONTH)
        val day = myCylinder.get(Calendar.DAY_OF_MONTH)
        val datePickerDialog = DatePickerDialog(
            this,
            DatePickerDialog.OnDateSetListener { view, year, month, dayOfMonth ->
                val date = "$dayOfMonth/${month+1}/$year"
                selectedDate?.text = date
                val simple_date_format = SimpleDateFormat("dd/MM/yyyy", Locale.ENGLISH)
                val theDate = simple_date_format.parse(date)
                val dateInMinuts = theDate.time/6000
                val currentDate = simple_date_format.parse(simple_date_format.format(System.currentTimeMillis()))
                val diffInMinuts = (currentDate.time / 6000) - dateInMinuts
                timeView?.text = diffInMinuts.toString()
            },
            year,
            month,
            day,
        )
        datePickerDialog.show()
    }
}