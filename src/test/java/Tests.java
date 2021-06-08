//import KR.Config.Config;
//import KR.Controllers.PathController;
//import KR.Controllers.PathStationController;
//import KR.Controllers.StationController;
//import KR.Domain.Path;
//import KR.Domain.PathStation;
//import KR.Domain.Station;
//import org.junit.Test;
//import org.junit.jupiter.api.Assertions;
//import org.junit.jupiter.api.MethodOrderer;
//import org.junit.jupiter.api.Order;
//import org.junit.jupiter.api.TestMethodOrder;
//import org.junit.jupiter.api.extension.ExtendWith;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.test.annotation.DirtiesContext;
//import org.springframework.test.context.ContextConfiguration;
//import org.springframework.test.context.junit.jupiter.SpringExtension;
//import org.springframework.test.context.web.WebAppConfiguration;
//
//import java.sql.SQLException;
//import java.util.ArrayList;
//import java.util.LinkedList;
//import java.util.List;
//
//@ExtendWith(SpringExtension.class)
//@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
//@ContextConfiguration(classes = Config.class)
//@WebAppConfiguration
//@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
//public class Tests {
//    @Autowired
//    private PathController pathController;
//    @Autowired
//    private StationController stationController;
//    @Autowired
//    private PathStationController psController;
//    private final Path path = new Path(902, "ADJ-JDA", new LinkedList<Station>());
//    private final Station station = new Station("ADJ");
//    private final PathStation ps = new PathStation(1, 1, "23", "24");
//
//
//    @Test
//    @Order(1)
//    public void createPath() throws SQLException {
//        pathController.addPath(path.getTrainNumber(), path.getPathName());
//        Path path1 = pathController.show(1).getBody();
//        Assertions.assertEquals(path, path1);
//    }
//}
