import java.util.*;
import java.util.stream.*;

public class ListWorkers {
    public ArrayList<Worker> list = new ArrayList<>();

    public Object[] to_String() {
        return list.toArray();

    }
    public List<Worker> StemaFilter () {
        return list.stream().filter(p -> p.Salary > 100).collect(Collectors.toList());
    }
}