def context = 'pipeline/pull-requests/hello-world-jenkins'

try
{
	node("maven-8-debian")
	{
		String mvnGoal = "verify"

//		if (env.IS_M2RELEASEBUILD)
//		{
//			mvnGoal = "-Dresume=false release:prepare release:perform"
//		}

		stage("checkout")
		{
			checkout scm
		}

		stage("environment")
		{
			sh "env | sort"
		}

		stage("build")
		{
			sh "mvn -B " + mvnGoal
		}

		stage("publish")
		{
			step([$class: 'JUnitResultArchiver', testResults: 'target/surefire-reports/TEST-*.xml'])
			step([$class: 'JacocoPublisher', execPattern: 'target/jacoco.exec'])
        
			setGitHubPullRequestStatus state: 'SUCCESS', context: context, message: 'Build Successful'
		}
	}
}
catch (Exception e)
{
    setGitHubPullRequestStatus state: 'FAILURE', context: context, message: 'Build Failed'
    throw e
}
