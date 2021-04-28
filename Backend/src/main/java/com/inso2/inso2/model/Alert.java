package com.inso2.inso2.model;

import javax.persistence.*;
import javax.validation.constraints.Size;
import java.util.Date;

@Entity
@Table(name = "alerts")
public class Alert {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "IdAlert")
    private long idAlert;

    @Size(min = 1, max = 50)
    @Column(name = "Title", length = 50, nullable = false)
    private String title;

    @Size(min = 1, max = 500)
    @Column(name = "Text", length = 500, nullable = false)
    private String text;

    @Column(name = "Read", nullable = false)
    private boolean read;

    @Column(name = "Date", columnDefinition="DATETIME", nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Date date;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "IdUser", nullable = false)
    private User user;

    public Alert() {
    }

    public Alert(@Size(min = 1, max = 50) String title, @Size(min = 1, max = 500) String text, boolean read, Date date, User user) {
        this.title = title;
        this.text = text;
        this.read = read;
        this.date = date;
        this.user = user;
    }

    public long getIdAlert() {
        return idAlert;
    }

    public void setIdAlert(long idAlert) {
        this.idAlert = idAlert;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public boolean isRead() {
        return read;
    }

    public void setRead(boolean read) {
        this.read = read;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
