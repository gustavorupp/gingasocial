package tv.gingasocial.restfb.types;

import static com.restfb.util.DateUtils.toDateFromLongFormat;

import java.util.Date;

import com.restfb.Facebook;
import com.restfb.types.NamedFacebookType;

public class Movie extends NamedFacebookType {
	@Facebook("created_time")
	private String createdTime;

	private static final long serialVersionUID = 1L;

	/**
	 * The time the movie was initially published.
	 * 
	 * @return The time the movie was initially published.
	 */
	public Date getCreatedTime() {
		return toDateFromLongFormat(createdTime);
	}

}
