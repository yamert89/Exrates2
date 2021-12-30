import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    kotlin("jvm") version "1.6.10"
    application
}

group = "porokhin.exrates"
version = "0.1-SNAPSHOT"
val ktor_version = System.getProperty("ktor_version")!!
val logback_version = System.getProperty("logback_version")!!

repositories {
    mavenCentral()
}

dependencies {
    testImplementation(kotlin("test"))
    //implementation("io.ktor:ktor-server-core:1.6.7")
    //implementation("io.ktor:ktor-server-netty:1.6.7")
    implementation("ch.qos.logback:logback-classic:$logback_version")
    implementation("io.ktor:ktor-client-core:$ktor_version")
    implementation("io.ktor:ktor-client-cio:$ktor_version")
    implementation("io.ktor:ktor-client-websockets:$ktor_version")
    implementation(project(":exchange"))


}

tasks.test {
    useJUnitPlatform()
}

tasks.withType<KotlinCompile> {
    kotlinOptions.jvmTarget = "11"
}

application {
    mainClass.set("MainKt")
}