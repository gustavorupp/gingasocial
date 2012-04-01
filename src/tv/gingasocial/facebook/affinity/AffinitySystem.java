package tv.gingasocial.facebook.affinity;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import tv.gingasocial.facebook.SocialTvUser;
import tv.gingasocial.restfb.types.Type;

import com.restfb.types.Comment;
import com.restfb.types.NamedFacebookType;
import com.restfb.types.Photo;
import com.restfb.types.Photo.Tag;
import com.restfb.types.Post;
import com.restfb.types.Post.Comments;
import com.restfb.types.Post.Likes;
import com.restfb.types.User;

public class AffinitySystem {

	private SocialTvUser socialTvUser;
	private User facebookUser;
	private List<String> userFriendsFacebookId;
	private Map<String, Integer> friendsAffinity; 

	/* POINTS */
	private static final Integer FEED_POST = 4;
	private static final Integer FEED_COMMENT = 3;
	private static final Integer FEED_LIKE = 2;

	private static final Integer OWN_PHOTO_TAGGED = 4;
	private static final Integer OWN_PHOTO_COMMENT = 3;
	private static final Integer OWN_PHOTO_LIKE = 2;

	private static final Integer FRIEND_PHOTO_TAGGED = 3;
	private static final Integer FRIEND_PHOTO_COMMENT = 2;
	private static final Integer FRIEND_PHOTO_LIKE = 1;
	
	private static final Integer RELATIONSHIP = 10;

	private static final Integer MIN_POINTS = 4;

	public AffinitySystem(SocialTvUser socialTvUser) {
		this.socialTvUser = socialTvUser;
		this.facebookUser = socialTvUser.getFacebookUser();
		this.userFriendsFacebookId = socialTvUser.getFriendsFacebookId();
		friendsAffinity = new HashMap<String, Integer>();
		start();

		Set<String> keySet = friendsAffinity.keySet();
		for(String string : keySet) {
			System.out.println(string + "=" + friendsAffinity.get(string));
		}
	}

	private void start() {
		List<Post> feed = socialTvUser.getFeed();
		for (Post post : feed) {
			Type postType = Type.getType(post.getType());
			String friendFacebookId = post.getFrom().getId();
//			System.out.println("POST ID > " + post.getId() + " - " + post.getMessage() + " TYPE >" + postType);
			switch (postType) {
			case STATUS:
			case LINK:
			case VIDEO:
				sumAffinityPoints(friendFacebookId, FEED_POST);
				checkLikePoints(post.getLikes());
				checkCommentsPoints(post.getComments());
				break;

			case PHOTO:
				if(post) {
					checkPhotoPoints(post.getId());
				}

			default:
				break;

			}// end switch
		}
	}
	
	public void sumAffinityPoints(String friendFacebookId, int points) {
		//Verify if facebookUser is fbUserId's friend
		if(!facebookUser.getId().equalsIgnoreCase(friendFacebookId) && userFriendsFacebookId.contains(friendFacebookId)) {
			int currentPoints = 0;
			if( friendsAffinity.containsKey(friendFacebookId) ) {
				currentPoints = friendsAffinity.get(friendFacebookId);
			}
			friendsAffinity.put( friendFacebookId, (currentPoints + points) );
		}
		
	}
	
	public void checkLikePoints(Likes likes) {
		if(likes != null && likes.getData().size() > 0) {
			List<NamedFacebookType> likesList = likes.getData();
			
			for (NamedFacebookType namedFacebookType : likesList) {
				sumAffinityPoints(namedFacebookType.getId(), FEED_LIKE);
			}
		}
	}
	
	public void checkCommentsPoints(Comments comments) {
		if(comments != null && comments.getData().size() > 0) {
			List<Comment> commentsList = comments.getData();
			
			for (Comment comment : commentsList) {
				String friendFacebookId = comment.getFrom().getId();
				sumAffinityPoints(friendFacebookId, FEED_COMMENT);
			}
		}
	}
	
	/**
	 * @param objectId represents the id to access the Photo information
	 */
	public void checkPhotoPoints(String facebookPostId) {
		Photo fetchObject = socialTvUser.fetchObject(facebookPostId, Post.class);
		String fromUserId = fetchObject.getFrom().getId();
		
		//Check if photo is on owner Album
		if(facebookUser.getId() == fromUserId) {
			List<Tag> tags = fetchObject.getTags();
			System.out.println(tags);
		}
		
		
		/*
		
		//If photo is on the facebookUser Album
		if( photoFromid == facebookUser.getFacebookUserId() ) {
			if( photo.hasTags() ) {
				List<Tag> tags = photo.getTags().getData();
				for(Tag tag : tags) {
					if( !StringUtils.isNullOrEmpty(tag.getId()) ) {
						Long taggedUserId = Long.parseLong(tag.getId());
						addAffinityPoints2Friend(taggedUserId, PHOTO_OWN_TAGGED);
					}
				}
			}
			
			if( photo.hasComments() ) {
				List<Comment> comments = photo.getComments().getData();
				for(Comment comment : comments) {
					Long commentUserId = comment.getFrom().getId();
					addAffinityPoints2Friend(commentUserId, PHOTO_OWN_COMMENT);
				}
			}
			
			if( photo.hasLikes() ) {
				List<Like> likes = photo.getLikes().getData();
				for(Like like : likes) {
					Long likeUserId = like.getId();
					addAffinityPoints2Friend(likeUserId, PHOTO_OWN_LIKE);
				}
			}
			
		//Checks if contains the photoFromId in list friends of facebookUser
		} else if( facebookUser.getUserFriendsId().contains(photoFromid) ) {
			if( photo.hasTags() ) {
				List<Tag> tags = photo.getTags().getData();
				for(Tag tag : tags) {
					if( !StringUtils.isNullOrEmpty(tag.getId()) ) {
						Long taggedUserId = Long.parseLong(tag.getId());
						addAffinityPoints2Friend(taggedUserId, PHOTO_FRIEND_TAGGED);
					}
				}
			}
			
			if( photo.hasComments() ) {
				List<Comment> comments = photo.getComments().getData();
				for(Comment comment : comments) {
					Long commentUserId = comment.getFrom().getId();
					addAffinityPoints2Friend(commentUserId, PHOTO_FRIEND_COMMENT);
				}
			}
			
			if( photo.hasLikes() ) {
				List<Like> likes = photo.getLikes().getData();
				for(Like like : likes) {
					Long likeUserId = like.getId();
					addAffinityPoints2Friend(likeUserId, PHOTO_FRIEND_LIKE);
				}
			}
		} //end else
		*/
		
	}

}
