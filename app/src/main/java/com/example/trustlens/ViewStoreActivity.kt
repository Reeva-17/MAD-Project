package com.example.trustlens

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.example.trustlens.model.Product
import com.example.trustlens.repository.ProductRepository
import com.example.trustlens.repository.UserRepository

class ViewStoreActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.view_store)

        val storeNameTv = findViewById<TextView>(R.id.tv_store_name)
        val storeHandleTv = findViewById<TextView>(R.id.tv_store_handle)

        // Set Store Info from Backend
        UserRepository.getCurrentUser { user ->
            if (user != null) {
                storeNameTv.text = user.name
                storeHandleTv.text = "@${user.name.lowercase().replace(" ", "_")}"
            }
        }

        findViewById<ImageButton>(R.id.backButton).setOnClickListener {
            finish()
        }

        loadStoreInventory()
    }

    private fun loadStoreInventory() {
        val container = findViewById<LinearLayout>(R.id.storeProductContainer)
        container.removeAllViews()

        ProductRepository.getAllProducts { products ->
            for (product in products) {
                val itemView = LayoutInflater.from(this).inflate(R.layout.item_product, container, false)
                
                itemView.findViewById<TextView>(R.id.productName).text = product.name
                itemView.findViewById<TextView>(R.id.productPrice).text = product.price
                itemView.findViewById<TextView>(R.id.productSeller).text = product.sellerHandle
                itemView.findViewById<TextView>(R.id.productRating).text = product.trustScore.toString()

                itemView.setOnClickListener {
                    val intent = Intent(this, ProductActivity::class.java)
                    intent.putExtra("PRODUCT_NAME", product.name)
                    intent.putExtra("PRODUCT_PRICE", product.price)
                    intent.putExtra("PRODUCT_SELLER", product.sellerHandle)
                    intent.putExtra("PRODUCT_SCORE", product.trustScore)
                    // Use a default icon since demo data doesn't have URLs
                    intent.putExtra("PRODUCT_IMAGE", R.mipmap.ic_jacket_foreground)
                    startActivity(intent)
                }
                container.addView(itemView)
            }
        }
    }
}
