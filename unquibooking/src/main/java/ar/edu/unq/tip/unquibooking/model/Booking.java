package ar.edu.unq.tip.unquibooking.model;

import java.time.LocalDate;

import javax.persistence.*;

@Entity
@Table(name= "booking")
public class Booking {
    
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    @Column(unique=true, nullable=false)
    private Long id;

    @ManyToOne
    private Desk desk;

    @Column
    private LocalDate date;
    
    @Column
    private Integer startTime;

    @Column
    private Integer endTime;

    public void setDate(LocalDate date){
        this.date = date;
    }

    public LocalDate getDate(){
        return date;
    }

    public void setStartTime(Integer time){
        this.startTime = time;
    }

    public Integer getStartTime(){
        return this.startTime;
    }

    public void setEndTime(Integer time){
        this.endTime = time;
    }

    public Integer getEndTime(){
        return this.endTime;
    }

    public void setDesk(Desk desk){
        this.desk = desk;
    }

    public Desk getDesk(){
        return desk;
    }
}
