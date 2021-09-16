#!/bin/bash

RESOURCES_FOLDER="./src/main/resources/controlledVocabularies"

rm -f "$RESOURCES_FOLDER/IANA_MEDIA_TYPES.txt" 2>&1

declare -a ianaGroups=("application" "audio" "font" "image" "message" "model" "multipart" "text" "video" )

for ianaGroup in ${ianaGroups[@]}; do
   echo $ianaGroup
   downloadURL="https://www.iana.org/assignments/media-types/${ianaGroup}.csv"
   curl -s $downloadURL | sed 1d | cut -d, -f1,3 --complement | sed '/^$/d' >> "$RESOURCES_FOLDER/IANA_MEDIA_TYPES.txt"
done