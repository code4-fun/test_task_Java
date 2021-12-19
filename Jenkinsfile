pipeline {
  agent any
  tools {
    gradle 'gradle-6.8'
  }
  stages {
    stage("build") {
      steps {
        echo 'building the application'
        sh './gradlew build'
      }
    }
  }
}