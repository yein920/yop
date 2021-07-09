package Autocrypt.controller;

import java.time.LocalDate;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.SessionAttributes;

import Autocrypt.dto.Member;
import Autocrypt.dto.MemberRepository;
import lombok.RequiredArgsConstructor;


@RequiredArgsConstructor		//생성자 만들기
@SessionAttributes("member")	//model 정보를 http세션에 담아주는 어노테이션 _ 여러 화면/요청에서 사용해야 하는 객체를 공유할 때 사용
								//model.addAttribute의 attributeName과 @SessionAttributes의 괄호값이 같다면 model에 추가될 때 자동으로 세션으로 넣어줌
@RestController
public class MemberController {

	
	private MemberRepository memberR;
	//private final MemberService memberService ; 
	Member member;
	
	@RequestMapping("/")		//로그인화면 꺼내 
		public String showLogin() {
//		String showLogin(String id, String password) {
			System.out.println("showLogin 호출해주라..");
			return "redirect:Login";
		}
	
	@PostMapping("signIn/into")		//데이터 받아 가져왔습니다. 가능한지 봐주십쇼  _ 로그인 비교 로직 
	public String Signin(String id, String password) {
		//log.info("id : {} , pw : {}", id, password);
		Member member = memberR.findMember(id, password); 	//Repository에 있는 쿼리 이용
		if(member != null) {
			return "loginOK";
		} if (password.equals(member.getPassword())){
			
		}
		
		return "loginFail";
	}
	
	
	
		@RequestMapping("signup")	//회원가입 입력 화면가져와
		public String Signup() {
			return "Signup";
		}
		
		@RequestMapping("signup/save")	//회원가입 로직 
		public String SignupSave(Member member){
			member.setDate(LocalDate.now());
			memberR.save(member);
			return"Login";
			
		}

		
}


//@RequestMapping("Login.do")
//String doLogin(HttpSession session, Model model, @RequestParam Map<String, Object> param) {
//	
//	//로그인이 가능한지 알아보도록 한다 
//	Map<String, Object> rs = memberService.checkLoginAvailable(param);
//	
//	//로그인 성공인지 실패인지 받아줌 
//	String resultCode =(String) rs.get("resultCode");
//	String msg = (String) rs.get("msg");
//	
//	
//	if (resultCode.startsWith("S")) {
//		//브라우저마다 로그인을 한 계정을 알기 위해 Session 사용 
//		//id가 들어감으로써 로그인 
//		int  loginedMemberId = (int) rs.get("id");
//		//로그인 처리 (이 브라우저에 대한 로그인 저장)
//		session.setAttribute("loginedMemberId", loginedMemberId);
//		msg =  "로그인 되었습니다";
//		
//		System.out.println("doLogin 로그인 됐음");
//		
//		//로그인된 후 보일 화면 
//		String redirectUrl = "/article/list";
//		
//		model.addAttribute("jsAlertMsg", msg);
//		model.addAttribute("jsLocationReplaceUrl", redirectUrl);
//	}else {
//		model.addAttribute("jsAlertMsg", msg);
//		model.addAttribute("jsHistoryBack", true);
//		
//	}
//	
//	
//	return "Login";
//}