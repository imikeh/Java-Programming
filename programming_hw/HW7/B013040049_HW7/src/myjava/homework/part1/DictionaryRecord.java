package myjava.homework.part1;

import java.io.Serializable;

public class DictionaryRecord implements Serializable{
	private static final long serialVersionUID = 1L;
    public String word;
    public String description;
    public DictionaryRecord(String word){
        this.word = word;
    }
    public DictionaryRecord(String word, String description){
        this.word = word;
        this.description = description;
    }
    public void setDescription(String description){
        this.description = description;
    }
}
