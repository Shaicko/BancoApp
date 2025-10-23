package com.example.bancoapp

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import com.example.bancoapp.databinding.ActivityResultBinding

class ResultActivity : AppCompatActivity() {
    private lateinit var binding: ActivityResultBinding
    companion object {
        const val EXTRA_EXITOSO = "EXTRA_EXITOSO"
        const val EXTRA_MONTO = "EXTRA_MONTO"
        const val EXTRA_NOMBRE = "EXTRA_NOMBRE"
        const val EXTRA_EMAIL = "EXTRA_EMAIL"
        const val EXTRA_ULTIMOS_DIGITOS = "EXTRA_ULTIMOS_DIGITOS"
        const val EXTRA_TIPO_TARJETA = "EXTRA_TIPO_TARJETA"
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityResultBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val args = intent.extras
        if (args != null) {
            val esExitoso = args.getBoolean(EXTRA_EXITOSO)
            val monto = args.getString(EXTRA_MONTO)
            val nombre = args.getString(EXTRA_NOMBRE)
            val email = args.getString(EXTRA_EMAIL)
            val ultimosDigitos = args.getString(EXTRA_ULTIMOS_DIGITOS)
            val tipoTarjeta = args.getString(EXTRA_TIPO_TARJETA)

            if (esExitoso) {
                binding.tvResultadoTitulo.text = getString(R.string.pago_exitoso)
                binding.tvResultadoTitulo.setTextColor(ContextCompat.getColor(this, R.color.green))
            } else {
                binding.tvResultadoTitulo.text = getString(R.string.pago_rechazado)
                binding.tvResultadoTitulo.setTextColor(ContextCompat.getColor(this, R.color.red))
            }

            val resumenTexto = getString(
                R.string.resumen_operacion,
                monto,
                nombre,
                email,
                tipoTarjeta,
                ultimosDigitos
            )

            binding.tvResumen.text = resumenTexto
        }
        binding.btnFinalizar.setOnClickListener {
            finishAffinity()
        }
    }
}