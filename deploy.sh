#!/usr/bin/env bash

TMP_DIR=/tmp/blog/
ORIGIN_ADDRESS=git@github.com:jjyyjjyy/tech.git
ORIGIN_BRANCH=gh-pages
cd notes
mvn clean asciidoctor:process-asciidoc
rm -rf ${TMP_DIR} && mkdir -p ${TMP_DIR}
cp -r target/generated-docs/ ${TMP_DIR}
cd ${TMP_DIR}
git init
git remote add origin ${ORIGIN_ADDRESS}
git branch -D ${ORIGIN_BRANCH}
git checkout -b ${ORIGIN_BRANCH}
git add .
git commit -m ":memo: Update"
git push --set-upstream origin ${ORIGIN_BRANCH}
cd -
echo "gh-pages sync successfully!"
