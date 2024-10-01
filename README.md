# Networking Application

## Overview
This networking application is designed to connect students with companies, facilitating job searches and professional networking. It provides a platform for students to showcase their skills and for companies to post job opportunities.

## Features

### For Students
- Profile Management: Create and edit personal profiles
- Networking: View profiles of other students and companies
- Post Creation: Share thoughts and experiences
- Job Exploration: Browse and apply for job postings

### For Companies
- Profile Management: Create and maintain company profiles
- Job Posting: Publish job opportunities
- Candidate Exploration: View student profiles
- Market Insights: Access statistics about job posts

## Technology Stack
- Spring MVC: Web application framework
- Spring Data JPA: Data access layer
- Microsoft SQL: Database management
- JSP (Java Server Pages): Dynamic web content
- HTML & Bootstrap: Frontend design

## Getting Started

### Prerequisites
- Java JDK 11 or higher
- Maven
- Microsoft SQL Server

### Installation
1. Clone the repository:
   ```
   git clone https://github.com/your-username/networking-application.git
   ```
2. Navigate to the project directory:
   ```
   cd networking-application
   ```
3. Configure the database connection in `src/main/resources/application.properties`
4. Build the project:
   ```
   mvn clean install
   ```
5. Run the application:
   ```
   mvn spring-boot:run
   ```

## Usage
After starting the application, navigate to `http://localhost:8080` in your web browser. You can register as either a student or a company representative.

## Contributing
We welcome contributions to this project. Please fork the repository and submit a pull request with your changes.

## License
This project is licensed under the MIT License - see the LICENSE.md file for details.

## Acknowledgments
- Spring Framework documentation
- Bootstrap documentation
- All contributors who have helped shape this project
