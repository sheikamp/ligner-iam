
<h3 align="center">Ligner: Identity & Access Management</h3>

<!-- TABLE OF CONTENTS -->
<details open="open">
  <summary><h2 style="display: inline-block">Table of Contents</h2></summary>
  <ol>
    <li>
      <a href="#about-the-project">About The Project</a>
      <ul>
        <li><a href="#built-with">Built With</a></li>
      </ul>
    </li>
    <li>
      <a href="#getting-started">Getting Started</a>
      <ul>
        <li><a href="#prerequisites">Prerequisites</a></li>
        <li><a href="#installation">Installation</a></li>
      </ul>
    </li>
    <li><a href="#usage">Usage</a></li>
  </ol>
</details>



<!-- ABOUT THE PROJECT -->
## About The Project

The IAM service takes care of user registration, account management.
It also keeps track of friendships between users 
and informs interested parties about changes to its entities by publishing events in RabbitMQ.


### Built With

* Java 11
* Spring Boot (Web)
* MariaDB
* RabbitMQ



<!-- GETTING STARTED -->
## Getting Started

To get a local copy up and running follow these simple steps.

### Prerequisites

The following tools are required for building the application:
* JDK 11: Possibly the easiest way to install Java on your system is using [SDK-MAN](https://sdkman.io/usage).
* docker: Please check the [official installation instructions](https://docs.docker.com/get-docker/).

### Installation

1. Clone the repo
   ```sh
   git clone https://github.com/github_username/repo_name.git
   ```
2. Build the docker image
   ```sh
   ./mvnw spring-boot:build-image
   ```


<!-- USAGE EXAMPLES -->
## Usage

The IAM service exposes a REST API, which is available under /users.
It supports the usual CRUD operations plus some more sophisticated methods like friendship management.
