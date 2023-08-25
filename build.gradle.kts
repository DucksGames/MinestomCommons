plugins {
    id("java")
    id("maven-publish")
}

group = "com.ducksgames.commons"
version = "1.0-SNAPSHOT"
java.sourceCompatibility = JavaVersion.VERSION_17
java.targetCompatibility = JavaVersion.VERSION_17

repositories {
    mavenCentral()
    maven(url = "https://jitpack.io")
}

dependencies {
    compileOnly("com.github.Minestom:Minestom:c5047b8037")
    compileOnly("net.kyori:adventure-text-minimessage:4.14.0")
}
publishing {
    publications.create<MavenPublication>("maven") {
        from(components["java"])
    }
}