package com.controller;

import com.alibaba.fastjson.JSONObject;
import com.po.Dep;
import com.po.Emp;
import com.po.ViewBean;
import com.po.Welfare;
import com.util.AjaxUtil;
import com.util.BizService;
import org.apache.commons.io.FileUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
@Controller
public class EmpController implements IEmpAction{

	@Resource(name="BizService")
	private BizService biz;

	public BizService getBiz() {
		return biz;
	}

	public void setBiz(BizService biz) {
		this.biz = biz;
	}

	@RequestMapping(value="save_Emp.do")
	public String save(HttpServletRequest request, HttpServletResponse response, Emp emp) {
		System.out.println("save方法正在运行"+emp);
		//a.文件上传
		//1.获取项目工程根路径，即文件存储根路径
		String realpath=request.getRealPath("/");
		/*************文件上传begin**************/
		MultipartFile multipartFile=emp.getPic();//获取文件路径对象
		if (multipartFile!=null && !multipartFile.isEmpty()) {//判断该对象是否为空且不为null
			//获取上传文件名称
			String fname=multipartFile.getOriginalFilename();
			//限制文件上传类型处理
			if(fname.lastIndexOf(".")!=-1){//确定文件有后缀
				//获取后缀名
				String ext=fname.substring(fname.lastIndexOf("."));//截取文件后缀名
				if(ext!=null && (ext.equalsIgnoreCase(".png") || ext.equalsIgnoreCase(".jpg"))){
					//为了减少在数据库名称重复，使用上传时间拼接名称
					String newFname=new Date().getTime()+ext;
					//完成文件上传(指定位置新建文件，将上传内容通过流写入)
					File newFile=new File(realpath+"/img/"+newFname);
					try {
						FileUtils.copyInputStreamToFile(multipartFile.getInputStream(), newFile);
						emp.setPicture(newFname);
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
			}
		}
		//b.完成添加
		boolean flag=biz.getEmpService().save(emp);
		if (flag) {
			String jsonstr=JSONObject.toJSONString(1);
			AjaxUtil.printString(response, jsonstr);
		}else{
			String jsonstr=JSONObject.toJSONString(0);
			AjaxUtil.printString(response, jsonstr);
		}
		return null;
	}

	@RequestMapping(value="update_Emp.do")
	public String update(HttpServletRequest request, HttpServletResponse response, Emp emp) {
		System.out.println("update方法正在运行"+emp.toString());
		//获取旧对象
		Emp oldemp=biz.getEmpService().findByEid(emp.getEid());
		//a.文件上传
		//1.获取项目工程根路径，即文件存储根路径
		String realpath=request.getRealPath("/");
		/*************文件上传begin**************/
		MultipartFile multipartFile=emp.getPic();//获取文件路径对象
		if (multipartFile!=null && !multipartFile.isEmpty()) {//判断该对象是否为空且不为null
			//获取上传文件名称
			String fname=multipartFile.getOriginalFilename();
			//限制文件上传类型处理
			if(fname.lastIndexOf(".")!=-1){//确定文件有后缀
				//获取后缀名
				String ext=fname.substring(fname.lastIndexOf("."));//截取文件后缀名
				if(ext!=null && (ext.equalsIgnoreCase(".png") || ext.equalsIgnoreCase(".jpg"))){
					//为了减少在数据库名称重复，使用上传时间拼接名称
					String newFname=new Date().getTime()+ext;
					//完成文件上传(指定位置新建文件，将上传内容通过流写入)
					File newFile=new File(realpath+"/img/"+newFname);
					try {
						FileUtils.copyInputStreamToFile(multipartFile.getInputStream(), newFile);
						emp.setPicture(newFname);
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
			}
		}else{
			/**修改时如果没有更换照片则获取旧对象中的picture**/
			emp.setPicture(oldemp.getPicture());
		}
		//b.完成修改
		boolean flag=biz.getEmpService().update(emp);
		if (flag) {
			String jsonstr=JSONObject.toJSONString(1);
			AjaxUtil.printString(response, jsonstr);
		}else{
			String jsonstr=JSONObject.toJSONString(0);
			AjaxUtil.printString(response, jsonstr);
		}
		return null;
	}

	@RequestMapping(value="delByEid_Emp.do")
	public String delByEid(HttpServletRequest request, HttpServletResponse response, Integer eid) {
		System.out.println("delByEid方法正在运行"+eid);
		/**删除员工信息首先
		 * 1.做查询单个确定有目标记录，
		 * 2.再根据eid删除从表empwelare，salary，然后删除emp主表，
		 * 3.表结构删除成功，最后删除原有照片**/
		//查询单个获取选中员工信息
		Emp oldemp=biz.getEmpService().findByEid(eid);
		//调用方法完成表结构删除
		boolean flag=biz.getEmpService().delByEid(eid);
		if (flag) {
			//删除原有照片
			String realpath=request.getRealPath("/");//获取项目工程路径
			File newFile=new File(realpath+"/img/"+oldemp.getPicture());//拼图片名称
			if (newFile!=null && !oldemp.getPicture().equals("default.jpg")) {
				newFile.delete();
			}
			String jsonstr=JSONObject.toJSONString(1);
			AjaxUtil.printString(response, jsonstr);
		}else{
			String jsonstr=JSONObject.toJSONString(0);
			AjaxUtil.printString(response, jsonstr);
		}
		return null;
	}

	@RequestMapping(value="findByEid_Emp.do")
	public String findByEid(HttpServletRequest request, HttpServletResponse response, Integer eid) {
		System.out.println("findByEid方法正在运行"+eid);
		Emp oldemp=biz.getEmpService().findByEid(eid);
		System.out.println(oldemp);
		String jsonstr=JSONObject.toJSONString(oldemp);
		AjaxUtil.printString(response, jsonstr);
		return null;
	}

	@RequestMapping(value="findEmpViewAll_Emp.do")
	public String findEmpViewAll(HttpServletRequest request, HttpServletResponse response, Integer page,
								 Integer rows) {
		System.out.println("findEmpViewAll方法正在运行"+page+"===="+rows);
		/***EasyUi中固定接收分页展示的数据类型为Map集合,固定值为page,rows,total***/
		Map<String,Object> map=new HashMap<String,Object>();
		ViewBean vb=new ViewBean();
		page=page==null || page<1 ? vb.getPage():page;
		rows=rows==null || rows<1 ? vb.getRows():rows;
		int viewMax=biz.getEmpService().findViewMax();
		vb.setPage(page);
		vb.setRows(rows);
		List<Emp> lsemp=biz.getEmpService().findEmpViewAll(vb);
		map.put("page", page);
		map.put("rows", lsemp);
		map.put("total", viewMax);
		String jsonstr=JSONObject.toJSONString(map);
		AjaxUtil.printString(response, jsonstr);
		return null;
	}

	@RequestMapping(value="doinit_Emp.do")
	public String doinit(HttpServletRequest request, HttpServletResponse response) {
		System.out.println("doinit方法正在运行");
		System.out.println(System.getProperty("file.encoding"));
		/***用map集合存储传输部门和福利两个值***/
		List<Dep> lsdep=biz.getDepService().findAll();
		List<Welfare> lswf=biz.getWelfareService().findAll();
		Map<String,Object> map=new HashMap();
		map.put("lsdep", lsdep);
		map.put("lswf", lswf);
		String jsonstr=JSONObject.toJSONString(map);
		AjaxUtil.printString(response, jsonstr);
		return null;
	}

}
