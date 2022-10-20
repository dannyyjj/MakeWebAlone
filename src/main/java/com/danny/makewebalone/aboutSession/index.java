//package com.danny.makewebalone.aboutSession;
//
//import org.springframework.ui.Model;
//import org.springframework.util.ObjectUtils;
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.util.WebUtils;
//
//import javax.servlet.http.Cookie;
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpSession;
//import java.util.Date;
//
//public class index {
//
//    @GetMapping("/")
//    public String main(HttpServletRequest request, User user, Model model) {
//        log.error("homeController");
//
//        // 쿠키 만료시 Cookie 값이 null이 된다. (유효 시간 동안은 개발자 모드 진입 후(F12) 쿠키 보면 AUTH 라는 이름으로 세션 ID가 들어가 있음)
//        Cookie dolbom = WebUtils.getCookie(request, "DolBom");
//
//        // 로그인 정보가 있을시
//        if (!ObjectUtils.isEmpty(dolbom)) {
//            if (StringUtils.equalsIgnoreCase(dolbom.getValue(), request.getSession().getId())) {
//                String username = (String) request.getSession().getAttribute("username");
//                if (StringUtils.isNotEmpty(username)) {
//                    model.addAttribute("username", username);
//
//                    HttpSession session = request.getSession(false);
//                    log.error("sessionId==============>{}", session.getId());
//                    log.error("getMaxInactiveInterval=>{}", session.getMaxInactiveInterval());
//                    log.error("creationTime===========>{}", new Date(session.getCreationTime()));
//                    log.error("lastAccessedTime=======>{}", new Date(session.getLastAccessedTime()));
//                    log.error("isNew==================>{}", session.isNew());
//
//                    return "home";
//                }
//            }
//        }
//        // 로그인 만료 or 비 로그인자 일시
//        log.error("로그인 만료 or 쿠키 없음");
//        return "loginForm";
//    }
//
//}
