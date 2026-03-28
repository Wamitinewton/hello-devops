pipeline {
    agent any

    environment {
        GITHUB_USER    = 'wamitinewton'
        IMAGE_NAME     = 'hello-devops'
        REGISTRY       = "ghcr.io/${GITHUB_USER}/${IMAGE_NAME}"
        INFRA_REPO     = "https://github.com/${GITHUB_USER}/hello-devops-infra.git"
        INFRA_REPO_DIR = 'hello-devops-infra'
    }

    stages {

        stage('Checkout') {
            steps {
                checkout scm
            }
        }

        stage('Build & Push Image') {
            steps {
                script {
                    def imageTag = "${env.BUILD_NUMBER}"
                    withCredentials([string(credentialsId: 'GITHUB_TOKEN', variable: 'GH_TOKEN')]) {
                        sh """
                            echo \$GH_TOKEN | docker login ghcr.io -u ${GITHUB_USER} --password-stdin
                            docker build -t ${REGISTRY}:${imageTag} -t ${REGISTRY}:latest .
                            docker push ${REGISTRY}:${imageTag}
                            docker push ${REGISTRY}:latest
                        """
                    }
                    env.IMAGE_TAG = imageTag
                }
            }
        }

        stage('Update GitOps Repo') {
            steps {
                withCredentials([string(credentialsId: 'GITHUB_TOKEN', variable: 'GH_TOKEN')]) {
                    sh """
                        rm -rf ${INFRA_REPO_DIR}
                        git clone https://${GITHUB_USER}:\$GH_TOKEN@github.com/${GITHUB_USER}/hello-devops-infra.git ${INFRA_REPO_DIR}
                        cd ${INFRA_REPO_DIR}
                        sed -i 's|image: ghcr.io/${GITHUB_USER}/${IMAGE_NAME}:.*|image: ghcr.io/${GITHUB_USER}/${IMAGE_NAME}:${env.IMAGE_TAG}|' apps/hello-devops/deployment.yaml
                        git config user.email "jenkins@hello-devops.dev"
                        git config user.name "Jenkins"
                        git add apps/hello-devops/deployment.yaml
                        git commit -m "ci: update hello-devops image to build ${env.IMAGE_TAG}" || echo "No changes to commit"
                        git push
                    """
                }
            }
        }
    }

    post {
        always {
            sh 'docker image prune -f'
        }
    }
}