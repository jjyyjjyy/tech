1.  环境搭建:

```shell
# 安装nodejs
curl -sL https://deb.nodesource.com/setup_9.x | sudo -E bash -
sudo apt install -y nodejs
# 安装yarn
curl -sS https://dl.yarnpkg.com/debian/pubkey.gpg | sudo apt-key add -
echo "deb https://dl.yarnpkg.com/debian/ stable main" | sudo tee /etc/apt/sources.list.d/yarn.list
# 配置yarn镜像
tee ~/.yarnrc <<-'EOF'
registry "http://registry.npm.taobao.org"
phantomjs "http://npm.taobao.org/mirrors/phantomjs"
sass-binary-site "http://npm.taobao.org/mirrors/node-sass"
EOF
# 配置yarn PATH
/etc/profile 文件添加: export PATH="$PATH:`yarn global bin`
source /etc/profile
# 安装ng-cli
yarn global add @angular/cli
ng set --global packageManager=yarn
# 验证
node -v
yarn --version
ng --version
```



