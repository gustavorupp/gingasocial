package tv.gingasocial.restfb.types;

import com.restfb.Facebook;
import com.restfb.json.JsonObject;

public class Story {
	@Facebook
	String story;

	@Facebook("story_tags")
	JsonObject storyTags;
	
	public String getStory() {
		return story;
	}
	
	public JsonObject getStoryTags() {
		return storyTags;
	}

}
