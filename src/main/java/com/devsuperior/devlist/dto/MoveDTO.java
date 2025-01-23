package com.devsuperior.devlist.dto;

public class MoveDTO {

	private Integer sourceIndex;
	private Integer destinationIndex;
	
	public MoveDTO() {
	}

	public MoveDTO(Integer sourceIndex, Integer destinationIndex) {
		this.sourceIndex = sourceIndex;
		this.destinationIndex = destinationIndex;
	}

	public Integer getSourceIndex() {
		return sourceIndex;
	}

	public void setSourceIndex(Integer sourceIndex) {
		this.sourceIndex = sourceIndex;
	}

	public Integer getDestinationIndex() {
		return destinationIndex;
	}

	public void setDestinationIndex(Integer destinationIndex) {
		this.destinationIndex = destinationIndex;
	}
	
	
}
