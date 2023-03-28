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
    private var tvAgeMinutes: TextView? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val btnDatePicker: Button = findViewById(R.id.btnDatePicker)
        tvSelectedDate = findViewById(R.id.tvSelectedDate)
        tvAgeMinutes = findViewById(R.id.tvAgeMinutes)


        btnDatePicker.setOnClickListener{
            clickDatePicker()
        }
    }

    private fun clickDatePicker() {
        val myCalender = Calendar.getInstance()//this is a calender from java that shows the current date and month instance
        val year = myCalender.get(Calendar.YEAR)
        val month = myCalender.get(Calendar.MONTH)
        val day = myCalender.get(Calendar.DAY_OF_MONTH)//this get a day in format day in the current month
        val dpd = DatePickerDialog(this,
            DatePickerDialog.OnDateSetListener{ _ , year, month, dayOfMonth -> //"view" is never used so we replace it with underscore _
                    //toast used to see if button works
//                Toast.makeText(this, "btn pressed ", Toast.LENGTH_LONG).show()
                val selectedDate = "$dayOfMonth/$month/$year" // this will hold the date entered into the DateDialog
                //now we set to see it on the device by targeting it's field

                tvSelectedDate?.text= selectedDate//? is added cuz the vaar is nullable

                val sdf = SimpleDateFormat("dd/MM/yyyy", Locale.ENGLISH)//this creates a format noticeable/usable/manipulable
                val theDate = sdf.parse(selectedDate)//so we are parsing the seletectedDate to be change into a DAteFormat
                //we need to make theDate safe by checking if it not empty then we can do an action
                theDate?.let{
                    //then we get the time that has passed from that date till the current date
                    val selectedDateInMinutes = theDate.time / 60000 //time same as getTime()

                    val currentDate = sdf.parse(sdf.format(System.currentTimeMillis()))//this get current date in millseconds

                    //execute the rest if the current data is no empty this is called null safety
                    currentDate?.let{
                        val currentDateInMinutes = currentDate.time / 60000

                        val differenceInMinutes = currentDateInMinutes - selectedDateInMinutes // time in minutes between selected date and current date

                        tvAgeMinutes?.text = differenceInMinutes.toString()
                    }

                }

            },
            year,
            month,
            day
        )

        //set to work with daysin the past
        dpd.datePicker.maxDate = System.currentTimeMillis() - 86400000

        dpd.show()//this shows the calender


    }


}