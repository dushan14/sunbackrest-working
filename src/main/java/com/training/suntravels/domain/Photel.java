package com.training.suntravels.domain;

import lombok.Data;

import javax.persistence.Column;

@Data
public class Photel
{
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
}
