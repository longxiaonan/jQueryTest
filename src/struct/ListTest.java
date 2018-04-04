package struct;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import com.gdcp.data.DataObj;

/**
    * @ClassName: ListTest
	* @Description:
    * @author 龙小南
    * @email longxiaonan@163.com
    * @date 2017年9月5日 上午11:05:34
    * @version 1.0
    */
public class ListTest {
	public static void main(String[] args) {
//		testContainsAndRemoveAll();
		
//		testListAddMap();
		testListAddList();
	}

	private static void testListAddList() {
		List<String> list = new ArrayList<>();
		list.add("123");
		list.add("234");
		list.add("345");
		
		List<String> list2 = new ArrayList<>();
		list2.add("aaa");
		list2.add("bbb");
		list2.add("ccc");
		list2.add("123");
		
		list2.addAll(list);
		System.out.println(list2);
	}

	/**
	 * Add Map时完整的map作为list中的一个元素添加到了list中
	 */
	private static void testListAddMap() {
		List<Map<String,Object>> list1 = new ArrayList<>();
		Map map1 = new LinkedHashMap<>();
		map1.put("aa", "11");
		map1.put("ab", "12");
		list1.add(map1);
		Map map2 = new LinkedHashMap<>();
		map2.put("ac", "13");
		map2.put("ad", "14");
		list1.add(map2);
		
		
		System.out.println(list1);
		
		List<Map<String,Object>> list2 = new ArrayList<>();
		Map map22 = new LinkedHashMap<>();
		map22.put("aa", "11");
		map22.put("ab", "12");
		list2.add(map22);
		
		System.out.println(list2);
		
		for(Map<String, Object> map : list2){
			System.out.println(list1.contains(map));
		}
	}

	/** 测试contains和removeAll的效率
	 *  大部分情况下removeAll的效率要高一些
	 *  */
	private static void testContainsAndRemoveAll() {
		List<String> allList = new ArrayList<>();
		List<DataObj> dqiData = new ArrayList<>();
		
		List<Map<String, Object>> resultList1 = new ArrayList<>();
		List<Map<String, Object>> resultList2 = new ArrayList<>();
		for(int i = 0; i < 10000; i++){//模拟数据库中查询出来的列表
			allList.add("aa"+i);
		}
		for(int i = 0; i < 10050; i++){//模拟获取到的数据
			DataObj o = new DataObj();
			o.put("DEVCODE","aa"+i);
			dqiData.add(o);
		}
		
		/** 方式一 */
		long start1 = System.currentTimeMillis();
		for (DataObj dataObj : dqiData) {
			String devCode = dataObj.getString("DEVCODE");
			if(dataObj != null && (devCode != null)){
				if(!allList.contains(devCode)){
					Map<String, Object> map = new LinkedHashMap<>();
					map.put("vinCode", devCode);
					resultList1.add(map);
				}
			}
		}
		long stop1 = System.currentTimeMillis();
		System.out.println("一个个匹配需要的时间为" + (stop1 - start1));
		System.out.println(resultList1);
		
		/** 方式二 */
		long start2 = System.currentTimeMillis();
		List list2 = new ArrayList<>();
		for (DataObj o : dqiData) {
			list2.add(o.getString("DEVCODE"));
		}
		System.out.println(list2);
		list2.removeAll(allList);
		for (Object s : list2) {
			Map<String, Object> map = new LinkedHashMap<>();
			map.put("vinCode", s);
			resultList2.add(map);
		}
		long stop2 = System.currentTimeMillis();
		System.out.println("通过removeAll匹配需要的时间为" + (stop2 - start2));
		System.out.println(resultList2);
	}
}
