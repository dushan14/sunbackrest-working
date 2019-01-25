package com.training.suntravels.domain;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Table(name = "DK_HOTEL")
@Data
@NoArgsConstructor
@Entity
public class Hotel
{
	@Id
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator = "id_Sequence")
	@SequenceGenerator(name = "id_Sequence", sequenceName = "DK_Hotel_Id_SEQ1")
	@Column(name = "ID")
	private int Id;

	@Column(name = "NAME")
	private String name;

	@Column(name = "ADDRESS")
	private String address;

	@Column(name = "STAR_RATING")
	private int starRating;

	@Column(name="TELEPHONE")
	private String telephone;

	@Column(name="COUNTRY")
	private String country;

	public Hotel( String name, String address, int starRating, String telephone, String country )
	{
		this.name = name;
		this.address = address;
		this.starRating = starRating;
		this.telephone = telephone;
		this.country = country;
	}

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
