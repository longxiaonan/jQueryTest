package nosql.mongo.query;
   /**
    * @ClassName: test
	* @Description:
    * @author 龙小南
    * @email longxiaonan@163.com
    * @date 2017年11月12日 下午9:45:40
    * @version 1.0
    */
public class test {
	public static void main(String[] args) {
		
		MongoSortParams addAsc = MongoSortParams.getInstance().addAsc("Recevitime");
		System.out.println(addAsc.toOrderbyDBObject());
	}
}
