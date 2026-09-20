package com.razzaghi.shopingbykmp.business.domain.main

import com.razzaghi.shopingbykmp.business.datasource.network.main.responses.ProfileDTO

data class Profile(
    val name: String = "",
    val age: String = "",
    val profileUrl: String = "",
)

fun ProfileDTO.toProfile() =
    Profile(name = name ?: "", age = age ?: "", profileUrl = image ?: "")
