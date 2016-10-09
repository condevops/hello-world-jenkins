node("java8-mvn-slave")
{
    echo "building branch " + env.BRANCH_NAME;

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
        junit testResults: '**/surefire-reports/*.xml'
    }
}
