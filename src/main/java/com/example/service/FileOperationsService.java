package com.example.service;

import com.example.controller.FileUploadController;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.SortedSet;
import java.util.TreeSet;

/**
 * Handles implementation of selection of distinct words, sorting and writing into a newly created file
 * param -- path
 * @return Newly created file name
 */

@Service
public class FileOperationsService {
    private static final org.slf4j.Logger logger = LoggerFactory.getLogger(FileUploadController.class);
    public String saveFile(Path path, String fileName){
        //Using a SortedSet, to have unique values which are in sorted order and Handling the order of case sensitive words
        SortedSet<String> sortedWords = new TreeSet<>(String.CASE_INSENSITIVE_ORDER);
        logger.info("Executing saveFile");
        try {
            //Can use StringBuffer however we are not using threads, StringBuilder will execute faster
            StringBuilder sb = new StringBuilder();

            //Reading each line from the file at path and storing in the sorted set
            Files.lines(path).forEach(line -> {
                String words[] = line.split(" ");
                for (String word: words) {
                    sortedWords.add(word);
                }
            });

            //as SortedSet returns objects, we will convert it into string to write to the new file
            logger.info("Writing to file");
            for (String words: sortedWords) {
                sb.append(words + " ");
            }

            //performing creation and writing to a new file
            fileName = fileName.substring(0, fileName.indexOf("."))+ "_Compressed_Sorted.txt";
            Files.write(Paths.get("./"+fileName), sb.toString().getBytes());
        }
        catch(IOException e){
            e.printStackTrace();
        }
      return fileName;
    }

    //Implementation to check the file is a .txt file or not
    public boolean isTextFile(String fileName) {
          if(!fileName.isEmpty() && fileName.toString().contains(".txt")){
              return true;
        }
        return false;
    }
}
