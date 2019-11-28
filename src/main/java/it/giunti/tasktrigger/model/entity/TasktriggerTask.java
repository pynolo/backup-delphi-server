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
@Table(name = "tasktrigger_task")
public class TasktriggerTask {
 
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private int id;
 
    @Column(name = "name")
    private String name;
    
    @Column(name = "executable")
    private String executable;
    
    @Column(name = "description")
    private String description;
    
    @Column(name = "modified_date")
    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date modifiedDate;
 

    public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getExecutable() {
		return executable;
	}

	public void setExecutable(String executable) {
		this.executable = executable;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Date getModifiedDate() {
		return modifiedDate;
	}

	public void setModifiedDate(Date modifiedDate) {
		this.modifiedDate = modifiedDate;
	}

	@Override
    public String toString() {
        return "TasktriggerTask [id=" + id + ", name=" + name + "]";
    }
 
}