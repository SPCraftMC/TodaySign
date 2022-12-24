import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    kotlin("jvm") version "1.7.21"
}

group = "com.xmcn.spcraft.todaysign"
version = "1.0-SNAPSHOT"

repositories {
    maven("https://lss233.littleservice.cn/repositories/minecraft")
    maven("https://maven.aliyun.com/nexus/content/groups/public/")
    maven("https://hub.spigotmc.org/nexus/content/repositories/snapshots/")

}

dependencies {
    implementation("org.spigotmc:spigot-api:1.18-R0.1-SNAPSHOT")
    implementation("io.github.dreamvoid:MiraiMC-Bukkit:1.7.1")
    implementation("com.github.YiC200333:XConomyAPI:2.19.1")
    testImplementation(kotlin("test"))
}

tasks.test {
    useJUnitPlatform()
}

tasks.withType<KotlinCompile> {
    kotlinOptions.jvmTarget = "1.8"
}
