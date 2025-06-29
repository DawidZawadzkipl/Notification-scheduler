# 📅 Local Notification Scheduler
### A Java-based local client-server application for scheduling and delivering notifications at specified times. Runs on localhost (127.0.0.1) and built with socket programming, multi-threading, and priority queue data structures.
## 🚀 Features

- Schedule Future Notifications: Set notifications to be delivered at specific dates and times
- Real-time Delivery: Notifications are automatically sent when the scheduled time arrives
- Multi-client Support: Multiple clients can connect simultaneously to the server
- Priority Queue System: Uses time-based priority queue for efficient notification scheduling
- Input Validation: Prevents scheduling notifications in the past and validates date formats
- Local Client-Server Architecture: Runs on 127.0.0.1 with server on port 44444

## 🛠️ Technologies Used

- Java 11+
- Maven
- Socket Programming
- Multi-threading
- Priority Queue

## 📋 Prerequisites

- Java 11 or higher
- (optional)Maven 3.6+

## 🚀 Quick Start
 Clone the repository
```
git clone https://github.com/DawidZawadzkipl/Notification-scheduler
cd notification-scheduler
```
 Compile the project
```
mvn clean compile
```
 Start the server
```
mvn exec:java -Dexec.mainClass="Server"
```
 Start the client (in a new terminal)
```
mvn exec:java -Dexec.mainClass="Client"
```

## 💻 Usage

- Start the Server: The server will listen on localhost (127.0.0.1) port 44444 for incoming client connections
- Connect Client(s): Run the client application on the same machine to connect to the local server
### Schedule Notifications:

- Choose option 1 from the menu
- Enter notification message
- Enter delivery time in format: yyyy-MM-dd HH:mm:ss
- Notifications will be automatically delivered at the specified time

## Core Components

- Server: Main server class handling client connections
- Client: Client application with user interface
- ClientHandler: Manages individual client connections (one per client)
- NotificationDispatcher: Background thread that monitors and delivers scheduled notifications
- Notification: Data model with time-based comparison for priority queue
- NotificationSender/Receiver: Handles sending notifications and receiving responses


## ⚠️ Limitations

- Server and clients must run on the same machine (localhost/127.0.0.1)
- Only one server can run at a time on port 44444
- Notifications are lost when server is restarted
- No user management or security features

### 📄 License
This project is licensed under the MIT License.
