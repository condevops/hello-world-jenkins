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

    if (branch == null || master.equals(branch))
    {
        stage("release")
        {
            #sh "mvn -B unleash:perform"
            sh "mvn -B release:prepare release:perform -Dresume=false"
        }
    }
    else
    {
        stage("build")
        {
            sh "mvn -B test-compile"
        }

        stage("tests")
        {
            sh "mvn -B verify"
        }
    }

    stage("publish")
    {
        step([$class: 'JUnitResultArchiver', testResults: 'target/surefire-reports/TEST-*.xml'])
        step([$class: 'JacocoPublisher', execPattern: 'target/jacoco.exec'])
    }
}
