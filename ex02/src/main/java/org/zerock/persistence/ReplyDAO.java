package org.zerock.persistence;

import java.util.List;

import org.zerock.domain.Criteria;
import org.zerock.domain.ReplyVO;

public interface ReplyDAO {
	
	public List<ReplyVO> list(Integer bno) throws Exception;
	
	public void create(ReplyVO vo) throws Exception;
	
	public void update(ReplyVO vo) throws Exception;
	
	public void delete(Integer rno) throws Exception;
	
	public List<ReplyVO> listPage(Integer bno,Criteria cri)throws Exception;
	//����¡ó��
	public int count(Integer bno) throws Exception;
	//�Խù��� ��� �� 
	public int getBno(Integer bno) throws Exception;
	
}
