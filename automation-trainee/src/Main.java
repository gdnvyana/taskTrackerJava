import java.util.Scanner;

public class Main {

    // Константа для хранения максимального количества задач
    private static final int MAX_TASKS = 10;

    // Константы для пунктов меню
    private static final int MENU_ADD_TASK = 1;
    private static final int MENU_SHOW_TASKS = 2;
    private static final int MENU_EXIT = 0;

    public static void main(String[] args) {

        // Создаем Scanner для ввода данных
        Scanner scanner = new Scanner(System.in);

        // Приветствие и знакомство с пользователем
        String userName = greetUser(scanner);

        // Создаем массив для хранения задач
        Task[] tasks = new Task[MAX_TASKS];
        int taskCount = 0;

        // Основной цикл программы
        runMainLoop(scanner, userName, tasks, taskCount);

        // Закрываем Scanner
        scanner.close();
    }

    //    Приветствие пользователя и просим ввести имя
    private static String greetUser(Scanner scanner) {
        System.out.println("Hi! I'm a task tracker.");
        System.out.print("Enter your name: ");
        String userName = scanner.nextLine();
        System.out.println(userName + ", Welcome to the task tracker!");
        return userName;
    }

    //    Основной цикл программы
    private static void runMainLoop(Scanner scanner, String userName, Task[] tasks, int taskCount) {
        int userChoice;

        do {
            // Показываем меню пользователю и получаем его выбор
            userChoice = showMenuAndGetChoice(scanner, userName);

            // Обрабатываем выбор пользователя
            taskCount = processUserChoice(scanner, userChoice, tasks, taskCount, userName);

        } while (userChoice != MENU_EXIT);
    }

    //    Отображаем меню и возвращаем выбор пользователя
    private static int showMenuAndGetChoice(Scanner scanner, String userName) {
        System.out.println("\n" + userName + ", select one of the menu options:");
        System.out.println(MENU_ADD_TASK + " - Enter a task");
        System.out.println(MENU_SHOW_TASKS + " - Display information about tasks");
        System.out.println(MENU_EXIT + " - Exit");
        System.out.print("Enter your choice: ");

        int choice = scanner.nextInt();
        scanner.nextLine();
        return choice;
    }

    //    Обрабатываем выбор пользователя и возвращаем обновленное количество задач
    private static int processUserChoice(Scanner scanner, int userChoice, Task[] tasks, int taskCount, String userName) {
        return switch (userChoice) {
            case MENU_ADD_TASK -> addNewTask(scanner, tasks, taskCount, userName);
            case MENU_SHOW_TASKS -> {
                showAllTasks(tasks, taskCount);
                yield taskCount;
            }
            case MENU_EXIT -> {
                exitProgram(userName);
                yield taskCount;
            }
            default -> {
                showInvalidChoiceMessage();
                yield taskCount;
            }
        };
    }

    //    Добавляет новую задачу
    private static int addNewTask(Scanner scanner, Task[] tasks, int taskCount, String userName) {
        // Для проверки места для новой задачи
        if (isTaskStorageFull(taskCount)) {
            showStorageFullMessage();
            return taskCount;
        }

        // Получаем данные о задаче от пользователя
        System.out.println("\n--- Entering a new task ---");

        String taskName = getTaskName(scanner);
        String taskDescription = getTaskDescription(scanner);
        int taskPriority = getTaskPriority(scanner);

        // Создаем новую задачу и добавляем в массив
        Task newTask = new Task(taskName, taskDescription, taskPriority);
        tasks[taskCount] = newTask;

        // Показываем сообщение об успешном добавлении
        showTaskAddedSuccessMessage(userName);

        // Увеличиваем счетчик задач
        return taskCount + 1;
    }

    //    Проверяет, заполнено ли хранилище задач
    private static boolean isTaskStorageFull(int taskCount) {
        return taskCount >= MAX_TASKS;
    }

    //    Показывает сообщение о заполненном хранилище
    private static void showStorageFullMessage() {
        System.out.println("The task archive is full! Cannot add more tasks.");
    }

    //    Получает название задачи от пользователя
    private static String getTaskName(Scanner scanner) {
        System.out.print("Let's go! Enter the name of your task: ");
        return scanner.nextLine();
    }

    //    Получает описание задачи от пользователя
    private static String getTaskDescription(Scanner scanner) {
        System.out.print("Now describe your task: ");
        return scanner.nextLine();
    }

    //    Получает приоритет задачи от пользователя
    private static int getTaskPriority(Scanner scanner) {
        System.out.print("What is the priority of your task (Please enter a number between 1 and 10): ");
        int priority = scanner.nextInt();
        scanner.nextLine();
        return priority;
    }

    //    Показывает сообщение об успешном добавлении задачи
    private static void showTaskAddedSuccessMessage(String userName) {
        System.out.println(userName + ", Great job! Your task has been successfully added.");
    }

    //    Отображает все добавленные задачи
    private static void showAllTasks(Task[] tasks, int taskCount) {
        if (hasNoTasks(taskCount)) {
            showNoTasksMessage();
            return;
        }

        displayTaskListHeader();
        for (int i = 0; i < taskCount; i++) {
            tasks[i].displayInfo(i + 1); // Используем метод displayInfo из класса Task
        }
        displayTaskListFooter(taskCount);
    }

    //    Проверяет, есть ли добавленные задачи
    private static boolean hasNoTasks(int taskCount) {
        return taskCount == 0;
    }

    //    Показывает сообщение об отсутствии задач
    private static void showNoTasksMessage() {
        System.out.println("\nNo tasks added yet.");
    }

    //    Отображает заголовок списка задач
    private static void displayTaskListHeader() {
        System.out.println("\n~~~~~ TASK LIST ~~~~~");
    }

    //    Отображает итоговую информацию о списке задач
    private static void displayTaskListFooter(int taskCount) {
        System.out.println("\nTotal tasks: " + taskCount);
    }

    //    Завершает программу
    private static void exitProgram(String userName) {
        System.out.println("\nGoodbye, " + userName + "! Have a nice day!");
    }

    //    Показывает сообщение о неверном выборе
    private static void showInvalidChoiceMessage() {
        System.out.println("\nError! Please choose " + MENU_ADD_TASK + ", " + MENU_SHOW_TASKS + ", or " + MENU_EXIT + ".");
    }
}