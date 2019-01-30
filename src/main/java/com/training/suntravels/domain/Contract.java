package com.training.suntravels.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "DK_CONTRACT")
@Data
public class Contract implements Serializable
{
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "id_Sequence_contract")
	@SequenceGenerator(name = "id_Sequence_contract", sequenceName = "DK_CONTRACT_Id_SEQ1")
	@Column(name = "ID")
	int id;

	@Column(name = "VALID_FROM")
	Date validFrom;

	@Column(name = "VALID_TO")
	Date validTo;

	@Column(name = "CURRENCY")
	String currency;

	@ManyToOne
	@JoinColumn(name = "HOTEL_ID")
	private Hotel hotel;

	@OneToMany
	@JsonIgnore
	private Set<RoomContract> roomContracts = new HashSet<>( 0 );

}
