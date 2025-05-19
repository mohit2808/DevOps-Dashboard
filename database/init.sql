-- Switch to the required database
USE devops_dashboard;

-- Create the user if it doesn't exist
CREATE USER IF NOT EXISTS 'devops_user'@'%' IDENTIFIED BY 'devops_pass';

-- Grant privileges to the user on the database
GRANT ALL PRIVILEGES ON devops_dashboard.* TO 'devops_user'@'%';
FLUSH PRIVILEGES;

-- Table for Jenkins Deployments
CREATE TABLE IF NOT EXISTS deployments (
                                           id INT AUTO_INCREMENT PRIMARY KEY,
                                           job_name VARCHAR(255) NOT NULL,
    build_id VARCHAR(255) NOT NULL,
    status VARCHAR(50),
    timestamp TIMESTAMP DEFAULT CURRENT_TIMESTAMP
    );

-- Table for Docker Container information
CREATE TABLE IF NOT EXISTS docker_containers (
                                                 id INT AUTO_INCREMENT PRIMARY KEY,
                                                 container_id VARCHAR(255) NOT NULL,
    status VARCHAR(255),
    timestamp TIMESTAMP DEFAULT CURRENT_TIMESTAMP
    );

-- Table for Kubernetes Pods
CREATE TABLE IF NOT EXISTS kubernetes_pods (
                                               id INT AUTO_INCREMENT PRIMARY KEY,
                                               pod_name VARCHAR(255) NOT NULL,
    status VARCHAR(50),
    namespace VARCHAR(255),
    timestamp TIMESTAMP DEFAULT CURRENT_TIMESTAMP
    );

-- Table for Ansible Logs
CREATE TABLE IF NOT EXISTS ansible_logs (
                                            id INT AUTO_INCREMENT PRIMARY KEY,
                                            playbook_name VARCHAR(255) NOT NULL,
    status VARCHAR(50),
    timestamp TIMESTAMP DEFAULT CURRENT_TIMESTAMP
    );

-- Table for System Availability
CREATE TABLE IF NOT EXISTS system_availability (
                                                   id INT AUTO_INCREMENT PRIMARY KEY,
                                                   service_name VARCHAR(255),
    status VARCHAR(50),
    uptime_percentage DECIMAL(5, 2),
    timestamp TIMESTAMP DEFAULT CURRENT_TIMESTAMP
    );
