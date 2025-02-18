# Project_RiskMap

## Overview
Project_RiskMap is a game developed as part of the **SOFTENG 281** assignment for **Semester 1, 2024**. This project involves implementing a strategic board game where players can simulate territorial expansion and conflict. The game is inspired by the classic board game *Risk* and aims to provide a digital platform for users to engage in strategic gameplay. 
## Table of Contents
- [Features](#features)
- [Installation](#installation)
- [Gameplay](#gameplay)
- [Contributing](#contributing)
- [License](#license)

## Features
- **Graph Data Structure:** Model the map as a graph with countries as nodes and edges representing neighboring relations.
- **Shortest Path Calculation:** Implement an algorithm to find and print the shortest path between two countries.
- **Tax Calculation:** Calculate the total taxes paid for crossing borders based on predefined values.
- **Interactive Command-Line Interface:** User-friendly interface to interact with the game.
- **Map Loading:** Load the game map into the graph structure from a file or predefined data.

## Installation
To run **Assignment-3-RiskMap** locally, follow these steps:

### 1. Clone the repository:
```sh
git clone https://github.com/Nicky8566/Assignment-3-RiskMap.git
```

### 2. Navigate to the project directory:
```sh
cd Assignment-3-RiskMap
```

### 3. Run the program:
#### For Mac:
```sh
./mvnw clean javafx:run
```
#### For Windows:
```sh
./mvnw.cmd clean javafx:run
```

## Gameplay

### Objective
The main objective of this assignment is to implement a Java application that simulates a simplified version of the Risk board game. A **Graph** data structure is used to model the map, where countries are nodes and edges represent the neighborhood relations. Players must implement code to:
- Load the map into a graph.
- Print the shortest path between two countries.
- Calculate the total taxes paid for crossing borders.

This assignment simulates a real-world scenario where a company needs to find optimal routing for international deliveries.

### How to Play
#### 1. Start the Game
Run the game by executing the Maven command.
- **For Mac:**
  ```sh
  ./mvnw clean javafx:run
  ```
- **For Windows:**
  ```sh
  .\mvnw.cmd clean javafx:run
  ```

#### 2. Select a Territory
Choose a starting territory to begin your expansion.

#### 3. Select a Destination
Choose a finishing territory, and the program will calculate the shortest path (with the lowest risk) to that country.

## Contributing
Contributions are welcome! If you have suggestions for improvements or new features, feel free to fork the repository and create a pull request.

### Steps to Contribute:
1. **Fork the Project**
2. **Create a Feature Branch**
   ```sh
   git checkout -b feature/AmazingFeature
   ```
3. **Commit your Changes**
   ```sh
   git commit -m 'Add some AmazingFeature'
   ```
4. **Push to the Branch**
   ```sh
   git push origin feature/AmazingFeature
   ```
5. **Open a Pull Request**

## License
Distributed under the **MIT License**. See `LICENSE` for more information.

