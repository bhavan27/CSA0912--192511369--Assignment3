# CSA09 – Programming in Java – Assignment 3 (CO3)

**Student:** V. C. Bhavan Bairav  
**Register Number:** 192511369  
**Course:** Programming in Java – CSA0912 (CO2)  
**Assignment:** Assignment 3 – 50 Marks

This repository contains my Java Assignment 3, covering exception handling, resource management and multithreading. It is organised into seven task folders based on the questions in the assignment PDF.

## Requirements

- **Java:** JDK 17 or later.
- **IDE:** VS Code or any Java IDE with terminal support.

## Repository Structure

Each task focuses on a different Java concept:

- `Task01/` – ATM exception handling and exception hierarchy
- `Task02/` – User-defined exceptions and exception propagation
- `Task03/` – Resource management and try-with-resources
- `Task04/` – Thread creation and thread life cycle
- `Task05/` – Race conditions and synchronization
- `Task06/` – Deadlock and producer-consumer problem
- `Task07/` – Concurrent result processor using ExecutorService

Each task folder contains:

- `src/` – Java source files
- `input/` – Input files and test data
- `output/` – Sample output transcripts and screenshots
- Task-specific README with implementation details

The output folders contain `.txt` sample transcripts and matching `.png` terminal-style screenshots.

## Compile and Run

Open a terminal in the repository root and execute the commands for the required task.

**Task 01 – ATM Exception Handling**
```bash
javac -d Task01/out Task01/src/*.java
java -cp Task01/out AtmDemo
```

**Task 02 – User-Defined Exceptions**
```bash
javac -d Task02/out Task02/src/*.java
java -cp Task02/out StorageDemo
```

**Task 03 – Resource Safety**
```bash
javac -d Task03/out Task03/src/*.java
java -cp Task03/out ResourceSafetyDemo
```

**Task 04 – Thread Life Cycle**
```bash
javac -d Task04/out Task04/src/*.java
java -cp Task04/out ThreadStationsDemo
```

**Task 05 – Race Conditions and Synchronization**
```bash
javac -d Task05/out Task05/src/*.java
java -cp Task05/out SeatBookingDemo
```

**Task 06 – Concurrency Problems**
```bash
javac -d Task06/out Task06/src/*.java
java -cp Task06/out ConcurrencyProblemsDemo
```

**Task 07 – Concurrent Result Processor**
```bash
javac -d Task07/out Task07/src/*.java
java -cp Task07/out ResultProcessor
```

## Questions Overview

| Question | Concepts Covered |
|---|---|
| Q1 | ATM exception hierarchy, checked and unchecked exceptions, try-catch, multi-catch, finally and stack traces |
| Q2 | Custom checked exceptions, three-layer exception propagation, throw, throws and exception chaining |
| Q3 | Manual resource closing, try-with-resources, failure injection and resource cleanup |
| Q4 | Thread class, Runnable interface, thread life cycle, execution order and ExecutorService |
| Q5 | Race conditions, synchronized methods and synchronized blocks |
| Q6 | Deadlock, lock ordering, producer-consumer using wait() and notifyAll(), and ArrayBlockingQueue |
| Q7 | ExecutorService, Callable, Future and concurrent processing of a 10,000-file workload using different thread pool sizes |

## Execution Evidence and Notes

The repository includes sample output transcripts and terminal-style screenshots for reference. These screenshots are illustrative sample outputs and are not captures of a local Java execution.

Thread execution order and benchmark timings may vary depending on the system and JVM. Actual execution results, logs, stack traces and performance measurements should be recorded from the local environment.

## Git Workflow

The assignment is organised task-wise to make the source code, test data and execution evidence easy to navigate. Progressive commits help maintain a clear record of the implementation of each question.

## Assignment Requirements

The assignment includes individual execution and contribution requirements, along with a joint report containing separate Student A and Student B sections. Test tables, stack traces, execution logs and raw measurements form part of the required evidence.
