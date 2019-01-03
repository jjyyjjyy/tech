#!/usr/bin/env bash
sudo apt update && sudo apt install -y apt-transport-https curl
curl -s http://soft-1252259164.file.myqcloud.com/apt-key.gpg | sudo apt-key add -
echo "deb https://mirrors.ustc.edu.cn/kubernetes/apt/ kubernetes-xenial main" | sudo tee /etc/apt/sources.list.d/kubernetes.list
sudo apt update && sudo apt install -y kubelet kubectl kubeadm
kubeadm version

echo "====== pull images now ======"

KUBE_VER=`kubeadm version -o short`
IMAGE_MIRROR="registry.cn-hangzhou.aliyuncs.com/google_containers"
images=`kubeadm config images list | tr "\/" "\n" | awk '!(NR%2)'`
for imageName in ${images[@]} ; do
  docker pull ${IMAGE_MIRROR}/${imageName}
  docker tag ${IMAGE_MIRROR}/${imageName} k8s.gcr.io/${imageName}
  docker rmi ${IMAGE_MIRROR}/${imageName}
done

echo "Next step:"
echo "sudo kubeadm init --kubernetes-version=`kubeadm version -o short` --pod-network-cidr=10.244.0.0/16 --ignore-preflight-errors=all"
echo "mkdir -p $HOME/.kube"
echo "sudo cp -i /etc/kubernetes/admin.conf $HOME/.kube/config"
echo "sudo chown $(id -u):$(id -g) $HOME/.kube/config"
echo "kubectl apply -f https://cloud.weave.works/k8s/v1.10/net.yaml"
