package com.example.myapplication

import android.app.Activity
import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.PersistableBundle
import android.widget.Button
import android.widget.EditText

class MainActivity : AppCompatActivity() {
    private lateinit var editText:EditText
    private lateinit var reverse:Button
    private lateinit var about:Button
    private lateinit var exit:Button
    private val RESULT_ACTIVITY_REQUEST_CODE = 123
    private val PREFS_NAME = "MyPrefsFile"
    private val KEY_TEXT = "textKey"
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        editText = findViewById(R.id.editText)
        reverse=findViewById(R.id.reverse)
        about=findViewById(R.id.about)
        exit=findViewById(R.id.exit)
        val prefs = getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE)
        val savedText = prefs.getString(KEY_TEXT, "")
        editText.setText(savedText)
        if(savedInstanceState != null){
            val savedText = savedInstanceState.getString(KEY_TEXT)
            editText.setText(savedText)
        }
        reverse.setOnClickListener{
            val inputText = editText.text.toString()
            val intent = Intent(this, ResultActivity::class.java)
            intent.putExtra("inputText", inputText)
            startActivityForResult(intent, RESULT_ACTIVITY_REQUEST_CODE)
        }
        about.setOnClickListener{
            val intent = Intent(this, AboutActivity::class.java)
            startActivity(intent)
        }
        exit.setOnClickListener {
            finishAffinity()
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == RESULT_ACTIVITY_REQUEST_CODE && resultCode == Activity.RESULT_OK){
            val modifiedText = data?.getStringExtra("modifiedText")
            editText.setText(modifiedText)
        }
    }

    override fun onPause() {
        super.onPause()
        val prefs = getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE)
        val editor = prefs.edit()
        editor.putString(KEY_TEXT, editText.text.toString())
        editor.apply()
    }
}