package KR.DAO;

import KR.Domain.Path;
import KR.Domain.Station;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import java.sql.*;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

@Component("db")
public class DB implements KR.DAO.Repository {

    private Connection connection;
//    private String url;
//    private String user;
//    private String pass;

    public DB(@Value("${db.url}") String url,
              @Value("${db.user}") String user,
              @Value("${db.pass}")String pass) throws SQLException {
        connection = DriverManager.getConnection(url,
                user, pass);
    }


    @Override
    public Path show(int number){
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(
                    "SELECT * FROM path");
            ResultSet resultSet =preparedStatement.executeQuery();
            resultSet.next();
            for (int i = 1; i < number; i++) {
                resultSet.next();
            }
            int id = resultSet.getInt(1);
            String name = resultSet.getString(2);
            int trainNumber = resultSet.getInt(3);
            preparedStatement = connection.prepareStatement(
                    "SELECT * FROM pathstation where pathid = ? ORDER BY StationOrder");
            preparedStatement.setInt(1, id);
            resultSet =preparedStatement.executeQuery();
            LinkedList<Station> list = new LinkedList<>();
            while (!resultSet.isLast()){
                resultSet.next();
                int stationId = resultSet.getInt(3);
                PreparedStatement preparedStatement1 = connection.prepareStatement(
                        "SELECT * FROM  station WHERE Id = ?"
                );
                preparedStatement1.setInt(1, stationId);
                ResultSet resultSet1 = preparedStatement1.executeQuery();
                resultSet1.next();
                String stationName = resultSet1.getString(2);
                String arrivalTime= resultSet1.getString(3);
                String departureTime= resultSet1.getString(4);
                resultSet1.close();
                list.add(new Station(stationName/*, arrivalTime, departureTime*/));
            }
            resultSet.close();
            return new Path(trainNumber, name, list);
        } catch (SQLException e){
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public List<Path> showAll(){
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(
                    "SELECT * FROM path");
            ResultSet resultSet = preparedStatement.executeQuery();
            List<Path> pathes = new ArrayList<>();
            for (int i = 1; i <= size("path"); i++) {
                pathes.add(show(i));
            }
            return pathes;
        } catch (SQLException e){
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public void addPath(Path path) {
        try {
            int trainNumber = path.getTrainNumber();
            String pathName = path.getPathName();
            LinkedList<Station> stations = path.getStations();
            PreparedStatement preparedStatement = connection.prepareStatement(
                    "INSERT INTO path values (null, ?, ?)");
            preparedStatement.setString(1, pathName);
            preparedStatement.setInt(2, trainNumber);
            preparedStatement.executeUpdate();
            int pathSize = size("path");
            preparedStatement = connection.prepareStatement(
                    "Select id from path");
            ResultSet resultSet = preparedStatement.executeQuery();
            for (int i = 0; i < pathSize; i++) {
                resultSet.next();
            }
            int pathId = resultSet.getInt(1);
            System.out.println(pathId);
            int stationSize = size("station");
            preparedStatement = connection.prepareStatement(
                    "Select id from station");
            resultSet = preparedStatement.executeQuery();
            for (int i = 0; i < stationSize; i++) {
                resultSet.next();
            }
            int stationId = resultSet.getInt(1);
            for (int i = 0; i < stations.size(); i++) {
                stationId++;
                String stationName = stations.get(i).getName();
//                String arrTime = stations.get(i).getArrivalTime();
//                String depTime = stations.get(i).getDepartureTime();
                preparedStatement = connection.prepareStatement(
                        "insert into station values (?, ?, ?, ?)");
                preparedStatement.setInt(1, stationId);
                preparedStatement.setString(2, stationName);
//                preparedStatement.setString(3, arrTime);
//                preparedStatement.setString(4, depTime);
                preparedStatement.executeUpdate();
                preparedStatement = connection.prepareStatement(
                        "insert into pathstation values (null, ?, ?, ?)");
                preparedStatement.setInt(1, pathId);
                preparedStatement.setInt(2, stationId);
                preparedStatement.setInt(3, i+1);
                preparedStatement.executeUpdate();
            }
            preparedStatement.close();
        } catch (SQLException e){
            e.printStackTrace();
        }
    }

    @Override
    public void addStation(int pathNumber, int stationId, String arrTime, String depTime){
//        try{
////            String stationName = station.getName();
////            String arrivalTime = station.getArrivalTime();
////            String departureTime = station.getDepartureTime();
//            Path path = show(pathNumber-1);
//
//            PreparedStatement preparedStatement = connection.prepareStatement(
//                    "Select id from path");
//            ResultSet resultSet = preparedStatement.executeQuery();
//            for (int i = 0; i < pathNumber; i++) {
//                resultSet.next();
//            }
//            int pathId = resultSet.getInt(1);
//            preparedStatement = connection.prepareStatement(
//                    "Select max(stationorder) from pathstation where pathid = ?");
//            preparedStatement.setInt(1, pathId);
//            resultSet = preparedStatement.executeQuery();
//            int order = path.getStations().size();
//            int ord = order+1;
//            preparedStatement = connection.prepareStatement(
//                    "update pathstation set stationorder = ? where pathid = ? and stationorder = ?");
//            preparedStatement.setInt(1, ord);
//            preparedStatement.setInt(2, pathId);
//            preparedStatement.setInt(3, order);
//            preparedStatement.executeUpdate();
//            preparedStatement = connection.prepareStatement(
//                    "insert into station values (null, ?, ?, ?)");
//            preparedStatement.setString(1, stationName);
////            preparedStatement.setString(2, arrivalTime);
////            preparedStatement.setString(3, departureTime);
//            preparedStatement.executeUpdate();
//            int stationSize = size("station");
//            preparedStatement = connection.prepareStatement(
//                    "Select id from station");
//            resultSet = preparedStatement.executeQuery();
//            for (int i = 0; i < stationSize; i++) {
//                resultSet.next();
//            }
//            int stationId = resultSet.getInt(1);
//
//            preparedStatement = connection.prepareStatement(
//                    "insert into pathstation values (null, ?, ?, ?)");
//            preparedStatement.setInt(1, pathId);
//            preparedStatement.setInt(2, stationId);
//            preparedStatement.setInt(3, order);
//            preparedStatement.executeUpdate();
//            preparedStatement.close();
//        } catch (SQLException e){
//            e.printStackTrace();
//        }
    }

    @Override
    public void changePath(int pathNumber, int trainNumber, String pathName){
        try {
            int pathSize = size("path");
            PreparedStatement preparedStatement = connection.prepareStatement(
                    "Select id from path");
            ResultSet resultSet = preparedStatement.executeQuery();
            for (int i = 0; i < pathSize; i++) {
                resultSet.next();
            }
            int pathId = resultSet.getInt(1);
            preparedStatement = connection.prepareStatement(
                    "update path set Name = ?, TrainNumber = ? where Id = ?");
            preparedStatement.setString(1, pathName);
            preparedStatement.setInt(2, trainNumber);
            preparedStatement.setInt(3, pathId);
            preparedStatement.executeUpdate();
            preparedStatement.close();
        } catch (SQLException e){
            e.printStackTrace();
        }
    }

    @Override
    public void changeStation(int pathNumber, int stationNumber,/* String name,*/ String timeA, String timeD){
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(
                    "Select id from path");
            ResultSet resultSet = preparedStatement.executeQuery();
            for (int i = 0; i < pathNumber; i++) {
                resultSet.next();
            }
            int pathId = resultSet.getInt(1);
            preparedStatement = connection.prepareStatement(
                    "select stationid from pathstation where pathid = ?");
            preparedStatement.setInt(1, pathId);
            resultSet = preparedStatement.executeQuery();
            for (int i = 0; i < stationNumber; i++) {
                resultSet.next();
            }
            int stationId = resultSet.getInt(1);
            resultSet.close();
            preparedStatement = connection.prepareStatement(
                    "update station set Name = ?, ArrrivalTime  = ?, DepartureTime = ? where Id = ?");
//            preparedStatement.setString(1, name);
            preparedStatement.setString(2, timeA);
            preparedStatement.setString(3, timeD);
            preparedStatement.setInt(4, stationId);
            preparedStatement.executeUpdate();
            preparedStatement.close();
        } catch (SQLException e){
            e.printStackTrace();
        }
    }

    @Override
    public void removeStation(int pathNumber, int stationNumber){
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(
                    "select id from path");
            ResultSet resultSet = preparedStatement.executeQuery();
            for (int i = 0; i < pathNumber; i++) {
                resultSet.next();
            }
            int pathId = resultSet.getInt(1);
            preparedStatement = connection.prepareStatement(
                    "select id from pathstation where PathId = ?");
            preparedStatement.setInt(1, pathId);
            resultSet = preparedStatement.executeQuery();
            for (int i = 0; i < stationNumber; i++) {
                resultSet.next();
            }
            int stationId = resultSet.getInt(1);
            resultSet.close();
            preparedStatement = connection.prepareStatement(
                    "delete from pathstation where id = ?");
            preparedStatement.setInt(1, stationId);
            preparedStatement.executeUpdate();
            preparedStatement = connection.prepareStatement(
                    "delete from station where id = ?");
            preparedStatement.setInt(1, stationId);
            preparedStatement.executeUpdate();
            preparedStatement.close();
        } catch (SQLException e){
            e.printStackTrace();
        }
    }

    @Override
    public void removePath(int pathNumber){
        try{
            PreparedStatement preparedStatement = connection.prepareStatement(
                    "select id from path");
            ResultSet resultSet = preparedStatement.executeQuery();
            for (int i = 0; i <= pathNumber; i++) {
                resultSet.next();
            }
            int pathId = resultSet.getInt(1);
            preparedStatement = connection.prepareStatement(
                    "select StationId from pathstation where pathid = ?");
            preparedStatement.setInt(1, pathId);
            resultSet = preparedStatement.executeQuery();
            preparedStatement = connection.prepareStatement(
                    "delete from pathstation where pathid = ?");
            preparedStatement.setInt(1, pathId);
            preparedStatement.executeUpdate();
            while (!resultSet.isLast()){
                resultSet.next();
                preparedStatement = connection.prepareStatement(
                        "delete from station where id = ?");
                preparedStatement.setInt(1, resultSet.getInt(1));
                preparedStatement.executeUpdate();
                System.out.println(resultSet.getInt(1));
            }
            resultSet.close();
            preparedStatement = connection.prepareStatement(
                    "delete from path where id = ?");
            System.out.println(pathId);
            preparedStatement.setInt(1, pathId);
            preparedStatement.executeUpdate();
            preparedStatement.close();
        } catch (SQLException e){
            e.printStackTrace();
        }
    }

    @Override
    public void createStation(String name) {

    }

    private int size(String schema){
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(
                    "SELECT COUNT(*) FROM " + schema);
            ResultSet resultSet = preparedStatement.executeQuery();
            resultSet.next();
            preparedStatement.close();
            return (int)resultSet.getLong(1);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }

//    public String getUrl() {
//        return url;
//    }
//
//    public void setUrl(String url) {
//        this.url = url;
//    }
//
//    public String getUser() {
//        return user;
//    }
//
//    public void setUser(String user) {
//        this.user = user;
//    }
//
//    public String getPass() {
//        return pass;
//    }
//
//    public void setPass(String pass) {
//        this.pass = pass;
//    }
}
