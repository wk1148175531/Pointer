package com.project.beans;

public class MovieMsg {
	
	
	String video_name;
	String img_url;
	String description;
	public String getVideoName() {
		return video_name;
	}
	public void setVideoName(String videoName) {
		this.video_name = videoName;
	}
	public String getUrl() {
		return img_url;
	}
	public void setUrl(String img_url) {
		this.img_url = img_url;
	}
	public String getDescript() {
		return description;
	}
	public void setDescript(String descript) {
		this.description = descript;
	}
	@Override
	public String toString() {
		return "MovieMsg [videoName=" + video_name + ", url=" + img_url + ", descript=" + description + "]";
	}

}
