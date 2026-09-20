package com.razzaghi.shopingbykmp.business.domain.main

import com.razzaghi.shopingbykmp.business.datasource.network.main.responses.BannerDTO

data class Banner(
    val banner: String,
    val id: Long
)

fun BannerDTO.toBanner() = Banner(banner = banner ?: "", id = id ?: 0)