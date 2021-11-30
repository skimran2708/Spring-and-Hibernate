package com.example.jackson.json.demo;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;

public class Driver {
    public static void main(String[] args) {
        try {
            //create  object mapper
            ObjectMapper mapper = new ObjectMapper();

            //Read JSON file and convert to Java POJO
            //data/sample-lite.json
            Student theStudent = mapper.readValue(new File("data/sample-full.json"),Student.class);

            //print firstname and lastname
            System.out.println("First Name: " + theStudent.getFirstName());
            System.out.println("Last Name: " + theStudent.getLastName());

            //print address
            Address tempAddress = theStudent.getAddress();
            System.out.println("Street: " + tempAddress.getStreet());
            System.out.println("City: " + tempAddress.getCity());

            //print languages
            for(String tempLang : theStudent.getLanguages())
                System.out.println(tempLang);

        }
        catch (Exception exception)
        {
            exception.printStackTrace();
        }
    }
}
