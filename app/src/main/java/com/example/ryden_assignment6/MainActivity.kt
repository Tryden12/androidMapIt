package com.example.ryden_assignment6

import android.content.DialogInterface
import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.appcompat.app.AlertDialog
import com.example.ryden_assignment6.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity(),View.OnClickListener {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        //val myListener = MyListener()
        //binding.button1.setOnClickListener(myListener)

        binding.buttonMapIt.setOnClickListener {
                showOnMap()
        }
        binding.buttonReset.setOnClickListener(this)
    }

    private fun showOnMap() {
        val streetNum = binding.editTextStreetNumber.getText().toString().trim()
        val streetName = binding.editTextTextStreetName.getText().toString().trim()
        val city = binding.editTextCity.getText().toString().trim()
        val state = binding.editTextState.getText().toString().trim()

        // Combine to make address
        val address = streetNum + " " + streetName + ", " + city + ", " + state

        val encodedLocation = Uri.encode(address)
        Log.i("ENCODED LOCATION", encodedLocation)
        val uriString = "geo:0,0?q=" + encodedLocation
        val uri = Uri.parse(uriString)
        val intent = Intent(Intent.ACTION_VIEW, uri)
        startActivity(intent)


    }

    override fun onClick(v: View?) {

        val builder = AlertDialog.Builder(binding.root.context)

        val listener = object : DialogInterface.OnClickListener {
            override fun onClick(dialog: DialogInterface?, which: Int) {
                if (which == DialogInterface.BUTTON_POSITIVE) {

                    binding.editTextStreetNumber.setText("")
                    binding.editTextTextStreetName.setText("")
                    binding.editTextCity.setText("")
                    binding.editTextState.setText("")

                } else if (which == DialogInterface.BUTTON_NEGATIVE) {

                }
            }

        }

        builder
            .setTitle(R.string.confirm)
            .setMessage(R.string.confirmMessage)
            .setIcon(R.drawable.ic_dialog_question)
            .setPositiveButton(android.R.string.ok, listener)
            .setNegativeButton(android.R.string.cancel, listener)
            .show()


    }
}