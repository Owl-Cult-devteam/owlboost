plugins {
    id("java")
    id("maven-publish")
}

group = "com.github.owl-cult-devteam"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

java {
    withSourcesJar()
}

publishing {
    publications {
        create<MavenPublication>("mavenJava") {
            from(components["java"])
        }
    }

    repositories {
        maven {
            name = "GitHubPackages"

            url = uri("https://github.com")

            credentials {
                username = System.getenv("GITHUB_ACTOR") ?: project.findProperty("gpr.user")?.toString()
                password = System.getenv("GITHUB_TOKEN") ?: project.findProperty("gpr.key")?.toString()
            }
        }
    }
}

dependencies {
    testImplementation(platform("org.junit:junit-bom:5.10.0"))
    testImplementation("org.junit.jupiter:junit-jupiter")
    testRuntimeOnly("org.junit.platform:junit-platform-launcher")
}

tasks.test {
    useJUnitPlatform()
}