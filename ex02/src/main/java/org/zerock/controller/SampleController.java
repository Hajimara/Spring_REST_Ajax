package org.zerock.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.javassist.tools.rmi.Sample;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.zerock.domain.SampleVO;

@RestController
@RequestMapping("/sample")
public class SampleController {

	@RequestMapping("/hello")
	public String sayHello() {
		return "Hello World";
	}
	
	@RequestMapping("/sendVO")
	public SampleVO sendVO() {
		
		SampleVO sampleVO = new SampleVO();
		sampleVO.setFirstName("ȫ");
		sampleVO.setLastName("�浿");
		sampleVO.setMno(123);
		
		return sampleVO;
	}
	@RequestMapping("/sendList")
	public List<SampleVO> sendList(){
		
		List<SampleVO> list = new ArrayList<SampleVO>();
		for (int i = 0; i < 10 ; i++) {
			SampleVO sampleVO = new SampleVO();
			sampleVO.setFirstName("ȫ");
			sampleVO.setLastName("�浿");
			sampleVO.setMno(i);
			list.add(sampleVO);
		}
		
		return list;
	}
	@RequestMapping("/sendMap")
	public Map<Integer,SampleVO> sendMap(){
		
		Map<Integer,SampleVO> map = new HashMap<Integer,SampleVO>();
		
		for (int i = 0; i < 10 ; i++) {
			SampleVO sampleVO = new SampleVO();
			sampleVO.setFirstName("ȫ");
			sampleVO.setLastName("�浿");
			sampleVO.setMno(i);
			map.put(i,sampleVO);
		}
		
		return map;
	}
	@RequestMapping("/sendErrorAuth")
	public ResponseEntity<Void> sendListAuth(){
		
		return new ResponseEntity<Void>(HttpStatus.BAD_REQUEST);
	}
	
	@RequestMapping("/sendErrorNot")
	public ResponseEntity<List<SampleVO>> sendListNot(){
		List<SampleVO> list = new ArrayList<SampleVO>();
		for (int i = 0; i < 10 ; i++) {
			SampleVO sampleVO = new SampleVO();
			sampleVO.setFirstName("ȫ");
			sampleVO.setLastName("�浿");
			sampleVO.setMno(i);
			list.add(sampleVO);
		}
		return new ResponseEntity<List<SampleVO>>(list,HttpStatus.NOT_FOUND);
	}
}
