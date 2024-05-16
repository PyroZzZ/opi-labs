import java.util.*;
import java.util.stream.*;

public class ListPatients {
    public ArrayList<Patient> list = new ArrayList<>();

    public Object[] to_String() {
        return list.toArray();

    }
    public List<Patient> StemaFilter () {
        return list.stream().filter(p -> p.Salary > 100).collect(Collectors.toList());
    }
}