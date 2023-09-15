plugins {
  id("com.android.library")
  id("org.jetbrains.kotlin.android")
  id("maven-publish")
}

android {
  namespace = "io.github.githubmanager79"
  compileSdk = 33

  defaultConfig {
    minSdk = 27

    testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    consumerProguardFiles("consumer-rules.pro")
  }

  buildTypes {
    release {
      isMinifyEnabled = false
      proguardFiles(getDefaultProguardFile("proguard-android-optimize.txt"), "proguard-rules.pro")
    }
  }
  compileOptions {
    sourceCompatibility = JavaVersion.VERSION_1_8
    targetCompatibility = JavaVersion.VERSION_1_8
  }
  kotlinOptions {
    jvmTarget = "1.8"
  }
}

publishing {
  publications {
    register("release", MavenPublication::class) {
      from(components.findByName("android"))

      groupId = "io.github.githubmanager79"
      artifactId = "clockwidgetlibrary"
      version = "1.0.0"

      pom {
        name.set("Clock Widget Library")
        description.set("A library for creating clock widgets")
        url.set("https://github.com/githubmanager79/ClockWidgetLibrary")

        licenses {
          license {
            name.set("MIT License")
            url.set("https://opensource.org/licenses/MIT")
          }
        }

        developers {
          developer {

            name.set("Bohdan Potorochyn")
            email.set("p.bogdanvladimirovich@gmail.com")
            organization.set("MyOrganization")
            organizationUrl.set("http://ec2-18-117-125-236.us-east-2.compute.amazonaws.com:8000")
          }
        }

        scm {
          connection.set("scm:git:git://github.com/githubmanager79/ClockWidgetLibrary.git")
          developerConnection.set("scm:git:ssh://github.com/githubmanager79/ClockWidgetLibrary.git")
          url.set("https://github.com/githubmanager79/ClockWidgetLibrary")
        }
      }

//      artifact("$buildDir/outputs/aar/clockwidgetlibrary-release.aar")
    }
  }
  repositories {
    maven {
      name = "ossrh"
      url = uri("https://s01.oss.sonatype.org/service/local/staging/deploy/maven2/")
      credentials {
        username = (project.findProperty("ossrhUsername") as String?) ?: System.getenv("OSSRH_USERNAME")
        password = (project.findProperty("ossrhPassword") as String?) ?: System.getenv("OSSRH_PASSWORD")
      }
    }
  }
}


dependencies {

  implementation("androidx.core:core-ktx:1.9.0")
  implementation("androidx.lifecycle:lifecycle-runtime-ktx:2.6.2")
  implementation("androidx.activity:activity-compose:1.7.2")
  implementation(platform("androidx.compose:compose-bom:2023.03.00"))
  implementation("androidx.compose.ui:ui")
  implementation("androidx.compose.ui:ui-graphics")
  implementation("androidx.compose.ui:ui-tooling-preview")
  implementation("androidx.compose.material3:material3")
  testImplementation("junit:junit:4.13.2")
  androidTestImplementation("androidx.test.ext:junit:1.1.5")
  androidTestImplementation("androidx.test.espresso:espresso-core:3.5.1")
  androidTestImplementation(platform("androidx.compose:compose-bom:2023.03.00"))
  androidTestImplementation("androidx.compose.ui:ui-test-junit4")
  debugImplementation("androidx.compose.ui:ui-tooling")
  debugImplementation("androidx.compose.ui:ui-test-manifest")
  implementation("androidx.appcompat:appcompat:1.6.1")
}

