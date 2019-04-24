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
                bat('del "C:\\monica\\sharedVirtual\\docker\\*.war" /s /f /q')
                echo 'Copy netflix-eureka-naming-server WAR file'
                bat("xcopy .\\netflix-eureka-naming-server\\target\\*.war C:\\monica\\sharedVirtual\\docker\\eureka\\target")
                echo 'Copy currency-exchange-service WAR file'
                bat("xcopy .\\currency-exchange-service\\target\\*.war C:\\monica\\sharedVirtual\\docker\\exchange\\target")
                echo 'Copy currency-conversion-service WAR file'
                bat("xcopy .\\currency-conversion-service\\target\\*.war C:\\monica\\sharedVirtual\\docker\\conversion\\target")
            }
        }
    }
    
}


