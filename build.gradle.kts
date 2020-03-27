import org.gradle.api.JavaVersion.VERSION_11

plugins {
    kotlin("jvm") version "1.3.71"
    application
}

group = "name.sargon"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

dependencies {
    implementation(kotlin("stdlib-jdk8"))
}

application {
    mainClassName = "name.sargon.knightpath.MainKt"
    applicationDefaultJvmArgs += "-ea"
}

tasks {
    compileKotlin {
        kotlinOptions.jvmTarget = VERSION_11.majorVersion
    }
    compileTestKotlin {
        kotlinOptions.jvmTarget = VERSION_11.majorVersion
    }
}
