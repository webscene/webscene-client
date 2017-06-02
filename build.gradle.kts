import org.gradle.jvm.tasks.Jar
import org.jetbrains.dokka.gradle.DokkaTask
import org.jetbrains.kotlin.gradle.tasks.Kotlin2JsCompile

group = "org.webscene"
version = "0.1-SNAPSHOT"

fun deleteDirectory(directory: File): Boolean {
    @Suppress("VARIABLE_WITH_REDUNDANT_INITIALIZER")
    var files: Array<File>? = arrayOf()

    if (directory.exists()) {
        files = directory.listFiles()
        if (files != null) {
            for (i in 0..files.size) {
                if (files[i].isDirectory) {
                    deleteDirectory(files[i])
                } else {
                    files[i].delete()
                }
            }
        }
    }
    return directory.delete()
}

buildscript {
    extra["kotlin-ver"] = "1.1.2-2"
    extra["dokka-ver"] = "0.9.13"

    repositories {
        mavenCentral()
        jcenter()
    }

    dependencies {
        classpath("org.jetbrains.kotlin:kotlin-gradle-plugin:${extra["kotlin-ver"]}")
        classpath("org.jetbrains.dokka:dokka-gradle-plugin:${extra["dokka-ver"]}")
    }
}

apply {
    plugin("kotlin2js")
    plugin("org.jetbrains.dokka")
}

repositories {
    mavenCentral()
}

dependencies {
    compile("org.jetbrains.kotlin:kotlin-stdlib-js:${extra["kotlin-ver"]}")
}

val dokka: DokkaTask by tasks
val compileKotlin2Js: Kotlin2JsCompile by tasks

dokka.moduleName = "webscene-client"
dokka.outputDirectory = "$buildDir/javadoc"
dokka.sourceDirs = files("src/main/kotlin")
compileKotlin2Js.kotlinOptions {
    outputFile = "web/webscene-client.js"
    sourceMap = true
}

dokka.doFirst {
    deleteDirectory(File("${projectDir.absolutePath}/build/javadoc"))
}

tasks {
    "createSourceJar"(Jar::class) {
        dependsOn("classes")
        classifier = "sources"
        from("src/main/kotlin")
    }

    "createDokkaJar"(Jar::class) {
        dependsOn("dokka")
        classifier = "javadoc"
        from(dokka.outputDirectory)
    }

    "createAllJarFiles" {
        dependsOn("jar", "createSourceJar", "createDokkaJar")
        println("Creating JAR files (library, sources and documentation)...")
    }
}

val createAllJarFiles: Task by tasks

createAllJarFiles.doLast {
    println("Finished creating JAR files.")
}