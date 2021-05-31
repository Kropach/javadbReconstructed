package KR.Domain;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.io.Serializable;
import java.util.LinkedList;


@Entity
@Table(name = "path")
//@Embeddable
public class Path implements Serializable {
    @Id
    @Column(name = "Id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(name = "TrainNumber")
    private int trainNumber;
    @Column(name = "PathName")
    private String pathName;
    @Transient
//    @JsonIgnore
    private transient LinkedList<Station> stations = new LinkedList<>();

    public Path() {
    }

    public Path(int trainNumber, String pathName) {
        this.trainNumber = trainNumber;
        this.pathName = pathName;
    }

    public Path(int trainNumber, String pathName, LinkedList<Station> stations) {
        this.trainNumber = trainNumber;
        this.pathName = pathName;
        this.stations = stations;
    }

    public LinkedList<Station> getStations() {
        return stations;
    }

    public void setStations(LinkedList stations) {
        this.stations = stations;
    }

    @Override
    public String toString() {
        return "Path{" +
                "trainNumber=" + trainNumber +
                ", pathName='" + pathName + '\'' +
                ", stations=" + stations +
                '}' + "\n";
    }

    public int getTrainNumber() {
        return trainNumber;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setTrainNumber(int trainNumber) {
        this.trainNumber = trainNumber;
    }

    public String getPathName() {
        return pathName;
    }

    public void setPathName(String pathName) {
        this.pathName = pathName;
    }

}
