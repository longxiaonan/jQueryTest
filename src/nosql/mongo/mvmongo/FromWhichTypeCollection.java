package nosql.mongo.mvmongo;

/**
 * 哪种类型、结构化查询的常量
 * @author lijian@cstonline.com
 *
 * @date 2015年4月10日 下午3:49:41
 */
public enum FromWhichTypeCollection {

	/**从非结构化查询*/
	ORIGINAL_TYPE("从非结构化查询"),
	
	/**从结构化查询*/
	HIS_TYPE("从结构化查询"),
	
	/**从结构化和非结构化共同查询*/
	ORIGINAL_HIS_TYPE("从结构化和非结构化共同查询")
	;
	
	private String message;
	
	private FromWhichTypeCollection(String message) {
		this.message = message;
	}
	
	@Override
	public String toString() {
		return this.message;
	}
}
