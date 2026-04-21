package com.example.trustlens

import android.os.Bundle
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import com.example.trustlens.model.Product
import com.example.trustlens.repository.ProductRepository
import com.google.firebase.auth.FirebaseAuth

class AddProductActivity : AppCompatActivity() {

    private val auth = FirebaseAuth.getInstance()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_product)

        val etName = findViewById<EditText>(R.id.etProductName)
        val etPrice = findViewById<EditText>(R.id.etProductPrice)
        val etDesc = findViewById<EditText>(R.id.etProductDesc)
        val spinnerCategory = findViewById<Spinner>(R.id.spinnerCategory)
        val btnSubmit = findViewById<Button>(R.id.btnAddProductSubmit)

        // Setup Spinner
        val categories = arrayOf("Women Clothes", "Men Clothes", "Kids Clothes", "Accessories", "Crochet", "Decor Items")
        val adapter = ArrayAdapter(this, android.R.layout.simple_spinner_item, categories)
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        spinnerCategory.adapter = adapter

        findViewById<ImageView>(R.id.btnBackAddProduct).setOnClickListener {
            finish()
        }

        btnSubmit.setOnClickListener {
            val name = etName.text.toString().trim()
            val price = etPrice.text.toString().trim()
            val category = spinnerCategory.selectedItem.toString()
            
            if (name.isEmpty() || price.isEmpty()) {
                Toast.makeText(this, "Please fill all required fields", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            val product = Product(
                name = name,
                price = price,
                category = category,
                sellerHandle = auth.currentUser?.email ?: "@unknown_seller"
            )

            ProductRepository.addProduct(product,
                onSuccess = {
                    Toast.makeText(this, "Product Listed Successfully", Toast.LENGTH_SHORT).show()
                    finish()
                },
                onFailure = { error ->
                    Toast.makeText(this, "Error: $error", Toast.LENGTH_SHORT).show()
                }
            )
        }
    }
}
