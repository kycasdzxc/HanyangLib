package kr.co.itwill.mylib;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

@RequestMapping("/mylib")
@Controller
public class MylibCont {

	public MylibCont() {
		System.out.println("-----MylibCont() 객체 생성됨");
	} // end
	
	@Autowired
	MylibDAO mylibDao;
	
	@Autowired
	VisitorDAO visitorDao;
	
	@RequestMapping("/libindex/{lib_id}")
	public ModelAndView read(@PathVariable String lib_id) throws Exception {
		ModelAndView mav = new ModelAndView();
		mav.setViewName("mylib/libindex");
		mav.addObject("bookCount", mylibDao.getCount(lib_id));
		mav.addObject("book80Count", mylibDao.get80Count(lib_id));
		mav.addObject("libInfo", mylibDao.getLibInfo(lib_id));
		mav.addObject("libRead", mylibDao.getLibRead(lib_id));
		mav.addObject("commuRead", mylibDao.getCommuRead(lib_id));
		mav.addObject("review", mylibDao.getReviewRead(lib_id));
		mav.addObject("lib_id", lib_id);
		return mav;
	}
	
	@ResponseBody
	@RequestMapping("/vsinsert")
	private int vsInsert(@RequestParam String vis_pid, @RequestParam String content, HttpServletRequest req) throws Exception {
	    VisitorDTO visitor = new VisitorDTO();
	    visitor.setVis_pid(vis_pid);
	    visitor.setVis_content(content);
	    // 로그인 기능을 구현했거나 따로 댓글 작성자를 입력받는 폼이 있다면 입력받아온 값을 사용하면 된다.
	    // 따로 구현하지 않았기 때문에 아이디는 임시로 "cloudd81"
	    String vis_myid = "cloudd81";
	    visitor.setVis_myid(vis_myid);
	    req.setAttribute("vis_myid", vis_myid);
	    return visitorDao.visitorInsert(visitor);
	} // vsInsert() end
	
	@RequestMapping("/vslist")
	@ResponseBody
	private List<VisitorDTO> vsList(@RequestParam String vis_pid) throws Exception {
	    return visitorDao.visitorList(vis_pid);	    
	} // vsList() end
	
	@ResponseBody
	@RequestMapping("/vsupdate")
	private int vsUpdate(@RequestParam int vis_no, @RequestParam String content) throws Exception {
	    VisitorDTO visitor = new VisitorDTO();
	    visitor.setVis_no(vis_no);
	    visitor.setVis_content(content);
	    return visitorDao.visitorUpdate(visitor);
	} // mCommentServiceInsert() end
	
	@RequestMapping("/delete/{vis_no}")
	@ResponseBody
	private int vsDelete(@PathVariable int vis_no) throws Exception {
	    return visitorDao.visitorDelete(vis_no);
	} // mCommentServiceDelete() end
	
	@RequestMapping("/myBooks/{lib_id}")
	public ModelAndView myBooks(@PathVariable String lib_id) throws Exception {
		ModelAndView mav = new ModelAndView();
		mav.setViewName("mylib/myBooks");
		mav.addObject("libRead", mylibDao.getLibRead(lib_id));
		mav.addObject("bookCount", mylibDao.getCount(lib_id));
		mav.addObject("book80Count", mylibDao.get80Count(lib_id));
		mav.addObject("libInfo", mylibDao.getLibInfo(lib_id));
		mav.addObject("lib_id", lib_id);
		return mav;
	}
	
	@RequestMapping("/myComm/{lib_id}")
	public ModelAndView myComm(@PathVariable String lib_id) throws Exception {
		ModelAndView mav = new ModelAndView();
		mav.setViewName("mylib/myComm");
		mav.addObject("libRead", mylibDao.getLibRead(lib_id));
		mav.addObject("bookCount", mylibDao.getCount(lib_id));
		mav.addObject("book80Count", mylibDao.get80Count(lib_id));
		mav.addObject("libInfo", mylibDao.getLibInfo(lib_id));
		mav.addObject("commuRead", mylibDao.getCommuRead(lib_id));
		mav.addObject("lib_id", lib_id);
		return mav;
	}
	
	@RequestMapping("/myReview/{lib_id}")
	public ModelAndView myReview(@PathVariable String lib_id) throws Exception {
		ModelAndView mav = new ModelAndView();
		mav.setViewName("mylib/myReview");
		mav.addObject("libRead", mylibDao.getLibRead(lib_id));
		mav.addObject("bookCount", mylibDao.getCount(lib_id));
		mav.addObject("book80Count", mylibDao.get80Count(lib_id));
		mav.addObject("libInfo", mylibDao.getLibInfo(lib_id));
		mav.addObject("review", mylibDao.getReviewRead(lib_id));		
		mav.addObject("lib_id", lib_id);
		return mav;
	}
	
	
	
} // class end