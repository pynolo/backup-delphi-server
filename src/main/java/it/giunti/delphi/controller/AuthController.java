package it.giunti.delphi.controller;

import java.util.LinkedHashMap;

import javax.naming.AuthenticationException;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import it.giunti.delphi.ControllerException;
import it.giunti.delphi.model.entity.DelphiUser;
import it.giunti.delphi.service.AuthService;
import it.giunti.delphi.service.DelphiUserService;

@RestController
@CrossOrigin(origins = "*")
public class AuthController {

    @Autowired
    @Qualifier("authService")
    private AuthService authService;
	@Autowired
	@Qualifier("delphiUserService")
	private DelphiUserService userService;
	
    @PostMapping("/api/authenticate")
    public DelphiUser authenticate(@Valid @RequestBody LinkedHashMap<String, String> paramsMap) throws ControllerException {
    	try {
    		String username = paramsMap.get("username");
    		String password = paramsMap.get("password");
			authService.authenticate(username, password);
			DelphiUser user = userService.getUserByUsername(username);
			return user;
		} catch (AuthenticationException e) {
			throw new ControllerException(e.getMessage(), e);
		}
    }
}
