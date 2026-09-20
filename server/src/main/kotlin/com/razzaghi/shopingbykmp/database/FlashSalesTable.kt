package com.razzaghi.shopingbykmp.database

import org.jetbrains.exposed.sql.Table

object FlashSalesTable : Table("flash_sales") {
    val id = long("id").autoIncrement()
    val expiredAt = varchar("expired_at", 50)
    // A real implementation would link this to a FlashSaleProducts junction table
    override val primaryKey = PrimaryKey(id)
}