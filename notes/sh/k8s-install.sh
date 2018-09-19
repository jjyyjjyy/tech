#!/usr/bin/env bash
sudo apt update && sudo apt install -y apt-transport-https curl
curl -s http://soft-1252259164.file.myqcloud.com/apt-key.gpg | sudo apt-key add -
echo "deb https://mirrors.ustc.edu.cn/kubernetes/apt/ kubernetes-xenial main" | sudo tee /etc/apt/sources.list.d/kubernetes.list
sudo apt update && sudo apt install -y kubelet kubectl kubeadm
kubeadm version

sudo sed -i '4a\Environment="KUBE_PAUSE=--pod-infra-container-image=registry.cn-hangzhou.aliyuncs.com/google_containers/pause:3.1"' /etc/systemd/system/kubelet.service.d/10-kubeadm.conf
sudo sed -i '5a\Environment="KUBELET_CGROUP_ARGS=--cgroup-driver=cgroupfs"' /etc/systemd/system/kubelet.service.d/10-kubeadm.conf
sudo sed -i 's/ExecStart=\/usr\/bin\/kubelet.*/& $KUBE_PAUSE/g' /etc/systemd/system/kubelet.service.d/10-kubeadm.conf
sudo systemctl daemon-reload
sudo systemctl restart kubelet

echo "====== pull images now ======"

KUBE_VER="v1.11.3"
IMAGE_MIRROR="registry.cn-hangzhou.aliyuncs.com/google_containers"
images=(
kube-proxy-amd64:$KUBE_VER
kube-scheduler-amd64:$KUBE_VER
kube-controller-manager-amd64:$KUBE_VER
kube-apiserver-amd64:$KUBE_VER
etcd-amd64:3.2.18
pause:3.1
coredns:1.1.3
)
for imageName in ${images[@]} ; do
  docker pull ${IMAGE_MIRROR}/${imageName}
  docker tag ${IMAGE_MIRROR}/${imageName} k8s.gcr.io/${imageName}
  docker rmi ${IMAGE_MIRROR}/${imageName}
done

echo "Done."

# sudo kubeadm init --kubernetes-version=1.11.3 --pod-network-cidr=10.244.0.0/16
