### CI/CD环境搭建:

1. 安装软件

```shell
sudo apt update && sudo apt upgrade
# openjdk
sudo apt install openjdk-8-jdk-headless
# maven
sudo apt install maven

# jenkins https://wiki.jenkins.io/display/JENKINS/Installing+Jenkins+on+Ubuntu
wget -q -O - https://pkg.jenkins.io/debian-stable/jenkins.io.key | sudo apt-key add -
cat <<EOF >/etc/apt/sources.list.d/jenkins.list
deb https://pkg.jenkins.io/debian-stable binary/
EOF
sudo apt update
sudo apt install jenkins

# docker
wget https://mirrors.tuna.tsinghua.edu.cn/docker-ce/linux/ubuntu/dists/$(lsb_release -cs)/pool/stable/amd64/docker-ce_18.03.1~ce-0~ubuntu_amd64.deb
sudo dpkg -i docker-ce*.deb
# sudo apt install -f

# docker-compose
sudo curl -L https://github.com/docker/compose/releases/download/1.21.2/docker-compose-`uname -s`-`uname -m` -o /usr/local/bin/docker-compose
sudo chmod +x /usr/local/bin/docker-compose

# check
java -version
mvn -version
git version
docker version
docker-compose --version
```

2.  配置Maven仓库镜像:

    ```shell
    curl https://hlt-demo.oss-cn-shanghai.aliyuncs.com/settings.xml > /usr/share/maven/conf/settings.xml
    ```

3.  配置Jenkins:

    -   jenkins用户执行sudo命令不需要密码

    ```shell
    sudo visudo
    # 最后一行添加:
    jenkins ALL=(ALL) NOPASSWD: ALL
    ```
    -   配置Tool Location (系统管理-> 系统设置 -> Tool Locations)

    ![tool](https://hlt-demo.oss-cn-shanghai.aliyuncs.com/tool.png)

    -   配置GitLab访问 (系统管理-> 系统设置 -> Gitlab)

    ![gitlab](https://hlt-demo.oss-cn-shanghai.aliyuncs.com/gitlab.png)

    -   生成SSH密钥对

    ```shell
    sudo ssh-keygen -f /var/lib/jenkins/ -C "jenkins@6ceng.com" 
    sudo chwon -R jenkins:jenkins /var/lib/jenkins/.ssh/
    ```

    -   安装插件:
        -   Git parameter
        -   GitLab
        -   Gitlab Authentication
        -   CloudBees Docker Build and Publish
        -   Mattermost Notification
        -   Maven Integration plugin
        -   NodeJS plugin
4.  配置Docker

```shell
# docker mirror
sudo mkdir -p /etc/docker
sudo tee /etc/docker/daemon.json <<-'EOF'
{
  "registry-mirrors": ["https://rpxa7eq1.mirror.aliyuncs.com"]
}
EOF
sudo systemctl daemon-reload
sudo systemctl restart docker

# docker against sudo
sudo usermod -aG docker $USER
sudo chmod 777 /var/run/docker.sock
logout
```


5.  Portainer(Docker GUI)

    文档地址: https://portainer.readthedocs.io/en/stable/

    ```yaml
    version: '3'
    services:
      app-portainer:
        container_name: portainer
        image: portainer/portainer
        restart: always
        command: -H unix:///var/run/docker.sock
        ports:
          - 19000:9000
        volumes:
          - /var/run/docker.sock:/var/run/docker.sock
          - ~/volumes/portainer/data:/data
    ```

6. Docker Registry

    ```shell
    version: '3'
    services:
      registry:
        image: registry:2
        ports:
          - 15000:5000
        environment:
            - REGISTRY_AUTH=htpasswd
            - REGISTRY_AUTH_HTPASSWD_PATH=/auth/htpasswd
            - REGISTRY_AUTH_HTPASSWD_REALM=Registry Realm
        volumes:
        # docker run --entrypoint htpasswd registry:2 -Bbn admin admin > ~/volumes/registry/htpasswd
            - ~/volumes/registry/htpasswd:/auth/htpasswd:ro
            - ~/volumes/registry/data:/var/lib/registry
      registry-ui:
        image: konradkleine/docker-registry-frontend:v2
        environment:
          - ENV_DOCKER_REGISTRY_HOST=192.168.0.3
          - ENV_DOCKER_REGISTRY_PORT=15000
          #- ENV_DOCKER_REGISTRY_USE_SSL=1
        ports:
          - 5080:80
    ```

    

7.  CI服务

    ```shell
    version: '3'
    services:
      ci-jenkins:
        restart: always
        image: jenkinsci/blueocean
        container_name: ci-jenkins
        volumes:
          - ~/volumes/jenkins/:/var/jenkins_home
          - /var/run/docker.sock:/var/run/docker.sock
          - /usr/bin/docker:/usr/bin/docker
        networks:
          - ci
        ports:
          - 12580:8080
          - 50000:50000
    
      ci-redis:
        image: redis
        container_name: ci-redis
        networks:
          - ci
        ports:
          - 16379:6379
        command:
        - --appendonly yes
        volumes:
        - ~/volumes/gitlab/redis/:/var/lib/redis
    
      ci-postgresql:
        container_name: ci-postgresql
        image: postgres:10.3-alpine
        networks:
          - ci
        volumes:
          - ~/volumes/gitlab/postgresql/:/var/lib/postgresql
        ports:
          - 15432:5432
        environment:
          - DB_USER=gitlab
          - DB_PASS=password
          - DB_NAME=gitlabhq_production
          - DB_EXTENSION=pg_trgm
    
      ci-gitlab:
        restart: always
        image: gitlab/gitlab-ce
        hostname: gitlab.javaone.cc
        container_name: ci-gitlab
        depends_on:
          - ci-postgresql
          - ci-redis
        ports:
          - "10080:80"
          - "10443:443"
          - "22:22"
        networks:
          - ci
        volumes:
          - ~/volumes/gitlab/data:/var/opt/gitlab
          - ~/volumes/gitlab/config:/etc/gitlab
          - ~/volumes/gitlab/logs:/var/log/gitlab
        environment:
          - DEBUG=false
    
          - DB_ADAPTER=postgresql
          - DB_HOST=postgresql
          - DB_PORT=15432
          - DB_USER=gitlab
          - DB_PASS=password
          - DB_NAME=gitlabhq_production
    
          - REDIS_HOST=redis
          - REDIS_PORT=16379
    
          - TZ=Asia/Shanghai
          - GITLAB_TIMEZONE=Shanghai
    
          - GITLAB_HTTPS=false
          - SSL_SELF_SIGNED=false
    
          - GITLAB_HOST=gitlab.javaone.cc
          - GITLAB_PORT=10080
          - GITLAB_SSH_PORT=10022
          - GITLAB_RELATIVE_URL_ROOT=
          - GITLAB_SECRETS_DB_KEY_BASE=long-and-random-alphanumeric-string
          - GITLAB_SECRETS_SECRET_KEY_BASE=long-and-random-alphanumeric-string
          - GITLAB_SECRETS_OTP_KEY_BASE=long-and-random-alphanumeric-string
    
          - GITLAB_NOTIFY_ON_BROKEN_BUILDS=true
          - GITLAB_NOTIFY_PUSHER=false
    
          - GITLAB_EMAIL=977996067@qq.com
          - GITLAB_EMAIL_REPLY_TO=977996067@qq.com
          - GITLAB_INCOMING_EMAIL_ADDRESS=977996067@qq.com
    
          - GITLAB_BACKUP_SCHEDULE=daily
          - GITLAB_BACKUP_TIME=01:00
    
          - SMTP_ENABLED=false
          - IMAP_ENABLED=false
          - OAUTH_ENABLED=false
    
          - OAUTH_CAS3_LABEL=cas3
          - OAUTH_CAS3_SERVER=
          - OAUTH_CAS3_DISABLE_SSL_VERIFICATION=false
          - OAUTH_CAS3_LOGIN_URL=/cas/login
          - OAUTH_CAS3_VALIDATE_URL=/cas/p3/serviceValidate
          - OAUTH_CAS3_LOGOUT_URL=/cas/logout
    
    networks:
      ci:
    ```


8.  Jenkins SSL配置:

```shell
# generate jks
openssl pkcs12 -inkey xxx.key -in 2xx.cert -export -out keys.pkcs12
keytool -importkeystore -srckeystore keys.pkcs12 -srcstoretype pkcs12 -destkeystore /var/lib/jenkins/jenkins.jks
# jenkins config
sudo vim /etc/default/jenkins
JENKINS_ARGS="--httpPort=-1 --httpsPort=8443 --httpsKeyStore=/var/lib/jenkins/jenkins.jks --httpsKeyStorePassword=password_you_entered"
```
