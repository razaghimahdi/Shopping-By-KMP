package presentation.navigation

import kotlinx.serialization.Serializable

@Serializable
sealed interface CategoriesNavigation {

    @Serializable
    data object Categories : CategoriesNavigation

    @Serializable
    data class Search(val categoryId: Long) : CategoriesNavigation

}

