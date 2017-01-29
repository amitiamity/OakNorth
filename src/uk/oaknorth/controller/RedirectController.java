package uk.oaknorth.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import uk.oaknorth.service.Service;


@Controller
public class RedirectController {
	
 
  

  @RequestMapping(value = "search", method = RequestMethod.GET)
  public ModelAndView resultPage(HttpServletRequest request,HttpServletResponse response){
	  ModelAndView mv = new ModelAndView("index");
	  Service s = new Service();
	  Map<String,List<String>> items = s.getOfficersService(request.getParameter("q"));
	  mv.addObject("items", items);
	  mv.addObject("searchedString", request.getParameter("q"));
	  return mv;
  }
  
  @RequestMapping("/")
  public ModelAndView homePage(HttpServletRequest request,HttpServletResponse response){
	  ModelAndView mv = new ModelAndView("index");
	  return mv;
  }
	
}
