package imp;

import java.util.HashMap;
import java.util.Map;

class OrgChart {
    Map<String, Employee> map;
    Employee dummy;

    private final String HIGHEST = "-1";

    public OrgChart() {
        this.map = new HashMap<>();
        this.dummy = new Employee(HIGHEST, "dummy", null);
    }

    public void add(String id, String name, String managerId)
    {
        if (map.containsKey(id)) {
            return;
        }
        if (managerId.equals(HIGHEST)) {
            Employee e = new Employee(id, name, dummy);
            map.put(id, e);
        } else if (map.containsKey(managerId)) {
            Employee e = new Employee(id, name, map.get(managerId));
            map.put(id, e);
        } else {
            Employee e = new Employee(id, name, dummy);
            map.put(id, e);
        }
    }

    public void print()
    {
        dfs(dummy, "");
    }

    private void dfs(Employee manager, String indent) {
        for (Employee e : manager.next) {
            System.out.println(indent + e.name + " [" + e.id + "]");
            dfs(e, indent + "  ");
        }
    }

    public void remove(String employeeId)
    {
        if (!map.containsKey(employeeId)) return;
        Employee e = map.get(employeeId);

        map.remove(employeeId);

        for (int i = 0; i< e.prev.next.size(); i++) {
            if (e.prev.next.get(i).id.equals(employeeId)) {
                e.prev.next.remove(i);
                break;
            }
        }

        for (Employee nextE : e.next) {
            nextE.prev = e.prev;
            e.prev.next.add(nextE);
        }
    }

    public void move(String employeeId, String newManagerId)
    {
        if (!map.containsKey(employeeId)) return;
        if (!map.containsKey(newManagerId)) return;
        Employee e = map.get(employeeId);
        for (int i = 0; i< e.prev.next.size(); i++) {
            if (e.prev.next.get(i).id.equals(employeeId)) {
                e.prev.next.remove(i);
                break;
            }
        }
        e.prev = map.get(newManagerId);

        e.prev.next.add(e);
    }

    public int count(String employeeId)
    {
        Employee e = map.get(employeeId);
        if (e.next.size() == 0) return 0;

        int count = 0;

        for (Employee pe : e.next) {
            count += count(pe.id) + 1;
        }

        return count;
    }
}
