& minikube -p minikube docker-env | Invoke-Expression
kubectl delete deployment edge-service-server-deployment
kubectl delete deployment garden-center-service-server-deployment
kubectl delete deployment garden-center-service-mongo-deployment
kubectl delete deployment plant-service-server-deployment
kubectl delete deployment plant-service-mongo-deployment
kubectl delete deployment employee-service-server-deployment
kubectl delete deployment employee-service-postgres-deployment
kubectl delete service edge-service-server
kubectl delete service garden-center-service-server
kubectl delete service garden-center-service-mongo
kubectl delete service plant-service-server
kubectl delete service plant-service-mongo
kubectl delete service employee-service-server
kubectl delete service employee-service-postgres
kubectl get deployments
kubectl get services