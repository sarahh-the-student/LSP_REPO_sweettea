# Reference: Analysis of Class Cohesion for Question 1

Conversation Transcript: Google Gemini

User:
I am analyzing the StudentPortalHelper class for Question 1. It contains a collection of disparate static methods, such as computeGPA, exportRosterToCsv, and isStrongPassword. My initial assessment is that this indicates poor design, but I want to articulate this using the correct terminology. Could you please clarify the distinction between high and low class cohesion and how heuristics come into play?

Gemini:
That's an excellent observation, and your assessment is spot on. You've identified a classic example of low cohesion.

Think of it like this:

High Cohesion: A class with high cohesion is like a well-organized toolbox where every tool is related to a specific job (e.g., a "Plumbing Kit" only has wrenches and pipe cutters). The class has a single, well-defined responsibility.

Low Cohesion: A class with low cohesion is like a junk drawer. It's a random collection of unrelated items (a battery, a key, a rubber band, a receipt). The class performs many unrelated tasks.

For a well-designed class, you always want high cohesion. It makes the class easier to understand, reuse, and maintain. Your observation about the "Helper" name is also very astute; Arthur Riel's heuristics specifically warn that class names like "Helper" or "Manager" often signal low cohesion because they become dumping grounds for miscellaneous functions.

User:
Thank you. It's clear the StudentPortalHelper class exhibits low cohesion. I will state that the class has low cohesion, which negatively impacts maintainability.

Gemini:
Exactly right. You've correctly identified the problem and the consequence. This type of class is often called a "God Class" because it tries to do everything. So, if you were asked how to fix it, what would your approach be? How would you clean up that junk drawer?

User:
Based on that, the logical refactoring approach would be to revamp the StudentPortalHelper class and to create smaller, single-responsibility classes.

Gemini:
That is the perfect refactoring strategy. The technique is called "Extract Class," where you identify the distinct responsibilities bundled into one class and move them into new, highly-cohesive classes. Your proposed classes (GpaCalculator, RosterExporter, etc.) are precisely the right way to improve the structure.

User:
You mentioned Arthur Riel's heuristics earlier. Could you elaborate on which specific heuristics apply to the StudentPortalHelper class?

Gemini:
Certainly. Several of Riel's heuristics are directly applicable here. The most obvious is the warning against naming classes with suffixes like "-er" or "-or" (and by extension, "Helper" or "Manager"), as it often indicates a procedural mindset rather than an object-oriented one, leading to low cohesion. Another key heuristic is that a class should not contain unrelated information; the StudentPortalHelper violates this by mixing academic, financial, and security concerns. Finally, a heuristic states that all methods in a class should be using most of the class's instance variables; here, most methods are static and don't use the cache variable at all, which is a strong sign that they don't belong together in this class.

# Reference: Analysis of Class Cohesion for Question 3

User:
I'm working on Question 3 and need to analyze a UML diagram. The prompt is:

A car manufacturer uses Java software to track current vehicles being built. The UML diagram below shows an excerpt of the current software structure... Each car can be built to one of three trim levels: Base, Luxury or Sport... At various points in the manufacturing process the customer can choose to change the trim level.

The diagram is attatched. My initial thought is that this structure is too rigid to support changing the trim level after creation. Could you clarify why this inheritance model fails?

Gemini:
Your intuition is correct—the structure is too rigid. The core technical reason is that in Java, an object's type is immutable for its entire lifetime.

When you create an object like new Sports(), that object is permanently a Sports object. The JVM allocates memory for a Sports object, and its fundamental class cannot be changed. To "change" the trim from Sports to Luxury, you would have to completely destroy the original Sports object and create a brand new Luxury object, manually copying all the relevant state (like VIN, engine details, etc.) from the old object to the new one. This isn't a dynamic change; it's a clunky and error-prone replacement.

User:
That makes perfect sense. So, for part (a), the structure fails because object types are static.

Now for part (b), the prompt suggests using composition. My understanding is that this means changing the relationship. How would I apply that here? Would the Car class hold a Trim object as a field?

Gemini:
That is exactly the correct approach. Instead of the Car being a specific trim, the Car has-a trim. This is a classic use case for the Strategy design pattern.

You would refactor it like this:

Create a TrimLevel interface that defines the contract for all trims (e.g., getTrimName(), getFeatures()).

Re-implement BaseTrim, SportsTrim, and LuxuryTrim as concrete classes that implement the TrimLevel interface.

Modify the Car class to have a private field, like private TrimLevel currentTrim;.

Most importantly, add a public setter method, public void setTrimLevel(TrimLevel newTrim), to the Car class.

This setter is the key. It allows you to swap out the TrimLevel object the Car is holding at any time. The Car object itself remains the same, but its behavior, which it delegates to its internal TrimLevel object, can be changed dynamically.

User:
I see. So the Car object's identity remains constant, but the internal trim-related behavior can be replaced.Thank you, that clarifies the entire refactoring process.

# Reference: Analysis of Class Cohesion for Question 5

User: Please help gramatically edit this: Before this course, I mainly used AI tools as an assistant, or a means to help me be more productive or work quicker. Throughout the course, I’ve used AI tools to help me with my own learning and understanding of the course work. For instance, I was not familiar with Java before this course, but I used Gemini to help me learn and develop my skills to complete my assignments. Additionally, I used Gemini to help me simplify code, or translate it to Python, a language I’m already familiar with. Tools like Github Copilot also helped me with the building blocks of my assignments or quickly doing git commands to update my repos.

The main drawbacks have been drawing the line between assistance and dependence on AI. As AI can be confident in incorrect answers, I still have to learn to do things on my own to maintain my own understanding and the integrity of my own work. Looking forward, as I am a Senior, I see AI benefitting me a lot in the professional world. I have already had internships where I have used their internal AI tools to build the skeleton of my code, act as an error-fixer or critic for my documentations, and to help me write quick unit tests. This allows me to be an efficient developer, and work faster and smarter.

Gemini: Before this course, I mainly used AI tools as a productivity assistant to work more quickly. Throughout this course, however, my use of AI evolved into a tool for actively improving my learning and understanding of the coursework. For instance, as I was not familiar with Java, I utilized Gemini to help learn the language and develop the skills necessary to complete my assignments. I also found it invaluable for simplifying complex code or translating it into Python, a language I’m already familiar with, which helped bridge the knowledge gap. Additionally, tools like GitHub Copilot were instrumental in generating the building blocks for my assignments and quickly formulating Git commands to update my repositories.

The main limitation I encountered was navigating the fine line between assistance and over-reliance on AI. Since AI can be confident in its incorrect answers, I learned that I must still solve problems independently to solidify my own understanding and maintain the integrity of my work. Looking forward, as a graduating senior, I see AI being immensely beneficial in the professional world. In previous internships, I have already used internal AI tools to build the skeleton of my code, act as a debugger or critic for my documentation, and help write quick unit tests. This allows me to be a more efficient developer, enabling me to work not just faster, but also smarter.

