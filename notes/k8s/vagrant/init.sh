#!/usr/bin/env bash

sudo sed -ri 's/.*swap.*/#&/' /etc/fstab

sudo sed -i 's/archive.ubuntu.com/mirrors.ustc.edu.cn/g' /etc/apt/sources.list
sudo apt-get update && sudo apt-get upgrade -y
sudo apt-get install -y curl gnupg-agent apt-transport-https ca-certificates software-properties-common
curl -fsSL https://mirrors.ustc.edu.cn/docker-ce/linux/ubuntu/gpg | sudo apt-key add -
sudo add-apt-repository "deb [arch=amd64] https://mirrors.ustc.edu.cn/docker-ce/linux/ubuntu $(lsb_release -cs) stable"
sudo apt-get update
sudo apt-get install -y docker-ce docker-ce-cli containerd.io
sudo usermod -aG docker vagrant
sudo mkdir -p /etc/docker
sudo tee /etc/docker/daemon.json <<-'EOF'
{
  "registry-mirrors": ["https://3itj1ym2.mirror.aliyuncs.com"]
}
EOF
sudo sed -i 's/ExecStart.*/& -H tcp:\/\/0.0.0.0:2375/g' /lib/systemd/system/docker.service
sudo chmod 777 /var/run/docker.sock
sudo chmod 777 /etc/docker -R
sudo systemctl daemon-reload
sudo systemctl restart docker
# install docker-compose
sudo wget -q https://soft-1252259164.cos.ap-shanghai.myqcloud.com/docker-compose-1.27.4 -O /usr/local/bin/docker-compose
sudo chmod +x /usr/local/bin/docker-compose

# install kubeadm kubectl kubelet
curl -s https://soft-1252259164.cos.ap-shanghai.myqcloud.com/apt-key.gpg | sudo apt-key add -
echo "deb https://mirrors.ustc.edu.cn/kubernetes/apt/ kubernetes-xenial main" | sudo tee /etc/apt/sources.list.d/kubernetes.list
sudo apt-get update && sudo apt-get upgrade -y
sudo apt-get install -y kubelet kubectl kubeadm

sudo reboot now
