package KR.DAO;

import KR.Domain.Path;
import KR.Domain.PathStation;
import KR.Domain.Station;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

@Repository
public class Hibernate implements KR.DAO.Repository{
    private SessionFactory sessionFactory;

    @Autowired
    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }
    private Session getSession(){
        return sessionFactory.getCurrentSession();
    }

    @Override
    public Path show(final int id)  {
        Session session = getSession();

//        Transaction transaction = session.beginTransaction();
//        List<Path> pathes = showAll();
//        return pathes.stream().filter(path -> path.getId() == id).findAny().orElse(null);
        Path path = (Path)session.createQuery("from Path where id = " + id).getSingleResult();
//        transaction.commit();
//        return (Path)session.createQuery("from Path where id = " + id).getSingleResult();
        return path;
    }

    @Override
    public List<Path> showAll(){
        Session session = getSession();
//        Transaction transaction = session.beginTransaction();
        List<Path> pathes = session.createQuery("from Path").list();
        for (int i = 0; i < pathes.size(); i++) {
            List<PathStation> pathStations = session.createQuery("from PathStation where pathId = "
                    + pathes.get(i).getId() + "order by arrivalTime").list();
            for (int j = 0; j < pathStations.size(); j++) {
                Station station = (Station) session.createQuery("from Station where id = "
                        +pathStations.get(j).getStationId()).getSingleResult();
                pathes.get(i).getStations().add(station);
            }
        }
        //через fetch как-то или сложный запрос
//        List<PathStation> temp = session.createQuery("from PathStation").list();
//        List<Path> pathes = new ArrayList<>();
//        for (PathStation ps : temp)
//            pathes.add(ps.getPath());
//        transaction.commit();
        return pathes;
    }

    @Override
    public void addPath(Path path){
        Session session = getSession();
//        Transaction transaction = session.beginTransaction();
        session.save(path);
//        transaction.commit();
    }

    @Override
    public void addStation(int pathNumber, int stationId, String arrTime, String depTime)  {
        Session session = getSession();
        session.save(new PathStation(pathNumber, stationId, arrTime, depTime));
    }

    @Override
    public void changePath(int pathNumber, int trainNumber, String pathName) {
        Session session = getSession();
        Path path = show(pathNumber);
        path.setTrainNumber(trainNumber);
        path.setPathName(pathName);
        session.update(path);
    }

    @Override
    public void changeStation(int pathNumber, int stationNumber, String timeA, String timeD) {
        Session session = getSession();
        List<PathStation> stations = session.createQuery("from PathStation where pathId = " + pathNumber).list();
        PathStation pS = stations.get(stationNumber-1);
        pS.setArrivalTime(timeA);
        pS.setDepartureTime(timeD);
        session.update(pS);
    }

    @Override
    public void removeStation(int pathNumber, int stationNumber) {
        Session session = getSession();
        List<PathStation> stations = session.createQuery("from PathStation where pathId = " + pathNumber).list();
        PathStation pS = stations.get(stationNumber-1);
        session.delete(pS);
    }

    @Override
    public void removePath(int pathNumber) {
        Session session = getSession();
        List<PathStation> stations = session.createQuery("from PathStation where pathId = " + pathNumber).list();
        for (PathStation pS:
             stations) {
            session.delete(pS);
        }
        Path path = show(pathNumber);
        session.delete(path);
    }

    @Override
    public void createStation(String name) {
        Session session = getSession();
        session.save(new Station(name));
    }
}
