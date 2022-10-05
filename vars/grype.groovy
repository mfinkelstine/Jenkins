//
//  Author: Meir Finkelstine
//  Date: 2022-01-06 17:19:11 +0000 (Thu, 06 Jan 2022)
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
//                                   G r y p e
// ========================================================================== //

// https://github.com/anchore/grype

def call(target, timeoutMinutes=10){
  label 'Grype'
  container('grype') {
    timeout(time: timeoutMinuntes, unit: 'MINUTES') {
      ansiColor('xterm') {
        withEnv(["TARGET=$target"]) {
          sh '''grype '$TARGET' --fail-on high --scope AllLayers'''
        }
      }
    }
  }
}
