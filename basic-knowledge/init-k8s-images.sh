#!/bin/bash
images=(kube-proxy-amd64:v1.10.2 kube-scheduler-amd64:v1.10.2 kube-controller-manager-amd64:v1.10.2 kube-apiserver-amd64:v1.10.2 kube-discovery-amd64:1.0 kubernetes-dashboard-amd64:v1.8.3 dnsmasq-metrics-amd64:1.0.1 kubedns-amd64:1.9)
for imageName in ${images[@]} ; do
  docker pull  registry.cn-hangzhou.aliyuncs.com/kube_containers/$imageName
  docker tag  registry.cn-hangzhou.aliyuncs.com/kube_containers/$imageName k8s.gcr.io/$imageName
  docker rmi  registry.cn-hangzhou.aliyuncs.com/kube_containers/$imageName
done

gcr_images=(exechealthz-amd64:v1.3.0 pause-amd64:3.1 k8s-dns-sidecar-amd64:1.14.8 k8s-dns-kube-dns-amd64:1.14.8 k8s-dns-dnsmasq-nanny-amd64:1.14.8)
for imageName in ${gcr_images[@]} ; do
  docker pull registry.cn-hangzhou.aliyuncs.com/google_containers/$imageName
  docker tag registry.cn-hangzhou.aliyuncs.com/google_containers/$imageName k8s.gcr.io/$imageName
  docker rmi registry.cn-hangzhou.aliyuncs.com/google_containers/$imageName
done

docker pull registry.cn-hangzhou.aliyuncs.com/kubernetes_containers/etcd-amd64:3.1.12
docker tag registry.cn-hangzhou.aliyuncs.com/kubernetes_containers/etcd-amd64:3.1.12 k8s.gcr.io/etcd-amd64:3.1.12
docker rmi registry.cn-hangzhou.aliyuncs.com/kubernetes_containers/etcd-amd64:3.1.12
