package com.training.suntravels.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;

@Data
@Entity
@Table(name = "DK_ROOM_TYPE")
@NoArgsConstructor
@AllArgsConstructor
public class RoomType implements Serializable
{
	@Id
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator = "id_Sequence_roomType")
	@SequenceGenerator(name = "id_Sequence_roomType", sequenceName = "DK_ROOM_TYPE_Id_SEQ1")
	@Column(name = "ID")
	private int id;

	@Column(name = "NAME")
	private String name;

	@Column(name = "NO_OF_ADULTS")
	private int noOfAdults;

	@ManyToOne
	@JoinColumn(name = "HOTEL_ID", nullable = false)
	private Hotel hotel;

}
