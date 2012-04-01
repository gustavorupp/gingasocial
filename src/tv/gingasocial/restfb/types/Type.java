package tv.gingasocial.restfb.types;

public enum Type {
	
	STATUS,
	LINK,
	MUSIC,
	VIDEO,
	PHOTO;
	
	public static Type getType(String fbTypeData) {
		for(Type typeData : values()) {
			if( fbTypeData.equalsIgnoreCase(typeData.toString()) ) {
				return typeData;
			}
		}
		
		return STATUS;
	}

}