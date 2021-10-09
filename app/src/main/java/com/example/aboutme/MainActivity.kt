package com.example.aboutme

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.view.inputmethod.InputMethodManager
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.databinding.DataBindingUtil
import com.example.aboutme.databinding.ActivityMainBinding


class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    //creating the actual data above onCreate
    private val myNameinKotlinCode=MyName("Thomas Edison") //creating an instance of the data class and setting the name

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
//        setContentView(R.layout.activity_main) We now replace this with the line below
        binding=DataBindingUtil.setContentView(this,R.layout.activity_main) //instruction to create the binding object and connect the layout with the activity

//        findViewById<Button>(R.id.done_button).setOnClickListener{
//            addNickname(it)
//        }
        binding.doneButton.setOnClickListener{
            addNickname(it)
        }

        binding.myName=myNameinKotlinCode
    }


    private fun addNickname(view: View) { //View is for the view on which the function was called. In this case, it's going to be the Done button
//        val editText = findViewById<EditText>(R.id.nickname_edit)
//        val nicknametxtView = findViewById<TextView>(R.id.nickname_text) old way without binding

        binding.apply {
//            nicknameText.text = nicknameEdit.text //We set the text of the nickname textView into the nickname editText
            myName?.nickname=nicknameEdit.text.toString()
            invalidateAll() //To refresh the UI with the new data we have to invalidate all binding expressions so that they get recreated with the new,correct data
            nicknameEdit.visibility = View.GONE//We don't need the visibility of editext after the button has been clicked so we remove it
            doneButton.visibility = View.GONE //In this case we don't need the button to appear also after it has been clicked
            nicknameText.visibility = View.VISIBLE //We bring the textView back to visible in order to show the nickname
        }

        //This chunk of code is to hide the keyboard after we have pressed the button
        val imm=getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        imm.hideSoftInputFromWindow(view.windowToken,0)
    }
}