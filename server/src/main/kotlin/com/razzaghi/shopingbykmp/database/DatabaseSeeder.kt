package com.razzaghi.shopingbykmp.database

import org.jetbrains.exposed.sql.insert
import org.jetbrains.exposed.sql.selectAll

object DatabaseSeeder {

    fun seed() {
        // Only run the seeder if the Categories table is completely empty
        if (CategoriesTable.selectAll().empty()) {

            // 1. Seed Banners
            val banners = listOf(
                "https://d33wubrfki0l68.cloudfront.net/92837bc083afaee80fcf94e034868749daa829ea/5d5a0/images/blog/templarbit-illustration-csp-header-92837bc0.jpg",
                "https://t4.ftcdn.net/jpg/03/06/69/49/360_F_306694930_S3Z8H9Qk1MN79ZUe7bEWqTFuonRZdemw.jpg",
                "https://t3.ftcdn.net/jpg/04/38/59/88/360_F_438598896_D9pyLmbMZ02CrxURfHxU4nG5UlzXv6Dy.jpg"
            ) //[cite: 31]

            banners.forEach { bannerUrl ->
                BannersTable.insert { it[imageUrl] = bannerUrl }
            }

            // 2. Seed Categories
            val categoryBaseImageUrl = "http://0.0.0.0:8080/category-image/" // Note: Update host for production

            val categoryNames = listOf(
                "Computer" to "computer.png", "Electronics" to "electronics.png",
                "Arts & Crafts" to "arts.png", "Automotive" to "car.png",
                "Baby" to "baby_cloth.png", "Beauty and Personal Care" to "beauty.png",
                "Women's Fashion" to "women_cloth.png", "Men's Fashion" to "men_cloth.png"
            ) // Adapted from[cite: 31]

            // Insert categories and keep track of their generated IDs for the products
            val categoryIds = categoryNames.map { (name, iconPath) ->
                CategoriesTable.insert {
                    it[this.name] = name
                    it[this.icon] = "$categoryBaseImageUrl$iconPath"
                    it[this.parent] = 0
                } get CategoriesTable.id
            }

            // 3. Seed Sample Products
            val productBaseImageUrl = "http://0.0.0.0:8080/product-image/"

            // Translating your Quintuple logic[cite: 31] to a simpler data class for DSL insertion
            data class SampleProduct(val title: String, val desc: String, val price: Long, val images: String, val categoryIndex: Int)

            val sampleProducts = listOf(
                SampleProduct(
                    "Sony WH-1000XM5 Headphones",
                    "The new integrated V1 processor unlocks the full potential of our QN1 HD Noise Cancelling processor...",
                    299L,
                    listOf("${productBaseImageUrl}sony1.jpg","${productBaseImageUrl}sony2.jpg").toString(),
                    0 // Computer Category
                ),
                SampleProduct(
                    "Samsung Galaxy S23",
                    "Flagship smartphone with AMOLED display and 128GB storage.",
                    899L,
                    listOf("${productBaseImageUrl}sumsuang1.jpg").toString(),
                    1 // Electronics Category
                ),
                SampleProduct(
                    "Nike Air Max",
                    "Stylish and comfortable sneakers for everyday wear.",
                    129L,
                    listOf("${productBaseImageUrl}nike1.jpg").toString(),
                    7 // Men's Fashion Category
                )
            ) // Adapted from[cite: 31]

            // Generate 30 random products as per your original script[cite: 31]
            repeat(30) { i ->
                val randomSample = sampleProducts.random()
                val finalPrice = randomSample.price + (0..1000).random() //[cite: 31]

                ExpandedProductsTable.insert {
                    it[title] = randomSample.title
                    it[description] = randomSample.desc
                    it[price] = finalPrice
                    // We extract the first image from your stringified list for the main cover image
                    it[image] = randomSample.images.removePrefix("[").removeSuffix("]").split(",").firstOrNull()?.trim()
                    it[categoryId] = categoryIds[randomSample.categoryIndex]
                    it[likes] = (0..500).random()
                    it[rate] = (1..5).random().toDouble() //[cite: 31]
                }
            }
        }
    }
}