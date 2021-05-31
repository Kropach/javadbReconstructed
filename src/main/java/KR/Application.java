package KR;

import KR.Config.HibernateConfig;
//import KR.Config.SpringConfig;
import KR.UI.ConsoleUI;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.sql.SQLException;

public class Application {
    public static void run() throws SQLException {

//        MainService mainService = new MainService(new DB());
//        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext(
//                "applicationContext.xml"
//        );
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(
                HibernateConfig.class
        );
//        MainService mainService = context.getBean("msBean", MainService.class);
//        context.refresh();
        ConsoleUI consoleUI = context.getBean(ConsoleUI.class);
        consoleUI.showMenu();
        context.close();
    }
}
