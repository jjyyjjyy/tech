FROM node:10-alpine as builder
ARG ver=1.7.0
RUN sed -i 's/dl-cdn.alpinelinux.org/mirrors.ustc.edu.cn/g' /etc/apk/repositories && \
 apk add --no-cache git python make openssl tar gcc > /dev/null
ADD http://registry.npm.taobao.org/yapi-vendor/download/yapi-vendor-${ver}.tgz /home/
RUN tar zxf /home/yapi-vendor-${ver}.tgz -C /home/ && mkdir /api && mv /home/package /api/vendors && \
 cd /api/vendors && npm install --production --registry https://registry.npm.taobao.org

FROM node:10-alpine
ENV TZ="Asia/Shanghai" HOME="/"
WORKDIR ${HOME}
COPY --from=builder /api/vendors /api/vendors
COPY config.json /api/
COPY plugin.json /api/vendors/
EXPOSE 10001
ENTRYPOINT ["node","/api/vendors/server/app.js"]
