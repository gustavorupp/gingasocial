package tv.gingasocial.restfb.types;

import com.restfb.Facebook;

public class StoryTag {
	@Facebook
	String id;

	@Facebook
	String name;

	@Facebook
	Integer offset;

	@Facebook
	Integer length;
	
	public String getId() {
		return id;
	}
	
	public String getName() {
		return name;
	}
	
	public Integer getOffset() {
		return offset;
	}
	
	public Integer getLength() {
		return length;
	}
}
