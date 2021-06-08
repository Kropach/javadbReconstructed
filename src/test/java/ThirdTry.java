import KR.Config.Config;
import KR.DAO.Hibernate;
import KR.Domain.Path;
import KR.Services.MainService;
import KR.Services.MainService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.junit4.SpringRunner;

import java.sql.SQLException;

import static org.junit.jupiter.api.Assertions.*;
//@RunWith(SpringJUnit4ClassRunner.class)
//@ContextConfiguration(classes = Config.class)
public class ThirdTry {


    private MainService mainService;

    public ThirdTry(MainService mainService) {
        this.mainService = mainService;
    }

    @Test
    void getAll() throws SQLException {
//        Assertions.assertEquals(1,mainService.showAll().size());
    }

//    @Test
//    @DisplayName("Get group test")
//    void getGroup() {
//        Assertions.assertEquals(1,controller.getGroup("Рабочая").size());
//    }
//
//    @Test
//    @DisplayName("Add child test")
//    void addChild() {
////        Controller controller = new Controller();
//        String name = "ымя";
//        boolean sex = false;
//        int age = 9;
//        String group = "Рабочая";
//        controller.addChild(name,sex,age,group);
//        Assertions.assertEquals(2,controller.getGroup("Рабочая").size());
//        Assertions.assertTrue(controller
//                .getGroup(group)
//                .stream()
//                .map(c -> c.getName())
//                .filter(s -> s.equals(name))
//                .count() == 1);
//    }
//
//    @Test
//    @DisplayName("Delete child test")
//    void deleteById() {
////        Controller controller = new Controller();
//        controller.deleteById(1);
//        Assertions.assertTrue(controller.getAll().isEmpty());
//    }
//
//    @Test
//    @DisplayName("Delete group test")
//    void deleteGroup() {
////        Controller controller = new Controller();
//        controller.deleteGroup("Рабочая");
//        Assertions.assertTrue(controller.getGroup("Рабочая").isEmpty());
//    }
}