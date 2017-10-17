package myjava.homework.part2;
import static java.lang.System.out;
public class Main {
    public static void main(String[] args) {
        MicroJson<Test> test_json = new MicroJson<>();
        Test test = new Test();
        out.println(test_json.serialize(test));      
        
       MicroJson<Test2> test2_json = new MicroJson<>();
       Test2 test2 = new Test2();
       out.println(test2_json.serialize(test2));
    }
}
