package com.razzaghi.shopingbykmp

interface Platform {
    val name: String
}

expect fun getPlatform(): Platform