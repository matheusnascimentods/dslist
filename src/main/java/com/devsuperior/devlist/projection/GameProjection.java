package com.devsuperior.devlist.projection;

public interface GameProjection {

	Long getId();
	String getTitle();
	Integer getYear();
	String getGenre();
	String getPlatforms();
	Double getScore();
	String getImgUrl();
	String getShortDescription();
	String getLongDescription();
	Integer getPosition();
}
