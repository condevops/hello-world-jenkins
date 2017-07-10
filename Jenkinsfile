pipeline 
{
	agent 
	{
		label 'maven-8-debian'
	}
	
	stages 
	{
//		stage("checkout")
//		{
//			checkout scm
//		}

		stage("environment")
		{
			steps 
			{
				sh "env | sort"
			}
		}

		stage("build")
		{
			steps
			{
				sh "mvn -B " + mvnGoal
			}
		}

		stage("publish")
		{
			steps
			{
				step([$class: 'JUnitResultArchiver', testResults: 'target/surefire-reports/TEST-*.xml'])
				step([$class: 'JacocoPublisher', execPattern: 'target/jacoco.exec'])
			}
		}
	}
	post 
	{
		always 
		{
			echo "build result: ${currentBuild.result}"
		}
	}
}

//node("maven-8-debian")
//{
//    String mvnGoal = "verify"
//
//    if (env.IS_M2RELEASEBUILD)
//    {
//        mvnGoal = "-Dresume=false release:prepare release:perform"
//    }
//
//    stage("checkout")
//    {
//        checkout scm
//    }
//
//    stage("environment")
//    {
//        sh "env | sort"
//    }
//
//    stage("build")
//    {
//        sh "mvn -B " + mvnGoal
//    }
//
//    stage("publish")
//    {
//        step([$class: 'JUnitResultArchiver', testResults: 'target/surefire-reports/TEST-*.xml'])
//        step([$class: 'JacocoPublisher', execPattern: 'target/jacoco.exec'])
//    }
//}
