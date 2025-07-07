# CDR Config Server

A Spring Cloud Config Server that provides centralized configuration management for all CDR microservices. It supports configuration storage in Git and includes encryption capabilities for sensitive data.

## Features

- Centralized configuration management
- Git backend for version-controlled configuration
- Encryption/Decryption of sensitive configuration
- Integration with Spring Cloud ecosystem
- Actuator endpoints for monitoring

## Prerequisites

- Java 17 or higher
- Maven 3.6.3 or higher
- RabbitMQ (for Spring Cloud Bus, optional)
- Git repository for configuration storage

## Environment Variables

### Required Configuration

```properties
# Server Configuration
SERVER_PORT=8071

# Spring Application Name
SPRING_APPLICATION_NAME=configserver

# Git Repository Configuration
SPRING_CLOUD_CONFIG_SERVER_GIT_URI=https://github.com/your-org/cdr-config-repo.git
SPRING_CLOUD_CONFIG_SERVER_GIT_DEFAULT_LABEL=main
SPRING_CLOUD_CONFIG_SERVER_GIT_TIMEOUT=5
SPRING_CLOUD_CONFIG_SERVER_GIT_CLONE_ON_START=true
SPRING_CLOUD_CONFIG_SERVER_GIT_FORCE_PULL=true

# Encryption Key (keep this secure in production!)
ENCRYPT_KEY=your-encryption-key-here
```

### Optional Configuration

```properties
# RabbitMQ Configuration (for Spring Cloud Bus)
SPRING_RABBITMQ_HOST=localhost
SPRING_RABBITMQ_PORT=5672
SPRING_RABBITMQ_USERNAME=guest
SPRING_RABBITMQ_PASSWORD=guest

# Git Authentication (if using private repository)
# SPRING_CLOUD_CONFIG_SERVER_GIT_USERNAME=your-username
# SPRING_CLOUD_CONFIG_SERVER_GIT_PASSWORD=your-token-or-password
```

## Getting Started

1. **Set up your configuration repository**
   - Create a Git repository for your configurations
   - Organize configurations by application and profile (e.g., `application.yml`, `cdr-edge-gateway-server-dev.yml`)

2. **Configure the application**
   - Update the Git repository URI in `application.yml`
   - Set a strong encryption key
   - Configure any required authentication

3. **Build the application**
   ```bash
   mvn clean install
   ```

4. **Run the application**
   ```bash
   java -jar target/cdr-configserver-1.0.0.jar
   ```
   Or using Maven:
   ```bash
   mvn spring-boot:run
   ```

## Configuration Encryption

The Config Server supports encrypting sensitive configuration values. To use this feature:

1. **Encrypt a value** (requires the server to be running):
   ```bash
   curl -X POST http://localhost:8071/encrypt -d "your-sensitive-value"
   ```

2. **Use the encrypted value** in your configuration files by prefixing it with `{cipher}`:
   ```yaml
   sensitive:
     property: "{cipher}encrypted-value-here"
   ```

## Monitoring

The service exposes the following monitoring endpoints:

- Health: `GET /actuator/health`
- Info: `GET /actuator/info`
- Refresh: `POST /actuator/refresh` (for refreshing configuration)
- Bus Refresh: `POST /actuator/bus-refresh` (for refreshing all clients)

## Security Considerations

- Always secure the Config Server with proper authentication
- Use HTTPS for all connections
- Store the encryption key securely (consider using environment variables or a secure vault)
- Limit access to the `/decrypt` and `/encrypt` endpoints in production

## Dependencies

- Spring Cloud Config Server
- Spring Boot Actuator
- Spring Cloud Bus (optional, for broadcasting configuration changes)
- Spring Security (for securing the server)

## License

[Specify your license here]

## Contact

[Your contact information]
