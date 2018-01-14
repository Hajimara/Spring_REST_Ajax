package org.zerock.controller;

import javax.inject.Inject;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.zerock.domain.BoardVO;
import org.zerock.domain.Criteria;
import org.zerock.domain.PageMaker;
import org.zerock.service.BoardService;

/*	컨트롤러 선언
 * Board의 모든 공통 경로를 /board/로 인식할 수 있게 한다.
 * 등록을 위한 입력 페이지를 보는경우 GET 실제로 데이터를 처리하는 부분 POST로 구분
 * GET은 입력페이지와 조회페이지
 * POST는 외부에서 많은 정보를 입력하는 경우에 사용 보여지면 안되는 정보를 전송하는데 사용
 * 실제로 화면에서 입력되어 들어오는 데이터를 처리하는 registPOST() 메소드의 파라미터는 자동으로
 * 모든 데이터를 BoardVO로 수집하도록 하는 부분과 향후에 뷰로 데이터를 전달할 가능성이 있으므로 Model 클래스의 객체를 받도록 설계
 * */

@Controller
@RequestMapping("/board/*")
public class BoardController {

  private static final Logger logger = LoggerFactory.getLogger(BoardController.class);

  @Inject
  private BoardService service;

  @RequestMapping(value = "/register", method = RequestMethod.GET)
  public void registerGET(BoardVO board, Model model) throws Exception {

    logger.info("register get ...........");
  }

//	@RequestMapping(value="/register" method= {RequestMethod.GET, RequestMethod.POST})
//	필요한 경우 둘다 배열로 선언 가능
//  내부적으로 ViewResolver라는 클래스가 있다 
  //이 클래스는 해당 메소드의 리턴 타입에 따라서 동작을 달리 함. void의 경우에는 해당 URI에 맞는 view를 자동으로 찾도록 설계되어 있기 때문에 
//  void인 경우에는 자동으로 해당 jsp를 찾도록 되어 있음. servlet-context.xml을 보면 ViewResolver가 jsp기반으로 설정된 것을 볼 수 있다. 
//
//  @RequestMapping( )은 GET, POST에 관계없이 동작하고, method 속성을 지정하는 경우에는 GET/POST에 따라서 구분할 수 있는 차이가 있다.
//  
	@RequestMapping(value="/register", method = RequestMethod.POST)
	public String registPOST(BoardVO board, RedirectAttributes rttr)throws Exception {
		logger.info("regist post ........");
		logger.info(board.toString());
		
		service.regist(board);
		
		rttr.addFlashAttribute("msg","success");     
		return "redirect:/board/listAll";

	}
	@RequestMapping(value="/listAll",method=RequestMethod.GET)
	public void listAll(Model model)throws Exception{
		logger.info("show all list.......");
		model.addAttribute("list",service.listAll());
	}
	/*파라미터는 외부에서 전달될 bno값을 전달 받는다
	 * @RequestParam을 이용하고 조회결과 게시물을 JSP로 전달해야 하기때문에
	 * Model 객체를 하용한다.
	 * */
	@RequestMapping(value="/read", method=RequestMethod.GET)
	public void read(@RequestParam("bno")int bno,Model model)
		throws Exception{
		model.addAttribute(service.read(bno));
		
	}
	@RequestMapping(value="/remove",method=RequestMethod.POST)
	public String remove(@RequestParam("bno") int bno,
			RedirectAttributes rttr)throws Exception{
		service.remove(bno);
		
		rttr.addFlashAttribute("msg","SUCCESS");
		
		return "redirect:/board/listAll";
	}
	@RequestMapping(value="/modify", method=RequestMethod.GET)
	public void modifyGET(int bno, Model model)throws Exception{
		model.addAttribute(service.read(bno));
		
	}
	@RequestMapping(value="/modify", method=RequestMethod.POST)
	public String modifyPOST(BoardVO board,RedirectAttributes rttr)throws Exception{
		
		logger.info("mod post.......");
		
		service.modify(board);
		rttr.addFlashAttribute("msg","SUCCESS");
		return "redirect:/board/listAll";
	}
	@RequestMapping(value="/listCri", method=RequestMethod.GET)
	public void listAll(Criteria cri,Model model)throws Exception{
		
		logger.info("show list Page with Criteria.........");
		
		model.addAttribute("list",service.listCriteria(cri));
	}
	@RequestMapping(value="/listPage", method=RequestMethod.GET)
	public void listPage(Criteria cri, Model model)throws Exception{
		
		logger.info(cri.toString());
		
		model.addAttribute("list", service.listCriteria(cri));
		
		PageMaker pageMaker = new PageMaker();
		pageMaker.setCri(cri);
		//pageMaker.setTotalCount(131);
		
		pageMaker.setTotalCount(service.listCountCriteria(cri));
		
		model.addAttribute("pageMaker", pageMaker);
		
	}
	@RequestMapping(value="/readPage", method=RequestMethod.GET)
	public void read(@RequestParam("bno") int bno,@ModelAttribute("cri") Criteria cri,
			Model model)throws Exception{
		model.addAttribute(service.read(bno));
	}
	@RequestMapping(value="/removePave", method=RequestMethod.POST)
	public String remove(@RequestParam("bno")int bno, Criteria cri, 
			RedirectAttributes rttr)throws Exception{
		service.remove(bno);
		
		rttr.addAttribute("page", cri.getPage());
		rttr.addAttribute("perPageNum", cri.getPerPageNum());
		rttr.addFlashAttribute("msg","SUCCESS");
		
		return "redirect:/borad/listPage";
	}
	@RequestMapping(value="/modifyPage",method=RequestMethod.GET)
	public void modifyPagingGET(@RequestParam("bno")int bno, @ModelAttribute("cri")Criteria cri,
			Model model)throws Exception{
		model.addAttribute(service.read(bno));
	}
	@RequestMapping(value="/modifyPage",method=RequestMethod.POST)
	public String modifyPagingPOST(BoardVO board, Criteria cri, RedirectAttributes rttr)throws Exception{
		
		service.modify(board);
		
		rttr.addAttribute("page", cri.getPage());
		rttr.addAttribute("perPageNum", cri.getPerPageNum());
		rttr.addFlashAttribute("msg","SUCCESS");
		
		return "redirect:/board/listPage";
	}
}
