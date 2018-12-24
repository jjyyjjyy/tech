#!/usr/bin/env bash
SITE_DIR=~/IdeaProjects/jjyyjjyy.github.io/
CHECK_NOTHING=$(echo `git status` | grep "nothing to commit")

cd notes
mvn clean asciidoctor:process-asciidoc
cd target/generated-docs
cp -r * ${SITE_DIR}  && cd ${SITE_DIR}
git pull
rm -rf pom.xml && rm -rf *.iml
if [[ ${#CHECK_NOTHING} != 0 ]];then
    echo "nothing to commit"
else
    git add --renormalize .
    git commit -m ":memo: Update"
    git push origin
fi
