package service.impl;

import java.util.List;

import Dao.UserinfoDao;
import Pojo.Userinfo;
import service.UserInfoService;


public class UserInfoServiceImpl implements UserInfoService  {
	private UserinfoDao userDAO;
	

	public UserinfoDao getUserDAO() {
		return userDAO;
	}

	public void setUserDAO(UserinfoDao userDAO) {
		this.userDAO = userDAO;
	}


	@Override
	public Userinfo search(Userinfo userinfo) {
		return userDAO.searchUserById(userinfo.getUserId());
	}

	public int add(Userinfo user) {
		// TODO Auto-generated method stub
		return userDAO.AddUser(user);
	}
	
	@Override
	public int change(Userinfo user) {
		return userDAO.UpdateUserinfo(user);
	}


	@Override
	public Userinfo findbyid(int id) {
		// TODO Auto-generated method stub
		return userDAO.searchUserById(id);
	}
	@Override
	public Userinfo findbyname(String name) {
		// TODO Auto-generated method stub
		return userDAO.searchUserByName(name);
	}
	@Override
	public int delete(int id) {
		// TODO Auto-generated method stub
		return userDAO.DeleteUser(id);
	}

	@Override
	public List listAllUser() {
		// TODO Auto-generated method stub
		return userDAO.getUserinfoList();
	}

}
