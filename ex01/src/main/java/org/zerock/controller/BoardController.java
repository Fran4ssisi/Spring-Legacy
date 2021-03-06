package org.zerock.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.zerock.domain.BoardVO;
import org.zerock.service.BoardService;

@Controller
@RequestMapping("/board/*")
public class BoardController {

  private static final Logger logger = LoggerFactory.getLogger(BoardController.class);
  
  @Autowired
  private BoardService service;
  
  @RequestMapping(value = "/register", method = RequestMethod.GET)
  public void registerGET(BoardVO board, Model model) throws Exception {
    
    logger.info("register get ...........");
  }
  
  @RequestMapping(value = "/register", method = RequestMethod.POST)
  public String registPOST(BoardVO board, RedirectAttributes rttr) throws Exception {
    logger.info("register get ...........");
    logger.info(board.toString());
    
    service.regist(board);
    
    rttr.addFlashAttribute("msg", "SUCCESS");
    
//    return "/board/success";
    return "redirect:/board/listAll";
  }
  
  @RequestMapping(value = "/listAll", method = RequestMethod.GET)
  public void listAll(Model model) throws Exception {
    logger.info("show all list ............................");
    model.addAttribute("list", service.listAll());
  }
  
  //@RequestParam은 Servlet에서 request.getParameter()의 효과와 유사하다.
  @RequestMapping(value = "/read", method = RequestMethod.GET)
  public void read(@RequestParam("bno") int bno, Model model) throws Exception {
    model.addAttribute(service.read(bno));
  }
  
  
}
