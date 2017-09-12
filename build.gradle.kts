import org.gradle.jvm.tasks.Jar
import org.jetbrains.dokka.gradle.DokkaTask
import org.jetbrains.kotlin.gradle.tasks.Kotlin2JsCompile

group = "org.webscene"
version = "0.1-SNAPSHOT"

val KOTLIN_VER = "1.1.4-3"
val DOKKA_VER = "0.9.13"


buildscript {
    repositories {
        mavenCentral()
        jcenter()
    }

    dependencies {
        classpath(kotlin("gradle-plugin"))
        classpath("org.jetbrains.dokka:dokka-gradle-plugin:0.9.13")
    }
}

apply {
    plugin("kotlin2js")
    plugin("org.jetbrains.dokka")
}

repositories {
    mavenCentral()
    jcenter()
}

dependencies {
    "compile"(kotlin(module = "stdlib-js", version = "1.1.4-3"))
}

val dokka: DokkaTask by tasks
val compileKotlin2Js: Kotlin2JsCompile by tasks

with(dokka) {
    moduleName = "webscene-client"
    outputDirectory = "$buildDir/javadoc"
    sourceDirs = files("src/main/kotlin")
}

compileKotlin2Js.kotlinOptions {
    outputFile = "web/webscene-client.js"
    sourceMap = true
}

dokka.doFirst {
    File("${projectDir.absolutePath}/build/javadoc").deleteRecursively()
}

task<Jar>("createSourceJar") {
    dependsOn("classes")
    classifier = "sources"
    from("src/main/kotlin")
}

task<Jar>("createDokkaJar") {
    dependsOn("dokka")
    classifier = "javadoc"
    from(dokka.outputDirectory)
}

task<Jar>("createAllJarFiles") {
    dependsOn("jar", "createSourceJar", "createDokkaJar")
    println("Creating JAR files (library, sources and documentation)...")
    doLast { println("Finished creating JAR files.") }
}
