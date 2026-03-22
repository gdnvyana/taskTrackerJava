public class Task {

    // Создаем поля класса
    private String name;
    private String description;
    private int priority;

    // Создаем конструктор для создания экземпляра задачи
    public Task(String name, String description, int priority) {
        this.name = name;
        this.description = description;
        this.priority = priority;
    }

    // Создаем геттеры для доступа к полям
    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public int getPriority() {
        return priority;
    }

    // Создаем сеттеры для изменения полей
    public void setName(String name) {
        this.name = name;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setPriority(int priority) {
        this.priority = priority;
    }

    // Создаем метод для отображения информации о задаче
    public void displayInfo(int taskNumber) {
        System.out.println("\nTask #" + taskNumber);
        System.out.println("  Name: " + name);
        System.out.println("  Description: " + description);
        System.out.println("  Priority: " + priority);
    }

    @Override
    public String toString() {
        return "Task{" +
                "name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", priority=" + priority +
                '}';
    }
}