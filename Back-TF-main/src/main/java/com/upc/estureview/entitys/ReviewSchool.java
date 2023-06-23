package com.upc.estureview.entitys;

import javax.persistence.*;
import java.time.LocalDate;

@Entity(name = "reviewSchool")
public class ReviewSchool {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "dateReviewS", nullable = false)
    private LocalDate dateReviewS;
    @Column(name = "reviewS", length = 300, nullable = false)
    private String reviewS;
    @Column(name = "calification", nullable = false)
    private int calification;
    @Column(name = "pension", nullable = false)
    private double pension;

    private Long identidadColegio;
    private Long identidadUsuario;

    public ReviewSchool() {
    }

    public ReviewSchool(Long id, LocalDate dateReviewS, String reviewS, int calification, double pension, Long identidadColegio, Long identidadUsuario) {
        this.id = id;
        this.dateReviewS = dateReviewS;
        this.reviewS = reviewS;
        this.calification = calification;
        this.pension = pension;
        this.identidadColegio = identidadColegio;
        this.identidadUsuario = identidadUsuario;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LocalDate getDateReviewS() {
        return dateReviewS;
    }

    public void setDateReviewS(LocalDate dateReviewS) {
        this.dateReviewS = dateReviewS;
    }

    public String getReviewS() {
        return reviewS;
    }

    public void setReviewS(String reviewS) {
        this.reviewS = reviewS;
    }

    public int getCalification() {
        return calification;
    }

    public void setCalification(int calification) {
        this.calification = calification;
    }

    public double getPension() {
        return pension;
    }

    public void setPension(double pension) {
        this.pension = pension;
    }

    public Long getIdentidadColegio() {
        return identidadColegio;
    }

    public void setIdentidadColegio(Long identidadColegio) {
        this.identidadColegio = identidadColegio;
    }

    public Long getIdentidadUsuario() {
        return identidadUsuario;
    }

    public void setIdentidadUsuario(Long identidadUsuario) {
        this.identidadUsuario = identidadUsuario;
    }
}
