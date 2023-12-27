package com.example.myapplication

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import android.widget.EditText
import android.widget.Button
import android.widget.RadioGroup
import android.widget.CheckBox
import android.widget.RadioButton

class ResultActivity : AppCompatActivity() {
    private lateinit var upperCaseCheckbox: CheckBox
    private lateinit var lowerCaseCheckbox: CheckBox
    private lateinit var ok: Button
    private lateinit var reversedTextView: TextView
    private lateinit var toReverse: Button
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_result)
        reversedTextView = findViewById(R.id.reversedTextView)
        upperCaseCheckbox = findViewById(R.id.upperCaseCheckbox)
        lowerCaseCheckbox = findViewById(R.id.lowerCaseCheckbox)
        toReverse = findViewById(R.id.toReverse)
        val inputText = intent.getStringExtra("inputText")
        reversedTextView.text = inputText

        toReverse.setOnClickListener {
            val currentText = reversedTextView.text.toString()
            reversedTextView.text = currentText.reversed()
        }
        ok = findViewById(R.id.Ok)
        ok.setOnClickListener {
            val intent = Intent()
            intent.putExtra("modifiedText", reversedTextView.text.toString())
            setResult(Activity.RESULT_OK, intent)
            finish()
        }
        upperCaseCheckbox.setOnCheckedChangeListener { _, isChecked ->
            if (isChecked) {
                reversedTextView.text = reversedTextView.text.toString().toUpperCase()
                lowerCaseCheckbox.isChecked = false
            }
        }
        lowerCaseCheckbox.setOnCheckedChangeListener { _, isChecked ->
            if (isChecked) {
                reversedTextView.text = reversedTextView.text.toString().toLowerCase()
                upperCaseCheckbox.isChecked = false
            }
        }
        findViewById<Button>(R.id.Ok).setOnClickListener {
            val resultIntent = Intent()
            resultIntent.putExtra("modifiedText", reversedTextView.text.toString())
            setResult(Activity.RESULT_OK, resultIntent)
            finish()
        }
    }
}