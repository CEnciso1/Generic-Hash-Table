/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.hashproject4;

/**
 *
 * @author CHRIS
 */
public class Driver {
    
     public static void main(String args[]){

       HashTableLinearProbe<Integer, Integer> intTable = new HashTableLinearProbe<>();

       intTable.insert(1, 100);
       
       System.out.println(intTable.insert(1, 100));

       intTable.insert(4, 50);

       intTable.insert(7, 239);
       
       System.out.println(intTable.getCurrentSize());
       
       System.out.println(intTable.toString());
       
       System.out.println(intTable.delete(12));

       intTable.insert(9, 34);

       intTable.insert(6, 12);
       
       System.out.println(intTable.getCurrentSize());
       
       System.out.println(intTable.find(8));
       
       System.out.println(intTable.toString());
       
       HashTableLinearProbe<String, Integer> stringTable = new HashTableLinearProbe<>();
 
       stringTable.insert("abe", 15);
       
       System.out.println(stringTable.find("abe"));

       stringTable.insert("abd", 13);

       stringTable.insert("abc", 11);
       
       System.out.println(stringTable.toString());  
       
       stringTable.insert("ab", 11);
       
       System.out.println(stringTable.toString()); 
     }
}
