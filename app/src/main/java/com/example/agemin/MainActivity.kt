package com.example.agemin

import android.app.DatePickerDialog
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import java.text.SimpleDateFormat
import java.util.*

class MainActivity : AppCompatActivity() {
    //create a member variable
    private var tvSelectedDate: TextView? = null//set to null and till given a value and private so it's not used by another class only in this class

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val btnDatePicker: Button = findViewById(R.id.btnDatePicker)
        tvSelectedDate = findViewById(R.id.tvSelectedDate)


        btnDatePicker.setOnClickListener{
            clickDatePicker()
        }
    }

    private fun clickDatePicker() {
        val myCalender = Calendar.getInstance()//this is a calender from java that shows the current date and month instance
        val year = myCalender.get(Calendar.YEAR)
        val month = myCalender.get(Calendar.MONTH)
        val day = myCalender.get(Calendar.DAY_OF_MONTH)//this get a day in format day in the current month

        DatePickerDialog(this,
                DatePickerDialog.OnDateSetListener{view, year, month, dayOfMonth ->

//                    Toast.makeText(this, "btn pressed", Toast.LENGTH_LONG).show()
                    val selectedDate = "$dayOfMonth/$month/$year" // this will hold the date entered into the DateDialog
                    //now we set to see it on the device by targeting it's field

                    tvSelectedDate?.text= selectedDate//? is added cuz the vaar is nullable

                    val sdf = SimpleDateFormat("dd/MM/yyyy", Locale.ENGLISH)//
                },
            year,
            month,
            day
            ).show()//this shows the calender

    }


}