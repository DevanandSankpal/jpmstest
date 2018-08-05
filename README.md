# jpmstest
JPMCTest
Application to process sales messages & Generate Report.

</br>Assumption
</br>1: Messages are stored in CSV file with "," as Delimiter. 
</br>2:The application will run in a single threaded environment
</br>3: All data is in memory
</br>4: Report needs to be printed out to stdout
</br>5: The SalesMessageSimulator class is used to push the message queue by reading data from .csv file. 

#How to run?
</br>There is a Main class and it is accepting message test file as a input parameter to load messages to queue in String format.
The Application is identify messageType and process message for reporting.

#Dependencies
</br>Java 7 and above
JUnit 4 needs to be on the class path



