#!/usr/bin/env bash
cd notes
mvn clean asciidoctor:process-asciidoc
cd target/generated-docs
mv * ~/IdeaProjects/jjyyjjyy.github.io/
cd ~/IdeaProjects/jjyyjjyy.github.io/
rm -rf pom.xml
git add .
git commit -m ":memo: Update"
git push origin
