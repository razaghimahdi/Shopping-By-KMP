package com.razzaghi.shopingbykmp.business.constants

import com.razzaghi.shopingbykmp.getPlatform

val BASE_URL = if (getPlatform().name.lowercase().contains("android")) {
    "http://10.0.2.2:8080"
} else {
    "http://localhost:8080"
}