import org.springframework.boot.gradle.tasks.bundling.BootJar
import org.springframework.boot.gradle.tasks.run.BootRun

plugins {
	java
	id("org.springframework.boot") version "4.1.1"
	id("io.spring.dependency-management") version "1.1.7"
}

group = "ai.rodolfomendes"
version = "0.0.1-SNAPSHOT"

java {
	toolchain {
		languageVersion = JavaLanguageVersion.of(26)
	}
}

repositories {
	mavenCentral()
}

dependencies {
	implementation("org.springframework.boot:spring-boot-starter")
	implementation("org.springframework.ai:spring-ai-starter-model-ollama")
	testImplementation("org.springframework.boot:spring-boot-starter-test")
	testRuntimeOnly("org.junit.platform:junit-platform-launcher")
}

dependencyManagement {
	imports {
		mavenBom("org.springframework.ai:spring-ai-bom:2.0.1")
	}
}

tasks.withType<Test> {
	useJUnitPlatform()
}

// 1. Silences warnings when running via ./gradlew bootRun
tasks.withType<BootRun> {
	jvmArgs = listOf("--enable-native-access=ALL-UNNAMED")
}

// 2. Silences warnings when running the built java -jar command
tasks.withType<BootJar> {
	manifest {
		attributes["Enable-Native-Access"] = "ALL-UNNAMED"
	}
}