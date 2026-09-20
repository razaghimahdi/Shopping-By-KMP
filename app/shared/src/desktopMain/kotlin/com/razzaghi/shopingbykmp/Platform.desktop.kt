package com.razzaghi.shopingbykmp



class DesktopPlatform: Platform {
    override val name: String = "Web with Kotlin/Desktop"
}

actual fun getPlatform(): Platform = DesktopPlatform()