package com.example.examenunidad1

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView

class Inicio : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_inicio)



        val btnRegresar: Button = findViewById(R.id.btnRegresar)

        btnRegresar.setOnClickListener{
            val v2: Intent = Intent(this, MainActivity::class.java)
            startActivity(v2)
        }

    }
}