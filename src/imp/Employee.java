package imp;

import java.util.ArrayList;
import java.util.List;

public class Employee {
    String id;
    String name;
    Employee prev;
    List<Employee> next;

    public Employee(String id, String name, Employee prev) {
        this.id = id;
        this.name = name;
        this.prev = prev;
        this.next = new ArrayList<>();

        if (prev != null)
            prev.next.add(this);
    }
}
