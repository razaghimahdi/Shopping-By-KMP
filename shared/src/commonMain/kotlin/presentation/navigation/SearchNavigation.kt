package presentation.navigation

import kotlinx.serialization.Serializable


@Serializable
sealed interface SearchNavigation {


    @Serializable
    data object Search : SearchNavigation

    @Serializable
    data class Detail(val id: Long) : SearchNavigation


}

