public class Worker {
    public String Name;
    public int Salary;
    public String Position;

    public Worker(String name, int salary, String position) {
        Name = name;
        Salary = salary;
        Position = position;
    }

    @Override
    public String toString() {
        return Name + " " + Salary + "$ " + Position;
    }
}

