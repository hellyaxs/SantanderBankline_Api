#!/bin/bash

kubectl apply -f namespace.yaml
kubectl apply -f postgres-configMap.yaml
kubectl apply -f postgres-pvc.yaml
kubectl apply -f postgres-deploy.yaml
kubectl apply -f back-end-configMap.yaml
kubectl apply -f back-end-deploy.yaml
kubectl apply -f front-end-deploy.yaml


