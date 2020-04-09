package service;

import java.util.List;

import Pojo.Userinfo;



public interface UserInfoService {
	public Userinfo search(Userinfo userinfo);//查找

	
	public int add(Userinfo user);//添加
	public int change(Userinfo user);
	public Userinfo findbyid(int id);
	public Userinfo findbyname(String name);
	public int delete(int id);
	public List listAllUser();
}
