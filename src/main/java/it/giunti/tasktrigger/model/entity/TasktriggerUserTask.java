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
@Table(name = "tasktrigger_user_task")
public class TasktriggerUserTask {
 
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private int id;
 
    @Column(name = "id_user")
    private int idUser;
    
    @Column(name = "id_task")
    private int idTask;
    
    @Column(name = "modified_date")
    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date modifiedDate;
 

    public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getIdUser() {
		return idUser;
	}

	public void setIdUser(int idUser) {
		this.idUser = idUser;
	}

	public int getIdTask() {
		return idTask;
	}

	public void setIdTask(int idTask) {
		this.idTask = idTask;
	}

	public Date getModifiedDate() {
		return modifiedDate;
	}

	public void setModifiedDate(Date modifiedDate) {
		this.modifiedDate = modifiedDate;
	}

	@Override
    public String toString() {
        return "TasktriggerUserTask [id=" + id + "]";
    }
 
}