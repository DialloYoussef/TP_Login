package com.pidba.apps.tp_login

import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import android.content.DialogInterface
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity

/**
 *
 */
class SecondActivity : AppCompatActivity() {

    private lateinit var sharedPreferences: SharedPreferences

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_second)

        // Initialize UI components
        val userInfoTextView: TextView = findViewById(R.id.userInfoTextView)
        val logoutButton: Button = findViewById(R.id.logoutButton)
        val backToHomeButton: Button = findViewById(R.id.backToHomeButton)

        // Initialize SharedPreferences
        sharedPreferences = getSharedPreferences("user_preferences", MODE_PRIVATE)

        // Display user information
        val name = sharedPreferences.getString("name", "")
        val surname = sharedPreferences.getString("surname", "")
        val gender = sharedPreferences.getString("gender", "")
        val country = sharedPreferences.getString("country", "")

        userInfoTextView.text = "Nom: $name\nPrénom: $surname\nSexe: $gender\nPays: $country"

        // Logout button click listener
        logoutButton.setOnClickListener {
            // Clear SharedPreferences to simulate logout
            val editor = sharedPreferences.edit()
            editor.clear()
            editor.apply()

            // Navigate back to MainActivity
            val intent = Intent(this, MainActivity::class.java)
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP or Intent.FLAG_ACTIVITY_NEW_TASK)
            startActivity(intent)
            finish()
        }

        // Back to home button click listener
        backToHomeButton.setOnClickListener {
            // Navigate back to MainActivity
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }

        logoutButton.setOnClickListener {
            showLogoutConfirmationDialog()
        }
    }


    private fun showLogoutConfirmationDialog() {
        val builder = AlertDialog.Builder(this)
        builder.setTitle("Confirmation")
        builder.setMessage("Êtes-vous sûr de vouloir vous déconnecter?")

        builder.setPositiveButton("Oui") { dialog, which ->
            // L'utilisateur a confirmé, déconnectez-le
            logoutUser()
        }

        builder.setNegativeButton("Non") { dialog, which ->
            // L'utilisateur a annulé la déconnexion, ne rien faire ici
        }

        val dialog = builder.create()
        dialog.show()

    }

    private fun logoutUser() {
        // Clear SharedPreferences to simulate logout
        val editor = sharedPreferences.edit()
        editor.clear()
        editor.apply()

        // Navigate back to MainActivity
        val intent = Intent(this, MainActivity::class.java)
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP or Intent.FLAG_ACTIVITY_NEW_TASK)
        startActivity(intent)
        finish()
    }
}
