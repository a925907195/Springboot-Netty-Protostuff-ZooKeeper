package com.fjsh.rpc.controller;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.fjsh.rpc.client.RpcProxy;
import com.fjsh.rpc.entity.User;
import com.fjsh.rpc.service.IHelloService;

@Controller
public class HelloController {

	//http://localhost:8080/fjsh-rpc-client/hello?name=fjsdfhd324
	@Autowired
	private RpcProxy rpcProxy;
	@RequestMapping("/hello")
	public void hello(String name){		
		IHelloService service = rpcProxy.create(IHelloService.class);
		long start=System.currentTimeMillis();
		for(int i=0;i<1000;i++)
		{			
			String result = service.hello(name);
			if(result.equals("hello"+name))
			{
				
			}
			else {
				try {
					throw new Exception();
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			
		}
		System.out.println(((System.currentTimeMillis()-start))+"ms");
		
	}
	
	@RequestMapping("/getUser")
	public void getUser(String name){
		IHelloService service = rpcProxy.create(IHelloService.class);
		System.out.println(service.getUser(name).toString());
	}
	
	@RequestMapping("/getUsers")
	public void getUsers(int size){
		IHelloService service = rpcProxy.create(IHelloService.class);
		List<User> list = service.getUsers(size);
		for(User user : list){
			System.out.println(user.toString());
		}
	}
	
	@RequestMapping("/updateUser")
	public void updateUser(String name){
		User user = new User(name, new Date(), true);
		IHelloService service = rpcProxy.create(IHelloService.class);
		user = service.updateUser(user);
		System.out.println(user.toString());
	}
}
