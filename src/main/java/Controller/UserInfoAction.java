package Controller;

import java.util.List;

import Dao.CategoryDao;
import Dao.UserinfoDao;
import Pojo.Category;
import Pojo.Userinfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.io.IOException;


@Controller
@SessionAttributes(value = {"user","category"})
@RequestMapping("/User")
public class UserInfoAction  {

	private String username;
	private String password;
	private List<Userinfo> list;
	private Userinfo userinfo=new Userinfo();
	private UserinfoDao dao=new UserinfoDao();

	@RequestMapping(value = "/login", method = RequestMethod.GET)
	public String LoginPage(Model model){
		model.addAttribute("userinfo", new Userinfo());
		return "login";
	}

	@RequestMapping(value = "/signup", method = RequestMethod.GET)
	public String SignupPage(Model model){
		model.addAttribute("signup", new Userinfo());
		return "addUsers";
	}

/*************** sign up*****************/
	@RequestMapping(value = "/signup.action", method= RequestMethod.POST)
	public String addUser(@Valid@ModelAttribute("signup") Userinfo userinfo, BindingResult bindingResult, HttpServletRequest request, String username, String password) throws Exception{

		if(bindingResult.hasErrors()){
			return "addUsers";
		}
		userinfo.setRright("0");
		System.out.println(userinfo);
		if(dao.searchUserByName(username)!=null){
			request.setAttribute("errorInfo", "The account has been registered");
			return "error";
		}
		int result=dao.AddUser(userinfo);
		if(result!=0){
			request.setAttribute("successInfo","Register Successfully");
			return "success";
		}else{
			return "error";
		}
	}

	/************ login in***************/
	@RequestMapping(value = "/login.action", method = RequestMethod.POST)
	public String login(@Valid @ModelAttribute("userinfo") Userinfo userinfo, BindingResult bindingResult, HttpServletRequest request, Model model) throws IOException, Exception{
		if(bindingResult.hasErrors()){
			return "login";
		}
		//userinfo.setUsername(username);
		//userinfo.setPassword(password);
		Userinfo result=dao.searchUser(userinfo);
		List<Category> c= new CategoryDao().getCategoryList();
		model.addAttribute("category",c);

		if(result!=null&&result.getRright().equals("1")){
			model.addAttribute("user", result);
			//response.sendRedirect("http://localhost:8080/administrator.jsp");
			return "administrator";
		}else if(result!=null&&result.getRright().equals("0")){
			model.addAttribute("user", result);
			//response.sendRedirect("http://localhost:8080/Book/all.action");
			return "redirect:../Book/all.action";
		}else{
			request.setAttribute("errorInfo", "Wrong Password or Username");
			return "error";
		}
		
	}

	/************** change right****************/
	@RequestMapping("changeRight.action")
	public String changeRight(String username, HttpServletRequest request) throws Exception{

		Userinfo user=dao.searchUserByName(username);
		if(user!=null){
			if(user.getRright().equals("0")){
				user.setRright("1");
			}else{
				user.setRright("0");
			}
			dao.UpdateUserinfo(user);
			request.setAttribute("successInfo","Change Successfully");

			return "success";
		}else{
			return "error";
		}
		
	}
	/************* logout************/
	@RequestMapping("logout.action")
	public String Logout(HttpSession session, SessionStatus sessionStatus){

		session.removeAttribute("user");
		sessionStatus.setComplete();
		return "redirect:/User/login";
	}
	
	/*********** list all users ************/
	@RequestMapping("ListUsers.action")
	public String listAll(Model model) throws Exception{
		list=dao.getUserinfoList();
		model.addAttribute("list",list);

		return "changeRight";
	}
}
