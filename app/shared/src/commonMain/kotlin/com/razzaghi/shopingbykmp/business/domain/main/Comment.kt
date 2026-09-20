package com.razzaghi.shopingbykmp.business.domain.main

import com.razzaghi.shopingbykmp.business.datasource.network.main.responses.CommentDTO


data class Comment(
    val id: Long,
    val comment: String,
    val createAt: String,
    val rate: Double,
    val user: User
)

fun CommentDTO.toComment() = Comment(
    id = id ?: 0,
    comment = comment ?: "",
    createAt = createAt ?: "",
    rate = rate ?: 0.0,
    user = user?.toUser() ?: User(),
)
