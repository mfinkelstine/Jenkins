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
//                                A p p r o v a l
// ========================================================================== //

// Prompts for human click approval before progressing - protect your production environments from deployments

// Usage:
//
//    approval(submitter: 'platform-engineering@mycompany.com,Deployers', timeout: 10)
//
// or better to DRY between pipelines:
//
//    approval(submitter: "$DEPLOYERS", timeout: 10)
//
// set to 2 hours instead of default 60 minutes - these values are those supported by the standard Jenkins timeout() function:
//
//    approval(submitter: "$DEPLOYERS", timeout: 2, timeoutUnits: 'HOURS')
//
// then configure $DEPLOYERS environment variable at the global Jenkins level:
//
//    Manage Jenkins -> Configure System -> Global properties -> Environment Variables -> Add -> DEPLOYERS
//
// submitter = comma separated list of users/groups by name or email address that are permitted to authorize
// ok        = what the ok button should say, defaults to 'Proceed' if empty/unspecified

def call(Map args = [submitter:'', timeout:60, timeoutUnits: 'MINUTES', ok:'']){
  milestone ordinal: null, label: "Milestone: Approval"
  args.timeout = args.timeout ?: 60
  args.timeoutUnits = args.timeoutUnits ?: 'MINUTES'
  timeout(time: args.timeout, unit: args.timeoutUnits) {
    input (
      message: """Are you sure you want to release this build?

This prompt will time out in ${args.timeout} ${args.timeoutUnits.toLowerCase()}""",
      ok: args.ok,
      // only allow people in these 2 groups to approve before proceeeding eg. to production deployment - this list can now be provided as an argument
      //submitter: "platform-engineering@mydomain.co.uk,Deployers"
      submitter: args.submitter
    )
  }
}
