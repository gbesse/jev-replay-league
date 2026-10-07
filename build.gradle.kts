plugins { java }
group = "org.jev"; version = "0.1.0"
java { toolchain { languageVersion = JavaLanguageVersion.of(21) } }
repositories { mavenCentral(); maven("https://repo.papermc.io/repository/maven-public/") }
dependencies { compileOnly("io.papermc.paper:paper-api:1.21.11-R0.1-SNAPSHOT"); testImplementation("org.junit.jupiter:junit-jupiter:5.13.4"); testRuntimeOnly("org.junit.platform:junit-platform-launcher:1.13.4") }
tasks.test { useJUnitPlatform() }
