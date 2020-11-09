package Spring;

import java.io.IOException;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.context.ApplicationContext;
import java.util.List;
import java.util.logging.FileHandler;
import java.util.logging.Handler;
import java.util.logging.SimpleFormatter;

class Launcher {

    static {

        System.setProperty("java.util.logging.SimpleFormatter.format",
                "%1$tF %1$tT [%4$-7s] %3$s - %5$s %n");

    }

    private static org.apache.log4j.Logger log4j = org.apache.log4j.Logger.getLogger(Class.class);

    public static void main(String[] args) throws IOException {
        Handler fileHandler = new FileHandler("logging.log", 100 * 1024, 3, true);
        fileHandler.setFormatter(new SimpleFormatter());
        log4j.info("Start Spring");

        try {
            ApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml"); // Загрузка файла с биновами

            ComputerDAO computerDAO = (ComputerDAO) context.getBean("computerdescription"); // Загрузка бина доступа к таблице клиентов 

            computerDAO.deleteAll(); // Удаление всех записей

            Computer computer = new Computer("Neo", "AMD", 6); // Создание нового объекта таблицы 
            computerDAO.insert(computer); // Вставить новый объект (запись) в таблицу 

            computerDAO.insert(new Computer("PBA", "Intel", 8)); // Вставить новый объект (запись) в таблицу
            computerDAO.insert(new Computer("Lenovo", "Silicon", 4)); // Вставить новый объект (запись) в таблицу 

            Computer person1 = computerDAO.findByCores(6); // Поиск записи по заданному параметру
            System.out.println(person1 != null ? person1 : "Нет данных"); // Вывод на экран найденной записи

            computerDAO.deleteByProcessor("tel"); // Удаление записей по фрагменту 
            computerDAO.delete("Neo", "AMD"); // Удаление записи 

            List<Computer> computers = computerDAO.findByProducer("ovo"); // Поиск записей по фрагменту 
            System.out.println(computers != null ? computers : "Нет данных");

            computerDAO.append("HP", "Intel", 6); // Добавлние записей
            computerDAO.append("Apple", "Intel", 6);
            computerDAO.append("Lenovo", "Silicon", 4);
            computerDAO.append("Neo", "VIA", 8);

            computerDAO.update("Neo", "Apple"); // Изменение записей в таблице

            System.out.println("Данные в таблице БД:");

            List<Computer> list = computerDAO.selectAll();
            for (Computer myComputer : list) {
                System.out.println(myComputer.getFirstName() + " " + myComputer.getProcessor() + " " + myComputer.getCores());
            }

            System.out.println("Вывод записей с производителем NEO и процессором VIA:");

            list = computerDAO.select("Neo", "VIA");
            for (Computer myComputer : list) {
                System.out.println(myComputer.getFirstName() + " " + myComputer.getProcessor() + "");
            }

        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Error!");
        }
        log4j.info("End Spring");
        org.apache.log4j.LogManager.shutdown();
    }

    public static org.apache.log4j.Logger getLog4j() {
        return log4j;
    }

    /**
     * @param aLog4j the log4j to set
     */
    public static void setLog4j(org.apache.log4j.Logger aLog4j) {
        log4j = aLog4j;
    }
}
