package com.razzaghi.shopingbykmp.repository

import com.razzaghi.shopingbykmp.business.datasource.network.main.responses.CommentDTO
import com.razzaghi.shopingbykmp.business.datasource.network.main.responses.CommentRequestDTO
import com.razzaghi.shopingbykmp.business.datasource.network.main.responses.UserDTO
import com.razzaghi.shopingbykmp.database.CommentsTable
import com.razzaghi.shopingbykmp.database.DatabaseFactory.dbQuery
import com.razzaghi.shopingbykmp.database.UsersTable
import org.jetbrains.exposed.sql.JoinType
import org.jetbrains.exposed.sql.insert
import org.jetbrains.exposed.sql.selectAll
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

interface CommentRepository {
    suspend fun getComments(productId: Long): List<CommentDTO>
    suspend fun addComment(email: String, request: CommentRequestDTO): Boolean
}

class CommentRepositoryImpl : CommentRepository {

    override suspend fun getComments(productId: Long): List<CommentDTO> = dbQuery {
        CommentsTable.join(UsersTable, JoinType.INNER, additionalConstraint = { CommentsTable.userId eq UsersTable.id })
            .selectAll().where { CommentsTable.productId eq productId }
            .map { row ->
                CommentDTO(
                    id = row[CommentsTable.id],
                    comment = row[CommentsTable.comment],
                    createAt = row[CommentsTable.createdAt], // Maps to "created_at" in JSON
                    rate = row[CommentsTable.rate],
                    user = UserDTO(
                        name = row[UsersTable.name],
                        image = row[UsersTable.image]
                    )
                )
            }
    }

    override suspend fun addComment(email: String, request: CommentRequestDTO): Boolean = dbQuery {
        val userRow = UsersTable.selectAll().where { UsersTable.email eq email }.singleOrNull()
        val userId = userRow?.get(UsersTable.id) ?: return@dbQuery false

        // Generate a simple timestamp for the comment
        val currentDateTime = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm"))

        CommentsTable.insert {
            it[this.userId] = userId
            it[this.productId] = request.productId
            it[this.comment] = request.comment
            it[this.rate] = request.rate
            it[this.createdAt] = currentDateTime
        }
        true
    }
}