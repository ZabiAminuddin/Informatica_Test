#Informatica_test API

#Task
“Given a very large text file that may not fit in available memory, create a file that contains the distinct words from the original file sorted in the ascending order.”

#Prerequisites
Java 8
IDE (Optional)
PostMan (For RESTApi calls)

#Solution
This application contains a FileUploadController.java class where we handle post call to upload a text file. It also includes checks for the file and file type.
It also includes a FileOperationsService.java class where we perform operations to selection of distinct words, sorting and writing into a newly created file.

To start the application run "java - jar {jarfile.jar}" from the application. //use mvn clean install command to build the jar

To upload a text file use Postman, 
Post call URL = http://localhost:{port}/upload
To attach file, you must attach it with the Body as form-data, Enter key as "textfile" and select file in the dropdown, In the Value, upload the desired .txt file.

A new file with name "{ORIGINAL_FILE_NAME}_Compressed_Sorted.txt" is created with distinct words from the original file sorted in the ascending order in the project folder.

#Attachments
In this project there the two files 
Original file - "Informatica_test.txt"
Compressed and sorted file (Result) -"Informatica_test_Compressed_Sorted.txt".
 