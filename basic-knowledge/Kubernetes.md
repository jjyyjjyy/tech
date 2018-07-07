### Kubernetes

1.  安装:

```shell
sudo apt update &&sudo apt install -y apt-transport-https curl
#tsocks curl -s https://packages.cloud.google.com/apt/doc/apt-key.gpg |sudo apt-key add -
cat <<EOF >/etc/apt/sources.list.d/kubernetes.list
deb https://mirrors.ustc.edu.cn/kubernetes/apt/ kubernetes-xenial main
EOF
sudo apt update && sudo apt install -y kubelet kubeadm kubectl
```

2.  配置:

```shell
systemctl disable firewalld
systemctl stop firewalld
sudo vim /etc/sysconfig/selinux -> SELINUX=disabled
# 关闭交换分区
sudo vim /etc/fstab -> 注释/swapfile行
sudo swapoff -a
```

3.  下载镜像:

```bash
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
```

4.  创建Kubernetes集群

```shell
# https://kubernetes.io/docs/setup/independent/create-cluster-kubeadm/
sudo kubeadm init --kubernetes-version=v1.10.2 --pod-network-cidr=10.244.0.0/16
mkdir -p $HOME/.kube
sudo cp -i /etc/kubernetes/admin.conf $HOME/.kube/config
sudo chown $(id -u):$(id -g) $HOME/.kube/config

mkdir -p /etc/cni/net.d/
cat <<EOF> /etc/cni/net.d/10-flannel.conf
{
"name": "cbr0",
"type": "flannel",
"delegate": {
"isDefaultGateway": true
}
}
EOF
sudo mkdir /usr/share/oci-umount/oci-umount.d -p
sudo mkdir /run/flannel/ -p
sudo chmod 777 /run/flannel/ -R
cat <<EOF> /run/flannel/subnet.env
FLANNEL_NETWORK=10.244.0.0/16
FLANNEL_SUBNET=10.244.1.0/24
FLANNEL_MTU=1450
FLANNEL_IPMASQ=true
EOF

kubectl apply -f https://raw.githubusercontent.com/coreos/flannel/v0.10.0/Documentation/kube-flannel.yml
kubectl taint nodes --all node-role.kubernetes.io/master-
# check
kubectl get nodes
kubectl get pods --all-namespaces
# trouble shooting:
# kubectl describe NAME -n kube-system
```

5.  Kubenetes Dashboard

```shell
kubectl create -f https://raw.githubusercontent.com/kubernetes/dashboard/v1.8.3/src/deploy/recommended/kubernetes-dashboard.yaml
kubectl proxy
# chrome
http://localhost:8001/api/v1/namespaces/kube-system/services/https:kubernetes-dashboard:/proxy/
```

6.  kubectl 命令

```shell
kubectl version
kubectl cluster-info
kubectl api-versions
kubectl config view
kubectl get no
kubectl get deployments

# ======资源控制======
kubectl create -f YML
kubectl run NAME --image=IMAGE

kubectl delete TYPE NAME

# ======部署管理======
# 实现水平扩展
kubectl scale
# 部署状态变更状态检查
kubectl rollout status
# 部署历史
kubectl rollout history
# 回滚部署
kubectl rollout undo
```

7.  minikube

```shell
sudo apt install virtualbox
curl -Lo minikube https://storage.googleapis.com/minikube/releases/v0.28.0/minikube-linux-amd64 && chmod +x minikube && sudo mv minikube /usr/local/bin/
minikube start --registry-mirror=https://registry.docker-cn.com
minikube dashboard

# 删除原有minikube
minikube delete && rm -rf ~/.minikube
```

8.  scale

```
DESIRED: 需要的实例数
CURRENT: 现有的实例数
```

9.  Worker Node Components:
    -   container runtime
    -   kubelet
    -   kube-proxy
10.  Master Node Components:
     -   Scheduler
     -   Controller manager
     -   API server