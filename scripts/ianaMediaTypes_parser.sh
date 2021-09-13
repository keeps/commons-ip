#!/bin/bash

RESOURCES_FOLDER="./src/main/resources/controlledVocabularies"



declare -a ianaGroups=("application" "audio" "font" "image" "message" "model" "multipart" "text" "video" )

for ianaGroup in ${ianaGroups[@]}; do
   downloadURL="https://www.iana.org/assignments/media-types/${ianaGroup}.csv"
   response_checksum= $(curl --write-out %{http_code} -L $downloadURL | sed -i "1d" | cut -d, -f1,3 --complement >> "$RESOURCES_FOLDER/IANA_MEDIA_TYPES.txt")
done