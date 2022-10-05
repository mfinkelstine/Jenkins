//
//  Author: Meir Finkelstine
//  Date: 2022-06-21 13:12:02 +0100 (Tue, 21 Jun 2022)
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
//                     D o w n l o a d   T e r r a g r u n t
// ========================================================================== //

// Downloads Terragrunt binary to $HOME/bin

// Won't overwrite an existing ~/bin/terragrunt unless the 'overwrite: true' arg is given

// Designed for Kubernetes ephemeral agents rather than old style long running agents, in which case it'd need to be modified with a unique destination per version

// Adapted from DevOps Bash Tools setup/install_terragrunt.sh

def call(version) {
  String label = "Download Terragrunt on agent '$HOSTNAME'"
  echo "Acquiring Lock: $label"
  lock(resource: "$label"){
    timeout(time: 5, unit: 'MINUTES') {
      installBinary(
        binary: 'terragrunt',
        url: "https://github.com/gruntwork-io/terragrunt/releases/download/v$version/terragrunt_{os}_{arch}"
      )
    }
  }
}
