1. ```sudo apt update```
2. Typora

```shell
sudo apt-key adv --keyserver keyserver.ubuntu.com --recv-keys BA300B7755AFCFAE

sudo add-apt-repository 'deb http://typora.io linux/'

sudo apt update

sudo apt install typora

sudo apt install gconf2
```
3. ```sudo apt install default-jdk maven git net-tools vim curl```

4. http://resources-1252259164.file.myqcloud.com/settings.xml

5. http://music.163.com/#/download

6. install MacOS X theme

   1. https://www.gnome-look.org/p/1171688/#files-panel

   2. extract files to ~/.theme

   3. ```shell
      sudo mkdir /usr/local/share/fonts/mac_fonts
      ```

   4. ```shell
      sudo mv ~/Downloads/fontoteka_433517277/*. /usr/local/share/fonts/mac_fonts/
      ```

   5. ```shell
      sudo chmod 644 /usr/local/share/fonts/mac_fonts/* -R

      sudo chmod 755 /usr/local/share/fonts/mac_fonts

      sudo fc-cache -fv
      ```

   6. ```shell
     mkdir ~/.local/share/themes
     
     https://www.gnome-look.org/p/1171688/#files-panel	-> extract to above dir
     
     mkdir ~/.local/share/icons
     https://www.gnome-look.org/p/1102582/#files-panel	-> extract to above dir

     #use gnome-tweak-tool to change theme and icon
     ```
   
7. ```shell
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