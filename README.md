# jpmstest
JPMCTest
Assumption
1: Messages are stored in CSV file with "," as Delimiter. 
2:The application will run in a single threaded environment
3: All data is in memory
4: Report needs to be printed out to stdout
5: The SalesMessageSimulator class is used to push the message queue by reading data from .csv file. 

#How to run?
There is a Main class and it is accepting message test file as a input parameter to load messages to queue in String format.
The Application is identify messageType and process message for reporting.

#Dependencies
Java 7 and above
JUnit 4 needs to be on the class path



