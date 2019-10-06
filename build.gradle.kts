buildscript {
    repositories {
        mavenCentral()
    }
    
    dependencies {
        classpath("org.jetbrains.kotlin:kotlin-gradle-plugin:1.3.50")
    }
}

subprojects {
    apply(plugin = "kotlin")
    apply(plugin = "kotlin-kapt")

    repositories {
        mavenCentral()
    }

    dependencies {
        "implementation"(kotlin("stdlib"))
        "implementation"(kotlin("reflect"))
    }
}