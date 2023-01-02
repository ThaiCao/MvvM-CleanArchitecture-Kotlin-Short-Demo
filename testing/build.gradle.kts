plugins {
    id(Plugins.java)
    id(Plugins.kotlin)
}

configurations.create("testArtifacts") {
    extendsFrom(configurations["testImplementation"])
}

tasks.register("testJar", Jar::class.java) {
    dependsOn("testClasses")
    classifier += "test"
    from(sourceSets["test"].output)
}

artifacts {
    add("testArtifacts", tasks.named<Jar>("testJar") )
}

dependencies {
    implementation(Kotlin.kotlin)
    implementation(Di.core)
    implementation(Coroutines.core)

    testImplementation(Test.junit)
    testImplementation(Test.coroutine)

    testImplementation(Test.mockk)
    tasks.withType<org.jetbrains.kotlin.gradle.tasks.KotlinCompile> {
        kotlinOptions.jvmTarget = "1.8"
    }
}
