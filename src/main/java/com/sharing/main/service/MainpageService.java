package com.sharing.main.service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.sharing.main.vo.Contag;
import com.sharing.main.vo.Mainpage;
import com.sharing.main.vo.TagList;
import com.sharing.main.dao.MainDao;

public class MainpageService implements CommandProcess{
	
	private static final int PAGE_SIZE = 6;
	private static final int TAG_SIZE = 24;
	private static final int PAGE_GROUP = 1;
	
	@Override
	public String requestProcess(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String pageNum = request.getParameter("pageNum");
		String type = request.getParameter("type");	
		String keyword = request.getParameter("keyword");
		String tagName = request.getParameter("tagName");
		
		if(pageNum == null) {
			pageNum = "1";
		}
		
		int currentPage = Integer.parseInt(pageNum);
		
		int startRow = currentPage * PAGE_SIZE - (PAGE_SIZE - 1);
		int endRow = startRow + PAGE_SIZE - 1;
		
		System.out.println("시작 : " + startRow);
		System.out.println("끝 : " + endRow);
		
		int listCount = 0;
		ArrayList<Mainpage> mainList = null;
		
		MainDao dao = new MainDao();
		
		
		boolean searchOption = (keyword == null || keyword.equals("")) ? false : true; 
		boolean tagOption = (tagName == null || tagName.equals("")) ? false : true; 
		
		if( searchOption) {
			listCount = dao.serchCount(keyword);
			mainList = dao.searchList(keyword, startRow, endRow);
			
		} else if(tagOption){
			listCount = dao.tagCount(tagName);
			mainList = dao.tagList(tagName, startRow, endRow);
			
		}else{
			 
			listCount = dao.getMainListCount();
			mainList = dao.mainList(startRow, endRow);

		}
		
		int pageCount = listCount / PAGE_SIZE 
				+ (listCount % PAGE_SIZE == 0 ? 0 : 1);
		
		int startPage = (currentPage / PAGE_GROUP) * PAGE_GROUP + 1
			- (currentPage % PAGE_GROUP == 0 ? PAGE_GROUP : 0);
		 
		int endPage = startPage + PAGE_GROUP - 1;
		 
		if(endPage > pageCount) {
			endPage = pageCount;
		}	
		
		// 게시물 번호 뽑아옴
		
		
		ArrayList<Contag> contagList = null;
		dao = new MainDao();
		
		int no = 0;
		
		
		for(Mainpage m : mainList) {
			no = m.getNo();
			contagList = dao.contagList(m.getNo());
		}
		
		
		
		ArrayList<TagList> allTagList = null;
		
		allTagList = dao.allTagList();
		
		
		Set<String> hlist = new HashSet<String>();
		
		for(TagList t : allTagList) {
			hlist.add(t.getHname());
			hlist.add(t.getBname());
		}
		
		Iterator<String> it = hlist.iterator();
		while(it.hasNext())
		System.out.print(it.next() + " ");
		
		
		ArrayList<String> tagList = new ArrayList<String>();
		tagList.addAll(hlist);
		
		/*
		for(int i = 0; i < mainList.size() ; i++) {
			contagList = dao.contagList(mainList.get(i).getNo());
			
		}
		*/
		
		
			
		request.setAttribute("currentPage", currentPage);
		request.setAttribute("listCount", listCount);
		request.setAttribute("startPage", startPage);
		request.setAttribute("endPage", endPage);
		request.setAttribute("pageGroup", PAGE_GROUP);
		request.setAttribute("searchOption", searchOption);
		request.setAttribute("mainList", mainList);
		request.setAttribute("contagList", contagList);
		request.setAttribute("allTagList", allTagList);
		request.setAttribute("tagList", tagList);
		request.setAttribute("pageCount", pageCount);
		request.setAttribute("endRow", endRow);
		request.setAttribute("tagOption", tagOption);
		
		
	
		
		
		if(searchOption) {				
			request.setAttribute("keyword", keyword);
		}
		
		if(tagOption) {				
			request.setAttribute("tagName", tagName);
		}
		
		
		return "main/mainPage";
	}

}
