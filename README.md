# ProManageScheduler 

ProManageScheduler is a **console-based Java application** that helps schedule tasks efficiently using a **Dynamic Programming approach**.  
Each task has a **deadline (1–5 days)** and a **revenue**, and the system calculates the **optimal schedule** that maximizes total revenue.

This project demonstrates the use of:
- Dynamic Programming
- JDBC with PostgreSQL
- Clean project structure using packages
- Menu-driven console applications in Java

---

##  Features

-  Add tasks with deadline and revenue
-  View all added tasks
-  Delete tasks using task ID
-  Calculate **maximum revenue using Dynamic Programming**
-  Display DP table for better understanding
-  Persistent storage using PostgreSQL

---

##  Problem Statement

Given a set of tasks where:
- Each task takes **1 day**
- Each task has a **deadline (1–5 days)**
- Each task has a **revenue**

The goal is to select tasks such that:
- No two tasks are scheduled on the same day
- Tasks are completed before or on their deadline
- **Total revenue is maximized**


## Tech Stack

- **Java (JDK 22)**
- **Dynamic Programming**
- **PostgreSQL**
- **JDBC**
- **IntelliJ IDEA**

---

##  Project Structure

src/
├── app/
│ └── AppRunner.java
├── db/
│ └── TaskStore.java
├── model/
│ └── Task.java
└── planner/
└── TaskPlannerDP.java


---

##  Database Schema

```sql
CREATE TABLE projects (
    project_id SERIAL PRIMARY KEY,
    title VARCHAR(100),
    deadline INT CHECK (deadline BETWEEN 1 AND 5),
    revenue INT
);

