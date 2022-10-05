//
//  Author: Meir Finkelstine
//  Date: 2021-04-30 15:25:01 +0100 (Fri, 30 Apr 2021)
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
//                                  D e p l o y
// ========================================================================== //

def call(){
  String label = "Deploy App, Environment: " + "$ENVIRONMENT".capitalize()
  echo "Acquiring Deployment Lock: $label"
  lock(resource: label, inversePrecedence: true){
    milestone ordinal: null, label: "Milestone: $label"
    retry(2){
      timeout(time: 20, unit: 'MINUTES') {
        // script in local repo
        sh 'deploy.sh'
      }
    }
  }
}
