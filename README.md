#JPMCTest
Application to process sales messages & Generate Reports.

#Assumption
</br>1:The application will run in a single threaded environment
</br>2: All data is in memory
</br>3: Report needs to be printed out to stdout
</br>4: The SalesMessageSimulator class is used to push the message queue by reading data from .csv file. 

#How to run?
</br>There is a Main class and it is accepting message test file as a input parameter to load messages to queue in String format.
The Application is identify messageType and process message for reporting.

#Dependencies
</br>Java 7 and above
</br>JUnit 4 needs to be on the class path

#Program Execution:
1. Junit Execution:

</br>-------------------------------------------------------
</br> T E S T S
</br>-------------------------------------------------------
</br>Running com.ds.jpmc.sales.messageprocessor.factory.SalesMessageFactoryTest
</br>Tests run: 4, Failures: 0, Errors: 0, Skipped: 0, Time elapsed: 0.114 sec
</br>Running com.ds.jpmc.sales.messageprocessor.model.AdjustmentTest
</br>Tests run: 6, Failures: 0, Errors: 0, Skipped: 0, Time elapsed: 0.001 sec
</br>Running com.ds.jpmc.sales.messageprocessor.model.SaleTest
</br>Not supporting MesssageType for Sale message
</br>Tests run: 7, Failures: 0, Errors: 0, Skipped: 0, Time elapsed: 0.003 sec

Results :

Tests run: 17, Failures: 0, Errors: 0, Skipped: 0

2. Input provided to application in .csv format. Test data is as below (Few Sample data. Input is provided at root "salesfeed.csv")
</br>MessageType,Product,Price,Quantity,AdjustmentTYpe
</br>MessageType1,Apple,10
</br>MessageType2,Apple,10,2
</br>MessageType1,Banana,10
</br>MessageType2,Banana,10,2
</br>MessageType3,Apple,2,,ADD
</br>MessageType1,Guava,10
</br>MessageType2,Guava,10,2
</br>MessageType3,Guava,2,,ADD
</br>MessageType1,Mango,10
</br>MessageType2,Mango,10,2
</br>MessageType1,Blackberry,10
</br>MessageType2,Blackberry,10,2
</br>MessageType3,Blackberry,2,,ADD
</br>MessageType3,Banana,2.10,,ADD
</br>MessageType3,Blackberry,2,,ADD


3. Reports genrated on console by application

<b> a. Sales Report </b>
 
</br>Message processor has started!
</br>Ignore message for First Header row
</br>=========== SALES REPORT ===========
 </br>Guava - units sold: 3 , sales amount: 36
 </br>Apple - units sold: 3 , sales amount: 36
 </br>Mango - units sold: 3 , sales amount: 30
 </br>Banana - units sold: 3 , sales amount: 30
</br>Total units sold - 12
</br>All sales - 132


</br></br>=========== SALES REPORT ===========
</br> Guava - units sold: 3 , sales amount: 36
</br> Apple - units sold: 6 , sales amount: 78
</br> Blackberry - units sold: 3 , sales amount: 42
</br> Mango - units sold: 3 , sales amount: 30
</br> Banana - units sold: 6 , sales amount: 66.30
</br>Total units sold - 21
</br>All sales - 252.30


</br>=========== SALES REPORT ===========
 </br>Guava - units sold: 6 , sales amount: 79.80
 </br>Apple - units sold: 6 , sales amount: 78
 </br>Blackberry - units sold: 6 , sales amount: 78
 </br>Mango - units sold: 6 , sales amount: 60
 </br>Banana - units sold: 6 , sales amount: 66.30
</br>Total units sold - 30
</br>All sales - 362.10


</br>=========== SALES REPORT ===========
 </br>Guava - units sold: 9 , sales amount: 139.80
 </br>Apple - units sold: 9 , sales amount: 126
 </br>Blackberry - units sold: 6 , sales amount: 90
 </br>Mango - units sold: 6 , sales amount: 60
 </br>Banana - units sold: 9 , sales amount: 96.30
</br>Total units sold - 39
</br>All sales - 512.10


</br>=========== SALES REPORT ===========
 </br>Guava - units sold: 9 , sales amount: 157.80
 </br>Apple - units sold: 12 , sales amount: 156
 </br>Blackberry - units sold: 9 , sales amount: 378
 </br>Mango - units sold: 9 , sales amount: 90
 </br>Banana - units sold: 10 , sales amount: 106.30
</br>Total units sold - 49
</br>All sales - 888.10
 
 <b>b. Adjustment Report</b>
 
 </br>The applicationg is pausing and will stop processing new messages.
</br>=========== ADJUSTMENTS REPORT ===========
 </br>Adjustment{type=ADD, productName='Apple', amount=2}
 </br>Adjustment{type=ADD, productName='Guava', amount=2}
 </br>Adjustment{type=ADD, productName='Blackberry', amount=2}
 </br>Adjustment{type=ADD, productName='Banana', amount=2.10}
 </br>Adjustment{type=ADD, productName='Blackberry', amount=2}
 </br>Adjustment{type=ADD, productName='Apple', amount=2}
 </br>Adjustment{type=ADD, productName='Guava', amount=2}
 </br>Adjustment{type=ADD, productName='Guava', amount=0.30}
 </br>Adjustment{type=SUBTRACT, productName='Blackberry', amount=2}
 </br>Adjustment{type=ADD, productName='Blackberry', amount=2}
 </br>Adjustment{type=ADD, productName='Guava', amount=2}
 </br>Adjustment{type=ADD, productName='Blackberry', amount=2}
 </br>Adjustment{type=ADD, productName='Apple', amount=2}
 </br>Adjustment{type=ADD, productName='Guava', amount=2}
 </br>Adjustment{type=MULTIPLY, productName='Blackberry', amount=3}
 </br>Adjustment{type=ADD, productName='Guava', amount=2}
 </br>Adjustment{type=ADD, productName='Blackberry', amount=2}

</br>Message processor has finished.

