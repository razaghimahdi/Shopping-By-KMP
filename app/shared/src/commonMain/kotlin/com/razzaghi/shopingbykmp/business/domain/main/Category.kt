package business.domain.main

data class Category(
    val icon: String = "",
    val id: Long = 0L,
    val name: String = "",
    val parent: Int = 0
)

val category_all = Category(
    icon = "",
    id = -1,
    name = "All",
    parent = 0,
)