package tv.gingasocial.restfb.types;

import static com.restfb.util.DateUtils.toDateFromLongFormat;

import java.util.Date;

import com.restfb.Facebook;
import com.restfb.types.NamedFacebookType;

public class Book extends NamedFacebookType {
	@Facebook
	private String category;

	@Facebook("created_time")
	private String createdTime;

	private static final long serialVersionUID = 1L;
	/**
	 * The category the book was initially published.
	 * 
	 * @return The time the book was initially published.
	 */	
	public String getCategory() {
		return category;
	}

	/**
	 * The time the book was initially published.
	 * 
	 * @return The time the book was initially published.
	 */
	public Date getCreatedTime() {
		return toDateFromLongFormat(createdTime);
	}

	

}
