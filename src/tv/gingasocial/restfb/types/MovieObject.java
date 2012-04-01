package tv.gingasocial.restfb.types;

import com.restfb.Facebook;
import com.restfb.types.NamedFacebookType;

public class MovieObject extends NamedFacebookType {
	@Facebook
	private String link;
	
	@Facebook
	private String likes;
	
	@Facebook
	private String category;
	
	@Facebook("is_published")
	private String isPublished;
	
	@Facebook("is_community_page")
	private String isCommunityPage;
	
	@Facebook
	private String description;
	
	@Facebook("release_date")
	private String releaseDate;
	
	@Facebook("talking_about_count")
	private String talkingAboutCount;
	
	@Facebook
	private String type;

	private static final long serialVersionUID = 1L;

	public String getLink() {
		return link;
	}

	public void setLink(String link) {
		this.link = link;
	}

	public String getLikes() {
		return likes;
	}

	public void setLikes(String likes) {
		this.likes = likes;
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	public String getIsPublished() {
		return isPublished;
	}

	public void setIsPublished(String isPublished) {
		this.isPublished = isPublished;
	}

	public String getIsCommunityPage() {
		return isCommunityPage;
	}

	public void setIsCommunityPage(String isCommunityPage) {
		this.isCommunityPage = isCommunityPage;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getReleaseDate() {
		return releaseDate;
	}

	public void setReleaseDate(String releaseDate) {
		this.releaseDate = releaseDate;
	}

	public String getTalkingAboutCount() {
		return talkingAboutCount;
	}

	public void setTalkingAboutCount(String talkingAboutCount) {
		this.talkingAboutCount = talkingAboutCount;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}
	
}
