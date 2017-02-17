#!/usr/bin/env bash

echo
echo "################ Send RQ - Create transaction #######################"

curl -H "Content-Type: application/json" \
    -X POST \
    -d @requests/create-transaction.json \
    -H "Accept: application/json" \
    -b cookies.txt \
    http://localhost:8081/api/transaction

echo
echo