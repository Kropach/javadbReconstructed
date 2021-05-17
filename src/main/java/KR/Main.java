package KR;

import KR.DAO.DB;
//import KR.DAO.HCRep;
import KR.Services.MainService;
import KR.UI.ConsoleUI;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.sql.SQLException;

public class Main {
    public static void main(String[] args) throws SQLException {
        Application.run();
    }
}
