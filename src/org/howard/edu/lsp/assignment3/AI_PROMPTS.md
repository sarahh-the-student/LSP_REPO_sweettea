# AI Prompts and Responses for Assignment 3

This document outlines the key prompts used with a generative AI (Google Gemini) to complete the coding portion of Assignment 3. The process involved an initial refactoring, a request for a conceptual explanation of the new design, and a significant, real-world debugging session.

### Prompt 1: Initial Object-Oriented Refactoring

**My Prompt:**
> "Here is my code for Assignment 2. Can you help me redesign it into multiple classes with clearer responsibilities to make it more object-oriented for Assignment 3?"

**AI Response Excerpt:**
> "Of course! The key idea is to break down the single `ETL` class into multiple smaller classes, each with a single, clear responsibility. I've decomposed your program into the following classes: `EtlDriver`, `Product`, `CsvExtractor`, `ProductTransformer`, `CsvLoader`, and `RunSummary`..."

### Prompt 2: Explaining the Benefits of the Redesign

**My Prompt:**
> "Can you explain what makes this new multi-class design better than the single file I had before?"

**AI Response Excerpt:**
> "The new design is better because it follows the Single Responsibility Principle. Each class has one specific job, which makes the code easier to read, test, and maintain. For example, to change a business rule, you only need to edit `ProductTransformer.java` without touching the file-reading or writing logic. This separation of concerns and the use of encapsulation for state management make the application more robust and scalable."

### Prompt 3: The Debugging Cycle - File Not Found

**My Prompt:**
> "I've been trying to compile but keep getting `error: file not found`. We've tried several things but it's still not working. Can you help me figure out the root cause?"

**AI Response Excerpt:**
> "This error means the path must be wrong. We need to see your actual folder structure. Please run these two diagnostic commands: `pwd` to confirm your location, and `ls -R src` to show me the entire folder tree so we can find the exact path."

### Prompt 4: Fixing Structural Java Errors

**My Prompt:**
> "Okay, the file path issues are fixed, but now I have a new set of errors, starting with: `error: class CsvLoader is public, should be declared in a file named CsvLoader.java`."

**AI Response Excerpt:**
> "These errors are all caused by the very first one. It means you have the code for the `CsvLoader` class incorrectly placed inside your `RunSummary.java` file. In Java, a public class must be in a file with the exact same name. You need to cut the `CsvLoader` code and move it to the correct `CsvLoader.java` file."