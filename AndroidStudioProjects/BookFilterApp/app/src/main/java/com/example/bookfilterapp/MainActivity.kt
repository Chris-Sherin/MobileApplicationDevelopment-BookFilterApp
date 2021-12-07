package com.example.bookfilterapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import com.google.android.material.textfield.TextInputLayout
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.lang.StringBuilder

class MainActivity : AppCompatActivity() {
    var count=0
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val authorVal=findViewById<TextInputLayout>(R.id.AuthorInput)
        val countryVal=findViewById<TextInputLayout>(R.id.CountryInput)
        val buttonVal=findViewById<Button>(R.id.button)
        val countRes=findViewById<TextView>(R.id.ResultCount)
        val ResDesc=findViewById<TextView>(R.id.ResultDisplay)

        buttonVal.setOnClickListener{
            val myApplication= application as MyApplication
            val httpApiService= myApplication.httpApiService

            CoroutineScope(Dispatchers.IO).launch {
                val decodedJsonResult=httpApiService.getFilter()


                withContext(Dispatchers.Main)
                {
                    val authName=authorVal.editText?.text?.toString()
                    val countryName=countryVal.editText?.text?.toString()
                    var myString=StringBuilder()
                    for(books in decodedJsonResult)
                    {
                        if((books.author.equals(authName))and (books.country).equals(countryName) )
                        {
                            if(count<3)
                            {
                                myString.append("Result: "+books.title)
                                myString.append("\n")
                            }
                            count++

                        }
                        ResDesc.text="$myString"
                    }
                    countRes.text="Results: $count"



                }


            }
        }
    }
}