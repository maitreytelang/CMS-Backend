package com.app.pojos;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;

@Entity
@Table(name = "topics")
public class Topic extends BaseEntity{
// id | name
	@Column(name = "name")
	private String topicName;

	public Topic() {}

	public Topic(String name) {
		super();
		this.topicName = name;
	}

	public String getTopicName() {
		return topicName;
	}

	public void setTopicName(String name) {
		this.topicName = name;
	}

	@Override
	public String toString() {
		return "Topic id : "+getId()+" [name=" + topicName + "]";
	}


}
