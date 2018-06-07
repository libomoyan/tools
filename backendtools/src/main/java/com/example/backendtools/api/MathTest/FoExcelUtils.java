package com.example.backendtools.api.MathTest;

import com.google.common.collect.Lists;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.ss.util.CellRangeAddress;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 读取 大图谱 Excel 数据 形成 sql 数据
 * 
 * 
 * Created by luohao on 2017/5/25.
 */
public class FoExcelUtils {

	public static final Map<Integer, String> subjects = new HashMap<Integer, String>() {
		private static final long serialVersionUID = 3483754929367672584L;
		{
			// 测评
			put(81, "9年级上学期测评");

		}
	};

	public static void main(String[] args) {
		FoExcelUtils g = new FoExcelUtils();
		List<Topic> topicList = null;

		List urls = new ArrayList<>();


		List list5 = new ArrayList<>();
		list5.add("D:\\测评\\需要的测评数据\\中考专题\\数学中考专题测评\\中考专题0531.xlsx");

		 urls.addAll(list5);

		int topicId = 6894;
		int subjectId = 75;

		// 循环 读取 Excel 文件
		for (int i = 0; i < urls.size(); i++) {
			List<Topic> lists = readExcelToObj(urls.get(i).toString(), 8, topicId, subjectId);
			int le = lists.size();
			topicId = lists.get(le - 1).getId();
			topicId++;
			subjectId++;
		}

	}

	/**
	 * 读取excel数据
	 * 
	 * @param path
	 */
	public static List<Topic> readExcelToObj(String path, int type, int topicId, int subjectId) {
		try {
			InputStream inputStream = new FileInputStream(path);
			// 获取 topic专题集合
			List<Topic> topics = readExcelToObj(inputStream, 0, topicId, subjectId);

			System.out.println();
			for (Topic topic : topics) {
				System.out.println();
				// System.out.println("-- 插入topic数据");
				String topicSql = "insert into topic(id,`name`,createdBy,category,subjectId,useStep2,maxPreStudyQuestion,maxPreStudyQuestionS2,algo,isMaster,useTree)values";
				// 生成 topic 脚本
				// System.out.println(topicSql);
				String sql3 = String.format("(%d,%s,'libo',1,%d,%s,%d,%d,%d,%s,%s);", topic.getId(),
						"'" + topic.getName() + "'", subjectId, "b'" + topic.getUseStep2() + "'",
						topic.getMaxPreStudyQuestion(), topic.getMaxPreStudyQuestionS2(), topic.getAlgo(),
						"b'" + topic.getIsMaster() + "'", "b'" + topic.getUseTree() + "'");
				// System.out.println(sql3);


				// 获取 topic_kpoint知识点集合
				List<TopicKpoint> list = topic.getTopicKpointList();
				System.out.println();
				// System.out.println("-- " + list.size() + "条");
				// System.out.println("-- 插入 topic_kpoint 数据");
				String sql = "insert into topic_kpoint(topicId,kpointId,createdBy,preCondition,`group`)values";
				// System.out.println(sql);
				int j = 0;
				for (TopicKpoint topicKpoint : list) {
					String sqls = String.format("(%d,%d,'libo',%s,1)", topicKpoint.getTopicId(),
							topicKpoint.getKpointId(), "'" + topicKpoint.getPreConditions() + "'");
					j++;
					if (list.size() != j) {
						sqls += ",";
					} else {
						sqls += ";\n";
					}
					// System.out.println(sqls);
				}
				System.out.println("-- " + list.size() + "条");
				System.out.println("-- 插入 topic_kpoint 大图谱数据");
				String sql1 = "insert into topic_kpoint(createdBy,topicId,kpointId,preCondition,`group`)values";
				System.out.println(sql1);
				int k = 0;
				for (TopicKpoint topicKpoint : list) {
					String sql2 = String.format("('libo',2000,%d,%s,579)", topicKpoint.getKpointId(),
							"'" + topicKpoint.getPreConditions() + "'");
					k++;
					if (list.size() != k) {
						sql2 += ",";
					} else {
						sql2 += ";\n";
					}
					System.out.println(sql2);
				}

			}
			return topics;
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * 读取excel数据
	 * 
	 * @param ins
	 * @param type
	 */
	public static List<Topic> readExcelToObj(InputStream ins, int type, int topicId, int subjectId) {
		Workbook wb = null;
		try {
			wb = WorkbookFactory.create(ins);
			Integer numberOfSheets = wb.getNumberOfSheets();
			List all = Lists.newArrayList();
			List topicList = Lists.newArrayList();
			for (int i = 0; i < numberOfSheets; i++) {
				List<TopicKpoint> list = null;
				list = readReadingExcel(wb, i, 0, 0, i + 1);
				all.addAll(list);
				Topic topic = new Topic();
				topic.setId(topicId);
				for (int j = 0; j < list.size(); j++) {
					list.get(j).setTopicId((long) topicId);
					list.get(j).setGroup(i + 1);
				}

				topic.setName(wb.getSheetName(i));
				topicId++;
				// 判断知识点的 数量
				// 1、如果 知识点数量 list.size<=5 algo 赋值为 9
				// 2、如果 知识点的数量 5<list.size<=20 algo 赋值为 0
				// 3、如果 知识点的数量 20<list.size algo 赋值为 8
				if (list.size() > 0 && list != null) {
					Integer num = list.size();
					if (num <= 5) {
						topic.setAlgo(9);
					} else if (5 < num && num <= 20) {
						topic.setAlgo(0);
					} else if (num > 20) {
						topic.setAlgo(8);
					}
				}
				// 如果知识点的数量 不大于25 maxPreStudyQuestion 赋值为 25 反之 赋值为 具体数量
				if (list.size() > 25) {
					topic.setMaxPreStudyQuestion(list.size());
				} else {
					topic.setMaxPreStudyQuestion(25);
				}
				topic.setTopicKpointList(list);
				topicList.add(topic);
			}

			return topicList;
		} catch (InvalidFormatException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * 读取excel文件
	 * 
	 * @param wb
	 * @param sheetIndex
	 *            sheet页下标：从0开始
	 * @param startReadLine
	 *            开始读取的行:从0开始
	 * @param tailLine
	 *            去除最后读取的行
	 */
	private static List<TopicKpoint> readReadingExcel(Workbook wb, int sheetIndex, int startReadLine, int tailLine,
                                                      int topicId) {

		Sheet sheet = wb.getSheetAt(sheetIndex);
		Row row = null;
		List<TopicKpoint> topicKpointList = new ArrayList<TopicKpoint>();
		Map<Integer, TopicKpoint> questionForMaintainMap = new HashMap<Integer, TopicKpoint>();
		for (int i = startReadLine; i < sheet.getLastRowNum() - tailLine + 1; i++) {

			row = sheet.getRow(i);
			if (i == 0) {
				continue;
			} // 过滤excel表里第一行数据

			if (row == null || row.getCell(9) == null || row.getCell(9).getNumericCellValue() == 0)
				continue;
			TopicKpoint question = null;
			if (questionForMaintainMap.get(i) == null) {
				question = new TopicKpoint();
				topicKpointList.add(question);
			} else {
				question = questionForMaintainMap.get(i);
			}
			question.setTopicId(Long.parseLong(topicId + ""));
			for (Cell c : row) {
				c.setCellType(Cell.CELL_TYPE_STRING);
				boolean isMerge = isMergedRegion(sheet, i, c.getColumnIndex());
				String rs;
				// 判断是否具有合并单元格
				if (isMerge) {
					rs = getMergedRegionValue(sheet, row.getRowNum(), c.getColumnIndex());
					List<Integer> rows = getMergedRegionRows(sheet, row.getRowNum(), c.getColumnIndex());
					for (Integer mergeRow : rows) {
						if (questionForMaintainMap.get(mergeRow) == null) {
							questionForMaintainMap.put(mergeRow, question);
						}
					}
				} else {
					rs = c.getRichStringCellValue() + "";
				}
				if (rs == null || rs.trim().equals(""))
					continue;
				// System.out.println(rs + " ");
				// 先验条件
				String preConditions = "";
				if (c.getColumnIndex() == 9) {
					question.setKpointId(Long.parseLong(rs.trim()));
				} else if (c.getColumnIndex() == 13) {
					// 先验条件1
					preConditions = rs;
				} else if (c.getColumnIndex() == 14) {
					// 先验条件2
					preConditions = question.getPreConditions().trim() + "," + rs;
				} else if (c.getColumnIndex() == 15) {
					// 先验条件3
					preConditions = question.getPreConditions().trim() + "," + rs;
				} else if (c.getColumnIndex() == 16) {
					// 先验条件4
					preConditions = question.getPreConditions().trim() + "," + rs;
				} else if (c.getColumnIndex() == 17) {
					// 先验条件5
					preConditions = question.getPreConditions().trim() + "," + rs;
				} else if (c.getColumnIndex() == 18) {
					// 先验条件6
					preConditions = question.getPreConditions().trim() + "," + rs;
				} else if (c.getColumnIndex() == 19) {
					// 先验条件7
					preConditions = question.getPreConditions().trim() + "," + rs;
				}
				question.setPreConditions(preConditions);

			}
			// System.out.println();
		}
		return topicKpointList;
	}

	/**
	 * 获取合并单元格的值
	 * 
	 * @param sheet
	 * @param row
	 * @param column
	 * @return
	 */
	public static String getMergedRegionValue(Sheet sheet, int row, int column) {

		int sheetMergeCount = sheet.getNumMergedRegions();

		for (int i = 0; i < sheetMergeCount; i++) {
			CellRangeAddress ca = sheet.getMergedRegion(i);
			int firstColumn = ca.getFirstColumn();
			int lastColumn = ca.getLastColumn();
			int firstRow = ca.getFirstRow();
			int lastRow = ca.getLastRow();

			if (row >= firstRow && row <= lastRow) {

				if (column >= firstColumn && column <= lastColumn) {
					Row fRow = sheet.getRow(firstRow);
					Cell fCell = fRow.getCell(firstColumn);
					return getCellValue(fCell);
				}
			}
		}

		return null;
	}

	private static List<Integer> getMergedRegionRows(Sheet sheet, int row, int column) {
		List<Integer> list = new ArrayList<Integer>();
		int sheetMergeCount = sheet.getNumMergedRegions();

		for (int i = 0; i < sheetMergeCount; i++) {
			CellRangeAddress ca = sheet.getMergedRegion(i);
			int firstColumn = ca.getFirstColumn();
			int lastColumn = ca.getLastColumn();
			int firstRow = ca.getFirstRow();
			int lastRow = ca.getLastRow();

			if (row >= firstRow && row <= lastRow) {

				if (column >= firstColumn && column <= lastColumn) {
					for (int j = firstRow; j <= lastRow; j++) {
						list.add(j);
					}
				}
			}
		}
		return list;
	}

	/**
	 * 判断合并了行
	 * 
	 * @param sheet
	 * @param row
	 * @param column
	 * @return
	 */
	private boolean isMergedRow(Sheet sheet, int row, int column) {

		int sheetMergeCount = sheet.getNumMergedRegions();
		for (int i = 0; i < sheetMergeCount; i++) {
			CellRangeAddress range = sheet.getMergedRegion(i);
			int firstColumn = range.getFirstColumn();
			int lastColumn = range.getLastColumn();
			int firstRow = range.getFirstRow();
			int lastRow = range.getLastRow();
			if (row == firstRow && row == lastRow) {
				if (column >= firstColumn && column <= lastColumn) {
					return true;
				}
			}
		}
		return false;
	}

	/**
	 * 判断指定的单元格是否是合并单元格
	 * 
	 * @param sheet
	 * @param row
	 *            行下标
	 * @param column
	 *            列下标
	 * @return
	 */
	private static boolean isMergedRegion(Sheet sheet, int row, int column) {

		int sheetMergeCount = sheet.getNumMergedRegions();
		for (int i = 0; i < sheetMergeCount; i++) {

			CellRangeAddress range = sheet.getMergedRegion(i);
			int firstColumn = range.getFirstColumn();
			int lastColumn = range.getLastColumn();
			int firstRow = range.getFirstRow();
			int lastRow = range.getLastRow();
			// System.out.println("first
			// column:"+firstColumn+","+"lastColumn:"+lastColumn+","+"first
			// row:"+firstRow+","+lastRow);
			if (row >= firstRow && row <= lastRow) {
				if (column >= firstColumn && column <= lastColumn) {
					return true;
				}
			}
		}
		return false;
	}

	/**
	 * 判断sheet页中是否含有合并单元格
	 * 
	 * @param sheet
	 * @return
	 */
	private boolean hasMerged(Sheet sheet) {
		return sheet.getNumMergedRegions() > 0 ? true : false;
	}

	/**
	 * 合并单元格
	 * 
	 * @param sheet
	 * @param firstRow
	 *            开始行
	 * @param lastRow
	 *            结束行
	 * @param firstCol
	 *            开始列
	 * @param lastCol
	 *            结束列
	 */
	private void mergeRegion(Sheet sheet, int firstRow, int lastRow, int firstCol, int lastCol) {
		sheet.addMergedRegion(new CellRangeAddress(firstRow, lastRow, firstCol, lastCol));
	}

	/**
	 * 获取单元格的值
	 * 
	 * @param cell
	 * @return
	 */
	public static String getCellValue(Cell cell) {

		if (cell == null)
			return "";

		if (cell.getCellType() == Cell.CELL_TYPE_STRING) {

			return cell.getStringCellValue();

		} else if (cell.getCellType() == Cell.CELL_TYPE_BOOLEAN) {

			return String.valueOf(cell.getBooleanCellValue());

		} else if (cell.getCellType() == Cell.CELL_TYPE_FORMULA) {

			return cell.getCellFormula();

		} else if (cell.getCellType() == Cell.CELL_TYPE_NUMERIC) {

			return String.valueOf(cell.getNumericCellValue());

		}
		return "";
	}
}
