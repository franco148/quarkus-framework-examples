# Notes

### Docker
- `./gradlew dockerBuildImage`
- `docker images | head -n 5`
- `docker images | grep intro`
- `docker run intro:latest`
- `docker run -it --entrypoint bash intro:latest`

### Gradle
- `./gradlew`
- `./gradlew clean`
- `./gradlew distTar`
- `./gradlew memory:docker`

### Docker memory
Tweak with (JDK10+)
- `-XX:InitialRAMPercentage`
- `-XX:MaxRAMPercentage`
- `-XX:MinRAMPercentage`
- `docker run --memory 200mb --name heap memory:latest heap`
- `docker run --memory 200mb --memory-swap 200mb --name memory-swap memory:latest heap`

### Kubernetes commands
- `kubectl apply -f our-yaml.yml`
- `kubectl get pods`
- `kubectl logs <pod-name>`
- `kubectl get pod <pod-name>`
- `kubectl describe <pod-name>`




