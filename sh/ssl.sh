#!/usr/bin/env bash
read -p "Enter your domain [www.example.com]: " DOMAIN
openssl genrsa -des3 -out $DOMAIN.key 1024
SUBJECT="/C=US/ST=Mars/L=iTranswarp/O=iTranswarp/OU=iTranswarp/CN=$DOMAIN"
openssl req -new -subj $SUBJECT -key $DOMAIN.key -out $DOMAIN.csr
mv $DOMAIN.key $DOMAIN.origin.key
openssl rsa -in $DOMAIN.origin.key -out $DOMAIN.key
openssl x509 -req -days 3650 -in $DOMAIN.csr -signkey $DOMAIN.key -out $DOMAIN.crt

echo "TODO:"
echo "Copy $DOMAIN.crt to /etc/nginx/ssl/$DOMAIN.crt"
echo "Copy $DOMAIN.key to /etc/nginx/ssl/$DOMAIN.key"
echo "Add configuration in nginx:"
echo "server {"
echo "    listen 443 ssl;"
echo "    ssl_certificate     /etc/nginx/ssl/$DOMAIN.crt;"
echo "    ssl_certificate_key /etc/nginx/ssl/$DOMAIN.key;"
echo "}"

# jks
# openssl pkcs12 -export -in $DOMAIN.crt -inkey $DOMAIN.key -out mycert.p12 -name tomcat -CAfile  $DOMAIN.crt -caname root -chain
# keytool -importkeystore -v -srckeystore mycert.p12 -srcstoretype pkcs12 -srcstorepass 123456 -destkeystore tomcat.keystore -deststoretype jks -deststorepass 123456
