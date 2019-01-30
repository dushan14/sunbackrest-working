package com.training.suntravels.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.util.Date;
import java.util.List;

@Data
public class SearchRequestDTO
{
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
	private Date checkIn;
	private int noOfNights;
	private List<RoomAdultCondition> conditions;
}
