package com.example.abc.fragments.mainBottom

import android.annotation.SuppressLint
import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.fragment.app.Fragment
import com.example.abc.ActivityEditName
import com.example.abc.R
import org.jetbrains.anko.find
import org.jetbrains.anko.support.v4.startActivityForResult


class FragmentStart : Fragment(), View.OnClickListener {
    private lateinit var mTextMessage: TextView
    private lateinit var mBtnEditName: Button

    companion object {
        const val CHANGE_NAME = 1000
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_main_start, container, false)

        mTextMessage = view.find(R.id.fragment_main_start_message)

        mBtnEditName = view.find(R.id.fragment_main_start_btn_editMessage)
        mBtnEditName.setOnClickListener(this)

        return view
    }

    override fun onClick(p0: View?) {
        if (p0 == null) return
        when (p0.id) {
            mBtnEditName.id -> {
                startActivityForResult<ActivityEditName>(CHANGE_NAME)
            }
        }
    }

    @SuppressLint("SetTextI18n")
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        when (requestCode) {

            CHANGE_NAME -> {
                when (resultCode) {
                    Activity.RESULT_OK -> {
                        if (data != null) {
                            val name = data.getStringExtra("name")
                            mTextMessage.text =
                                "Hola $name, en la compañía ABC, eres muy importante para nosotros"
                        }
                    }
                }
            }

        }
    }

}