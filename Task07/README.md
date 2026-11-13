# Task 07 – Concurrent Fault-Tolerant Result Processor
Compile/run from the repository root:
`javac -d Task07/out Task07/src/ResultProcessor.java`
`java -cp Task07/out ResultProcessor`
Optional: `java -cp Task07/out ResultProcessor 10000`.
The program generates a reproducible 10,000-file workload, injects missing/corrupt files, and processes each input using ExecutorService, Callable and Future. Outcomes are collected in ConcurrentLinkedQueue. It measures pool sizes 1, 2, 4 and 8. Timings vary by machine; use your own run's measurements in the report.