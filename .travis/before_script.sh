#!/bin/bash

set -ex

# decrypt maven setting.xml
openssl aes-256-cbc -K $encrypted_f647f4e729e9_key -iv $encrypted_f647f4e729e9_iv -in .travis/settings.xml.enc -out settings.xml -d
