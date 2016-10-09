node("java8-mvn-slave")
{
    String branch = env.BRANCH_NAME;

    stage("initialize")
    {
        echo "** environment **"
        sh 'env | sort'

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

    stage("deploy")
    {
        if (branch == null || master.equals(branch))
        {
            sh "mvn -B deploy"
        }
        else
        {
            echo "artifact deployment not required"
        }
    }
}
