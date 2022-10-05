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
//                      D o w n l o a d   T e r r a f o r m
// ========================================================================== //

// Downloads Terraform binary to $HOME/bin

// Won't overwrite an existing ~/bin/terraform unless the 'overwrite: true' arg is given

// Designed for Kubernetes ephemeral agents rather than old style long running agents, in which case it'd need to be modified with a unique destination per version, although I'd recommend using tfenv instead in that case

// Adapted from DevOps Bash Tools setup/install_terraform.sh, install_binary.sh, install_packages.sh and lib/utils.sh

// you may need to call this first to ensure the prerequisite commands curl and unzip are available:
//
//    installPackages(['curl', 'unzip'])

def call(version) {
  String label = "Download Terraform on agent '$HOSTNAME'"
  echo "Acquiring Lock: $label"
  lock(resource: "$label"){
    timeout(time: 5, unit: 'MINUTES') {
      installBinary(
        binary: 'terraform',
        url: "https://releases.hashicorp.com/terraform/$version/terraform_${version}_{os}_{arch}.zip"
      )
    }
  }
}
