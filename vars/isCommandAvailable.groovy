//
//  Author: Meir Finkelstine
//  Date: 2022-07-03 18:47:04 +0100 (Sun, 03 Jul 2022)
//
//  vim:ts=2:sts=2:sw=2:et
//
//  https://github.com/mfinkelstine/Jenkins
//
//  Liceese: see accompanying Meir Finkelstine LICENSE file
//
//  If you're using my code you're welcome to connect with me on LinkedIn and optionally send me feedback to help steer this or other code I publish
//
//  https://www.linkedin.com/in/mfinkelstine
//

// ========================================================================== //
//                 Checks if given command is available in $PATH
// ========================================================================== //

// returns Boolean whether given command is available in the $PATH

def call(bin){
  sh(
    label: "Check if '$bin' command is available in \$PATH ($PATH)",
    returnStatus: true,
    script: "command -v '$bin'"
  )
}
