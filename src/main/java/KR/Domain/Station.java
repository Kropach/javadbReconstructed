package KR.Domain;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "station")
//@Embeddable
public class Station implements Serializable {
    @Id
    @Column(name = "Id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(name = "Name")
    private String name;
//    @Column(name = "ArrrivalTime")
//    private String arrivalTime;
//    @Column(name = "DepartureTime")
//    private  String departureTime;

    public Station() {
    }

    public Station(String name/*, String arrivalTime, String departureTime*/) {
        this.name = name;
//        this.arrivalTime = arrivalTime;
//        this.departureTime = departureTime;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

//    public String getArrivalTime() {
//        return arrivalTime;
//    }
//
//    public void setArrivalTime(String arrivalTime) {
//        this.arrivalTime = arrivalTime;
//    }
//
//    public String getDepartureTime() {
//        return departureTime;
//    }
//
//    public void setDepartureTime(String departureTime) {
//        this.departureTime = departureTime;
//    }

    @Override
    public String toString() {
        return "Station{" +
                "name='" + name + '\'' +
//                ", arrivalTime='" + arrivalTime + '\'' +
//                ", departureTime='" + departureTime + '\'' +
                '}';
    }
}
