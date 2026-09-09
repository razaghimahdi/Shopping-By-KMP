package com.razzaghi.shopingbykmp.repository

import com.razzaghi.shopingbykmp.business.datasource.network.main.responses.NotificationDTO
import com.razzaghi.shopingbykmp.database.DatabaseFactory.dbQuery
import com.razzaghi.shopingbykmp.database.NotificationsTable
import com.razzaghi.shopingbykmp.database.UsersTable
import org.jetbrains.exposed.sql.selectAll

interface NotificationRepository {
    suspend fun getNotificationsForUser(email: String): List<NotificationDTO>
}

class NotificationRepositoryImpl : NotificationRepository {
    override suspend fun getNotificationsForUser(email: String): List<NotificationDTO> = dbQuery {
        val userRow = UsersTable.selectAll().where { UsersTable.email eq email }.singleOrNull()
        val userId = userRow?.get(UsersTable.id) ?: return@dbQuery emptyList()

        NotificationsTable.selectAll().where { NotificationsTable.userId eq userId }
            .map {
                NotificationDTO(
                    id = it[NotificationsTable.id],
                    title = it[NotificationsTable.title],
                    description = it[NotificationsTable.description],
                    isRead = it[NotificationsTable.isRead]
                )
            }
    }
}