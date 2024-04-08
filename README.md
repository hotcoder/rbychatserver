# Getting Started

### Reference Documentation
For further reference, please consider the following sections:

* [Official Apache Maven documentation](https://maven.apache.org/guides/index.html)
* [Spring Boot Maven Plugin Reference Guide](https://docs.spring.io/spring-boot/docs/3.2.4/maven-plugin/reference/html/)
* [Create an OCI image](https://docs.spring.io/spring-boot/docs/3.2.4/maven-plugin/reference/html/#build-image)

### UserController Endpoints:

#### User Authentication (POST):
```bash
curl -X POST `http://localhost:8080/api/users/authenticate` \
     -H "Content-Type: application/json" \
     -d '{"username": "your_username", "password": "your_password"}
```

#### Add User (POST):
```bash
curl -X POST `http://localhost:8080/api/users/add` \
     -H "Content-Type: application/json" \
     -d '{"username": "new_user", "password": "new_password"}
```

### ChatController Endpoints:

#### Get Messages by Room ID (GET):

```bash
curl -X GET `http://localhost:8080/api/chat/rooms/{roomId}/messages`

```
#### Send Message (POST):

```bash
curl -X POST `http://localhost:8080/api/chat/messages` \
     -H "Content-Type: application/json" \
     -d '{"message": "Test message"}'
```

> **Notice:** Replace `{roomId}` in the first command with the actual room ID you want to retrieve messages for.
