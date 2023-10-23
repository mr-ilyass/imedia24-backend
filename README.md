
<div align="center">

# iMedia24 Coding challenge

</div>



## Dockerized our Product Service
### Prerequisites

Before proceeding, ensure that you have the following installed on your machine:
* Docker: [Installation Guide](https://docs.docker.com/get-docker/)

### Usage
Follow the steps below to build and run your Kotlin application using Dockerfile:
- Open a terminal and navigate to the directory containing the Dockerfile and the application files.
- Build the Docker image using the following command:

```
docker build -t product-service .
```

- Once the image is built, you can run the Docker container using the following command:

```
docker run -p 8080:8080 product-service
```

- You can now access the application at http://\<hostname\>:8080/ 
- Additionally, you can explore the API documentation by accessing Swagger UI at http://\<hostname\>:8080/swagger-ui.html
