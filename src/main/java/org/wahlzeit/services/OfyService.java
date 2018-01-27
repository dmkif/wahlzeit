package org.wahlzeit.services;

import org.wahlzeit.model.Administrator;
import org.wahlzeit.model.Client;
import org.wahlzeit.model.Globals;
import org.wahlzeit.model.Guest;
import org.wahlzeit.model.Moderator;
import org.wahlzeit.model.Photo;
import org.wahlzeit.model.PhotoCase;
import org.wahlzeit.model.Tag;
import org.wahlzeit.model.User;
import org.wahlzeit.model.mainframe.Mainframe;
import org.wahlzeit.model.mainframe.MainframePhoto;
import org.wahlzeit.model.mainframe.MainframeType;
import org.wahlzeit.model.persistence.DatastoreAdapter.ImageWrapper;

import com.googlecode.objectify.Objectify;
import com.googlecode.objectify.ObjectifyFactory;
import com.googlecode.objectify.ObjectifyService;

/**
 * A badly named class, to be renamed to ObjectifyService first, something better later.
 * 
 * @review
 */
public class OfyService {

	/**
	 * Register all entities at startup
	 */
	static {
		factory().register(Photo.class);
		factory().register(MainframePhoto.class);
		factory().register(Globals.class);
		factory().register(Tag.class);
		factory().register(User.class);
		factory().register(Administrator.class);
		factory().register(Moderator.class);
		factory().register(Client.class);
		factory().register(Guest.class);
		factory().register(PhotoCase.class);
		factory().register(ImageWrapper.class);
		factory().register(MainframeType.class);
		factory().register(Mainframe.class);
	}

	public static Objectify ofy() {
		return ObjectifyService.ofy();
	}

	public static ObjectifyFactory factory() {
		return ObjectifyService.factory();
	}
}
