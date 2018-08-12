#!/usr/bin/env bash
KUBE_VER="v1.11.2"
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

docker pull quay.io/coreos/flannel:v0.10.0-amd64
