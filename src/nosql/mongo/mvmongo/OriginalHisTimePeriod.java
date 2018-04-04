package nosql.mongo.mvmongo;

import java.util.Date;

import com.gdcp.common.util.date.DateUtil;

/**
 * 结构化和非结构化的时间段
 * @author lijian@cstonline.com
 *
 * @date 2015年4月10日 下午5:30:39
 */
public class OriginalHisTimePeriod {
	
	/**非结构化时间段的开始时间*/
	private Date originalStartTime;
	
	/**非结构化时间段的结束时间*/
	private Date originalEndTime;
	
	/**结构化时间段的开始时间*/
	private Date hisStartTime;
	
	/**结构化时间段的结束时间*/
	private Date hisEndTime;
	
	
	
	/**非结构化时间段的开始时间*/
	public Date getOriginalStartTime() {
		return originalStartTime;
	}

	/**非结构化时间段的开始时间*/
	public void setOriginalStartTime(Date originalStartTime) {
		this.originalStartTime = originalStartTime;
	}

	/**非结构化时间段的结束时间*/
	public Date getOriginalEndTime() {
		return originalEndTime;
	}

	/**非结构化时间段的结束时间*/
	public void setOriginalEndTime(Date originalEndTime) {
		this.originalEndTime = originalEndTime;
	}

	/**结构化时间段的开始时间*/
	public Date getHisStartTime() {
		return hisStartTime;
	}

	/**结构化时间段的开始时间*/
	public void setHisStartTime(Date hisStartTime) {
		this.hisStartTime = hisStartTime;
	}

	/**结构化时间段的结束时间*/
	public Date getHisEndTime() {
		return hisEndTime;
	}

	/**结构化时间段的结束时间*/
	public void setHisEndTime(Date hisEndTime) {
		this.hisEndTime = hisEndTime;
	}

	@Override
	public String toString() {
		return "OriginalHisTimePeriod [originalStartTime=" + dateToString(originalStartTime)
				+ ", originalEndTime=" + dateToString(originalEndTime) + ", hisStartTime="
				+ dateToString(hisStartTime) + ", hisEndTime=" + dateToString(hisEndTime) + "]";
	}
	
	private String dateToString(Date date) {
		
		return date == null ? null : DateUtil.formatYYYYMMDDHHMMSS(date);
	}
}
