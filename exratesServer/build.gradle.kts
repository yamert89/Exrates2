plugins {
    kotlin("jvm") version "1.6.10"
}

group = "porokhin.exrates"
version = "0.1-SNAPSHOT"
val ktor_version = System.getProperty("ktor_version")!!
val logback_version = System.getProperty("logback_version")!!


repositories {
    mavenCentral()
}

dependencies {
    implementation(kotlin("stdlib"))
    implementation("io.ktor:ktor-server-core:$ktor_version")
    implementation("io.ktor:ktor-server-netty:$ktor_version")
    implementation("ch.qos.logback:logback-classic:$logback_version")
    implementation("io.ktor:ktor-serialization:$ktor_version")
    testImplementation("io.ktor:ktor-server-test-host:$ktor_version")
    testImplementation("org.jetbrains.kotlin:kotlin-test")
}