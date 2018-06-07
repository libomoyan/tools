package com.example.backendtools.api.EnglishTest;

public class Topic {
	private String topicNameL1;
	private String kpointId;
	private int no;
	private String description;
	private String serialNumber;// 编号
	private String gradeStr;// 年级: 6:六年级上 7:六年级下 8:七年级上 9:七年级下 10:八年级上 11:八年级下 12:九年级上 13:九年级下
	private int level;// 年级段 1:小学 2：初中 3：高中
	private String termStr;// 学期 1:第一学期 2:第二学期
	private String pressStr;// 版本
	private String categoryStr;// 科目
	private String typeStr;// 类型 1:同步测评 2:阶段性测评 3:专题测评
	private String estimatedTimeStr;// 测试需要的时间

	public String getGradeStr() {
		return gradeStr;
	}

	public void setGradeStr(String gradeStr) {
		this.gradeStr = gradeStr;
	}

	public int getLevel() {
		return level;
	}

	public void setLevel(int level) {
		this.level = level;
	}

	public String getTermStr() {
		return termStr;
	}

	public void setTermStr(String termStr) {
		this.termStr = termStr;
	}

	public String getPressStr() {
		return pressStr;
	}

	public void setPressStr(String pressStr) {
		this.pressStr = pressStr;
	}

	public String getCategoryStr() {
		return categoryStr;
	}

	public void setCategoryStr(String categoryStr) {
		this.categoryStr = categoryStr;
	}

	public String getTypeStr() {
		return typeStr;
	}

	public void setTypeStr(String typeStr) {
		this.typeStr = typeStr;
	}

	public String getEstimatedTimeStr() {
		return estimatedTimeStr;
	}

	public void setEstimatedTimeStr(String estimatedTimeStr) {
		this.estimatedTimeStr = estimatedTimeStr;
	}

	public String getSerialNumber() {
		return serialNumber;
	}

	public void setSerialNumber(String serialNumber) {
		this.serialNumber = serialNumber;
	}

	public String getTopicNameL1() {
		return topicNameL1;
	}

	public void setTopicNameL1(String topicNameL1) {
		this.topicNameL1 = topicNameL1;
	}

	public String getKpointId() {
		return kpointId;
	}

	public void setKpointId(String kpointId) {
		this.kpointId = kpointId;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public int getNo() {
		return no;
	}

	public void setNo(int no) {
		this.no = no;
	}
}
