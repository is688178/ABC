package com.example.abc.fragments.mainBottom

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.View.OnClickListener
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import com.example.abc.R
import android.content.Intent
import android.net.Uri
import org.jetbrains.anko.find


class FragmentABC : Fragment(), OnClickListener {
    private lateinit var mTelephoneText: TextView
    private val mTelephone = "3331183219"

    private lateinit var mLocationText: TextView
    private val mLocation = "20.6666377,-103.3692374"

    private lateinit var mEmailText: TextView
    private val mEmail = "is688178@gmail.com"

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_main_abc, container, false)

        mTelephoneText = view.find(R.id.fragment_main_abc_telephone)
        mTelephoneText.setOnClickListener(this)

        mLocationText = view.find(R.id.fragment_main_abc_location)
        mLocationText.setOnClickListener(this)

        mEmailText = view.find(R.id.fragment_main_abc_email)
        mEmailText.setOnClickListener(this)

        return view
    }

    override fun onClick(p0: View?) {
        if (p0 == null) return
        when (p0.id) {
            mTelephoneText.id -> {
                val intent = Intent(Intent.ACTION_DIAL, Uri.fromParts("tel", mTelephone, null))
                startActivity(intent)
            }

            mLocationText.id -> {
                // Creates an Intent that will load a map
                val gmmIntentUri = Uri.parse("geo:$mLocation")
                val mapIntent = Intent(Intent.ACTION_VIEW, gmmIntentUri)

                // Make the Intent explicit by setting the Google Maps package
                mapIntent.setPackage("com.google.android.apps.maps")

                // Attempt to start an activity that can handle the Intent
                startActivity(mapIntent)
            }

            mEmailText.id -> {
                val email = Intent(Intent.ACTION_SEND)
                email.putExtra(Intent.EXTRA_EMAIL, arrayOf(mEmail))
                email.putExtra(Intent.EXTRA_SUBJECT, "Correo para ABC")
                email.putExtra(Intent.EXTRA_TEXT, "Texto de correo para ABC")

                //need this to prompts email client only
                email.type = "message/rfc822"

                startActivity(Intent.createChooser(email, "Choose an Email client :"))
            }
        }
    }
}

