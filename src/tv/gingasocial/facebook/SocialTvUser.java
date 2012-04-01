package tv.gingasocial.facebook;

import java.util.ArrayList;
import java.util.List;

import tv.gingasocial.facebook.affinity.AffinitySystem;
import tv.gingasocial.restfb.types.Book;
import tv.gingasocial.restfb.types.Movie;

import com.restfb.Connection;
import com.restfb.DefaultFacebookClient;
import com.restfb.FacebookClient;
import com.restfb.types.Album;
import com.restfb.types.Post;
import com.restfb.types.User;
import com.restfb.types.Video;

public class SocialTvUser {

	private final FacebookClient facebookClient;
	private User facebookUser;
	private List<Post> feed;
	private List<User> friends;
	private List<String> friendsFacebookId;
	private List<Album> albums;
	private List<Video> videos;
	private List<Movie> movies;
	private List<Book> books;
	
	public SocialTvUser(String accessToken) {
		facebookClient = new DefaultFacebookClient(accessToken);
		facebookUser = facebookClient.fetchObject("me", User.class);
		
		fetchFeed();
		fetchFriends();
		fetchAlbums();
		fetchVideos();
		fetchMovies();
		fetchBooks();
	}
	
	public User getFacebookUser() {
		return facebookUser;
	}
	
	public FacebookClient getFacebookClient() {
		return facebookClient;
	}
	
	private void fetchFeed() {
		Connection<Post> myFeed = facebookClient.fetchConnection("me/feed", Post.class);
		feed = myFeed.getData();
	}
	
	private void fetchFriends() {
		Connection<User> myFriends = facebookClient.fetchConnection("me/friends", User.class);
		friends = myFriends.getData();
		
		friendsFacebookId = new ArrayList<String>();
		for(User friend : friends) {
			friendsFacebookId.add(friend.getId());
		}
	}
	
	private void fetchAlbums() {
		Connection<Album> myAlbums = facebookClient.fetchConnection("me/albums", Album.class);
		albums = myAlbums.getData();
	}
	
	private void fetchVideos() {
		Connection<Video> myVideos = facebookClient.fetchConnection("me/videos", Video.class);
		videos = myVideos.getData();
	}
	
	private void fetchBooks() {
		Connection<Book> myBooks = facebookClient.fetchConnection("me/books", Book.class);
		books = myBooks.getData();
	}
	
	private void fetchMovies() {
		Connection<Movie> myMovies = facebookClient.fetchConnection("me/movies", Movie.class);
		movies = myMovies.getData();
	}
	
	public <T> T fetchObject(String id, Class<T> classType) {
		T fetchObject = facebookClient.fetchObject(id, classType);
		return fetchObject;
	}
	
	public List<Album> getAlbums() {
		return albums;
	}
	
	public List<Book> getBooks() {
		return books;
	}
	
	public List<User> getFriends() {
		return friends;
	}
	
	public List<String> getFriendsFacebookId() {
		return friendsFacebookId;
	}
	
	public List<Movie> getMovies() {
		return movies;
	}

	public List<Video> getVideos() {
		return videos;
	}
	
	public List<Post> getFeed() {
		return feed;
	}
	
	public static void main(String[] args) {
		SocialTvUser socialTvUser = new SocialTvUser("AAACEdEose0cBAJZAyNI7hzuMydw3f8KhgpUXW6U0VDsieiDZAHsrugcSE4Ux8eSQqaZA0vyZCV5efORMbLPRhgkh9FmMIni2CVkgHI61pgZDZD");
		new AffinitySystem(socialTvUser);
		
//		new SocialTvUser("AAACEdEose0cBAO6KtCC2cjj3Fx9eVZC09zZA5BoFQbdcDXQ4xJKcoaECIowBVVzsZBglwXq7JIZA14gvBmcYbiIWMCwDkfI2rZAXH4huVGwZDZD"); //0 friends
		
	}
	
}
