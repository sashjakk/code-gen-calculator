dependencies {
    implementation(project(":annotation"))

    implementation("com.google.auto.service:auto-service:1.0-rc6")
    kapt("com.google.auto.service:auto-service:1.0-rc6")

    implementation("com.squareup:kotlinpoet:1.4.0")
}