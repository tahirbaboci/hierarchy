import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
	id("org.springframework.boot") version "2.6.2"
	id("io.spring.dependency-management") version "1.0.11.RELEASE"
	kotlin("jvm") version "1.6.10"
	kotlin("plugin.spring") version "1.6.10"
}

group = "com.tahir"
version = "0.0.1"
java.sourceCompatibility = JavaVersion.VERSION_11

repositories {
	mavenCentral()
}

object versions {
	val spring_boot="2.6.2"
}

dependencies {

	implementation("org.springframework.boot:spring-boot-starter-web:${versions.spring_boot}")
	implementation("org.springframework.boot:spring-boot-starter:${versions.spring_boot}")
	implementation("org.springframework.boot:spring-boot-starter-security")
	implementation("org.springframework.boot:spring-boot-starter-data-jpa:2.6.2")

	implementation("org.jetbrains.kotlin:kotlin-reflect")
	implementation("org.jetbrains.kotlin:kotlin-stdlib-jdk8")

	implementation("com.fasterxml.jackson.module:jackson-module-kotlin:2.13.1")

    testImplementation("org.springframework.boot:spring-boot-starter-test:${versions.spring_boot}")
	testImplementation("org.hibernate:hibernate-testing:5.6.3.Final")
	testImplementation("io.mockk:mockk:1.12.2")
	testImplementation("org.springframework.security:spring-security-test")
	testImplementation("org.scalacheck:scalacheck_3:1.15.4")

	implementation("org.xerial:sqlite-jdbc:3.32.3.2")
	implementation("com.github.gwenn:sqlite-dialect:0.1.0")

	implementation("org.springdoc:springdoc-openapi-ui:1.6.4")
}

tasks.withType<KotlinCompile> {
	kotlinOptions {
		freeCompilerArgs = listOf("-Xjsr305=strict",)
		jvmTarget = "11"
	}
}

tasks.withType<Test> {
	useJUnitPlatform()
}