package com.training.suntravels.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Table;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
@Table(name = "DK_HOTEL")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Hotel
{
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ID")
	private int Id;

	@Column(name = "NAME")
	private String name;

	@Column(name = "ADDRESS")
	private String address;

	@Column(name = "STAR_RATING")
	private int starRating;

	@Override
	public String toString()
	{
		return "Hotel{" +
				"Id=" + Id +
				", name='" + name + '\'' +
				", address='" + address + '\'' +
				", starRating=" + starRating +
				'}';
	}
}
