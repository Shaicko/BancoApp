package com.example.bancoapp

import android.content.Intent
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.util.Patterns
import android.view.View
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.os.bundleOf
import com.example.bancoapp.databinding.ActivityPaymentBinding
import java.text.NumberFormat
import java.util.Calendar
import kotlin.math.min
import kotlin.random.Random

class PaymentActivity : AppCompatActivity() {
    private lateinit var binding: ActivityPaymentBinding
    private var isFormatting = false // Para evitar bucles infinitos
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
        binding = ActivityPaymentBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val montoAleatorio = Random.nextDouble(100.0, 5000.0)
        val montoFormateado = NumberFormat.getCurrencyInstance().format(montoAleatorio)
        binding.tvMonto.text = getString(R.string.monto_formato, montoFormateado)

        val adapter = ArrayAdapter.createFromResource(
            this,
            R.array.tipos_tarjeta,
            R.layout.spinner_item_selected
        )
        adapter.setDropDownViewResource(R.layout.spinner_dropdown_item)
        binding.spinnerTipoTarjeta.adapter = adapter

        binding.etFechaExp.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
            }
            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
            }
            override fun afterTextChanged(s: Editable?) {
                if (isFormatting) {
                    return
                }
                isFormatting = true
                val digits = s.toString().replace("\\D".toRegex(), "")
                val formatted = when {
                    digits.length <= 2 -> digits
                    else -> {
                        val mm = digits.substring(0, 2)
                        val yy = digits.substring(2, min(4, digits.length))
                        "$mm/$yy"
                    }
                }
                s?.replace(0, s.length, formatted)
                isFormatting = false
            }
        })

        binding.btnPagar.setOnClickListener {
            val nombre = binding.etNombre.text.toString().trim()
            val numeroTarjeta = binding.etNumeroTarjeta.text.toString().trim()
            val tipoTarjeta = binding.spinnerTipoTarjeta.selectedItem.toString()
            val fechaExp = binding.etFechaExp.text.toString().trim()
            val cvv = binding.etCVV.text.toString().trim()
            val email = binding.etEmail.text.toString().trim()

            var esValido = true
            var vistaConError: View? = null

            // 1. Validar Nombre
            val nombreRegex = "^[\\p{L} ]+$".toRegex()
            if (nombre.isEmpty()) {
                binding.etNombre.error = getString(R.string.error_nombre_obligatorio)
                esValido = false
                if (vistaConError == null) vistaConError = binding.etNombre
            } else if (!nombre.matches(nombreRegex)) {
                binding.etNombre.error = getString(R.string.error_nombre_invalido)
                esValido = false
                if (vistaConError == null) vistaConError = binding.etNombre
            } else {
                binding.etNombre.error = null
            }

            // 2. Validar Número de Tarjeta
            if (numeroTarjeta.length != 16) {
                binding.etNumeroTarjeta.error = getString(R.string.error_numero_tarjeta)
                esValido = false
                if (vistaConError == null) vistaConError = binding.etNumeroTarjeta
            } else {
                binding.etNumeroTarjeta.error = null
            }

            // 3. Validar Fecha de Expiración
            var fechaValida = true
            var errorFecha: String? = null

            if (fechaExp.isEmpty()) {
                errorFecha = getString(R.string.error_fecha_exp)
                fechaValida = false
            } else if (fechaExp.length != 5 || fechaExp[2] != '/') {
                errorFecha = getString(R.string.error_fecha_exp_formato)
                fechaValida = false
            } else {
                try {
                    val mesInput = fechaExp.substring(0, 2).toInt()
                    val anioInput = fechaExp.substring(3, 5).toInt()

                    if (mesInput < 1 || mesInput > 12) {
                        errorFecha = getString(R.string.error_fecha_exp_mes)
                        fechaValida = false
                    } else {
                        val c = Calendar.getInstance()
                        val anioActual = c.get(Calendar.YEAR) % 100
                        val mesActual = c.get(Calendar.MONTH) + 1
                        if (anioInput < anioActual) {
                            errorFecha = getString(R.string.error_fecha_exp_expirada)
                            fechaValida = false
                        } else if (anioInput == anioActual && mesInput < mesActual) {
                            errorFecha = getString(R.string.error_fecha_exp_expirada)
                            fechaValida = false
                        }
                    }
                } catch (_: NumberFormatException) {
                    errorFecha = getString(R.string.error_fecha_exp_formato)
                    fechaValida = false
                }
            }
            if (!fechaValida) {
                binding.etFechaExp.error = errorFecha
                esValido = false
                if (vistaConError == null) vistaConError = binding.etFechaExp
            } else {
                binding.etFechaExp.error = null
            }

            // 4. Validar CVV
            if (cvv.length < 3) {
                binding.etCVV.error = getString(R.string.error_cvv)
                esValido = false
                if (vistaConError == null) vistaConError = binding.etCVV
            } else {
                binding.etCVV.error = null
            }

            // 5. Validar Email
            if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
                binding.etEmail.error = getString(R.string.error_email)
                esValido = false
                if (vistaConError == null) vistaConError = binding.etEmail
            } else {
                binding.etEmail.error = null
            }

            // Comprobación final
            if (!esValido) {
                Toast.makeText(this, getString(R.string.error_campos_incompletos), Toast.LENGTH_SHORT).show()
                vistaConError?.requestFocus()
                return@setOnClickListener
            }

            val esExitoso = Random.nextDouble() <= 0.75
            Log.d("Exito", "Estado de la transacción: $esExitoso")

            val bundle = bundleOf(
                EXTRA_EXITOSO to esExitoso,
                EXTRA_MONTO to montoFormateado,
                EXTRA_NOMBRE to nombre,
                EXTRA_EMAIL to email,
                EXTRA_ULTIMOS_DIGITOS to numeroTarjeta.takeLast(4),
                EXTRA_TIPO_TARJETA to tipoTarjeta
            )

            val intent = Intent(this, ResultActivity::class.java)
            intent.putExtras(bundle)
            startActivity(intent)
        }
    }
}