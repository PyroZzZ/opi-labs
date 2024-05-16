import javax.swing.*;
import java.awt.*;
import java.util.List;
import java.util.Random;

public class ListFrame extends JFrame {
    public ListFrame() {
        super("Список сотрудников");
        createGUI();
    }

    ListPatients patients = new ListPatients();
    JList<Object> jList;
    DefaultListModel<Object> model;
    Random rnd = new Random();
    Object[] patientsString;

    public void createGUI() {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        JPanel panel = new JPanel();
        JPanel leftPanel = new JPanel();
        JPanel rightPanel = new JPanel();

        JTextField searchField = new JTextField(20);
        JButton searchButton = new JButton("Искать");
        JTextField name = new JTextField(20);
        JTextField salary = new JTextField(20);
        JTextField position = new JTextField(20);

        patients.list.add(new Patient("Геннадий", rnd.nextInt(300), "Системный администратор"));
        patients.list.add(new Patient("Николай", rnd.nextInt(300), "Младший разработчик"));
        patients.list.add(new Patient("Екатерина", rnd.nextInt(300), "Программист"));
        patients.list.add(new Patient("Александр", rnd.nextInt(300), "Дизайнер"));
        patients.list.add(new Patient("Ольга", rnd.nextInt(300), "Менеджер проекта"));

        GridLayout layout = new GridLayout(0, 1);
        model = new DefaultListModel<>();
        patientsString = patients.to_String();

        for (Object obj : patientsString) {
            model.addElement(obj);
        }

        leftPanel.setLayout(layout);
        rightPanel.setLayout(layout);
        panel.setLayout(new GridLayout(1, 2));
        leftPanel.add(searchField);
        leftPanel.add(searchButton);
        leftPanel.add(name);
        leftPanel.add(salary);
        leftPanel.add(position);

        JButton add_button = new JButton("Добавить");

        add_button.addActionListener(e -> {
            patients.list.add(new Patient(name.getText(), Integer.parseInt(salary.getText()), position.getText()));
            model.clear();
            patientsString = patients.to_String();

            for (Object obj : patientsString) {
                model.addElement(obj);
            }
        });

        leftPanel.add(add_button);
        JButton deleteButton = new JButton("Удалить");

        deleteButton.addActionListener(e -> {
            patients.list.remove(jList.getSelectedValue());
            model.clear();
            patientsString = patients.to_String();

            for (Object obj : patientsString) {
                model.addElement(obj);
            }
        });

        leftPanel.add(deleteButton);

        jList = new JList<>(model);
        rightPanel.add(new JScrollPane(jList));

        JButton filterButton = new JButton("Фильтр");

        filterButton.addActionListener(e -> {
            model.clear();
            List<Patient> items = patients.StemaFilter();

            for (Patient item : items) {
                model.addElement(item);
            }
        });

        rightPanel.add(filterButton);
        JButton clearFilterButton = new JButton("Очистить фильтр");

        clearFilterButton.addActionListener(e -> {
            model.clear();
            patientsString = patients.to_String();

            for (Object obj : patientsString) {
                model.addElement(obj);
            }
        });

        rightPanel.add(clearFilterButton);
        panel.add(leftPanel);
        panel.add(rightPanel);

        searchButton.addActionListener(e -> {
            String searchText = searchField.getText();
            model.clear();

            for (Patient patient : patients.list) {
                if (patient.Name.toLowerCase().contains(searchText.toLowerCase())) {
                    model.addElement(patient);
                }
            }
        });

        getContentPane().add(panel);
    }

    public static void main(String[] args) {
        javax.swing.SwingUtilities.invokeLater(() -> {
            JFrame.setDefaultLookAndFeelDecorated(true);
            ListFrame frame = new ListFrame();
            frame.setResizable(false);
            frame.setPreferredSize(new Dimension(800, 600));
            frame.pack();
            frame.setVisible(true);
        });
    }
}