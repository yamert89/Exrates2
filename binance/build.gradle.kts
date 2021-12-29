plugins {
    kotlin("jvm") version "1.6.10"
}

group = "porokhin.exrates"
version = "0.1-SNAPSHOT"

repositories {
    mavenCentral()
}

dependencies {
    implementation(kotlin("stdlib"))
    implementation(project(":exchange"))
}