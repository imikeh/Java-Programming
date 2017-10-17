package myjava.homework;

import java.util.Map;
import java.util.Set;

public interface ILookup {
    public boolean lookup (Set<Book> shelfset, Map<String, Boolean> shelf, Book b);
}
