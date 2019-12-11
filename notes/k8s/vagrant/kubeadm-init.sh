#!/usr/bin/env bash

sudo kubeadm init --kubernetes-version=$(kubeadm version -o short) --pod-network-cidr=10.244.0.0/16 --ignore-preflight-errors=all

mkdir -p $HOME/.kube
sudo cp -i /etc/kubernetes/admin.conf $HOME/.kube/config
sudo chown $(id -u):$(id -g) $HOME/.kube/config

kubectl apply -f https://cloud.weave.works/k8s/v1.10/net.yaml

tee -a ~/.zshrc <<-'EOF'
alias k='kubectl'
alias kn='kubectl get nodes -o wide'
alias kp='kubectl get pods --all-namespaces -o wide'
alias kc='kubectl create -f'
alias ka='kubectl apply -f'
alias kr='kubectl replace -f'
EOF
source <(kubectl completion zsh)
source ~/.zshrc
