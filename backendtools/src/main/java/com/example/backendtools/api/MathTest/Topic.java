package com.example.backendtools.api.MathTest;

import java.util.List;

public class Topic {
	private Integer id;
	private String name;
	private Integer category;
	private Integer subjectId;
	private Integer useStep2;
	private Integer maxPreStudyQuestion;
	private Integer maxPreStudyQuestionS2;
	private Integer algo;
	private Integer isMaster;
	private Integer useTree;
	private List<TopicKpoint> topicKpointList;

	public Topic() {
		useStep2 = 1;
		maxPreStudyQuestion = 25;
		maxPreStudyQuestionS2 = 0;
		algo = 0;
		isMaster = 1;
		useTree = 1;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Integer getCategory() {
		return category;
	}

	public void setCategory(Integer category) {
		this.category = category;
	}

	public Integer getUseStep2() {
		return useStep2;
	}

	public void setUseStep2(Integer useStep2) {
		this.useStep2 = useStep2;
	}

	public Integer getMaxPreStudyQuestion() {
		return maxPreStudyQuestion;
	}

	public void setMaxPreStudyQuestion(Integer maxPreStudyQuestion) {
		this.maxPreStudyQuestion = maxPreStudyQuestion;
	}

	public Integer getMaxPreStudyQuestionS2() {
		return maxPreStudyQuestionS2;
	}

	public void setMaxPreStudyQuestionS2(Integer maxPreStudyQuestionS2) {
		this.maxPreStudyQuestionS2 = maxPreStudyQuestionS2;
	}

	public Integer getAlgo() {
		return algo;
	}

	public void setAlgo(Integer algo) {
		this.algo = algo;
	}

	public Integer getIsMaster() {
		return isMaster;
	}

	public void setIsMaster(Integer isMaster) {
		this.isMaster = isMaster;
	}

	public Integer getUseTree() {
		return useTree;
	}

	public void setUseTree(Integer useTree) {
		this.useTree = useTree;
	}

	public List<TopicKpoint> getTopicKpointList() {
		return topicKpointList;
	}

	public void setTopicKpointList(List<TopicKpoint> topicKpointList) {
		this.topicKpointList = topicKpointList;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getSubjectId() {
		return subjectId;
	}

	public void setSubjectId(Integer subjectId) {
		this.subjectId = subjectId;
	}

}
