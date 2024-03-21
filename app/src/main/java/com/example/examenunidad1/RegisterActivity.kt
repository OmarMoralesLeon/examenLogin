package com.example.examenunidad1

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity

class RegisterActivity : AppCompatActivity() {

    private lateinit var sharedPreferences: SharedPreferences

    companion object {
        const val USER_DATA_PREF = "user_data"
        const val KEY_NAME = "name"
        const val KEY_CORREO = "correo"
        const val KEY_PASSWORD = "password"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)

        sharedPreferences = getSharedPreferences(USER_DATA_PREF, Context.MODE_PRIVATE)

        val registro = findViewById<Button>(R.id.Registro)
        registro.setOnClickListener {
            val name = findViewById<EditText>(R.id.name).text.toString()
            val correo = findViewById<EditText>(R.id.correo).text.toString()
            val password = findViewById<EditText>(R.id.password).text.toString()
            val password2 = findViewById<EditText>(R.id.password2).text.toString()

            if (name.isNotEmpty() && correo.isNotEmpty() && password.isNotEmpty() && password2.isNotEmpty()) {
                if (password == password2 && isPasswordValid(password)) {
                    saveUserData(name, correo, password)
                    showSuccessDialog(name)
                } else {
                    showErrorDialog("Revisa que coincida la contraseña y cumpla con los criterios.")
                }
            } else {
                showErrorDialog("Completa todos los campos")
            }
        }

        val volverLogin = findViewById<TextView>(R.id.volverLogin)
        volverLogin.setOnClickListener {
            goLogin()
        }
    }

    private fun saveUserData(name: String, correo: String, password: String) {
        try {
            val editor = sharedPreferences.edit()
            editor.putString(KEY_NAME, name)
            editor.putString(KEY_CORREO, correo)
            editor.putString(KEY_PASSWORD, password)
            editor.apply()
        } catch (e: Exception) {
            e.printStackTrace()
            showErrorDialog("Error al guardar la información del usuario.")
        }
    }

    private fun showSuccessDialog(name: String) {
        val dialog = AlertDialog.Builder(this)
        dialog.setTitle("Registro Exitoso")
        dialog.setMessage("Se ha registrado correctamente un nuevo usuario: $name")
        dialog.setPositiveButton("Aceptar") { _, _ ->
            goLogin()
        }
        dialog.show()
    }

    private fun showErrorDialog(message: String) {
        val dialog = AlertDialog.Builder(this)
        dialog.setTitle("Error")
        dialog.setMessage(message)
        dialog.setPositiveButton("Aceptar") { _, _ -> }
        dialog.show()
    }

    private fun isPasswordValid(password: String): Boolean {

        return password.length >= 4
    }

    private fun goLogin() {
        val i = Intent(this, MainActivity::class.java)
        startActivity(i)
    }
}
