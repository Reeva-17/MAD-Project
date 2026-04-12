package com.example.trustlens

class ProductDetailActivity : AppCompatActivity() {

    private lateinit var productImagesPager: ViewPager2
    private lateinit var reviewsRecycler: RecyclerView
    private lateinit var similarProductsRecycler: RecyclerView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.product_detail)

        productImagesPager = findViewById(R.id.productImagesPager)
        reviewsRecycler = findViewById(R.id.reviewsRecycler)
        similarProductsRecycler = findViewById(R.id.similarProductsRecycler)

        // Sample product images
        val images = listOf(R.drawable.saree1, R.drawable.saree2, R.drawable.saree3)
        productImagesPager.adapter = ImagePagerAdapter(images)

        // Sample reviews
        val reviews = listOf(
            Review("Flipkart Customer", "Worth every penny", 5f),
            Review("Rashi", "Good quality, late delivery", 4f)
        )
        reviewsRecycler.layoutManager = LinearLayoutManager(this)
        reviewsRecycler.adapter = ReviewsAdapter(reviews)

        // Sample similar products
        val similar = listOf(
            Product("Parmila Fashion Saree", "₹397", R.drawable.saree1),
            Product("Aadishakti Saree", "₹372", R.drawable.saree2),
            Product("Redfish Saree", "₹424", R.drawable.saree3)
        )
        similarProductsRecycler.layoutManager = LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)
        similarProductsRecycler.adapter = SimilarProductsAdapter(similar)
    }
}
