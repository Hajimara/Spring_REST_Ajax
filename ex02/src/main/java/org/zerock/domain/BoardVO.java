package org.zerock.domain;

import java.util.Date;

/*	영속persistence계층 비즈니스 계층
 * 테이블과 관련된 BoardVO 클래스를 설계하고 작성
 * MyBatis를 이용하는 BoardDAO,BoardDAOImpl을 작성
 * Resources 폴더내에 XML Mapper를 작성
 * src/test/java 밑의 BoardMapperTests.java를 작성하고 만들어진 DAO와 SQL의 동작을 테스트 한다.
 * 1. 가장먼저 테이블의 구조를 객체화 시킬 때 사용하는 BoardVO클래스를 작성한다.
 * 		테이블의 칼럼이름과 VO의 속성이름을 동일하게 해주는 것이 좋다.
 *		VO는 전체 영역에서 파라미터와 리턴 타입으로 사용되므로 가장 먼저!! 작업되고
 * 2. 이후에는 MyBatis의 DAO, XML Mapper를 설정해주고 테스트 해준다.
 * 3. root-context.xml에 SessionFactory와 sqlSessionTemplate을 추가해준다.
 * 4. BoardDAO를 생성한다 DB와 value object를 연결? CRUD 작업을 실행
 * 5. src/main/resources에서 mappers에 XML Mapper에서 SQL처리 작성
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
