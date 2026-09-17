package com.razzaghi.shopingbykmp.database

import com.zaxxer.hikari.HikariConfig
import com.zaxxer.hikari.HikariDataSource
import kotlinx.coroutines.Dispatchers
import org.jetbrains.exposed.sql.Database
import org.jetbrains.exposed.sql.SchemaUtils
import org.jetbrains.exposed.sql.transactions.experimental.newSuspendedTransaction
import org.jetbrains.exposed.sql.transactions.transaction

object DatabaseFactory {
    fun init() {
        // TODO: Move these to environment variables or an application.conf file later
        val driverClassName = "com.mysql.cj.jdbc.Driver"
        // Note: We add the timezone flag as MySQL often requires it to prevent connection crashes
        val jdbcURL = "jdbc:mysql://localhost:3306/shopping_db?useSSL=false&serverTimezone=UTC"
        val user = "root" // Default MySQL user is usually root, not postgres
        val password = "root" // <-- MAKE SURE TO PUT YOUR ACTUAL MYSQL PASSWORD HERE

        val hikariConfig = HikariConfig().apply {
            setDriverClassName(driverClassName)
            setJdbcUrl(jdbcURL)
            setUsername(user)
            setPassword(password)
            maximumPoolSize = 3
            isAutoCommit = false
            transactionIsolation = "TRANSACTION_REPEATABLE_READ"
            validate()
        }

        Database.connect(HikariDataSource(hikariConfig))

        // Auto-generate the table if it is missing
        transaction {
            SchemaUtils.create(
                UsersTable, NotificationsTable, AddressesTable,
                OrdersTable, ProductsTable,
                BasketsTable, CategoriesTable, ExpandedProductsTable,
                ProductGalleriesTable, CommentsTable,
                BannersTable, FlashSalesTable, ProductLikesTable
            )

            DatabaseSeeder.seed()
        }
    }

    // Helper function to run database queries asynchronously
    suspend fun <T> dbQuery(block: suspend () -> T): T =
        newSuspendedTransaction(Dispatchers.IO) { block() }
}