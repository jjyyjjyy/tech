#!/usr/bin/env bash

sudo swapoff -a
sudo sed -i '/ swap / s/^\(.*\)$/#\1/g' /etc/fstab

sudo sed -i 's/archive.ubuntu.com/mirrors.ustc.edu.cn/g' /etc/apt/sources.list
sudo apt-get update && sudo apt-get upgrade -y && sudo apt-get autoclean && sudo apt-get autoremove -y
sudo apt-get install -y git net-tools vim curl unzip unar apt-transport-https ca-certificates software-properties-common language-pack-zh-hans
curl -fsSL https://mirrors.ustc.edu.cn/docker-ce/linux/ubuntu/gpg | sudo apt-key add -
sudo add-apt-repository "deb [arch=amd64] https://mirrors.ustc.edu.cn/docker-ce/linux/ubuntu $(lsb_release -cs) stable"
sudo apt-get update
sudo apt-get install -y docker-ce
sudo usermod -aG docker vagrant
sudo docker version
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
sudo wget https://soft-1252259164.cos.ap-shanghai.myqcloud.com/docker-compose-1.23.2 -O /usr/local/bin/docker-compose
sudo chmod +x /usr/local/bin/docker-compose
docker-compose version

# install kubeadm kubectl kubelet
sudo apt update && sudo apt install -y apt-transport-https curl
curl -s https://soft-1252259164.cos.ap-shanghai.myqcloud.com/apt-key.gpg | sudo apt-key add -
echo "deb https://mirrors.ustc.edu.cn/kubernetes/apt/ kubernetes-xenial main" | sudo tee /etc/apt/sources.list.d/kubernetes.list
sudo apt update && sudo apt install -y kubelet kubectl kubeadm
kubeadm version

KUBE_VER=`kubeadm version -o short`
IMAGE_MIRROR="registry.cn-hangzhou.aliyuncs.com/google_containers"
kubeadm config images list > /tmp/ki
images=`tail -n7 /tmp/ki | tr "\/" "\n" | awk '!(NR%2)'`
for imageName in ${images[@]} ; do
  docker pull ${IMAGE_MIRROR}/${imageName}
  docker tag ${IMAGE_MIRROR}/${imageName} k8s.gcr.io/${imageName}
  docker rmi ${IMAGE_MIRROR}/${imageName}
done