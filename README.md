# Edge-service

**Inleiding:**</br> 
Het thema dat ons is toegewezen is "tuincentra, werknemers en planten", we hebben hiervoor dus 3 Back-End microservices geschreven met één Edge-service die deze aanspreekt. Bijkomend hebben we gebruik gemaakt van Kubernetes Deployemt. De screenshots van het diagram staan ook hier weergegeven.

###GitHub Repositories Back-End services:
1. Employee service:
https://github.com/Goetsie/employee-service-APT
2. Garden Center service:
https://github.com/Goetsie/garden_center-service-APT
3. Plant service:
https://github.com/Goetsie/plant-service-APT

#####Deployment Diagram Microservices met Kubernetes: enkel Edge-service exposed
![](screenshots/DeploymentDiagramEdge.JPG)

#####Deployment Diagram Microservices met Kubernetes testing: alle microservices exposed
![](screenshots/DeploymentDiagramEdgeTesting.JPG)
-----------------------------------------------------------------------------------

####Bewijs output en werking swagger:
Alle calls:
![](screenshots/swagger/main.JPG)
1. /gardencenters (get)
![](screenshots/swagger/getGardencenters.JPG)
2. /gardencenters/{gardencenterid}/employees (get)
![](screenshots/swagger/getEmployeesByGardencenterId.JPG)
3. /gardencenters/{gardencenterid}/plants (get)
![](screenshots/swagger/getPlantsByGardencenterId.JPG)
4. /plants (get)
![](screenshots/swagger/getPlants.JPG)
5. /plants (post)
![](screenshots/swagger/postPlantParameters.JPG)
![](screenshots/swagger/postPlant.JPG)
6. /plants (put)
![](screenshots/swagger/putPlantParameters.JPG)
![](screenshots/swagger/putPlant.JPG)
7. /plants/description/{description} (get)
![](screenshots/swagger/getPlantByDescriptionParameters.JPG)
![](screenshots/swagger/getPlantByDescription.JPG)
8. /plants/name/{name} (get)
![](screenshots/swagger/getPlantByNameParameters.JPG)
![](screenshots/swagger/getPlantByName.JPG)
9. /plants/{plantNumber} (delete)
![](screenshots/swagger/deletePlantParameters.JPG)
![](screenshots/swagger/deletePlant.JPG)
10. /plants/{plantNumber} (get)
![](screenshots/swagger/getPlantPlantIdParameters.JPG)
![](screenshots/swagger/getPlantByPlantId.JPG)

###Code coverage edge service:

[![Coverage Status](https://coveralls.io/repos/github/badges/shields/badge.svg?branch=master)](https://coveralls.io/github/badges/shields?branch=main)

![](screenshots/swagger/GardenCenterDataControllerUnitTests.png)

