#!/usr/bin/env bash
sudo kubeadm reset -f
sudo systemctl stop kubelet
sudo systemctl stop docker
rm -rf ~/.kube/
sudo rm -rf /usr/share/oci-umount
sudo rm -rf /run/flannel
sudo rm -rf /etc/cni
sudo rm -rf /var/lib/cni/
sudo rm -rf /var/lib/kubelet/*
sudo ifconfig cni0 down
sudo ifconfig flannel.1 down
sudo ifconfig docker0 down
sudo ip link delete cni0
sudo ip link delete flannel.1
sudo systemctl start docker
