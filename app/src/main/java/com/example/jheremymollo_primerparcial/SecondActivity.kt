package com.example.jheremymollo_primerparcial

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.speech.tts.TextToSpeech
import android.widget.Toast
import com.example.jheremymollo_primerparcial.databinding.ActivitySecondBinding

class SecondActivity : AppCompatActivity() {

    private lateinit var binding: ActivitySecondBinding
    private lateinit var myHandler: Handler
    private var productRegistered = ""
    private var priceRegistered = ""
    private var stateStock = ""
    private var code = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySecondBinding.inflate(layoutInflater)
        setContentView(binding.root)
        myHandler = Handler(mainLooper)

        startParallelProcess()

    }

    private fun startParallelProcess() {
        Toast.makeText(this, "Procesando", Toast.LENGTH_SHORT).show()
        Thread {
            try {
                Thread.sleep(1000)
                for (i in 0 .. 10){
                    Thread.sleep(500)
                    myHandler.post {
                        binding.apply {
                            txtCounting.text = "$i % ... "
                            pbLoad.progress = i * 10
                        }
                    }
                }
                runOnUiThread {
                    Thread.sleep(5000)
                    Toast.makeText(this, "Listo... Registro completo", Toast.LENGTH_SHORT).show()
                    binding.txtCounting.text = "Terminado..."
                    randomCode()
                    showRegister()
                }
                Thread.sleep(5000)

            } catch (e: InterruptedException){
                e.printStackTrace()
            }
        }.start()
    }

    private fun showRegister() {

        productRegistered = "${intent.getStringExtra("product")}"
        priceRegistered = "${intent.getStringExtra("price")}"
        stateStock = "${intent.getStringExtra("state")}"

        binding.txtResult.text = """
            El producto registrado es: $productRegistered .
            Tiene un precio de: $priceRegistered Bs.
            El producto es $stateStock en el inventario.
            Su codigo de registro es: $code .
            Producto registrado exitosamente
        """.trimIndent()
    }

    private fun randomCode() {
        var list = mutableListOf<Int>()
        for (i in 2..17){
            var n = i + 3
            list.add(n)
        }
        code = list.random()

    }


}