/*
 * Copyright ZoftSolutions(C) 2016 SUMESH T.G <ZoftSolutions>
 *
 * This program is free software; you can redistribute it and/or
 * modify it under the terms of the GNU General Public License
 * as published by the Free Software Foundation; either version 2
 * of the License, or (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program; if not, write to the Free Software
 *  Developed by ZoftSolutions (2015) Company.
 */
package test;

/**
 *
 * @author SUMESH T.G <ZoftSolutions>
 */
import com.zs.ina.enquiry.dao.EnquiryDetailsSearchPOJO;
import java.io.*;
import java.util.List;
public class DeserializeDemo {

   public static void main(String [] args) {
      List<EnquiryDetailsSearchPOJO> enquiryList = null;
      try {
         FileInputStream fileIn = new FileInputStream("Enquiry_details.ser");
         ObjectInputStream in = new ObjectInputStream(fileIn);
         enquiryList = ( List<EnquiryDetailsSearchPOJO> ) in.readObject();
         in.close();
         fileIn.close();
      }catch(IOException i) {
         i.printStackTrace();
         return;
      }catch(ClassNotFoundException c) {
         System.out.println("Employee class not found");
         c.printStackTrace();
         return;
      }
      
      System.out.println("Deserialized Employee...");
      System.out.println("Name: " + enquiryList);
      System.out.println("Address: " + enquiryList);
      System.out.println("SSN: " + enquiryList);
      System.out.println("Number: " + enquiryList);
   }
}
