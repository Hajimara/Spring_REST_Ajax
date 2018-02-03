package org.zerock.domain;

import java.util.Date;

/*	����persistence���� ����Ͻ� ����
 * ���̺�� ���õ� BoardVO Ŭ������ �����ϰ� �ۼ�
 * MyBatis�� �̿��ϴ� BoardDAO,BoardDAOImpl�� �ۼ�
 * Resources �������� XML Mapper�� �ۼ�
 * src/test/java ���� BoardMapperTests.java�� �ۼ��ϰ� ������� DAO�� SQL�� ������ �׽�Ʈ �Ѵ�.
 * 1. ������� ���̺��� ������ ��üȭ ��ų �� ����ϴ� BoardVOŬ������ �ۼ��Ѵ�.
 * 		���̺��� Į���̸��� VO�� �Ӽ��̸��� �����ϰ� ���ִ� ���� ����.
 *		VO�� ��ü �������� �Ķ���Ϳ� ���� Ÿ������ ���ǹǷ� ���� ����!! �۾��ǰ�
 * 2. ���Ŀ��� MyBatis�� DAO, XML Mapper�� �������ְ� �׽�Ʈ ���ش�.
 * 3. root-context.xml�� SessionFactory�� sqlSessionTemplate�� �߰����ش�.
 * 4. BoardDAO�� �����Ѵ� DB�� value object�� ����? CRUD �۾��� ����
 * 5. src/main/resources���� mappers�� XML Mapper���� SQLó�� �ۼ�
 * */

public class BoardVO {

	private Integer bno;
	private String title;
	private String content;
	private String writer;
	private Date regdate;
	private int viewcnt;
	private int replycnt;

	private String[] files;

	public Integer getBno() {
		return bno;
	}

	public void setBno(Integer bno) {
		this.bno = bno;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getWriter() {
		return writer;
	}

	public void setWriter(String writer) {
		this.writer = writer;
	}

	public int getViewcnt() {
		return viewcnt;
	}

	public void setViewcnt(int viewcnt) {
		this.viewcnt = viewcnt;
	}

	public Date getRegdate() {
		return regdate;
	}

	public void setRegdate(Date regdate) {
		this.regdate = regdate;
	}

	@Override
	public String toString() {
		return "bno : " + bno + "<p>title : " + title + "<p>content : " + content + "<p>writer : " + writer
				+ "<p>regdate : " + regdate + "<p>viewcnt : " + viewcnt + "<p>files : " + files + "<p>replycnt : "
				+ replycnt;
	}

	public int getReplycnt() {
		return replycnt;
	}

	public void setReplycnt(int replycnt) {
		this.replycnt = replycnt;
	}

	public String[] getFiles() {
		return files;
	}

	public void setFiles(String[] files) {
		this.files = files;
	}
}
