package com.example.bancoapp

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import com.example.bancoapp.databinding.ActivityLoginBinding

class LoginActivity : AppCompatActivity() {
    private lateinit var binding: ActivityLoginBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val usuarioCorrecto = "tallercmovil"
        val contrasenaCorrecta = "t4ll3rcm0v1l?"

        binding.btnEntrar.setOnClickListener {
            val usuarioIngresado = binding.etUsuario.text.toString().trim()
            val contrasenaIngresada = binding.etContrasena.text.toString().trim()

            if (usuarioIngresado == usuarioCorrecto && contrasenaIngresada == contrasenaCorrecta) {
                val intent = Intent(this, PaymentActivity::class.java)
                startActivity(intent)
                finish()
            } else {
                Toast.makeText(this, R.string.error_usuario_contrasena, Toast.LENGTH_SHORT).show()
            }
        }
    }
}