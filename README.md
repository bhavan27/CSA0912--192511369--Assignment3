# CSA09 – Programming in Java – Assignment 3 (CO3)

This ZIP is organised by the seven questions in the supplied 11-page assignment PDF.

## Requirements
- JDK 17 or later.
- VS Code or another Java IDE/terminal.

## Structure
- `Task01/` ATM exception handling
- `Task02/` user-defined exceptions and propagation
- `Task03/` resource safety and try-with-resources
- `Task04/` thread creation and life cycle
- `Task05/` race conditions and synchronization
- `Task06/` deadlock and producer-consumer
- `Task07/` concurrent result processor

Each task folder contains `src/`, `input/`, `output/`, and a task README. Output folders contain `.txt` sample transcripts and matching `.png` terminal-style screenshots. Screenshots are illustrative sample output, not captures from a local Java execution; run the code on your computer and replace/augment them with your own execution screenshots before submission.

## Compile and run
Open a terminal in the repository root and use the relevant commands:

```bash
javac -d Task01/out Task01/src/*.java
java -cp Task01/out AtmDemo

javac -d Task02/out Task02/src/*.java
java -cp Task02/out StorageDemo

javac -d Task03/out Task03/src/*.java
java -cp Task03/out ResourceSafetyDemo

javac -d Task04/out Task04/src/*.java
java -cp Task04/out ThreadStationsDemo

javac -d Task05/out Task05/src/*.java
java -cp Task05/out SeatBookingDemo

javac -d Task06/out Task06/src/*.java
java -cp Task06/out ConcurrencyProblemsDemo

javac -d Task07/out Task07/src/*.java
java -cp Task07/out ResultProcessor
```

## Question summary
1. ATM exception hierarchy, checked/unchecked failures, try/catch/multi-catch/finally and stack trace.
2. Checked custom exceptions, three-layer propagation, `throw`, `throws` and exception chaining.
3. Manual resource closing compared with try-with-resources, failure injection and resource cleanup.
4. Thread vs Runnable, lifecycle methods, ten finish-order observations and ExecutorService alternative.
5. Unsynchronized race demonstration, synchronized method and synchronized block.
6. Deadlock reproduction and lock ordering, producer-consumer using `wait()`/`notifyAll()`, ArrayBlockingQueue alternative.
7. ExecutorService, Callable, Future and concurrent result collection for a 10,000-file workload, including pool sizes 1, 2, 4 and 8.

## Before submission
The PDF requires each student to execute every task individually, maintain at least one progressive commit per task, provide a joint report with Student A/B sections, and include actual test tables, stack traces, logs and raw measurements. This ZIP is a structured implementation starter. Check every program on your own JDK, replace sample screenshots with your actual execution screenshots, record actual timings and complete the individual contribution table. Thread ordering and benchmark timings vary by machine.
