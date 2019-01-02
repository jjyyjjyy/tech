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

KUBE_VER=`kubeadm version -o short`
IMAGE_MIRROR="registry.cn-hangzhou.aliyuncs.com/google_containers"
images=`kubeadm config images list | tr "\/" "\n" | awk '!(NR%2)'`
for imageName in ${images[@]} ; do
  docker pull ${IMAGE_MIRROR}/${imageName}
  docker tag ${IMAGE_MIRROR}/${imageName} k8s.gcr.io/${imageName}
  docker rmi ${IMAGE_MIRROR}/${imageName}
done

echo "Done."
