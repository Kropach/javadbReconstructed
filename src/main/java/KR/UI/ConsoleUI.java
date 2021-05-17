package KR.UI;

import KR.DAO.Repository;
import KR.Domain.Path;
import KR.Domain.Station;
import KR.Domain.Timetable;
import KR.Services.MainService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.sql.SQLException;
import java.util.LinkedList;
import java.util.Scanner;
@Component("consoleUI")
public class ConsoleUI {
    Repository<Path, Station> repository;
    MainService service;
    private int command;
    private Scanner scanner = new Scanner(System.in);
    @Autowired
    public ConsoleUI(MainService service) {
        this.service = service;
    }

    public void showMenu() throws SQLException {
        while (true){
            System.out.println("1 - Show all pathes");
            System.out.println("2 - Show defined path by index");
            System.out.println("3 - Add new path in timetable");
            System.out.println("4 - Add new station to path by path index");
            System.out.println("5 - Change station in path");
            System.out.println("6 - Change path by index");
            System.out.println("7 - Remove station from path by index");
            System.out.println("8 - Remove path from timetable");
            System.out.println("9 - Create a new station (new!)");
            System.out.println("0 - Exit");
            command = scanner.nextInt();
            switch (command){
                case 1:
                    System.out.println(service.showAll().toString());
                    break;
                case 2:
                    System.out.println("Input path number");
                    System.out.println(service.show(scanner.nextInt()).toString());
//                    try {
//                        System.out.println(service.show(scanner.nextInt()).toString());
//                    } catch (Exception e) {
//                        System.out.println("Wrong number");
//                    }
                    break;
                case 3:
                    System.out.println("Input train number, path name");
                    Path path = new Path(scanner.nextInt(), scanner.next()/*, new LinkedList<Station>()*/);
//                    System.out.println("Input start station(name, arr and dep time)");
//                    Station start = new Station(scanner.next(),scanner.next(),scanner.next());//start
//                    System.out.println("Input finish station(name, arr and dep time)");
//                    Station finish = new Station(scanner.next(),scanner.next(),scanner.next());//finish
//                    path.getStations().add(start);
//                    path.getStations().add(finish);
                    service.addPath(path);
                    break;
                case 4:
                    System.out.println("Input path id in which station will be added");
                    int number = scanner.nextInt();
                    System.out.println("Input station(id, arr and dep time)");
                    int stationId = scanner.nextInt();
                    service.addStation(number, stationId, scanner.next(), scanner.next());
//                    try {
//                        service.addStation(number, station);
//                    } catch (Exception e) {
//                        System.out.println("Wrong number of path");;
//                    }
                    break;
                case 5:
                    System.out.println("Input path and station id and new station arr and dep time");
                    service.changeStation(scanner.nextInt(), scanner.nextInt(), scanner.next(), scanner.next());
//                    try {
//
//                    } catch (Exception e) {
//                        System.out.println("Wrong path or station number");
//                    }
                    break;
                case 6:
                    System.out.println("Input path id, new path number and name");
                    try {
                        service.changePath(scanner.nextInt(), scanner.nextInt(), scanner.next());
                    } catch (Exception e) {
                        System.out.println("Wrong path id");
                    }
                    break;
                case 7:
                    System.out.println("Input path id and station number");
                    service.removeStation(scanner.nextInt(), scanner.nextInt());
//                    try {
//
//                    } catch (Exception e) {
//                        System.out.println("Wrong path or station number");
//                    }
                    break;
                case 8:
                    System.out.println("Input path number");
                    try {
                        service.removePath(scanner.nextInt());
                    } catch (Exception e) {
                        System.out.println("Wrong path number");
                    }
                    break;
                case 9:
                    System.out.println("Input a station name");
                    try{
                        service.createStation(scanner.next());
                    } catch (Exception e){
                        System.out.println("Wrong station name");
                    }
                    break;
                case 0:
                    return;
            }
        }
    }
}
