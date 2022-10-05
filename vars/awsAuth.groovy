#!/usr/bin/env groovy
//
//  Author: Meir Finkelstine
//  Date: 2022-07-03 19:18:16 +0100 (Sun, 03 Jul 2022)
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
//                            A W S   C L I   A u t h
// ========================================================================== //

def call() {
  sh(
    label: 'AWS CLI auth',
    script: 'aws sts get-caller-identity'
  )
  script {
    if(!env.AWS_ACCOUNT_ID && isCommandAvailable('jq')){
      env.AWS_ACCOUNT_ID = sh(
                            label: 'Generate AWS Account ID environment variable',
                            returnStdout: true,
                            script: 'aws sts get-caller-identity | jq -r .Account'
                           )
      if(!env.AWS_ACCOUNT_ID){
        error('Failed to determine AWS Account ID')
      }
    }
  }
}
