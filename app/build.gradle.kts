import org.springframework.boot.gradle.tasks.bundling.BootJar

plugins {
    id("org.springframework.boot")
    kotlin("plugin.spring")
    kotlin("jvm")
}

dependencies {
    implementation(project(":domain"))
    implementation(project(":database"))
    implementation(project(":infra"))

    implementation("org.springframework:spring-context")
    implementation("org.springframework.boot:spring-boot-starter-validation")
    implementation("org.springframework:spring-tx")
    implementation("org.jetbrains.kotlin:kotlin-stdlib-jdk8")
}

tasks.withType<Jar> {
    enabled = true
}

tasks.withType<BootJar> {
    enabled = false
}