package com.training.suntravels.domain;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;
import java.util.Date;

@Entity
@Table(name = "DK_CONTRACT")
@Data
public class Contract implements Serializable
{
	@Id
	@Column(name = "ID")
	int id;

	@Column(name = "VALID_FROM")
	Date validFrom;

	@Column(name = "VALID_TO")
	Date validTo;

	@Column(name = "HOTEL_ID")
	int hotelId;

	@Column(name = "CURRENCY")
	String currency;
}
