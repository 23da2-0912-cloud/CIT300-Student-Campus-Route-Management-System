# CIT300 Data Structures and Algorithms
## University Student Record and Campus Route Management System

### Module
CIT300 Data Structures and Algorithms — Graded Practical Assignment 1 (Week 10)

### Project Type
Java console application

## Group Members
| Member | Student ID | Assigned Responsibility |
|---|---|---|
| M.R.F RUKAIYA | 23DA2-0912 | Linked list and student-record management; integration and presentation lead |
| M.T AANISHA SAHLA | 23DA2-1021 | Stack and queue implementation and related operations |
| Zaid Ahamed | 23DA2-1061 | BST implementation and hashing/search functionality |
| M.T ARRAHUMAN | 23DA2-0918 | Graph implementation, campus locations, connections, and BFS traversal |

All members: integration, validation, testing, debugging, documentation, GitHub collaboration, and final demonstration.

## System Features
1. Add student record
2. Update student record
3. Delete student record
4. Display student records using a linked list
5. Add student service requests to a queue
6. Process the next service request
7. Display recent actions using a stack
8. Display students using a BST ordered by Student ID
9. Search a student using hashing
10. Add campus location
11. Remove campus location
12. Add campus connection/road
13. Remove campus connection/road
14. Display the campus network as an adjacency list
15. Traverse the campus network using BFS
16. Exit

## Data Structures Used
- **Linked List:** stores student records.
- **Stack:** stores recent system actions.
- **Queue:** stores student service requests in arrival order.
- **Binary Search Tree (BST):** stores students by Student ID and displays them in sorted order.
- **Hash Table:** provides student ID lookup using a custom hash function with separate chaining.
- **Graph:** represents campus locations and undirected connections using an adjacency list.
- **BFS:** traverses connected campus locations from a selected starting point.

## Validation Included
- Menu choices must be within the allowed range.
- Student IDs must be unique.
- Student name, programme, and location values cannot be empty.
- Marks must be between 0 and 100.
- Service requests require an existing student ID.
- Duplicate campus locations are rejected.
- Connections require two existing, different locations.
- Duplicate or unavailable campus connections are handled.
- Missing student/location records are handled with clear messages.

## Project Structure
```text
CIT300_Student_Campus_System/
├── src/
│   ├── Main.java
│   ├── Student.java
│   ├── StudentLinkedList.java
│   ├── ActionStack.java
│   ├── ServiceRequestQueue.java
│   ├── StudentBST.java
│   ├── HashStudentDirectory.java
│   └── CampusGraph.java
└── README.md
```

## How to Run
1. Open the project folder in Visual Studio Code.
2. Make sure a JDK is installed and the VS Code Java Extension Pack is installed.
3. Open the VS Code terminal.
4. Run:
   ```bash
   javac -d out src/*.java
   ```
5. Then run:
   ```bash
   java -cp out Main
   ```

## Suggested Demo Data
### Students
- 23DA2-1001 | Ahamed Nimal | Bachelor of IT | 78
- 23DA2-1002 | Fathima Sana | Bachelor of IT | 86
- 23DA2-1003 | Rizwan Kareem | Computer Science | 69

### Campus Locations
- Main Gate
- Library
- IT Building
- Cafeteria
- Student Center

### Campus Connections
- Main Gate <-> Library
- Main Gate <-> IT Building
- Library <-> Student Center
- IT Building <-> Cafeteria
- Cafeteria <-> Student Center

