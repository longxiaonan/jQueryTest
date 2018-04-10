package nosql.mongo.mvmongo;

import java.util.Date;

/**
 * 某个集合的已经迁移（处理过）的最小、最大的迁移开始时间
 * @author lijian@cstonline.com
 *
 * @date 2015年4月9日 上午10:55:21
 */
public class MvMongoDate {
	
	/**集合名称*/
	private String collectionname;
	
	/**已经迁移过数据开始时间  (大于等于)*/
	private Date dataTimeGte;
	
	/**已经迁移过数据的结束时间  （小于）*/
	private Date dataTimelt;

	/**集合名称*/
	public String getCollectionname() {
		return collectionname;
	}

	/**集合名称*/
	public void setCollectionname(String collectionname) {
		this.collectionname = collectionname;
	}

	/**已经迁移过数据开始时间  (大于等于)*/
	public Date getDataTimeGte() {
		return dataTimeGte;
	}

	/**已经迁移过数据开始时间  (大于等于)*/
	public void setDataTimeGte(Date dataTimeGte) {
		this.dataTimeGte = dataTimeGte;
	}

	/**已经迁移过数据的结束时间  （小于）*/
	public Date getDataTimelt() {
		return dataTimelt;
	}

	/**已经迁移过数据的结束时间  （小于）*/
	public void setDataTimelt(Date dataTimelt) {
		this.dataTimelt = dataTimelt;
	}

	@Override
	public String toString() {
		return "MvMongoDate [collectionname=" + collectionname
				+ ", dataTimeGte=" + toDateString( dataTimeGte) + ", dataTimelt=" + toDateString(dataTimelt)
				+ "]";
	}
	
	private String toDateString(Date date) {
		
		return date == null ? null : DateUtil.formatYYYYMMDDHHMMSS(date);
	}
	
}
