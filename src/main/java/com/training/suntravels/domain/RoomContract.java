package com.training.suntravels.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;

@Data
@Entity
@Table(name = "DK_ROOM_CONTRACT")
@NoArgsConstructor
@AllArgsConstructor
public class RoomContract implements Serializable
{

	@Id
	@ManyToOne
	@JoinColumn(name = "CONTRACT_ID")
	private Contract contract;

	@Id
	@ManyToOne
	@JoinColumn(name = "ROOM_TYPE_ID")
	private RoomType roomType;

	@Column(name = "NO_OF_ROOMS")
	private int noOfRooms;

	@Column(name = "PRICE")
	private double price;

}
