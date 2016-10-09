node("java8-mvn-slave")
{
    echo "building branch " + env.BRANCH_NAME;

    sh 'env > env.txt'
    readFile('env.txt').split("\r?\n").each {
        println it
    }

    stage("initialize")
    {
        checkout scm
        sh "mvn -B dependency:go-offline help:active-profiles"
    }

    stage("build")
    {
        sh "mvn -B test-compile"
    }

    stage("tests")
    {
        sh "mvn -B verify"

        step([$class: 'JUnitResultArchiver', testResults: 'target/surefire-reports/TEST-*.xml'])
        step([$class: 'JacocoPublisher', execPattern: 'target/jacoco.exec'])
    }


}
