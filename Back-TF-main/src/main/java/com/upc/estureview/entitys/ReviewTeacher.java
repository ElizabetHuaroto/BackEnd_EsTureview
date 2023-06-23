package com.upc.estureview.entitys;

import javax.persistence.*;
import java.time.LocalDate;

@Entity (name = "reviewTeachers")
public class ReviewTeacher {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "dateReviewT", nullable = false)
    private LocalDate dateReviewT;
    @Column(name = "reviewT", length = 300, nullable = false)
    private String reviewT;
    @Column(name = "calification", nullable = false)
    private int calification;
    @Column(name = "payForHour", nullable = false)
    private double payForHour; //es lo mismo que pension

    private Long identidadProfesor;
    private Long identidadUsuario;

    public ReviewTeacher(Long id, LocalDate dateReviewT, String reviewT, int calification, double payForHour, Long identidadProfesor, Long identidadUsuario) {
        this.id = id;
        this.dateReviewT = dateReviewT;
        this.reviewT = reviewT;
        this.calification = calification;
        this.payForHour = payForHour;
        this.identidadProfesor = identidadProfesor;
        this.identidadUsuario = identidadUsuario;
    }

    public ReviewTeacher() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LocalDate getDateReviewT() {
        return dateReviewT;
    }

    public void setDateReviewT(LocalDate dateReviewT) {
        this.dateReviewT = dateReviewT;
    }

    public String getReviewT() {
        return reviewT;
    }

    public void setReviewT(String reviewT) {
        this.reviewT = reviewT;
    }

    public int getCalification() {
        return calification;
    }

    public void setCalification(int calification) {
        this.calification = calification;
    }

    public double getPayForHour() {
        return payForHour;
    }

    public void setPayForHour(double payForHour) {
        this.payForHour = payForHour;
    }

    public Long getIdentidadProfesor() {
        return identidadProfesor;
    }

    public void setIdentidadProfesor(Long identidadProfesor) {
        this.identidadProfesor = identidadProfesor;
    }

    public Long getIdentidadUsuario() {
        return identidadUsuario;
    }

    public void setIdentidadUsuario(Long identidadUsuario) {
        this.identidadUsuario = identidadUsuario;
    }

}
