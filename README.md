# Trade confirmation extraction
The application allows to extract a trade confirmation from the partner email report  
## Prerequisites

Make sure you have the following installed on your machine:

- **Java 17**
- **Maven 3.3.0**

## Installation

1. Clone this repository to your machine:
    - **git clone https://github.com/salltamsir/trade**
2. Navigate to the project directory
   - **cd trade**
   
## Execution
### Unix system
1. Make sure you have rights to execute the script
   - **chmod 777 run.sh**

2. Execute the run.sh script with one argument representing the absolute path of trade confirmation file
   - **./run.sh path_to_file**

### Windows system
1. Build project
   - **mvn clean install**
2. Run jar with one argument representing the absolute path of trade confirmation file
   - **java - jar trade-1.0-SNAPSHOT-jar-with-dependencies.jar path_to_file**
- **Script to be created**

