pipeline {
    agent any

    stages {
        stage('Test') {
            steps {
                echo 'Open Command Prompt and Launch Android Emulator'
                sh 'start cmd.exe /k emulator @%DeviceName%'

                echo 'Run Full Test'
                sh 'mvn test -PFullFunctionalTest'
            }
        }
    }
}