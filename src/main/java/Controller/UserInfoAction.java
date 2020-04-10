package Controller;

import java.util.List;

import Dao.UserinfoDao;
import Pojo.Userinfo;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;


@Controller
@SessionAttributes("user")
@RequestMapping("/User")
public class UserInfoAction extends BaseAction {
	
	//ApplicationContext context=new ClassPathXmlApplicationContext("applicationContext.xml");
	private String username;
	private String password;
	private List<Userinfo> list;
	private Userinfo userinfo=new Userinfo();
	private UserinfoDao dao=new UserinfoDao();

	public List getList() {
		return list;
	}
	public void setList(List list) {
		this.list = list;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}


	@RequestMapping("/signup.action")

	public String addUser(HttpServletRequest request) throws Exception{

		userinfo.setUsername(request.getParameter("username"));
		userinfo.setPassword(request.getParameter("password"));
		userinfo.setRright("0");
		System.out.println(userinfo);
		int result=dao.AddUser(userinfo);//��Ӷ���
		if(result!=0){
			return "success";
		}else{
			return "error";
		}
	}

	@RequestMapping("/login.action")
	public String login(String username, String password, HttpServletResponse response, Model model, HttpSession session) throws IOException, Exception{//��¼
		//Map<String,Object> session=ActionContext.getContext().getSession();
		//Userinfo userinfo=(Userinfo)context.getBean("userinfo");
		System.out.println("yes");
		userinfo.setUsername(username);
		userinfo.setPassword(password);
		
		//System.out.println("cbuey"+getUsername());
		//System.out.println("cbuey"+getPassword());
		
		//UserInfoDao dao=(UserInfoDao)context.getBean("userinfoDAO");
		Userinfo result=dao.searchUser(userinfo);
		//System.out.println("cbuey"+result.getUsername());
		if(result!=null&&result.getRright().equals("1")){
			model.addAttribute("user", result);
			response.sendRedirect("http://localhost:8080/administrator.jsp");
			return null;
		}else if(result!=null&&result.getRright().equals("0")){
			model.addAttribute("user", result);
			response.sendRedirect("http://localhost:8080/Book/all.action");
			return null;
		}else{
			return "login";
		}
		
	}
	@RequestMapping("changeRight.action")
	public String changeRight(String username) throws Exception{
		
//		UserInfoDao dao=(UserInfoDao)context.getBean("userinfoDAO");
		Userinfo user=dao.searchUserByName(username);
		if(user!=null){
			if(user.getRright().equals("0")){
				user.setRright("1");
			}else{
				user.setRright("0");
			}
			new UserinfoDao().UpdateUserinfo(user);
			return "success";
		}else{
			return "error";
		}
		
	}
	@RequestMapping("logout.action")
	public String Logout(HttpSession session){

		session.removeAttribute("user");
		return "login";
	}
	
	public String searchUser(Model model, HttpSession session) throws IOException, Exception{
		
		//Map<String,Object> session=ActionContext.getContext().getSession();
		//Userinfo userinfo=(Userinfo)context.getBean("userinfo");
		//userinfo.setUsername(getUsername());
		
		System.out.println("cbuey"+getUsername());
		System.out.println("cbuey"+getPassword());
		
		//UserInfoDao dao=(UserInfoDao)context.getBean("userinfoDAO");
		Userinfo result=dao.searchUserByName(getUsername());
		if(result!=null){
			model.addAttribute("user", result);
			return "SUCCESS";
		}else{
			return "ERROR";
		}
	}
	@RequestMapping("ListUsers.action")
	public String listAll(Model model) throws Exception{
		//System.out.println("空");
		//UserInfoDao dao=(UserInfoDao)context.getBean("userinfoDAO");
		list=dao.getUserinfoList();
		model.addAttribute("list",list);
		/*if(list.isEmpty()){
			System.out.println("空");
		}*/
		return "changeRight";
	}
}
