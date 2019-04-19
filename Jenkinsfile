pipeline{
    agent any
    stages{        
        stage('Build Jars'){
            steps{
                echo 'Building Eureka service'
               dir('netflix-eureka-naming-server') {
                   bat "mvn clean install"
                    }
                echo 'Building currency exchange service'
                dir('currency-exchange-service') {
                   bat "mvn clean install"
                    }
                echo 'Building currency conversion service'
                dir('currency-conversion-service') {
                   bat "mvn clean install"
                    } 
            }
        }
        
        stage('Test'){
            steps{
                echo 'Perform tests'
            }
        }
        
        stage('Deploy'){
            steps{
                echo 'deploy'
            }
        }
    }
    
}


