//
//  Author: Meir Finkelstine
//  Date: 2022-06-30 18:03:25 +0100 (Thu, 30 Jun 2022)
//
//  vim:ts=2:sts=2:sw=2:et
//
//  https://github.com/mfinkelstine/Jenkins
//
//  License: see accompanying Meir Finkelstine LICENSE file
//
//  If you're using my code you're welcome to connect with me on LinkedIn and optionally send me feedback to help steer this or other code I publish
//
//  https://www.linkedin.com/in/mfinkelstine
//

// ========================================================================== //
//                Download Jenkins CLI from current Jenkins Master
// ========================================================================== //

// Downloads Terraform binary to $HOME/bin if run as a user, or /usr/local/bin if run as root

// Adapted from DevOps Bash Tools jenkins_cli.sh, install_binary.sh, install_packages.sh and lib/utils.sh

def call() {
  String label = "Download Jenkins CLI on agent '$HOSTNAME'"
  echo "Acquiring Lock: $label"
  lock(resource: "$label"){
    timeout(time: 5, unit: 'MINUTES') {
      jar_path = env.JENKINS_CLI_JAR ?: "$HOME/bin/jenkins-cli.jar"
      installBinary(url: "$JENKINS_URL/jnlpJars/jenkins-cli.jar", destination: "$jar_path")
    }
  }
}
