package it.giunti.tasktrigger.controller;

import java.util.LinkedHashMap;

import javax.naming.AuthenticationException;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import it.giunti.tasktrigger.ControllerException;
import it.giunti.tasktrigger.model.dao.TasktriggerUserDao;
import it.giunti.tasktrigger.model.entity.TasktriggerUser;
import it.giunti.tasktrigger.service.AuthService;

@RestController
@CrossOrigin(origins = "*")
public class AuthController {

    @Autowired
    @Qualifier("authService")
    private AuthService authService;
	@Autowired
	@Qualifier("tasktriggerUserDao")
	private TasktriggerUserDao userDao;
	
    @PostMapping("/api/authenticate")
    public TasktriggerUser authenticate(@Valid @RequestBody LinkedHashMap<String, String> paramsMap) throws ControllerException {
    	try {
    		String username = paramsMap.get("username");
    		String password = paramsMap.get("password");
			authService.authenticate(username, password);
			TasktriggerUser user = userDao.selectUserByUsername(username);
			return user;
		} catch (AuthenticationException e) {
			throw new ControllerException(e.getMessage(), e);
		}
    }
}
