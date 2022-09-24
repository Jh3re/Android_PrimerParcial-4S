package com.example.jheremymollo_primerparcial

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.jheremymollo_primerparcial.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private var productRegistered = ""
    private var priceRegistered = ""
    private var stateStock = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.swStateStock.setOnCheckedChangeListener { compoundButton, b ->
            if (binding.swStateStock.isChecked){
                Toast.makeText(this, "Nuevo", Toast.LENGTH_SHORT).show()
            }else{
                Toast.makeText(this, "Existente", Toast.LENGTH_SHORT).show()
            }
        }
        binding.btnRegister.setOnClickListener {
            registerProduct()
        }
    }

    private fun registerProduct() {
        val product = binding.etNameProduct.text
        val stock = binding.etPrice.text
        if (product.isEmpty() || product.length < 3 || stock.isEmpty()) {
            Toast.makeText(this, "Informacion incompleta", Toast.LENGTH_SHORT).show()
        }else{
            if (binding.swStateStock.isChecked) {
                productRegistered = binding.etNameProduct.text.toString()
                priceRegistered = binding.etPrice.text.toString()
                stateStock = "nuevo"
                passScreen()
            } else {
                productRegistered = binding.etNameProduct.text.toString()
                priceRegistered = binding.etPrice.text.toString()
                stateStock = "existente"
                passScreen()
            }
        }
    }

    private fun passScreen(){
        val intent = Intent(this, SecondActivity::class.java)
        intent.apply {
            putExtra("product", productRegistered)
            putExtra("price", priceRegistered)
            putExtra("state", stateStock)
        }
        startActivity(intent)
    }
}