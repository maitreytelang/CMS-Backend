package com.app.pojos;

import java.time.LocalDate;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "tutorials")
public class Tutorial extends BaseEntity{
// id | name        | author | publish_date | visits | contents | topic_id
	private String name;

	private String author;

	@Column(name = "publish_date")
	private LocalDate publishDate;

	private int visits;

	private String contents;

	@ManyToOne
	@JoinColumn(name = "topic_id",nullable = false)
	private Topic topic;


	public Tutorial() {}

	public Tutorial(String name, String author, LocalDate publishDate, int visits, String contents) {
		super();
		this.name = name;
		this.author = author;
		this.publishDate = publishDate;
		this.visits = visits;
		this.contents = contents;
	}


	public String getName() {
		return name;
	}


	public String getAuthor() {
		return author;
	}


	public LocalDate getPublishDate() {
		return publishDate;
	}


	public int getVisits() {
		return visits;
	}


	public String getContents() {
		return contents;
	}


	public void setName(String name) {
		this.name = name;
	}


	public void setAuthor(String author) {
		this.author = author;
	}


	public void setPublishDate(LocalDate publishDate) {
		this.publishDate = publishDate;
	}


	public void setVisits(int visits) {
		this.visits = visits;
	}


	public void setContents(String contents) {
		this.contents = contents;
	}

	public Topic getTopic() {
		return topic;
	}

	public void setTopic(Topic topic) {
		this.topic = topic;
	}

	@Override
	public String toString() {
		return "Tutorial id : "+getId()+" [name=" + name + ", author=" + author + ", publishDate=" + publishDate + ", visits=" + visits
				+ ", contents=" + contents + "]";
	}

}
