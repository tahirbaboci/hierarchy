#!/bin/bash

curl  -v --header "Content-Type: application/json" \
      --request POST \
      -u theUsername:thePassword \
      -d @testData/hierarchy.json \
      http://localhost:8080/employee/hierarchy \
      | jq
#If you don't have jq installed at your pc, please comment the line above