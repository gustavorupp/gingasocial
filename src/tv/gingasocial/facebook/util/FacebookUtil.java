package tv.gingasocial.facebook.util;

import java.util.List;

import tv.gingasocial.facebook.SocialTvUser;
import tv.gingasocial.restfb.types.Book;
import tv.gingasocial.restfb.types.MovieObject;

public class FacebookUtil {

	private void getObjectProperties() {
		SocialTvUser client = new SocialTvUser("");
		List<Book> books = client.getBooks();
		String id = books.get(0).getId();
		MovieObject movieObject = client.getFacebookClient().fetchObject(id, MovieObject.class);
		System.out.println(movieObject.getLikes());
	}
	
}
