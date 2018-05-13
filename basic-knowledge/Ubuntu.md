1. ```sudo apt update && sudo apt upgrade```
2. Typora

```shell
sudo apt-key adv --keyserver keyserver.ubuntu.com --recv-keys BA300B7755AFCFAE
sudo add-apt-repository 'deb http://typora.io linux/'
sudo apt update
sudo apt install typora
sudo apt install gconf2
```
3. ```sudo apt install default-jdk openjdk-8-source maven git net-tools vim curl tmux neofetch gnome-tweak-tool```

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
mkdir ~/adapta-gtk-theme && cd ~/adapta-gtk-theme && \
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
7.  生成CSR

```
openssl req -nodes -newkey rsa:2048 -keyout server.key -out server.csr
```

