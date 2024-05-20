import javax.swing.*;
import java.awt.*;
import java.util.List;
import java.util.Random;

public class ListFrame extends JFrame {
    public ListFrame() {
        super("Список сотрудников");
        createGUI();
    }

    ListWorkers workers = new ListWorkers();
    JList<Object> jList;
    DefaultListModel<Object> model;
    Random rnd = new Random();
    Object[] workersString;

    public void createGUI() {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        JPanel panel = new JPanel();
        JPanel leftPanel = new JPanel();
        JPanel rightPanel = new JPanel();
        JPanel searchPanel = new JPanel();
        JPanel searchLeftPanel = new JPanel();
        JPanel searchRightPanel = new JPanel();
        JPanel fieldsPanel = new JPanel();
        JPanel fieldsLeftPanel = new JPanel();
        JPanel fieldsRightPanel = new JPanel();

        JTextField searchField = new JTextField(20);
        JButton searchButton = new JButton("Искать");
        JTextField name = new JTextField(20);
        JTextField salary = new JTextField(20);
        JTextField position = new JTextField(20);

        JLabel searchLabel = new JLabel("Поиск: ");
        JLabel nameLabel = new JLabel("Имя: ");
        JLabel salaryLabel = new JLabel("Зарплата: ");
        JLabel positionLabel = new JLabel("Должность: ");

        workers.list.add(new Worker("Геннадий", rnd.nextInt(300), "Системный администратор"));
        workers.list.add(new Worker("Николай", rnd.nextInt(300), "Младший разработчик"));
        workers.list.add(new Worker("Екатерина", rnd.nextInt(300), "Программист"));
        workers.list.add(new Worker("Александр", rnd.nextInt(300), "Дизайнер"));
        workers.list.add(new Worker("Ольга", rnd.nextInt(300), "Менеджер проекта"));

        GridLayout layout = new GridLayout(0, 1);
        model = new DefaultListModel<>();
        workersString = workers.to_String();

        for (Object obj : workersString) {
            model.addElement(obj);
        }

        leftPanel.setLayout(layout);
        rightPanel.setLayout(layout);
        searchLeftPanel.setLayout(layout);
        searchRightPanel.setLayout(layout);
        fieldsLeftPanel.setLayout(layout);
        fieldsRightPanel.setLayout(layout);

        panel.setLayout(new GridLayout(1, 2));
        searchPanel.setLayout(new GridLayout(1, 2));
        fieldsPanel.setLayout(new GridLayout(1, 2));

        searchLeftPanel.add(searchLabel);
        searchRightPanel.add(searchField);

        searchPanel.add(searchLeftPanel);
        searchPanel.add(searchRightPanel);
        leftPanel.add(searchPanel);
        leftPanel.add(searchButton);

        fieldsLeftPanel.add(nameLabel);
        fieldsLeftPanel.add(salaryLabel);
        fieldsLeftPanel.add(positionLabel);
        fieldsRightPanel.add(name);
        fieldsRightPanel.add(salary);
        fieldsRightPanel.add(position);

        fieldsPanel.add(fieldsLeftPanel);
        fieldsPanel.add(fieldsRightPanel);

        leftPanel.add(fieldsPanel);

        JButton addButton = new JButton("Добавить");

        addButton.addActionListener(e -> {
            workers.list.add(new Worker(name.getText(), Integer.parseInt(salary.getText()), position.getText()));
            model.clear();
            workersString = workers.to_String();

            for (Object obj : workersString) {
                model.addElement(obj);
            }
        });

        leftPanel.add(addButton);
        JButton deleteButton = new JButton("Удалить");

        deleteButton.addActionListener(e -> {
            workers.list.remove(jList.getSelectedValue());
            model.clear();
            workersString = workers.to_String();

            for (Object obj : workersString) {
                model.addElement(obj);
            }
        });

        leftPanel.add(deleteButton);

        jList = new JList<>(model);
        rightPanel.add(new JScrollPane(jList));

        JButton filterButton = new JButton("Фильтр");

        filterButton.addActionListener(e -> {
            model.clear();
            List<Worker> items = workers.StemaFilter();

            for (Worker item : items) {
                model.addElement(item);
            }
        });

        rightPanel.add(filterButton);
        JButton clearFilterButton = new JButton("Очистить фильтр");

        clearFilterButton.addActionListener(e -> {
            model.clear();
            workersString = workers.to_String();

            for (Object obj : workersString) {
                model.addElement(obj);
            }
        });

        rightPanel.add(clearFilterButton);

        JButton editButton = new JButton("Изменить сотрудника");

        editButton.addActionListener(e -> {
            Worker editWorker = workers.list.get(jList.getSelectedIndex());
            workers.list.remove(jList.getSelectedValue());

            if (!name.getText().isEmpty()) editWorker.Name = name.getText();
            if (!salary.getText().isEmpty() && Integer.parseInt(salary.getText()) > 0) editWorker.Salary = Integer.parseInt(salary.getText());
            if (!position.getText().isEmpty()) editWorker.Position = position.getText();

            workers.list.add(jList.getSelectedIndex(), new Worker(editWorker.Name, editWorker.Salary, editWorker.Position));

            model.clear();
            workersString = workers.to_String();

            for (Object obj : workersString) {
                model.addElement(obj);
            }
        });

        rightPanel.add(editButton);

        panel.add(leftPanel);
        panel.add(rightPanel);

        searchButton.addActionListener(e -> {
            String searchText = searchField.getText();
            model.clear();

            for (Worker worker : workers.list) {
                if (worker.Name.toLowerCase().contains(searchText.toLowerCase())) {
                    model.addElement(worker);
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