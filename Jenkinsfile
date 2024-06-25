pipeline {

    agent any
    environment {
        REPO_URL = 'https://github.com/hellyaxs/SantanderBankline_Api.git'
        REPO_DIR = 'SantanderBankline_Api'
        GIT_USER_EMAIL = 'you@example.com' // Altere para o seu e-mail
        GIT_USER_NAME = 'Your Name'     // Altere para o seu nome
    }
     triggers {
            cron('H 3 * * *') // Build todos os dias às 3h da manhã
            pollSCM('H/5 * * * *') // Verificar mudanças a cada 5 minutos
        }
    stages{
        stage('chekcout'){
            steps{
                script {
                    withCredentials([string(credentialsId: 'github-token', variable: 'GIT_TOKEN')]) {
                        // Verifica se o diretório já existe
                        if (fileExists(REPO_DIR)) {
                            dir(REPO_DIR) {
                                // Configura o Git para não usar rebase ao puxar as mudanças
                                sh 'git config pull.rebase false'
                                // Configura o Git com o usuário e email
                                sh "git config user.email '${GIT_USER_EMAIL}'"
                                sh "git config user.name '${GIT_USER_NAME}'"

                            }
                        } else {
                            // Se o diretório não existe, clona o repositório
                            sh """
                            git clone https://${GIT_TOKEN}@${REPO_URL} ${REPO_DIR}
                            cd ${REPO_DIR}
                            git config user.email '${GIT_USER_EMAIL}'
                            git config user.name '${GIT_USER_NAME}'
                            """
                        }
                    }
                }
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