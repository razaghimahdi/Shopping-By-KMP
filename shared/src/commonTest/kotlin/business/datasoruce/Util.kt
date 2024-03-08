package business.datasoruce


import business.datasource.network.common.MainGenericResponse
import business.datasource.network.main.responses.HomeDTO
import business.datasource.network.main.responses.toHome
import business.domain.main.Home
import kotlinx.serialization.json.Json

private val json = Json {
    explicitNulls = false
    ignoreUnknownKeys = true
    isLenient = true
    prettyPrint = true
    encodeDefaults = true
    classDiscriminator = "#class"
}

fun serializeHomeData(jsonData: String): Home {

    val home: Home = json.decodeFromString<MainGenericResponse<HomeDTO>>(jsonData).result!!.toHome()

    return home
}