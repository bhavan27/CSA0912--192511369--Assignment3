# Task 03 – Resource Safety
Compile/run: `javac -d Task03/out Task03/src/ResourceSafetyDemo.java` then `java -cp Task03/out ResourceSafetyDemo`.
Compares a deliberately flawed manual-close pattern with try-with-resources. The resource counter is a controlled simulation and does not leave real OS file handles open. It demonstrates reverse-order closing and cleanup during exceptions.