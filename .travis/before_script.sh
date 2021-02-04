#!/bin/bash

set -ex

# How to update settings.xml.enc (to update artifactory token)
# 1) create a copy of file temp.settings.xml (don't add it to git)
#    > cp temp.settings.xml settings.xml
# 2) generate new artifactory token for a user called $DATE_travis_commons_ip_deployer (e.g. 20191031_travis_commons_ip_deployer)
#    > ask KEEPS sysadmins to do it
# 3) encrypt file using travis tools
#    > travis encrypt-file settings.xml (say yes to override)
# 4) remove temp. file
#    > rm settings.xml

# decrypt maven setting.xml
openssl aes-256-cbc -K $encrypted_1d784fce58c3_key -iv $encrypted_1d784fce58c3_iv -in .travis/settings.xml.enc -out settings.xml -d
