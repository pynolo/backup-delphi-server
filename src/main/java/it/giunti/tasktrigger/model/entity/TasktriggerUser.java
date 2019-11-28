package it.giunti.tasktrigger.model.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonFormat;
 
@Entity
@Table(name = "tasktrigger_user")
public class TasktriggerUser {
 
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private int id;
 
    @Column(name = "username")
    private String username;
 
    @Column(name = "modified_date")
    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date modifiedDate;
 

    public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public Date getModifiedDate() {
		return modifiedDate;
	}

	public void setModifiedDate(Date modifiedDate) {
		this.modifiedDate = modifiedDate;
	}

	@Override
    public String toString() {
        return "TasktriggerUser [id=" + id + ", taskName=" + username + "]";
    }
 
}