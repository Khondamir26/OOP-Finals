# 🛡️ Security System - Java Console Application

A simple console-based security system that manages access logs for **Employees** and **Visitors**. It allows registering individuals, logging entries and exits, and tracking presence within a facility using Java OOP principles.

---

## 📁 Project Structure

```plaintext
.
├── Person.java          # Abstract base class for Employee and Visitor
├── Employee.java        # Employee class
├── Visitor.java         # Visitor class
├── AccessLog.java       # Handles access log entries with timestamps
├── SecuritySystem.java  # Core system handling registration and logs
├── Main.java            # Main application with interactive menu

🚀 Features
Register Employees and Visitors
Log Entry and Exit events
Prevent:
Duplicate entries (already inside)
Exits without entry
View:
Current presence inside the premises
Complete access logs
Logs filtered by person ID
Uses custom exceptions for robust error handling
Timestamped logging using LocalDateTime

🛠️ Technologies Used
Java 8+
Object-Oriented Programming (OOP)
Java Collections API
Exception Handling
Console-based I/O

📦 How to Run
Compile all .java files:
javac *.java

Run the application:
main.java

🧪 Example Output (Demo Section)

--- People currently inside ---
--- Access Logs ---
2025-06-05T10:15:30 - ENTER - Alice (E101)
2025-06-05T10:16:01 - ENTER - Bob (V201)
2025-06-05T10:17:20 - EXIT - Alice (E101)
2025-06-05T10:18:11 - EXIT - Bob (V201)
--- Logs for ID: E101 ---
2025-06-05T10:15:30 - ENTER - Alice (E101)
2025-06-05T10:17:20 - EXIT - Alice (E101)

Usage Guide (Interactive Menu)
1. Register Employee
2. Register Visitor
3. Log Entry
4. Log Exit
5. View Current Presence
6. View All Logs
7. Search Logs by ID
8. Exit

👨‍💻 Author
Khondamir Tuychiyev – Java Developer

