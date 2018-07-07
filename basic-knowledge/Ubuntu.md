1. 

 ```sudo apt update && sudo apt -y upgrade```


2. Typora

```shell
sudo apt-key adv --keyserver keyserver.ubuntu.com --recv-keys BA300B7755AFCFAE
sudo add-apt-repository 'deb http://typora.io linux/'
sudo apt update
sudo apt install typora
sudo apt install gconf2
```
3. 

```sudo apt install default-jdk openjdk-8-source maven git net-tools vim curl tmux neofetch gnome-tweak-tool apt-transport-https ca-certificates software-properties-common language-pack-zh-hans graphviz``` 

4. http://music.163.com/#/download

5. 
```shell
     #安装Shadowsocks-qt5客户端,设置SOCKS5代理
     sudo add-apt-repository ppa:hzwhuang/ss-qt5
     sudo apt-get update
     sudo apt-get install shadowsocks-qt5

     #生成pac文件
     sudo apt-get install python-pip python-dev build-essential 
     sudo pip install --upgrade pip 
     sudo pip install --upgrade virtualenv
     sudo pip install genpac
     mkdir ~/Documents/pacs && cd ~/Documents/pacs
     genpac --proxy="SOCKS5 127.0.0.1:1080" --gfwlist-proxy="SOCKS5 	127.0.0.1:1080" -o autoproxy.pac --gfwlist-url="https://raw.githubusercontent.com/gfwlist/gfwlist/master/gfwlist.txt"
     #设置系统自动代理:
     file://~/Documents/pacs/autoproxy.pac
```

6. 安装主题

```shell

sudo apt install -y autoconf automake inkscape libgdk-pixbuf2.0-dev libglib2.0-dev libsass0 libxml2-utils pkg-config sassc parallel

git config --global http.proxy 'socks5://127.0.0.1:1080'
git config --global https.proxy 'socks5://127.0.0.1:1080'

cd ~ && git clone https://github.com/adapta-project/adapta-gtk-theme.git && \
cd ~/adapta-gtk-theme && \
git fetch --all && \
git reset --hard origin/master && \
./autogen.sh \
    --enable-chrome \
    --enable-parallel \
    --disable-cinnamon \
    --disable-unity \
    --disable-xfce \
    --with-selection_color='#3F51B5' \
    --with-second_selection_color='#5C6BC0' \
    --with-accent_color='#4DB6AC' \
    --with-suggestion_color='#009688' \
    --with-destruction_color='#FF5252' && \
make && \
find . -type f|xargs sed -i 's/2a373e/3c3f41/g' && \
find . -type f|xargs sed -i 's/2A373E/3c3f41/g' && \
sudo make install
```
7. 生成CSR

```
openssl req -nodes -newkey rsa:2048 -keyout server.key -out server.csr
```

8. 安装docker

```shell
#!/bin/bash
# clear out-of-date docker packages
sudo apt update && sudo apt upgrade -y
sudo apt install -y net-tools vim curl apt-transport-https ca-certificates software-properties-common language-pack-zh-hans
sudo apt remove -y docker docker-ce docker-engine docker.io
# install docker via apt
curl -fsSL https://mirrors.ustc.edu.cn/docker-ce/linux/ubuntu/gpg | sudo apt-key add -
sudo add-apt-repository "deb [arch=amd64] https://mirrors.ustc.edu.cn/docker-ce/linux/ubuntu $(lsb_release -cs) stable"
sudo apt update
sudo apt install -y docker-ce
# docker against sudo
sudo chmod 777 /var/run/docker.sock
# clear unused packages
sudo apt autoremove -y
sudo apt autoclean
sudo docker version
# image mirror
sudo mkdir -p /etc/docker
sudo tee /etc/docker/daemon.json <<-'EOF'
{
  "registry-mirrors": ["https://3itj1ym2.mirror.aliyuncs.com"]
}
EOF
sudo systemctl daemon-reload
sudo systemctl restart docker
# install docker-compose
sudo wget http://soft-1252259164.file.myqcloud.com/docker-compose-1.21.2 -O /usr/local/bin/docker-compose
sudo chmod +x /usr/local/bin/docker-compose
docker-compose version
```

9. 安装zsh
```shell
sudo apt install zsh
sh -c "$(wget https://raw.githubusercontent.com/robbyrussell/oh-my-zsh/master/tools/install.sh -O -)"
chsh -s /bin/zsh
# 安装插件
sudo apt-get install powerline
git clone https://github.com/powerline/fonts.git
cd fonts
./install.sh
git clone https://github.com/zsh-users/zsh-autosuggestions ${ZSH_CUSTOM:-~/.oh-my-zsh/custom}/plugins/zsh-autosuggestions
git clone https://github.com/zsh-users/zsh-syntax-highlighting.git ${ZSH_CUSTOM:-~/.oh-my-zsh/custom}/plugins/zsh-syntax-highlighting
git clone https://github.com/jhipster/jhipster-oh-my-zsh-plugin.git ${ZSH_CUSTOM:-~/.oh-my-zsh/custom}/plugins/jhipster

sudo mkdir /usr/share/fonts/OTF/ -p
wget https://raw.githubusercontent.com/powerline/powerline/develop/font/10-powerline-symbols.conf
wget https://raw.githubusercontent.com/powerline/powerline/develop/font/PowerlineSymbols.otf
sudo cp 10-powerline-symbols.conf /usr/share/fonts/OTF/ 
sudo mv 10-powerline-symbols.conf /etc/fonts/conf.d/
sudo mv PowerlineSymbols.otf /usr/share/fonts/OTF/

# 修改~/.zshrc
ZSH_THEME="agnoster"
DEFAULT_USER=`whoami`
export LC_ALL=zh_CN.UTF-8
export LANG=zh_CN.UTF-8
plugins=(
  git
  zsh-autosuggestions
  zsh-syntax-highlighting
  docker
  docker-compose
  minikube
  jhipster
  kubectl
)

alias f='free -h'
alias d='df -h'
alias s='sudo apt update && sudo apt upgrade -y'
alias c='clear'
alias w='which'
alias dh='du -h --max-depth=1'
alias mp='mvn clean package -DskipTests=true'
alias mt='mvn clean tomcat7:run'
alias ms='mvn clean spring-boot:run'

source ~/.zshrc
# 设置终端字体为 Meslo LG s for Powerline Regular 12

```