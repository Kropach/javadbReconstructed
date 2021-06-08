package KR.Controllers;

import KR.Services.MainService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.sql.SQLException;

@RestController
@RequestMapping("/station")
public class StationController {
    private final MainService mainService;
    @Autowired
    public StationController(MainService mainService) {
        this.mainService = mainService;
    }


    @PutMapping(value = "/{pathStation}")
    public void changeStation(@RequestParam("pathId") int pathId, @RequestParam("stationId") int stationId,
                              @RequestParam("timeA") String timeA, @RequestParam("timeD") String timeD) throws SQLException {
        mainService.changeStation(pathId, stationId, timeA, timeD);
    }

    @PostMapping(value = "/{name}")
    public void createStation(@RequestParam("name") String name){
        mainService.createStation(name);
    }

}
