package com.example.backendtools.api.EnglishTest;

import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.*;

/**
 * 测试使用 读取 Excel 表格 生成 sql 语句
 * 
 * @author libo
 *
 * @date 2018年5月5日上午9:51:27
 */
public class TopicGenerator {
	public static final String TITLE_NAME = "一级专题名";
	public static final String TITLE_ID = "ID";
	public static final String TITLE_NO = "编号";
	public static final String TITLE_DESC = "细分知识点";
	public static final String TITLE_PRESS = "教材版本";
	public static final String TITLE_GRADE = "年级";
	public static final String TITLE_CATEGORY = "科目";
	public static final String TITLE_TIME = "预计时间";
	public static final String TITLE_TYPE = "类型";

	public static final Map<Integer, String> subjects = new HashMap<Integer, String>() {
		private static final long serialVersionUID = 3483754929367672584L;
		{
			put(45, "英语线上测评");
			put(44, "6A译林");
			put(43, "8A译林");
			put(42, "英语大图谱");
			put(41, "数学大图谱");
			put(40, "8A牛津");
			put(39, "9A外研");
			put(38, "9A牛津");
			put(37, "9A人教");
			put(36, "9A译林");
			put(35, "8A外研");
			put(34, "8A人教");
			put(33, "7A外研");
			put(32, "7A牛津");
			put(31, "7A人教");
			put(30, "7A译林");
			put(29, "6A牛津");
			put(46, "2017寒假英语训练营");
			put(47, "数学Demo课");

			// B series
			put(48, "6B牛津");
			put(49, "6B译林");
			put(50, "7B牛津");
			put(51, "7B译林");
			put(52, "8B牛津");
			put(53, "8B译林");
			put(54, "9B外研");
			put(55, "9B译林");
			put(56, "9B牛津");
			put(18, "7B外研");
			put(16, "7B人教");

			// others
			put(59, "七年级英语");
			put(62, "7年级同步测评-人教版");
			put(63, "8年级同步测评-人教版");
			put(64, "7年级同步测评-外研版");
			put(65, "8年级同步测评-外研版");
			put(66, "9年级同步测评-外研版");

			// B测评 0504
			put(67, "6年级同步测评(第二学期)-牛津版");
			put(68, "7年级同步测评(第二学期)-牛津版");
			put(69, "8年级同步测评(第二学期)-牛津版");
			put(70, "9年级同步测评(第二学期)-牛津版");
			put(71, "6年级同步测评(第二学期)-苏教译林版");
			put(72, "7年级同步测评(第二学期)-苏教译林版");
			put(73, "8年级同步测评(第二学期)-苏教译林版");
			put(74, "9年级同步测评(第二学期)-苏教译林版");

			put(101, "新八暑期课(苏教译林版)");
			put(103, "英语7年级上阶段性测评");
			put(104, "英语8年级上阶段性测评");
			put(105, "英语9年级上阶段性测评");
			put(106, "英语9年级下阶段性测评");
		}
	};

	public static void main(String[] args) {
		TopicGenerator g = new TopicGenerator();

//		String url38 = "D:\\测评\\需要的测评数据\\表格模板测试.xlsx";
        String url38 = "D:\\测评\\需要的测评数据\\人教版(阶段性测评)\\人教版7年级测评\\7年级上册阶段性测评（人教版）20180525.xlsx";
		int topicId = 7161;

		int id = 467;

		// System.out.println("-- 新增版本类型");
		// System.out.println("insert into dict (dicttype, dicttext, dictvalue)
		// values");
		// String topicSql1 = String.format("('press_type', '牛津版', 9);");
		// System.out.println(topicSql1 + "\n");
		// System.out.println("insert into dict (dicttype, dicttext, dictvalue)
		// values");
		// String topicSql2 = String.format("('press_type', '苏教译林版', 10);");
		// System.out.println(topicSql2 + "\n");

		/*
		 * 新增subject 数据
		 */
		// System.out.println("-- 新增subject数据");
		// System.out.println("insert into
		// subject(id,name,category,createdBy,level,grade,press)");
		// System.out.println("values(101,'新八暑期课',0,'libo',2,8,'苏教译林版');");
		// System.out.println();

		Map<String, List<Topic>> topicMap = null;

		List list = null;

		// topicMap = g.readExcel(url34);
		// list = g.writeSql(topicMap, topicId, id, 66, 4);

		// topicMap = g.readExcel(url25);
		// list = g.writeSql(topicMap, topicId, id, 67, 4);
		// topicMap = g.readExcel(url26);
		// list = g.writeSql(topicMap, (Integer) list.get(0), (Integer) list.get(1), 68,
		// 4);

		// topicId = g.writeSql(topicMap, topicId, topicInfoId, 74, 4);

		// topicMap = g.readExcel(url33);
		// list = g.writeSql(topicMap, topicId, id, 101, 4);

		// topicMap = g.readExcel(url34);
		// list = g.writeSql(topicMap, topicId, id, 103, 4);
		// topicMap = g.readExcel(url35);
		// list = g.writeSql(topicMap, (Integer) list.get(0), (Integer) list.get(1),
		// 104, 4);
		// topicMap = g.readExcel(url36);
		// list = g.writeSql(topicMap, (Integer) list.get(0), (Integer) list.get(1),
		// 105, 4);
		// topicMap = g.readExcel(url37);
		// list = g.writeSql(topicMap, (Integer) list.get(0), (Integer) list.get(1),
		// 106, 4);

		topicMap = g.readExcel(url38);
		list = g.writeSql(topicMap, topicId, id, 103, 4);
		// topicMap = g.readExcel(url35);
		// list = g.writeSql(topicMap, (Integer) list.get(0), (Integer) list.get(1),
		// 104, 4);

		System.out.println();
		System.out.println();
		System.out.println("############# FOR GREAT MASTER GRAPH ###############");

		// g.writeSqlMasterG(2001, 578, url32);

		// g.writeSqlMasterG(2001, 578, url34);
		// g.writeSqlMasterG(2001, 578, url35);
		// g.writeSqlMasterG(2001, 578, url36);
		// g.writeSqlMasterG(2001, 578, url37);

		g.writeSqlMasterG(2001, 578, url38);

	}

	private List writeSql(Map<String, List<Topic>> topicMap, int topicId, int id, int subjectId, int algo) {
		List list = new ArrayList<>();
		System.out.println("-- " + subjects.get(subjectId));
		for (Map.Entry<String, List<Topic>> entry : topicMap.entrySet()) {
			String k = entry.getKey();
			List<Topic> v = entry.getValue();
			algo = (v.size() < 15) ? 4 : 0;

			// 如果 知识点的数量 大于25 需要 为字段 maxPreStudyQuestion 重新赋值（默认为25）
			int vsize = v.size();

			if (vsize > 25) {
				System.out.println(
						"insert into topic (id, name, subjectId, category, state, hasLearningStage, createdBy, workflowType, isMaster, useTree, algo,maxPreStudyQuestion) values");
				String topicSql = String.format("(%d, '%s', %d, 0, 0, 0, 'libo', 0,  b'1', b'1', %d,%d);", topicId, k,
						subjectId, algo, vsize);
				System.out.println(topicSql + "\n");
			} else {
				System.out.println(
						"insert into topic (id, name, subjectId, category, state, hasLearningStage, createdBy, workflowType, isMaster, useTree, algo) values");
				String topicSql = String.format("(%d, '%s', %d, 0, 0, 0, 'libo', 0,  b'1', b'1', %d);", topicId, k,
						subjectId, algo);
				System.out.println(topicSql + "\n");
			}

			// System.out.println("================" + v.get(0).getSerialNumber());
			// System.out.println("================" + v.get(0).getCategoryStr());
			// System.out.println("================" + v.get(0).getEstimatedTimeStr());
			// System.out.println("================" + v.get(0).getGradeStr());
			// System.out.println("================" + v.get(0).getPressStr());
			// System.out.println("================" + v.get(0).getTypeStr());

			// 判断学科类型 英语 数学
			int category = 0;
			if ("英语".equals(v.get(0).getCategoryStr())) {
				category = 0;
			} else if ("数学".equals(v.get(0).getCategoryStr())) {
				category = 1;
			}
			// 判断 年级 学期
			List gradeList = getGrade(v.get(0).getGradeStr());
			// System.out.println("grade=" + gradeList.get(0));
			// 判断是小学 初中 高中
			// System.out.println("level=" + gradeList.get(1));
			// 判断上学期 还是 下学期
			// System.out.println("term=" + gradeList.get(2));
			// 判断 版本
			String press = getPress(v.get(0).getPressStr());
			// System.out.println("press=" + press);
			// 判断 测评 类型 1:同步测评 2:阶段性测评 3:专题测评
			int type = getType(v.get(0).getTypeStr());
			// System.out.println("type=" + type);
			// 计算测试的时间
			String time = v.get(0).getEstimatedTimeStr();
            int estimatedTime=0;
			if(time!=null){
                String[] times = time.replace("分钟", "").split("-");
                 estimatedTime = (Integer.parseInt(times[1].replaceAll(" ", ""))
                        - Integer.parseInt(times[0].replaceAll("　", ""))) / 2
                        + Integer.parseInt(times[0].replaceAll("　", ""));
                estimatedTime *= 60;
                // System.out.println("estimatedTime=" + estimatedTime);
            }

			System.out.println("-- 新增topic_info 数据");
			System.out.println(
					"insert into topic_info (topicId, serialNumber, type, `level`, grade, term, press, estimatedTime, createdBy, notes)values");
			String topicInfosql = String.format("(%d,%s,%d,%s,%s,%s,%s,%s,'libo','通用版');", topicId,
					"'" + v.get(0).getSerialNumber() + "'", type, gradeList.get(1), gradeList.get(0), gradeList.get(2),
					press, estimatedTime);
			System.out.println(topicInfosql);
			System.out.println();

			// 生成topic_kpoint sql 脚本
			System.out.println("-- 新增topic_kpoint 数据");
			System.out.println("insert into topic_kpoint (createdBy, topicId, kpointId, preCondition, `group`) values");
			for (int i = 0; i < v.size(); i++) {
				int group = i / 14 + 1;
				String topicKpointSql = String.format("('libo', %d, %s, '', %d)", topicId, v.get(i).getKpointId(),
						group);
				if (i < v.size() - 1)
					topicKpointSql += ",";
				else
					topicKpointSql += ";\n";
				System.out.println(topicKpointSql);
			}
			topicId++;
			id++;
			list.add(0, topicId);
			list.add(1, id);
		}
		return list;
	}

	public Map<String, List<Topic>> readExcel(String url) {
		Workbook workbook = null;
		FileInputStream excelFile = null;
		Map<String, List<Topic>> topicMap = new LinkedHashMap<>();
		try {
			excelFile = new FileInputStream(new File(url));
			workbook = new XSSFWorkbook(excelFile);
			Iterator<Sheet> sheetIt = workbook.iterator();
			while (sheetIt.hasNext()) {
				readSheet(sheetIt, topicMap);
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (Exception e) {
			System.err.println("Error happended when reading spreasheet: " + url);
			e.printStackTrace();
		} finally {
			try {
				excelFile.close();
				workbook.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return topicMap;
	}

	private void readSheet(Iterator<Sheet> sheetIt, Map<String, List<Topic>> topicMap) {
		Sheet datatypeSheet = sheetIt.next();
		String postfix = datatypeSheet.getSheetName();
		Iterator<Row> iterator = datatypeSheet.iterator();
		int count = 0;
		String tempTopicNameL1 = null;
		String serialNumbe = null;
		String pressStr = null;
		String gradeStr = null;
		String categoryStr = null;
		String estimatedTimeStr = null;
		String typeStr = null;
		Map<Integer, String> colName = new HashMap<>();
		while (iterator.hasNext()) { // row of sheet
			if (count == 0) {
				iterator.next();
				count++;
				continue;
			}
			if (count == 1) {
				Row colNameRow = iterator.next();
				count++;
				Iterator<Cell> cellIt = colNameRow.cellIterator();
				for (int i = 0; cellIt.hasNext(); i++) {
					Cell cell = cellIt.next();
					String title = cell.getStringCellValue().trim();
					colName.put(i, title);
				}
				continue;
			}
			Row currentRow = iterator.next();
			Topic topic = new Topic();

			Iterator<Cell> cellIt = currentRow.cellIterator();
			for (int i = 0; cellIt.hasNext(); i++) {
				Cell cell = cellIt.next();
				if (TITLE_NAME.equals(colName.get(i))) {// 一级专题名
					String stringCellValue = cell.getStringCellValue().trim();
					if (tempTopicNameL1 == null || (!("").equals(stringCellValue))
							&& (!stringCellValue.startsWith("U") || !stringCellValue.startsWith("M"))) {
						tempTopicNameL1 = stringCellValue + postfix;
					}
					topic.setTopicNameL1(tempTopicNameL1);
					continue;
				}
				if (TITLE_NO.equals(colName.get(i))) {// 编号
					String stringCellValue = cell.getStringCellValue().trim();
					if (serialNumbe == null || (!("").equals(stringCellValue))
							&& (!stringCellValue.startsWith("U") || !stringCellValue.startsWith("M"))) {
						serialNumbe = stringCellValue;
					}
					topic.setSerialNumber(serialNumbe);
					continue;
					// continue;
					// try {
					// topic.setNo((int) cell.getNumericCellValue());
					// } catch (Exception e) {
					// topic.setNo(Integer.parseInt(cell.getStringCellValue().trim()));
					// }
					// continue;
				}
				if (TITLE_PRESS.equals(colName.get(i))) {// 版本
					String stringCellValue = cell.getStringCellValue().trim();
					if (pressStr == null || (!("").equals(stringCellValue))
							&& (!stringCellValue.startsWith("U") || !stringCellValue.startsWith("M"))) {
						pressStr = stringCellValue;
					}
					topic.setPressStr(pressStr);
					continue;
				}
				if (TITLE_GRADE.equals(colName.get(i))) {// 年级
					String stringCellValue = cell.getStringCellValue().trim();
					if (gradeStr == null || (!("").equals(stringCellValue))
							&& (!stringCellValue.startsWith("U") || !stringCellValue.startsWith("M"))) {
						gradeStr = stringCellValue;
					}
					topic.setGradeStr(gradeStr);
					continue;
				}
				if (TITLE_CATEGORY.equals(colName.get(i))) {// 科目
					String stringCellValue = cell.getStringCellValue().trim();
					if (categoryStr == null || (!("").equals(stringCellValue))
							&& (!stringCellValue.startsWith("U") || !stringCellValue.startsWith("M"))) {
						categoryStr = stringCellValue;
					}
					topic.setCategoryStr(categoryStr);
					continue;
				}
				if (TITLE_TIME.equals(colName.get(i))) {// 使用时间
					String stringCellValue = cell.getStringCellValue().trim();
					if (estimatedTimeStr == null || (!("").equals(stringCellValue))
							&& (!stringCellValue.startsWith("U") || !stringCellValue.startsWith("M"))) {
						estimatedTimeStr = stringCellValue;
					}
					topic.setEstimatedTimeStr(estimatedTimeStr);
					continue;
				}
				if (TITLE_TYPE.equals(colName.get(i))) {// 类型
					String stringCellValue = cell.getStringCellValue().trim();
					if (typeStr == null || (!("").equals(stringCellValue))
							&& (!stringCellValue.startsWith("U") || !stringCellValue.startsWith("M"))) {
						typeStr = stringCellValue;
					}
					topic.setTypeStr(typeStr);
					continue;
				}
				if (TITLE_ID.equals(colName.get(i))) {
					topic.setKpointId(cell.getStringCellValue().trim());
					continue;
				}

				if (TITLE_DESC.equals(colName.get(i))) {
					topic.setDescription(cell.getStringCellValue().trim());
					continue;
				}
			}

			populate(topicMap, topic);
			count++;
		}
	}

	private void populate(Map<String, List<Topic>> topicMap, Topic topic) {
		if (topic == null || topic.getKpointId() == null || topic.getKpointId().equals("")) {
			// System.out.println("null property value found");
			return;
		}
		String topicNameL1 = topic.getTopicNameL1().trim();
		if (topicMap.containsKey(topicNameL1)) {
			topicMap.get(topicNameL1).add(topic);
		} else {
			List<Topic> topics = new ArrayList<>();
			topics.add(topic);
			topicMap.put(topicNameL1, topics);
		}
	}

	/*
	 * Write sql for great master graphs
	 */
	private void writeSqlMasterG(int topicId, int group, String... urls) {
		Map<String, List<Topic>> topicMap = null;
		for (String url : urls) {
			topicMap = readExcel(url);
			writeSqlMasterG(topicMap, topicId, group);
		}

	}

	private int writeSqlMasterG(Map<String, List<Topic>> topicMap, int topicId, int group) {
		for (Map.Entry<String, List<Topic>> entry : topicMap.entrySet()) {
			String k = entry.getKey();
			List<Topic> v = entry.getValue();
			System.out.println("-- " + k);
			System.out.println("insert into topic_kpoint (createdBy, topicId, kpointId, preCondition, `group`) values");
			for (int i = 0; i < v.size(); i++) {
				String topicKpointSql = String.format("('libo', %d, %s, '', %d)", topicId, v.get(i).getKpointId(),
						group);
				if (i < v.size() - 1)
					topicKpointSql += ",";
				else
					topicKpointSql += ";\n";
				System.out.println(topicKpointSql);
			}
		}
		return topicId;
	}

	private List getGrade(String gradeStr) {
		List list = new ArrayList<>();
		int grade = 0;
		int level = 2;
		int term = 1;
		if ("九年级".equals(gradeStr) || "九年级(下学期)".equals(gradeStr) || "九年级（下）".equals(gradeStr)
				|| "初中".equals(gradeStr)) {
			grade = 13;
			level = 2;
			term = 2;
		} else if ("七年级(下学期)".equals(gradeStr) || "七年级（下）".equals(gradeStr)) {
			grade = 9;
			level = 2;
			term = 2;
		} else if ("八年级(下学期)".equals(gradeStr) || "八年级（下）".equals(gradeStr)) {
			grade = 11;
			level = 2;
			term = 2;
		} else if ("六年级（上）".equals(gradeStr) || "六年级(上学期)".equals(gradeStr)) {
			grade = 6;
			level = 1;
			term = 1;
		} else if ("六年级（下）".equals(gradeStr) || "六年级(下学期)".equals(gradeStr)) {
			grade = 7;
			level = 1;
			term = 2;
		} else if ("七年级（上）".equals(gradeStr) || "七年级(上学期)".equals(gradeStr)) {
			grade = 8;
			level = 2;
			term = 1;
		} else if ("八年级（上）".equals(gradeStr) || "八年级(上学期)".equals(gradeStr)) {
			grade = 10;
			level = 2;
			term = 1;
		} else if ("九年级（上）".equals(gradeStr) || "九年级(上学期)".equals(gradeStr)) {
			grade = 12;
			level = 2;
			term = 1;
		} else if ("小升初".equals(gradeStr)) {
			grade = 5;
			level = 1;
		}
		list.add(0, grade);
		list.add(1, level);
		list.add(2, term);
		return list;
	}

	private int getType(String typeStr) {
		int type = 0;
		if ("专题测评".equals(typeStr)) {
			type = 3;
		} else if ("同步测评".equals(typeStr)) {
			type = 1;
		} else if ("阶段性测评".equals(typeStr)) {
			type = 2;
		}
		return type;
	}

	private String getPress(String banben) {
		String press = "";
		if ("人教版".equals(banben)) {
			press = "1";
		} else if ("苏科版".equals(banben)) {
			press = "2";
		} else if ("浙教版".equals(banben)) {
			press = "3";
		} else if ("北师大版".equals(banben)) {
			press = "4";
		} else if ("冀教版".equals(banben)) {
			press = "5";
		} else if ("沪教版".equals(banben)) {
			press = "6";
		} else if ("华师大版".equals(banben)) {
			press = "7";
		} else if ("外研版".equals(banben)) {
			press = "8";
		} else if ("牛津版".equals(banben)) {
			press = "9";
		} else if ("苏教译林版".equals(banben)) {
			press = "10";
		} else if ("通用版".equals(banben)) {
			press = "11";
		}
		return press;
	}
}
