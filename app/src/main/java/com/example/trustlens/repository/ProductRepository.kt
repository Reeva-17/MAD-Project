package com.example.trustlens.repository

import com.example.trustlens.model.Product
import com.example.trustlens.util.TrustEngine
import com.google.firebase.database.*

object ProductRepository {

    private val dbRef: DatabaseReference = FirebaseDatabase.getInstance(
        "https://mad-backend-79e1d-default-rtdb.asia-southeast1.firebasedatabase.app/"
    ).getReference("products")

    fun addProduct(product: Product, onSuccess: () -> Unit = {}, onFailure: (String) -> Unit = {}) {
        val id = dbRef.push().key ?: return
        val calculatedScore = TrustEngine.calculateScore(product)
        val productWithData = product.copy(id = id, trustScore = calculatedScore)
        
        dbRef.child(id).setValue(productWithData)
            .addOnSuccessListener { onSuccess() }
            .addOnFailureListener { onFailure(it.message ?: "Error") }
    }

    fun seedDemoData() {
        val demoProducts = listOf(
            Product(name = "Premium Leather Jacket", price = "4500/-", category = "Men Clothes", sellerHandle = "@leather_lux"),
            Product(name = "Floral Summer Dress", price = "1200/-", category = "Women Clothes", sellerHandle = "@style_boutique"),
            Product(name = "Kids Denim Overalls", price = "850/-", category = "Kids Clothes", sellerHandle = "@tiny_trends"),
            Product(name = "Minimalist Smart Watch", price = "2999/-", category = "Accessories", sellerHandle = "@tech_spot"),
            Product(name = "Handmade Crochet Scarf", price = "600/-", category = "Crochet", sellerHandle = "@crafty_hands"),
            Product(name = "Bohemian Wall Art", price = "1500/-", category = "Decor Items", sellerHandle = "@home_vibes"),
            Product(name = "Casual Canvas Shoes", price = "1100/-", category = "Men Clothes", sellerHandle = "@sole_mate"),
            Product(name = "Silver Pendant Necklace", price = "950/-", category = "Accessories", sellerHandle = "@jewelry_box"),
            Product(name = "Woolen Baby Booties", price = "400/-", category = "Kids Clothes", sellerHandle = "@cozy_kids"),
            Product(name = "Oversized Hoodie", price = "1400/-", category = "Women Clothes", sellerHandle = "@comfort_zone"),
            Product(name = "Retro Sunglasses", price = "750/-", category = "Accessories", sellerHandle = "@shady_deals"),
            Product(name = "Hand-woven Basket", price = "1200/-", category = "Decor Items", sellerHandle = "@rustic_roots"),
            Product(name = "Crochet Plushie Toy", price = "550/-", category = "Crochet", sellerHandle = "@toy_tales"),
            Product(name = "Linen Button-down Shirt", price = "1300/-", category = "Men Clothes", sellerHandle = "@gentle_style"),
            Product(name = "Pleated Midi Skirt", price = "900/-", category = "Women Clothes", sellerHandle = "@chic_finds"),
            Product(name = "Toddler Rain Boots", price = "650/-", category = "Kids Clothes", sellerHandle = "@splash_gear"),
            Product(name = "Scented Soy Candle", price = "450/-", category = "Decor Items", sellerHandle = "@glow_home"),
            Product(name = "Beaded Bracelet Set", price = "300/-", category = "Accessories", sellerHandle = "@bead_art"),
            Product(name = "Mens Slim Fit Jeans", price = "1800/-", category = "Men Clothes", sellerHandle = "@denim_den"),
            Product(name = "Silk Evening Gown", price = "5000/-", category = "Women Clothes", sellerHandle = "@glam_edge")
        )

        dbRef.removeValue().addOnCompleteListener {
            demoProducts.forEach { addProduct(it) }
        }
    }

    fun getAllProducts(callback: (List<Product>) -> Unit) {
        dbRef.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                val list = mutableListOf<Product>()
                snapshot.children.forEach { data ->
                    data.getValue(Product::class.java)?.let { list.add(it) }
                }
                callback(list)
            }
            override fun onCancelled(error: DatabaseError) { callback(emptyList()) }
        })
    }

    fun getProductsByCategory(category: String, callback: (List<Product>) -> Unit) {
        dbRef.orderByChild("category").equalTo(category)
            .addListenerForSingleValueEvent(object : ValueEventListener {
                override fun onDataChange(snapshot: DataSnapshot) {
                    val list = mutableListOf<Product>()
                    snapshot.children.forEach { data ->
                        data.getValue(Product::class.java)?.let { list.add(it) }
                    }
                    callback(list)
                }
                override fun onCancelled(error: DatabaseError) { callback(emptyList()) }
            })
    }
}
