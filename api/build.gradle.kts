plugins {
    id("org.springframework.boot")
    kotlin("plugin.spring")
    kotlin("jvm")
}

dependencies {
    implementation(project(":app"))

    implementation("org.springframework.boot:spring-boot-starter-web")
    implementation("com.fasterxml.jackson.module:jackson-module-kotlin")

    implementation("org.springdoc:springdoc-openapi-ui:1.6.6")

    testImplementation("org.springframework.boot:spring-boot-starter-test")
    testImplementation("org.jetbrains.kotlin:kotlin-test")
    testImplementation("io.rest-assured:rest-assured:5.4.0")
    testImplementation("io.rest-assured:json-path:5.4.0")
    testImplementation("io.rest-assured:xml-path:5.4.0")
    testImplementation("org.springframework.kafka:spring-kafka-test")

    constraints {
        testImplementation("org.apache.groovy:groovy-xml:4.0.16")
        testImplementation("org.apache.groovy:groovy:4.0.16")
    }
}

configurations.all {
    resolutionStrategy {
        force("org.apache.groovy:groovy:4.0.16")
        force("org.apache.groovy:groovy-xml:4.0.16")
    }
    exclude(group = "org.codehaus.groovy")
}
