//
//  Author: Meir Finkelstine
//  Date: 2022-07-22 18:06:58 +0100 (Fri, 22 Jul 2022)
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
//                         D o w n l o a d   D a t r e e
// ========================================================================== //

//def call(version = '1.5.1') {
//  withEnv(["VERSION=${version}"]){
def call() {
  installPackages(
    [
      'bash',
      'curl',
      'unzip'
    ]
  )
  String label = "Download Datree on agent '$HOSTNAME'"
  echo "Acquiring Lock: $label"
  lock(resource: "$label"){
    sh (
      label: "$label",
      script: '''
        set -eux

        curl https://get.datree.io |
        /usr/bin/env bash
      '''
    )
  }
}
