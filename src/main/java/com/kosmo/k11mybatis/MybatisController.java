package com.kosmo.k11mybatis;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import mybatis.MemberVO;
import mybatis.MyBoardDTO;
import mybatis.MybatisDAOImpl;
import mybatis.MybatisMemberImpl;
import util.PagingUtil;

@Controller
public class MybatisController
{
	/*
	 * servlet-context.xml에서 생성한 bean을 자동으로 주입받아 Mybatis를 사용할 준비를 한다.
	 * @Autowired는 타입만 일치하면 자동으로 주입받을 수 있다.
	 */
	@Autowired
	private SqlSession sqlSession;

	@RequestMapping("/mybatis/list.do")
	public String list(Model model, HttpServletRequest req)
	{
		int totalRecordCount = sqlSession.getMapper(MybatisDAOImpl.class).getTotalCount();
		System.out.println(totalRecordCount);
		// 페이지 처리를 위한 설정값
		int pageSize = 4;
		int blockPage = 2;
		// 전체 페이지 수 계산
		int totalPage = (int) Math.ceil((double) totalRecordCount / pageSize);
		// 현재 페이지에 대한 파라미터 처리 및 시작 / 끝의 rownum구하기
		int nowPage = req.getParameter("nowPage") == null ? 1 : Integer.parseInt(req.getParameter("nowPage"));
		int start = (nowPage - 1) * pageSize + 1;
		int end = nowPage * pageSize;
		// 리스트 페이지에 출력할 게시물 가져오기
		ArrayList<MyBoardDTO> lists = sqlSession.getMapper(MybatisDAOImpl.class).listPage(start, end);
		// 페이지 번호에 대한 처리
		String pagingImg = PagingUtil.pagingImg(totalRecordCount, pageSize, blockPage, nowPage, req.getContextPath() + "/mybatis/list.do?");
		model.addAttribute("pagingImg", pagingImg);
		// 레코드에 대한 가공을 위해 for문으로 반복
		for (MyBoardDTO dto : lists)
		{
			// 내용에 대한 줄바꿈 처리
			String temp = dto.getContents().replace("\r\n", "<br/>");
			dto.setContents(temp);
		}
		// model객체에 저장
		model.addAttribute("lists", lists);
		return "07Mybatis/list";
	}

	//방명록 글쓰기 폼
	@RequestMapping("/mybatis/write.do")
	public String write(Model model, HttpSession session, HttpServletRequest req)
	{
		//글쓰기 페이지로 진입시 세션영역에 데이터가 없다면 로그인페이지로 이동
		if (session.getAttribute("siteUserInfo") == null)
		{
			/*
			 * 로그인에 성공할 경우 글쓰기 페이지로 이동하기 위해
			 * 돌아갈 경로를 아래와 같이 저장함
			 */
			model.addAttribute("backUrl", "07Mybatis/write");
			return "redirect:login.do";
		}
		return "07Mybatis/write";
	}

	// 로그인 페이지
	@RequestMapping("/mybatis/login.do")
	public String login(Model model)
	{
		return "07Mybatis/login";
	}

	@RequestMapping("/mybatis/loginAction.do")
	public ModelAndView loginAction(HttpServletRequest req, HttpSession session)
	{
		// login메소드를 호출함
		MemberVO vo = sqlSession.getMapper(MybatisMemberImpl.class).login(req.getParameter("id"), req.getParameter("pass"));
		ModelAndView mv = new ModelAndView();
		if (vo == null)
		{
			// 로그인에 실패한 경우
			mv.addObject("LoginNG", "아이디/패스워드가 틀렸습니다.");
			mv.setViewName("07Mybatis/login");
			return mv;
		} else
		{
			// 로그인에 성공한 경우 세션영역에 VO객체를 저장한다.
			session.setAttribute("siteUserInfo", vo);
		}
		String backUrl = req.getParameter("backUrl");
		if (backUrl == null || backUrl.equals(""))
		{
			// 돌아갈 페이지가 없다면 로그인 페이지로 이동한다.
			mv.setViewName("07Mybatis/login");
		} else
		{
			// 지정된 페이지로 이동한다.
			mv.setViewName(backUrl);
		}
		return mv;
	}
}
