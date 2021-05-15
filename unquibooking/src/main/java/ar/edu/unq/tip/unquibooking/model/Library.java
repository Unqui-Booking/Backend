package ar.edu.unq.tip.unquibooking.model;

import javax.persistence.*;

@Entity
@Table(name= "library")
public class Library {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    @Column(unique=true, nullable=false)
    private Long id;

    @Column(nullable=false)
    private Integer openTime = 0;

    @Column(nullable=false)
    private Integer closeTime = 0;

    public void setOpenTime(Integer openTime){
        this.openTime = openTime;
    }

    public Integer getOpenTime(){
        return openTime;
    }

    public void setCloseTime(Integer closeTime){
        this.closeTime = closeTime;
    }

    public Integer getCloseTime(){
        return closeTime;
    }

}