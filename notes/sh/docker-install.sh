#!/bin/bash
# prepare
sudo apt update && sudo apt upgrade -y && sudo apt autoclean && sudo apt autoremove -y
sudo apt install -y net-tools vim curl apt-transport-https ca-certificates software-properties-common language-pack-zh-hans
sudo apt remove -y docker docker-ce docker-engine docker.io
# install docker via apt
curl -fsSL https://mirrors.ustc.edu.cn/docker-ce/linux/ubuntu/gpg | sudo apt-key add -
sudo add-apt-repository "deb [arch=amd64] https://mirrors.ustc.edu.cn/docker-ce/linux/ubuntu $(lsb_release -cs) stable"
sudo apt update
sudo apt install -y docker-ce
sudo usermod -aG docker $USER
sudo docker version
# image mirror
sudo mkdir -p /etc/docker
sudo tee /etc/docker/daemon.json <<-'EOF'
{
  "registry-mirrors": ["http://3itj1ym2.mirror.aliyuncs.com"]
}
EOF
# expose endpoint for external services, eg: portainer.
sudo sed -i 's/ExecStart.*/& -H tcp:\/\/0.0.0.0:2375/g' /lib/systemd/system/docker.service
sudo chmod 777 /var/run/docker.sock
sudo chmod 777 /etc/docker/ -R
sudo systemctl daemon-reload
sudo systemctl restart docker
# install docker-compose
sudo wget http://soft-1252259164.file.myqcloud.com/docker-compose-1.24.0 -O /usr/local/bin/docker-compose
sudo chmod +x /usr/local/bin/docker-compose
docker-compose version
