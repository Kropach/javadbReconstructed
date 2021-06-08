package KR.Controllers;


import KR.Domain.Path;
import KR.Domain.PathStation;
import KR.Domain.Station;
import KR.Services.MainService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

@RestController
@RequestMapping("/path")
public class PathController {
    private final MainService mainService;
    @Autowired
    public PathController(MainService mainService) {
        this.mainService = mainService;
    }

//    @RequestMapping(value = "/id/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    @GetMapping(value = "/{id}")
    public ResponseEntity<Path> show(@PathVariable("id") int id) throws SQLException {
        return new ResponseEntity<>(mainService.show(id), HttpStatus.OK);
    }
    @GetMapping()
    public ResponseEntity<List<Path>> showAll() throws SQLException {
        List<Path> pathes = mainService.showAll();
        return pathes != null && !pathes.isEmpty()
                ? new ResponseEntity<>(pathes, HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
    @PostMapping(value = "/{newPath}")
    public void addPath(@RequestParam("trainNumber") int trainNumber, @RequestParam("pathName") String pathName) throws SQLException {
        mainService.addPath(new Path(trainNumber, pathName, new LinkedList<Station>()));
    }



    @PutMapping(value = "/{pathId}")
    public void changePath(@RequestParam("pathId") int pathId, @RequestParam("trainNumber") int trainNumber,
                           @RequestParam("pathName") String pathName) throws SQLException {
        mainService.changePath(pathId, trainNumber, pathName);
    }



    @DeleteMapping(value = "/{pathId}")
    public void removePath(@RequestParam("pathId") int pathId) throws SQLException{
        mainService.removePath(pathId);
    }
}
