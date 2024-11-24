package business.datasource.network.main.responses

import business.domain.main.Comment
import business.domain.main.User
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable


@Serializable
data class CommentDTO(
    @SerialName("id") val id: Long?,
    @SerialName("comment") val comment: String?,
    @SerialName("created_at") val createAt: String?,
    @SerialName("rate") val rate: Double?,
    @SerialName("user") val user: UserDTO?
)

fun CommentDTO.toComment() = Comment(
    id = id ?: 0,
    comment = comment ?: "",
    createAt = createAt ?: "",
    rate = rate ?: 0.0,
    user = user?.toUser() ?: User(),
)

