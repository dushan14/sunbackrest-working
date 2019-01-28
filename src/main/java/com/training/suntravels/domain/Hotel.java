package com.training.suntravels.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

@Table(name = "DK_HOTEL")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Hotel implements Serializable
{
	@Id
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator = "id_Sequence_hotel")
	@SequenceGenerator(name = "id_Sequence_hotel", sequenceName = "DK_Hotel_Id_SEQ1")
	@Column(name = "ID")
	private int id;

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

	@OneToMany
	@JsonIgnore
	private Set<RoomType> roomTypes = new HashSet<RoomType>(0);

	@OneToMany
	@JsonIgnore
	private Set<Contract> contracts=new HashSet<>( 0 );

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
				"Id=" + id +
				", name='" + name + '\'' +
				", address='" + address + '\'' +
				", starRating=" + starRating +
				'}';
	}
}
