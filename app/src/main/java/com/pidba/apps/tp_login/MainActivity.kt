package com.pidba.apps.tp_login

import android.os.Bundle
import android.content.Intent
import android.content.SharedPreferences
import android.widget.*
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {

    private lateinit var sharedPreferences: SharedPreferences
    private lateinit var genderRadioGroup: RadioGroup
    private lateinit var maleRadioButton: RadioButton
    private lateinit var femaleRadioButton: RadioButton
    private lateinit var countrySpinner: Spinner

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Initialize UI components
        val nameEditText: EditText = findViewById(R.id.nameEditText)
        val surnameEditText: EditText = findViewById(R.id.surnameEditText)
        genderRadioGroup = findViewById(R.id.genderRadioGroup)
        maleRadioButton = findViewById(R.id.maleRadioButton)
        femaleRadioButton = findViewById(R.id.femaleRadioButton)
        countrySpinner = findViewById(R.id.countrySpinner)
        val registerButton: Button = findViewById(R.id.registerButton)

        // Initialize SharedPreferences
        sharedPreferences = getSharedPreferences("user_preferences", MODE_PRIVATE)

        // Set default values
        maleRadioButton.isChecked = true // "M" selected by default

        // Set up the country spinner with default selection
        val countries = arrayOf("Guinee", "France", "Canada", "Australi")
        val adapter = ArrayAdapter(this, android.R.layout.simple_spinner_item, countries)
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        countrySpinner.adapter = adapter
        val defaultCountryPosition = countries.indexOf("Guinee")
        countrySpinner.setSelection(defaultCountryPosition)

        registerButton.setOnClickListener {
            // Check if all fields are filled
            if (nameEditText.text.isNotEmpty() && surnameEditText.text.isNotEmpty() &&
                genderRadioGroup.checkedRadioButtonId != -1 && countrySpinner.selectedItemPosition != 0) {

                // Save user information in SharedPreferences
                val editor = sharedPreferences.edit()
                editor.putString("name", nameEditText.text.toString())
                editor.putString("surname", surnameEditText.text.toString())
                editor.putString("gender", findViewById<RadioButton>(genderRadioGroup.checkedRadioButtonId).text.toString())
                editor.putString("country", countrySpinner.selectedItem.toString())
                editor.apply()

                // Open the second activity
                val intent = Intent(this, SecondActivity::class.java)
                startActivity(intent)
            } else {
                Toast.makeText(this, "Veuillez remplir tous les champs", Toast.LENGTH_SHORT).show()
            }
        }
    }
}
