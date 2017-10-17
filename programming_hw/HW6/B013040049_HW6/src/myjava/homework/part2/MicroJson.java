package myjava.homework.part2;

import java.lang.reflect.Field;

public class MicroJson<S>{
	
    //Get variable type string using: getType(field)
    //Get variable name string using: getName(field)
    //Get variable value string using: field.get(target)
   
    public String serialize(S target){
        StringBuilder result = new StringBuilder("{");
        //Fields is the collection of all variable in target
        Field[] fields = target.getClass().getDeclaredFields();
        try {            
        	
        	for(int i=0;i<fields.length;i++){
	        	
	            result.append("\"" + getName(fields[i]) + "\"" + ":");
	           
	            if(getType(fields[i]).equals("String")){
	            	result.append("\"" + (fields[i].get(target)) + "\"");	
	            }
	            else{
	            	result.append((fields[i].get(target)));	     
	            }
	            
	            if(i+1<fields.length){
	            	result.append(",");
	            }
	            
        	} 
        	
            /* Example : Get information of first variable
            result.append("Variable type is " + getType(fields[0]) + "\n");
            result.append("Variable name is " + getName(fields[0]) + "\n");
            result.append("Variable value is " + fields[0].get(target) + "\n");
             */
        	
        } catch (IllegalArgumentException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
        
        result.append("}");
        return result.toString();
    }

    public String getType(Field field){
        return (field.getType()).getSimpleName();
    }
    public String getName(Field field){
        return field.getName();
    }
}
