

package arbitrator

import arbitrator.mandatory_tests.db
import arbitrator.optional_tests.mail

def call(Map args) {

    pipeline {
        agent any

        stages {
            stage ('start') {
                steps {
                    script {
                        branch = "${JOB_BASE_NAME}"

                        sh """
                            echo 'simple echo'
                            echo ${JOB_NAME}
                        
                    
                            #stuff
                            branch=${branch}
                            echo $branch

                        """
                    }
                }
            }
            stage ('Get branch') {
                steps {
                    script {
                        sh """
                            echo "${JOB_BASE_NAME}" | sed 's/\\%2F/\\//g' > /tmp/file.txt
                            cat /tmp/file.txt
                        """
                    }
                }
            }
            stage ('Dev branch check') {
                steps {
                    script {
                        def git_branch = readFile("/tmp/file.txt").trim()
                        
                        if (git_branch == "develop") {
                            echo "develop branch - do stuff"
                            args.develop = true
                        }else{
                            echo "skipping"
                        }
                    }
                }
            }
            stage ('Non Dev stuff') {
                when {
                    expression {
                        !args.develop
                    }
                }
                steps {
                    script {
                        def git_branch = readFile("/tmp/file.txt").trim()

                        if (git_branch == "develop") {
                            return
                        }else{
                            echo "do non-develop stuff"
                        }
                    }
                }
            }
            stage ('Master stuff') {
                when {
                    expression {
                        !args.develop
                    }
                }
                steps {
                    script {
                        echo "hold non-develop stuff"
                    }
                }
            }
        }
    }
}