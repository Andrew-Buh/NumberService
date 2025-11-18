plugins {
    id("java")
    id("org.springframework.boot") version "3.2.0"
    kotlin("plugin.spring") version "1.9.25"
    id("io.spring.dependency-management") version "1.1.4"
}

group = "com.example"
version = "1.0-SNAPSHOT"

java {
    toolchain {
        languageVersion = JavaLanguageVersion.of(17)
    }
}

repositories {
    mavenCentral()
}

dependencies {
    implementation("org.springframework.boot:spring-boot-starter-web")
    implementation("org.springframework.boot:spring-boot-starter-actuator")

    implementation("org.apache.poi:poi-ooxml:5.4.0")

    implementation("io.swagger.core.v3:swagger-annotations:2.2.28")
    implementation("org.springdoc:springdoc-openapi-starter-webmvc-ui:2.3.0")

//    implementation("org.springframework.boot:spring-boot-starter-validation")
//    implementation("com.fasterxml:classmate:1.6.0")

    compileOnly("org.projectlombok:lombok:1.18.30")
    annotationProcessor("org.projectlombok:lombok:1.18.30")

    implementation("org.mapstruct:mapstruct:1.5.5.Final")
    annotationProcessor("org.mapstruct:mapstruct-processor:1.5.5.Final")

    testImplementation("org.springframework.boot:spring-boot-starter-test")
}

tasks.test {
    useJUnitPlatform()
}