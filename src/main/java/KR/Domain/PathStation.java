package KR.Domain;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "pathstation")
public class PathStation implements Serializable {
    @Id
    @Column(name = "Id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
//    @ManyToOne
//    @JoinColumn(name = "Id")
    @Column(name = "PathId")
    private int pathId;
//    private Path path;
//    @ManyToOne
    @JoinColumn(name = "Id")
    @Column(name = "StationId")
    private int stationId;

//    @Column(name = "StationOrder")
//    private int stationOrder;

    @Column(name = "ArrivalTime")
    private String arrivalTime;
    @Column(name = "DepartureTime")
    private  String departureTime;

    public PathStation() {
    }


    public PathStation(int pathId, int stationId, String arrivalTime, String departureTime) {
        this.pathId = pathId;
        this.stationId = stationId;
        this.arrivalTime = arrivalTime;
        this.departureTime = departureTime;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getArrivalTime() {
        return arrivalTime;
    }

    public void setArrivalTime(String arrivalTime) {
        this.arrivalTime = arrivalTime;
    }

    public String getDepartureTime() {
        return departureTime;
    }

    public void setDepartureTime(String departureTime) {
        this.departureTime = departureTime;
    }

    //    public Path getPath() {
//        return path;
//    }
//
//    public void setPath(Path path) {
//        this.path = path;
//    }
//
    public int getPathId() {
        return pathId;
    }

    public void setPathId(int pathId) {
        this.pathId = pathId;
    }

    public int getStationId() {
        return stationId;
    }

    public void setStationId(int stationId) {
        this.stationId = stationId;
    }

//    public int getStationOrder() {
//        return stationOrder;
//    }

//    public void setStationOrder(int stationOrder) {
//        this.stationOrder = stationOrder;
//    }

    @Override
    public String toString() {
        return "PathStation{" +
                "pathId=" + pathId +
                ", stationId=" + stationId +
//                ", stationOrder=" + stationOrder +
                ", arrivalTime='" + arrivalTime + '\'' +
                ", departureTime='" + departureTime + '\'' +
                '}';
    }
}
