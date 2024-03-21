package com.example.examenunidad1

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.os.CountDownTimer
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {

    private var numIntentos = 0
    private val maxIntentos = 3
    private val tiempoEsperaMillis: Long = 180000 // 5 minutos en milisegundos
    private var timer: CountDownTimer? = null


    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val Iniciar = findViewById<Button>(R.id.Iniciar)
        val email = findViewById<EditText>(R.id.email)
        val password = findViewById<EditText>(R.id.password)
        val tiempoRestante = findViewById<TextView>(R.id.tiempoRestante)

        Iniciar.setOnClickListener {
            val correo = email.text.toString()
            val passwor = password.text.toString()

            if (correo == "omarmoralesleon0@gmail.com" && passwor == "Omar123") {
                val v2: Intent = Intent(this, Inicio::class.java)
                startActivity(v2)
            } else {
                numIntentos++
                if (numIntentos >= maxIntentos) {
                    Iniciar.isEnabled = false
                    startTimer(tiempoRestante)
                }
                Toast.makeText(this, "E-mail o Password incorrecto", Toast.LENGTH_SHORT).show()
            }
        }

        val Salir = findViewById<Button>(R.id.Salir)
        Salir.setOnClickListener {
            finishAffinity()
        }

        val registrar = findViewById<TextView>(R.id.registrar)
        registrar.setOnClickListener { goRegistrar() }
    }

    private fun goRegistrar() {
        val i = Intent(this, RegisterActivity::class.java)
        startActivity(i)
    }

    private fun startTimer(tiempoRestante: TextView) {
        timer?.cancel()
        timer = object : CountDownTimer(tiempoEsperaMillis, 1000) {
            override fun onTick(millisUntilFinished: Long) {
                val minutos = millisUntilFinished / 60000
                val segundos = (millisUntilFinished % 60000) / 1000
                val tiempoFormateado = String.format("%02d:%02d", minutos, segundos)
                tiempoRestante.text = "Por favor, espera $tiempoFormateado antes de intentarlo nuevamente."
            }

            override fun onFinish() {
                tiempoRestante.text = ""
                numIntentos = 0
                findViewById<Button>(R.id.Iniciar).isEnabled = true
            }
        }.start()
    }

    override fun onDestroy() {
        timer?.cancel()
        super.onDestroy()
    }
}