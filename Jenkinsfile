pipeline {
    agent any

    stages {
        stage('Test') {
            steps {
                echo 'Open Command Prompt and Launch Android Emulator'
                bat 'start cmd.exe /k emulator @%DeviceName%'

                echo 'Run Full Test'
                bat 'mvn test -PFullFunctionalTest'
            }
        }
    }
}