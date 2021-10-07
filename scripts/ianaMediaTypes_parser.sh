#!/bin/bash

RESOURCES_FOLDER="./src/main/resources/controlledVocabularies"

rm -f "$RESOURCES_FOLDER/IANA_MEDIA_TYPES.txt" 2>&1

declare -a ianaGroups=("application" "audio" "font" "image" "message" "model" "multipart" "text" "video" )
declare -a missingMimeTypes=("image/jpeg" "image/gif" "text/plain" "video/mpeg")

for ianaGroup in ${ianaGroups[@]}; do
   echo $ianaGroup
   downloadURL="https://www.iana.org/assignments/media-types/${ianaGroup}.csv"
   curl -s $downloadURL | sed 1d | cut -d, -f1,3 --complement | sed '/^$/d' >> "$RESOURCES_FOLDER/IANA_MEDIA_TYPES.txt"
done

for mimeType in ${missingMimeTypes[@]}; do
  c=$(grep -c $mimeType "$RESOURCES_FOLDER/IANA_MEDIA_TYPES.txt")
  if [ "$c" -eq "0" ]; then
    echo $mimeType >> "$RESOURCES_FOLDER/IANA_MEDIA_TYPES.txt"
  fi
done