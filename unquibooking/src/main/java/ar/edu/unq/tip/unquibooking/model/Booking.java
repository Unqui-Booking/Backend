package ar.edu.unq.tip.unquibooking.model;

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

    @Column(nullable=false)
    private String date;
    
    @Column(nullable=false)
    private Integer startTime;

    @Column(nullable=false)
    private Integer endTime;

    public void setId(Long id){
        this.id = id;
    }

    public Long getId(){
        return id;
    }

    public void setDate(String date){
        this.date = date;
    }

    public String getDate(){
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
