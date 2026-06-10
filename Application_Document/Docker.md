# Docker Cheat Sheet

## 1. Check Docker Installation

### Check Docker Version

```bash
docker --version
```

**Use:** Verify Docker is installed.

### Check Docker Compose Version

```bash
docker compose version
```

**Use:** Verify Docker Compose is installed.

---

# 2. Images

## List Downloaded Images

```bash
docker images
```

**Use:** Shows all images available locally.

Example:

```text
REPOSITORY   TAG   IMAGE ID
mysql        8.4   abc123
```

## Pull an Image

```bash
docker pull mysql:8.4
```

**Use:** Downloads the image from Docker Hub.

## Delete an Image

```bash
docker rmi mysql:8.4
```

**Use:** Removes an image.

---

# 3. Containers

## View Running Containers

```bash
docker ps
```

**Use:** Shows currently running containers.

Example:

```text
CONTAINER ID   IMAGE       NAMES
509f0fc10737   mysql:8.4   event-planner-db
```

## View All Containers

```bash
docker ps -a
```

**Use:** Shows running and stopped containers.

## Start a Container

```bash
docker start event-planner-db
```

**Use:** Starts a stopped container.

## Stop a Container

```bash
docker stop event-planner-db
```

**Use:** Gracefully stops a container.

## Restart a Container

```bash
docker restart event-planner-db
```

**Use:** Restarts a container.

## Delete a Container

```bash
docker rm event-planner-db
```

**Use:** Removes a stopped container.

### Force Delete

```bash
docker rm -f event-planner-db
```

**Use:** Stops and removes the container immediately.

---

# 4. Container Logs

## View Logs

```bash
docker logs event-planner-db
```

**Use:** Displays container logs.

## Follow Logs Live

```bash
docker logs -f event-planner-db
```

**Use:** Continuously displays logs.

Press:

```text
Ctrl + C
```

to stop.

---

# 5. Execute Commands Inside a Container

## Open Shell

```bash
docker exec -it event-planner-db bash
```

**Use:** Open terminal inside container.

## Open MySQL

```bash
docker exec -it event-planner-db mysql -u event_user -p
```

**Use:** Connect to MySQL running inside the container.

---

# 6. Docker Compose Commands

## Start Services

```bash
docker compose up -d
```

**Use:** Creates and starts containers in detached mode.

## Start with Logs

```bash
docker compose up
```

**Use:** Starts containers and shows logs.

## Stop Services

```bash
docker compose stop
```

**Use:** Stops containers but keeps them.

## Start Stopped Services

```bash
docker compose start
```

**Use:** Starts previously stopped containers.

## Restart Services

```bash
docker compose restart
```

**Use:** Restarts all services.

## Remove Containers

```bash
docker compose down
```

**Use:** Removes containers and network.

Volumes remain intact.

## Remove Containers + Volumes

```bash
docker compose down -v
```

**Use:** Removes containers, networks, and volumes.

⚠️ Database data will be deleted.

---

# 7. Volumes

## List Volumes

```bash
docker volume ls
```

**Use:** Shows all Docker volumes.

Example:

```text
backend_mysql_data
```

## Inspect Volume

```bash
docker volume inspect backend_mysql_data
```

**Use:** Shows where data is stored.

## Delete Volume

```bash
docker volume rm backend_mysql_data
```

**Use:** Deletes stored data.

---

# 8. Networks

## List Networks

```bash
docker network ls
```

**Use:** Shows Docker networks.

## Inspect Network

```bash
docker network inspect backend_default
```

**Use:** Shows connected containers.

---

# 9. System Cleanup

## Remove Stopped Containers

```bash
docker container prune
```

## Remove Unused Images

```bash
docker image prune
```

## Remove Everything Unused

```bash
docker system prune
```

## Remove Everything Including Volumes

```bash
docker system prune -a --volumes
```

⚠️ Deletes unused images, containers, networks, and volumes.

---

# 10. Commands Specific to Event Planner Project

## Start MySQL

```bash
cd ~/Application/event-planner/backend
docker compose up -d
```

## Check MySQL Status

```bash
docker ps
```

## View MySQL Logs

```bash
docker logs event-planner-db
```

## Connect to MySQL

```bash
docker exec -it event-planner-db mysql -u event_user -p
```

Password:

```text
event_planner
```

## Stop MySQL

```bash
docker compose stop
```

## Delete MySQL Container

```bash
docker compose down
```

## Delete MySQL Container and Data

```bash
docker compose down -v
```

⚠️ This permanently deletes the `event_planner` database.
