# Bonus Assignment - Data Processing and Storage Assignment
## Matthew Li

## Setup Instructions
- Ensure Java is installed and up-to-date
- In a terminal, cd into the correct directory (for this project it would be DataProcessingStorage/)
- Compile the java project by running this command in terminal: **javac InMemoryDB.java**
- Then run the tests and code within the file using this command in terminal: **java InMemoryDB**

## Runtime Results
Once the file is up and running, the terminal will show these lines in order:

- null
- Error
- null
- 6
- Error
- Error
- null
- null

These lines mimic the Figure 2 tests shown in the assignment documentation. 

*It's important to note that trying to implement null in the int get() function was difficult since it requires an Integer return type or sentinel value. This was by throwing an exception and then manually converting it to null for testing*

## Assignment Observations and Suggestions
I believe this assignment is already good enough to be "official" in the future! For the implementation there is a lot of freedom while still maintaining a structure for the student to follow, and overall the resources were helpful for the database. 

Maybe for grading have it be partial credit per method? Some of them could be weighted higher such as the commit command functionality while others are weighted lighter to allow for students to still receive some credit even if the code isn't fully working.