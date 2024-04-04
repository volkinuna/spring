package com.example.myblog.controller;

import com.example.myblog.dto.Post;
import com.example.myblog.service.PostService;
import com.example.myblog.util.PagingUtil;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.net.URLEncoder;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
public class PostController {
    @Autowired
    PostService postService;

    @Autowired
    PagingUtil pagingUtil;

    //모든 도메인 뒤에 '/'가 있으나 생략됨
    @GetMapping(value = "/") //localhost/로 접속
    public String index() {
        return "index";
    }

    @GetMapping(value = "/view") //localhost/view
    public String view() {
        return "post/view";
    }

    //localhost/list
    //같은 경로로 get 방식과 post 방식을 동시에 받을 수 있다.
    // - get 방식으로 오는 것 + post 방식으로 오는 것 모두 받을 수 있음
    @RequestMapping(value = "/list", method = {RequestMethod.GET, RequestMethod.POST})
    public String list(HttpSession session, HttpServletRequest request, Model model) {
        try {
            String pageNum = request.getParameter("pageNum"); //처음엔 pageNum이 null
            pagingUtil.setCurrentPage(1); // 페이지번호 항상 1로 우선 초기화

            //현재 페이지의 값을 바꿔준다.
            if(pageNum != null) pagingUtil.setCurrentPage(Integer.parseInt(pageNum));

            int memberId = (int) session.getAttribute("member_id");
            String searchKey = request.getParameter("searchKey"); //검색키워드
            String searchValue = request.getParameter("searchValue"); //검색어

            if (searchValue == null) {
                //검색어가 없다면
                searchKey = "subject"; //검색 키워드의 디폴트는 subject
                searchValue = ""; //검색어의 디폴트는 빈문자열
            }

            Map map = new HashMap();
            map.put("memberId", memberId);
            map.put("searchKey", searchKey);
            map.put("searchValue", searchValue);

            //1. 전체 게시물의 갯수를 가져온다.(페이징 처리시 필요)
            int dataCount = postService.getDateCount(map);

            //2. 페이징 처리를 한다.(준비 단계)
            //numPerPage : 페이지당 보여줄 게시물 목록의 갯수
            pagingUtil.resetPaging(dataCount, 5);

            map.put("start", pagingUtil.getStart()); //1, 6, 11, 16, ...
            map.put("end", pagingUtil.getEnd()); //5, 10, 15, 20, ...

            //3. 페이징 처리할 리스트를 가지고 온다.
            List<Post> lists = postService.getPostList(map);

            //4. 검색어에 대한 쿼리스트링을 만든다.
            String param = "";
            String listUrl = "/list";
            String articleUrl = "/view?pageNum=" + pagingUtil.getCurrentPage();

            //검색어가 있다면
            if(searchValue != null && !searchValue.equals("")) {
                param = "searchKey=" + searchKey;
                param += "&searchValue=" + URLEncoder.encode(searchValue, "UTF-8");
            }
            

            //검색어가 있다면
            if(!param.equals("")) {
                //listUrl의 값 : /list?searchKey=subject&searchValue=네번째
                listUrl += "?" + param;

                //articleUrl의 값 : /view?pageNum=1&searchKey=subject&searchValue=네번째
                articleUrl += "&" + param;
            }

            //5. 페이징 버튼을 만들어준다.
            //◀이전 1 2 3 4 5 다음▶
            String pageIndexList = pagingUtil.pageIndexList(listUrl);

            model.addAttribute("lists", lists);                 //DB에서 가져온 전체 게시물 리스트
            model.addAttribute("articleUrl", articleUrl);       //상세 페이지로 이동하기위한 url
            model.addAttribute("pageIndexList", pageIndexList); //페이징 버튼
            model.addAttribute("dataCount", dataCount);         //게시물의 전체 갯수
            model.addAttribute("searchKey", searchKey);         //검색키워드
            model.addAttribute("searchValue", searchValue);     //검색어

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return "post/list";
    }

    @GetMapping(value = "/write") //localhost/write
    public String write() {
        return "post/write";
    }

    @GetMapping(value = "/rewrite") //localhost/rewrite
    public String rewrite() {
        return "post/rewrite";
    }
}