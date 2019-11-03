package com.example.abc

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity
import org.jetbrains.anko.find

class ActivityEditName : AppCompatActivity() {
    private lateinit var mTextName: EditText
    private lateinit var mButtonSave: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        this.supportActionBar?.hide() //hide the title bar
        setContentView(R.layout.activity_edit_client_name)

        mTextName = find(R.id.activity_edit_tiet_name)
        mButtonSave = find(R.id.activity_edit_btn_save)

        mButtonSave.setOnClickListener {
            val result = Intent()
            result.putExtra("name", mTextName.text.toString())
            setResult(Activity.RESULT_OK, result)
            finish()
        }
    }

}