package com.razzaghi.shopingbykmp.security

import com.auth0.jwt.JWT
import com.auth0.jwt.algorithms.Algorithm
import java.util.Date

class TokenManager {
    // TODO: In production, load these from environment variables (e.g., application.conf)
    val secret = "shopping-by-kmp-super-secret-key"
    val issuer = "http://0.0.0.0:8080/"
    val audience = "shopping-by-kmp-users"
    private val expirationPeriod = 3600000L // 1 hour in milliseconds

    fun generateJWTToken(email: String): String {
        return JWT.create()
            .withAudience(audience)
            .withIssuer(issuer)
            .withClaim("email", email)
            .withExpiresAt(Date(System.currentTimeMillis() + expirationPeriod))
            .sign(Algorithm.HMAC256(secret))
    }
}