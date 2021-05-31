package KR.Services;

import KR.DAO.Repository;
import KR.Domain.Path;
import KR.Domain.Station;
import KR.Domain.Timetable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.SQLException;
import java.util.List;
//@Component("mainService")
@Service
public class MainService {
    private Repository<Path, Station> repository;
    @Autowired
    public MainService(@Qualifier("hibernate") Repository<Path, Station> repository) {
        this.repository = repository;
    }

    @Transactional
    public List<Path> showAll() throws SQLException {
        return repository.showAll();
    }
    @Transactional
    public Path show(int number) throws SQLException {
        return repository.show(number);
    }
    @Transactional
    public void addPath(Path path) throws SQLException {
        repository.addPath(path);
    }
    @Transactional
    public void addStation(int pathNumber, int stationId, String arrTime, String depTime) throws SQLException {
        repository.addStation(pathNumber, stationId, arrTime, depTime);
    }
    @Transactional
    public void changeStation(int pathNumber, int stationNumber, String timeA, String timeD) throws SQLException {
        repository.changeStation(pathNumber, stationNumber, timeA, timeD);
    }
    @Transactional
    public void changePath(int pathNumber, int trainNumber, String pathName) throws SQLException {
        repository.changePath(pathNumber, trainNumber, pathName);
    }
    @Transactional
    public void removeStation(int pathNumber, int stationNumber) throws SQLException {
        repository.removeStation(pathNumber, stationNumber);
    }
    @Transactional
    public void removePath(int pathNumber) throws SQLException {
        repository.removePath(pathNumber);
    }
    @Transactional
    public void createStation(String name){
        repository.createStation(name);
    }

}

