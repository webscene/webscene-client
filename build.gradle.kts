@file:Suppress("PropertyName")

import org.gradle.jvm.tasks.Jar
import org.jetbrains.dokka.gradle.DokkaTask
import org.jetbrains.kotlin.gradle.tasks.Kotlin2JsCompile


group = "org.webscene"
version = "0.1-SNAPSHOT"

plugins {
    `maven-publish`
}

publishing {
    publications {
        create("docs", MavenPublication::class.java) {
            from(components["java"])
            artifact(createDokkaJar)
        }
        create("sources", MavenPublication::class.java) {
            from(components["java"])
            artifact(createSourceJar)
        }
    }

    repositories {
        maven { url = uri("$buildDir/repository") }
    }
}

buildscript {
    extra["dokka-ver"] = "0.9.14"
    extra["kotlin-ver"] = "1.1.51"

    repositories {
        mavenCentral()
        jcenter()
    }

    dependencies {
        classpath(kotlin(module = "gradle-plugin", version = "${extra["kotlin-ver"]}"))
        classpath("org.jetbrains.dokka:dokka-gradle-plugin:${extra["dokka-ver"]}")
    }
}

val DOKKA_VER = "${extra["dokka-ver"]}"
val KOTLIN_VER = "${extra["kotlin-ver"]}"

apply {
    plugin("kotlin2js")
    plugin("org.jetbrains.dokka")
}

repositories {
    mavenCentral()
    jcenter()
}

dependencies {
    "compile"(kotlin(module = "stdlib-js", version = KOTLIN_VER))
}

val dokka by tasks.getting(DokkaTask::class) {
    moduleName = "webscene-client"
    outputDirectory = "$buildDir/javadoc"
    sourceDirs = files("src/main/kotlin")
    doFirst { File("${projectDir.absolutePath}/build/javadoc").deleteRecursively() }
}
val compileKotlin2Js by tasks.getting(Kotlin2JsCompile::class) {
    kotlinOptions {
        outputFile = "${projectDir.absolutePath}/web/webscene-client.js"
        sourceMap = true
    }
}
val createDokkaJar by tasks.creating(Jar::class) {
    dependsOn(dokka)
    classifier = "javadoc"
    from(dokka.outputDirectory)
}
val createSourceJar by tasks.creating(Jar::class) {
    dependsOn("classes")
    classifier = "sources"
    from("src/main/kotlin")
}

task<Jar>("createAllJarFiles") {
    dependsOn("jar", createSourceJar, createDokkaJar)
    println("Creating JAR files (library, sources and documentation)...")
    doLast { println("Finished creating JAR files.") }
}
