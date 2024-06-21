pipeline {

    agent any
     triggers {
            cron('H 3 * * *') // Build todos os dias às 3h da manhã
            pollSCM('H/5 * * * *') // Verificar mudanças a cada 5 minutos
        }
    stages{
        stage('Test'){
            steps{
                echo 'Testing the project'
            }
        }
        stage('Build image'){
            steps{
                echo 'Building the project'
                 sh 'docker-compose up -d'

            }
        }
    }
}