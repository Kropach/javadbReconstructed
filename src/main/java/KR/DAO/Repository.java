package KR.DAO;
import KR.Domain.*;

import java.sql.SQLException;
import java.util.List;

public interface Repository<P, S> {
    P show(int number) throws SQLException;
    List<P> showAll() throws SQLException;
    void addPath(Path path) throws SQLException;
    void addStation(int pathNumber, int stationId, String arrTime, String depTime) throws SQLException;
    void changePath(int pathNumber, int trainNumber, String pathName) throws SQLException;
    void changeStation(int pathNumber, int stationNumber, String timeA, String timeD) throws SQLException;
    void removeStation(int pathNumber, int stationNumber) throws SQLException;
    void removePath(int pathNumber) throws SQLException;
    void createStation(String name);
}
