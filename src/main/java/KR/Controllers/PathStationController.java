package KR.Controllers;

import KR.Services.MainService;
import org.springframework.web.bind.annotation.*;

import java.sql.SQLException;
@RestController
@RequestMapping("/pathstation")
public class PathStationController {
    private final MainService mainService;

    public PathStationController(MainService mainService) {
        this.mainService = mainService;
    }

    @PostMapping(value = "/{station}")
    public void addStation(@RequestParam("pathId") int pathId, @RequestParam("stationId") int stationId,
                           @RequestParam("arrTime") String arrTime, @RequestParam("depTime") String depTime) throws SQLException {
        mainService.addStation(pathId, stationId, arrTime, depTime);
    }
    @DeleteMapping(value = "/{pathStation}")
    public void removeStation(@RequestParam("pathId") int pathId, @RequestParam("stationId") int stationId) throws SQLException{
        mainService.removeStation(pathId, stationId);
    }
}
