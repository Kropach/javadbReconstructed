//package KR.DAO;
//
//import KR.Domain.Path;
//import KR.Domain.Station;
//import KR.Domain.Timetable;
//
//import java.util.ArrayList;
//import java.util.LinkedList;
//import java.util.List;
//import java.util.Scanner;
//
//public class HCRep implements Repository<Path, Station> {
//    private static HCRep instance;
//    private Timetable timetable = new Timetable();
//    private Scanner scanner = new Scanner(System.in);
//
//    private HCRep(){
//        init();
//    }
//
//    private void init() {
//        timetable.getPathes().add(new Path(10, "R-E", new LinkedList<Station>()));
//        timetable.getPathes().get(0).getStations().add(new Station("T", "12:10", "12:13"));
//        timetable.getPathes().get(0).getStations().add(new Station("H", "12:34", "12:36"));
//        timetable.getPathes().get(0).getStations().add(new Station("Y", "13:00", "13:22"));
//    }
//
//
//
//    public static HCRep getInstance(){
//        if (instance == null){
//            instance = new HCRep();
//        }
//        return instance;
//    }
//    @Override
//    public Path show(int number) {
//        return timetable.getPathes().get(number);
//    }
//
//    @Override
//    public List<Path> showAll() {
//        return timetable.getPathes();
//    }
//
//    @Override
//    public void addPath(Path path) {
//        timetable.getPathes().add(path);
//    }
//
//    @Override
//    public void addStation(int pathNumber, Station station) {
//        int size = timetable.getPathes().get(pathNumber).getStations().size();
//        timetable.getPathes().get(pathNumber).getStations().add(size-1,station);
//    }
//
//    @Override
//    public void changePath(int pathNumber, int trainNumber, String pathName) {
//        timetable.getPathes().get(pathNumber).setTrainNumber(trainNumber);
//        timetable.getPathes().get(pathNumber).setPathName(pathName);
//    }
//
//    @Override
//    public void changeStation(int pathNumber, int stationNumber, String name, String timeA, String timeD) {
//        timetable.getPathes().get(pathNumber).getStations().get(stationNumber).setName(name);
//        timetable.getPathes().get(pathNumber).getStations().get(stationNumber).setArrivalTime(timeA);
//        timetable.getPathes().get(pathNumber).getStations().get(stationNumber).setDepartureTime(timeD);
//    }
//
//    @Override
//    public void removeStation(int pathNumber, int stationNumber) {
//        timetable.getPathes().get(pathNumber).getStations().remove(stationNumber);
//    }
//
//    @Override
//    public void removePath(int pathNumber) {
//        timetable.getPathes().remove(pathNumber);
//    }
//}
