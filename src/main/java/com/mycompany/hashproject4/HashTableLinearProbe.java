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
public class HashTableLinearProbe <K, V>{
    
    private int maxSize = 3;
    private int currentSize = 0;
    public HashEntry<K,V> hashTable[];
    
    public int getMaxSize(){
        return this.maxSize;
    }
    
    public int getCurrentSize(){
        return this.currentSize;
    }
    
    HashTableLinearProbe(){
        this.maxSize = 3;
        hashTable = new HashEntry[maxSize];
    }
    
    HashTableLinearProbe(int maxSize){
        this.maxSize = maxSize;
        hashTable = new HashEntry[maxSize];
    }
    
    
    //The hashtable is made of HashEntry objects
    private static class HashEntry<K,V>{
        boolean deleted;
        K key;
        V value;
        
        HashEntry(K key, V value){
            this.key = key;
            this.value = value;
            deleted = false;
        }
    }
    
    public boolean isOccupied(int index){
        return(hashTable[index] != null);
    }
    
    //Returns hash value(index)
    public int getHashValue(K key){
        //Key is invalid
        if(key == null) return -1;
        
        if(key instanceof Integer) return (int)key % maxSize;
            
        else{
            int totalVal = 0;
            
            for(int i = 0; i < ((String)key).length(); i++){
                totalVal += ((String)key).charAt(i);
            }
            
            return totalVal % maxSize;
        }
    }
    
    public V find(K key){
        int index = getHashValue(key);
        int indexRunner = index;
        
        if(index == -1) return null;
        //iterates through array starting and ending from original index;
        do{
            
            if(hashTable[indexRunner]== null){
                indexRunner = (indexRunner + 1) % maxSize;
                continue;
            }
            
            //Key is found return value
            if(hashTable[indexRunner].key.equals(key)) return hashTable[indexRunner].value;
            
            indexRunner = (indexRunner + 1) % maxSize;
                
        }while(indexRunner != index); //Cycled through array, key was not found
        
        return null;
        
    }
    
    private void rehash(){
        int primes[] = {7,17,37,79,163};
        int newSize = 0;
        currentSize = 0;
        for(int i = 0; i < primes.length; i++){
            if(primes[i] >= maxSize * 2){
                newSize = primes[i];
                break;
            }
        }
        //Make new table twice the size
        HashEntry<K,V> newTable[] = new HashEntry[newSize];
        
        //Rehash undeleted entries
        for(int i = 0; i < maxSize; i++){
            if(hashTable[i] != null && !(hashTable[i].deleted)){
                newTable[i] = hashTable[i];
                currentSize++;
            }
        }
        
        //Update pointer of hashTable to point to newTable and update size
        maxSize = newSize;
        hashTable = newTable;
    }
    
    public boolean insert(K key, V value){
        
        //hashTable is full rehash
        if(currentSize == maxSize) rehash();
        
        //key is invalid return false
        if(getHashValue(key) == -1)return false;
        
        int index = getHashValue(key);
        int indexRunner = index;
        
        do{
            //index is empty, insert new entry
            if(hashTable[indexRunner] == null){
                currentSize++;
                hashTable[indexRunner] = new HashEntry(key, value);
                return true;
            }
            //checks for duplicate
            if(hashTable[indexRunner].key.equals(key) && !(hashTable[indexRunner].deleted)) return false;
            
            //Remove deleted flag
            if(hashTable[indexRunner].key.equals(key) && (hashTable[indexRunner].deleted)){
                hashTable[indexRunner].deleted = false;
                return true;
            }
                
            //Current index is occupied, updates index to adjacent index
            indexRunner = (indexRunner + 1) % maxSize;
            
        }while(indexRunner != index); //Cycled through hashTable, empty index could not be found
        
        return false;
    }
    
    public boolean delete(K key){
        int index = getHashValue(key);
        int indexRunner = index;
        //key is invalid return false 
        if(index == -1)return false;
        
        do{
            //if keys matches and entry is not flagged as deleted, mark entry as deleted return true
            if(hashTable[indexRunner] != null && hashTable[indexRunner].key.equals(key) && !(hashTable[indexRunner].deleted)){
                hashTable[indexRunner].deleted = true;
                return true;
            }
            //Current index is occupied, updates index to adjacent index
            indexRunner = (indexRunner + 1) % maxSize;
            
        }while(indexRunner != index); //Cycled through hashTable, key could not be found or entry already flagged as deleted
        return false;
    }
    
     public String toString() {

       String statement = "";

       for(int i=0; i<maxSize; i++) {
           
           statement += i + " ";
           if(hashTable[i] != null) {

               statement += hashTable[i].key + ", " + hashTable[i].value + " ";
               if(hashTable[i].deleted) statement += "*";
           }

           statement += "\n";
       }

       return statement;
   }
}
