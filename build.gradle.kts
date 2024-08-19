import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

val jasperVer: String by project // 6.20.0
val itextVer: String by project // 2.1.7.js10

plugins {
	id("org.springframework.boot") version "3.2.2"
	id("io.spring.dependency-management") version "1.1.4"
	kotlin("jvm") version "1.7.22"
	kotlin("plugin.spring") version "1.9.22"
}

group = "com.borntonight"
version = "0.0.1-SNAPSHOT"

java {
	sourceCompatibility = JavaVersion.VERSION_17
}

repositories {
	mavenCentral()
	maven {
		url = uri("https://jaspersoft.jfrog.io/jaspersoft/third-party-ce-artifacts/")
	}
}

dependencies {
	implementation("org.springframework.boot:spring-boot-starter")
	implementation("org.jetbrains.kotlin:kotlin-reflect")
	testImplementation("org.springframework.boot:spring-boot-starter-test")

	// Jasper
	implementation("com.lowagie:itext:$itextVer")
	implementation("net.sf.jasperreports:jasperreports:$jasperVer")
	implementation("net.sf.jasperreports:jasperreports-fonts:$jasperVer")
	implementation("net.sf.jasperreports:jasperreports-functions:$jasperVer")
	implementation("net.sf.jasperreports:jasperreports-metadata:$jasperVer")
	implementation("net.sf.jasperreports:jasperreports-chart-customizers:$jasperVer")
	implementation("net.sf.jasperreports:jasperreports-chart-themes:$jasperVer")

}

tasks.withType<KotlinCompile> {
	kotlinOptions {
		freeCompilerArgs += "-Xjsr305=strict"
		jvmTarget = "17"
	}
}

tasks.withType<Test> {
	useJUnitPlatform()
}
