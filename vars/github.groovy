def generateAppToken() {
    def appId = 26100
    def genJwtToken = "/var/lib/jenkins/scripts/generate-jwt.sh"
    def genAppToken = "/var/lib/jenkins/scripts/generate-installation-token.sh"

    withCredentials([file(credentialsId: 'datajegerne_key', variable: 'PRIVATE_KEY')]) {
        def jwtToken = sh(script: "$genJwtToken ${env.PRIVATE_KEY} $appId", returnStdout: true).trim()
        def appToken = sh(script: "$genAppToken $jwtToken", returnStdout: true).trim()

        return appToken
    }
}