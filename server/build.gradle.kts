plugins {
    alias(libs.plugins.kotlinJvm)
    alias(libs.plugins.ktor)
    alias(libs.plugins.kotlinSerialization)

}

group = "com.razzaghi.shopingbykmp"
version = "1.0.0"

application {
    mainClass = "com.razzaghi.shopingbykmp.ApplicationKt"
}

dependencies {
    // Shared multiplatform models & DTOs
    api(project(":core"))

    // Logging & Engine
    implementation(libs.logback)
    implementation(libs.ktor.serverCore)
    implementation(libs.ktor.serverNetty)

    // Ktor Server Plugins
    implementation(libs.ktor.serverContentNegotiation)
    implementation(libs.ktor.serializationKotlinxJson)
    implementation(libs.ktor.serverAuth)
    implementation(libs.ktor.serverCors)
    implementation(libs.ktor.serverCallLogging)
    implementation(libs.ktor.serverAuthJwt)

    // Database & Connection Pooling
    implementation(libs.exposed.core)
    implementation(libs.exposed.dao)
    implementation(libs.exposed.jdbc)
    implementation(libs.exposed.javaTime)
    implementation(libs.postgresql)
    implementation(libs.mysql.connector)
    implementation(libs.hikaricp)

    // Dependency Injection
    implementation(libs.koin.ktor)
    implementation(libs.koin.logger)

    // Security
    implementation(libs.bcrypt)

    // Testing
    testImplementation(libs.ktor.serverTestHost)
    testImplementation(libs.kotlin.testJunit)
}