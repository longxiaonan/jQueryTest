package nosql.mongo;

/**
 * Mongo DB name 的enum常量
 */
public enum MongoDBName {
	
	/**dbName:gdcp*/
	GDCP("gdcp");
	
	private String dbName;
	
	private MongoDBName(String dbName) {
		this.dbName = dbName;
	}

	@Override
	public String toString() {
		return this.dbName;
	}
}
