//
//  Author: Meir Finkelstine
//  Date: 2022-06-21 10:46:51 +0100 (Tue, 21 Jun 2022)
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
//                    Docker Login to Quay Container Registry
// ========================================================================== //

// QUAY_USER and QUAY_TOKEN must be set in the calling environment

def call(user='', token='') {
  user = user ?: env.QUAY_USER ?: error('dockerLoginQuay: user not specified and QUAY_USER not set in the environment')
  token = token ?: env.QUAY_TOKEN ?: error('dockerLoginQuay: token not specified and QUAY_TOKEN not set in the environment')
  dockerLogin(user, token, 'quay.io')
}
