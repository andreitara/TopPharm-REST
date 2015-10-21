package md.pharm.restservice.security;

import antlr.StringUtils;
import md.pharm.hibernate.user.ManageUser;
import md.pharm.hibernate.user.User;
import md.pharm.hibernate.user.permission.Permission;
import md.pharm.restservice.util.ErrorCodes;
import md.pharm.restservice.util.StaticStrings;
import org.springframework.web.filter.GenericFilterBean;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

class StatelessAuthenticationFilter extends GenericFilterBean {
	protected StatelessAuthenticationFilter(){}

	@Override
	public void doFilter(ServletRequest req, ServletResponse res, FilterChain chain) throws IOException, ServletException {
		final HttpServletRequest httpRequest = (HttpServletRequest) req;
		String uri = StringUtils.stripFront(StringUtils.stripBack(httpRequest.getRequestURI(),'/'),'/');
		if(isAccessiblePage(uri)){
			chain.doFilter(req, res);
		}else {
			String token = ((HttpServletRequest) req).getHeader(StaticStrings.HEADER_SECURITY_TOKEN);
			ManageUser manageUser = new ManageUser("MD");
			User user = manageUser.getUserByConnectionKey(token);
			if(user==null){
				manageUser = new ManageUser("RO");
				user = manageUser.getUserByConnectionKey(token);
			}
			if(user != null){
				Permission permission = user.getPermission();
				if(permission!=null && userHasPermission(permission,httpRequest)){
					HeaderMapRequestWrapper wrapper = new HeaderMapRequestWrapper(httpRequest);
					String username = user.getUsername();
					String country = user.getCountry();
					wrapper.addHeader(StaticStrings.HEADER_USERNAME, username);
					wrapper.addHeader(StaticStrings.HEADER_COUNTRY, country);
					chain.doFilter(wrapper, res);
				}else{
                    String newURI = "error/" + ErrorCodes.InsufficientAccountPermissions.name;
                    req.getRequestDispatcher(newURI).forward(req, res);
				}
			}else{
                String newURI = "error/" + ErrorCodes.InvalidAuthenticationInfo.name;
                req.getRequestDispatcher(newURI).forward(req, res);
			}
		}
	}

	public static boolean userHasPermission(Permission permission, HttpServletRequest httpRequest){
		String uri = StringUtils.stripFront(StringUtils.stripBack(httpRequest.getRequestURI(), '/'), '/');
		if(uri.startsWith(StaticStrings.USER_PAGES)){
			if(httpRequest.getMethod().equals("GET")){
				if(permission.isReadUsers())
					return true;
			}else{
				if(permission.isWriteUsers())
					return true;
			}
		}else if(uri.startsWith(StaticStrings.MEDICAL_ENTITY_PAGES)){
			if(httpRequest.getMethod().equals("GET")){
				if(permission.isReadMedicalEntity())
					return true;
			}else{
				if(permission.isWriteMedicalEntity())
					return true;
			}
		}else if(uri.startsWith(StaticStrings.TASK_PAGES)){
			if(httpRequest.getMethod().equals("GET")){
				if(permission.isReadTasks())
					return true;
			}else{
				if(permission.isWriteTasks())
					return true;
			}
		}else if(uri.startsWith(StaticStrings.MESSAGES_PAGES)){
			return true;
		}
		return false;
	}

    public static boolean isAccessiblePage(String uri){
        if(StaticStrings.ACCESSIBLE_PAGES.contains(uri))
            return true;
        if(uri.contains("error"))
            return true;
        return false;
    }
}