# Hotel Booking System

This is a hotel booking application developed using React Vite 4.5.4 for the frontend and Spring Boot 3.2.3 for the backend.

## Table of Contents

- [Introduction](#introduction)
- [Key Components](#key-components)
- [Database](#database)
- [Main Features](#main-features)
- [Getting Started](#getting-started)
  - [Installation](#installation)
  - [Usage](#usage)
- [Contributing](#contributing)
- [Special Thanks](#special-thanks)
- [License](#license)

## Introduction

This project aims to develop a hotel booking system using the advanced features of the Spring Framework for the backend and React for the frontend. The platform allows users to search, book, and manage hotel rooms online, ensuring a smooth and secure experience. The APIs are tested using Postman, and interactive documentation is provided via Swagger.

## Key Components

1. **Spring Boot**:
   - Utilization of Spring Boot to simplify application configuration and deployment.
   - Creation of a standalone application ready to be executed.

2. **Spring MVC**:
   - Implementation of the MVC model to handle user interactions.
   - Creation of controllers to manage operations such as room search, booking, etc.

3. **Spring Data**:
   - Usage of Spring Data JPA to interact with the database.
   - Mapping of Java entities to database tables for storing information about rooms, reservations, etc.

4. **Spring Security**:
   - Integration of Spring Security to ensure platform security.
   - Management of user authentication and authorization for accessing different functionalities.

5. **Postman**:
   - Utilization of Postman to test the developed APIs.
   - Creation of API collections and test scenarios to verify the application's functionality.
   
6. **Frontend in React vite**:
   - The frontend of the application can be developed using React Vite with bootstrap, providing a modern and responsive user experience.


## Database

For this project, I used a relational database MySQL. This databases is well-supported by Spring Data JPA and provide a suitable data structure for storing information about hotel rooms, reservations, users, etc.

## Main Features

1. **Room Search and Booking**:
   - Users can search for available hotel rooms by specifying criteria such as arrival date, departure date, number of people, etc.
   - Display of available rooms with details such as price, amenities, photos, etc.
   - Ability to book a room by providing necessary information.

2. **Reservation Management**:
   - Users can view their booking history and manage details such as dates, rooms, etc.
   - Administrators can view all bookings made and manage their status.

3. **Authentication and User Management**:
   - Users can create an account, log in, and manage their personal information.
   - Administrators can manage user accounts and associated roles.

4. **Room Management (for Administrators)**:
   - Administrators can add new rooms, modify details of existing rooms, and remove obsolete rooms.
   - Availability management functionality is provided to update room availability dates.

## Getting Started

To get a local copy up and running, follow these simple steps.

### Installation

#### Clone the repository

```bash
git clone https://github.com/ChaariMahmoud/marina-hotel.git
```

#### Navigate into the project directory:


#### Frontend
Switch to the main branch for the frontend:

```bash
git checkout main
```
#### Install frontend dependencies:

```bash
npm install
```
#### Backend
Switch to the backend branch:

```bash
git checkout backend
```
#### Install backend dependencies:

```bash
mvn install
```
#### Usage
Start the backend server:

```bash
mvn spring-boot:run
```

Start the frontend development server:

```bash
npm run dev
```
Open your browser and navigate to http://localhost:5174 to view the application.


#### Branches
main: Contains the frontend code.
backend: Contains the backend code.
#### Contributing
Contributions are welcome! Feel free to open issues and pull requests.

#### Special Thanks
A special acknowledgment to [Wajih Jouini](https://github.com/jouini-wajih) for his valuable contributions to the frontend of this project.

Let me know if there's anything else you'd like to add or modify!
