package it.giunti.delphi.service;

import java.util.Hashtable;

import javax.naming.AuthenticationException;
import javax.naming.Context;
import javax.naming.NamingEnumeration;
import javax.naming.NamingException;
import javax.naming.directory.Attributes;
import javax.naming.directory.SearchControls;
import javax.naming.directory.SearchResult;
import javax.naming.ldap.InitialLdapContext;
import javax.naming.ldap.LdapContext;
import javax.transaction.Transactional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import it.giunti.delphi.model.dao.DelphiUserDao;
import it.giunti.delphi.model.entity.DelphiUser;

@Service("authService")
public class AuthService {
	private static final Logger LOG = LoggerFactory.getLogger(AuthService.class);
	
	private static final String LDAP_HOST = "ldap.intranet.giunti.it";
	private static final String LDAP_DOMAIN = "giunti.it";
	private static final String LDAP_BASE_DN = "dc=intranet,dc=giunti,dc=it";
	private static final String LDAP_PRINCIPAL = "CN=Ricercheportale,OU=Utenti di Servizio,DC=intranet,DC=giunti,DC=it";
	private static final String LDAP_CREDENTIAL = "x7ap2roj";
	
	private static final String ATTRIBUTE_FOR_USER = "sAMAccountName";
	private static final String ATTRIBUTE_FOR_NAME = "cn";
	private static final String ATTRIBUTE_FOR_MAIL = "mail";
	
	@Autowired
	@Qualifier("delphiUserDao")
	DelphiUserDao userDao;
	
	@Transactional
	public void authenticate(String username, String password) throws AuthenticationException {
		String errorString = null;
		//Search on DB
		DelphiUser u = userDao.selectUserByUsername(username);
		if (u != null) {
			// You specify in the authenticate user the attributes that you want returned.
			// Some companies use standard attributes <like 'description' to hold an employee ID.
			Attributes att = null;
			try {
				att = authenticateLdapUser(username, password,
						LDAP_DOMAIN, LDAP_HOST, LDAP_BASE_DN);
			} catch (NamingException e) {
				LOG.debug(username+" non presente in ldap");
				errorString = username+" non presente in ldap";
			}
			//Se l'utente è nel DB verifica:
			//Password ldap corretta altrimenti password DB corretta
			if (att == null) {
				errorString = "Password errata";
			}
			//String name = att.get(ATTRIBUTE_FOR_NAME).toString();
			//name = name.substring(ATTRIBUTE_FOR_NAME.length()+2);
		} else {
			errorString = username+" non autorizzato";
		}
		if (errorString != null) throw new AuthenticationException(errorString);
	}

	private static Attributes authenticateLdapUser(String userName, String password,
			String domain, String host, String baseDn) throws NamingException {
		String returnedAtts[] = { ATTRIBUTE_FOR_USER, ATTRIBUTE_FOR_NAME, ATTRIBUTE_FOR_MAIL };
		String searchFilter = "(&(objectClass=user)(" + ATTRIBUTE_FOR_USER
				+ "=" + userName + "))";
		Attributes result = null;
		// Create the search controls
		SearchControls searchCtls = new SearchControls();
		searchCtls.setReturningAttributes(returnedAtts);
		// Specify the search scope
		searchCtls.setSearchScope(SearchControls.SUBTREE_SCOPE);
		String searchBase = baseDn;
		Hashtable<String, String> environment = new Hashtable<String, String>();
		environment.put(Context.INITIAL_CONTEXT_FACTORY, "com.sun.jndi.ldap.LdapCtxFactory");
		// Using standard Port, check your instalation
		environment.put(Context.PROVIDER_URL, "ldap://" + host + ":389");
		environment.put(Context.SECURITY_AUTHENTICATION, "simple");
		environment.put(Context.SECURITY_PRINCIPAL, LDAP_PRINCIPAL);//username + "@" + domain);
		environment.put(Context.SECURITY_CREDENTIALS, LDAP_CREDENTIAL);//password);
		LdapContext ldapCtx = null;
		//Acquisisce il context
		ldapCtx = new InitialLdapContext(environment, null);
		//Ottiene il dn dell'utente cercato
		NamingEnumeration<SearchResult> answer = ldapCtx.search(searchBase,
				searchFilter, searchCtls);
		String dn = null;
		while (answer.hasMoreElements()) {
			SearchResult sr = answer.next();
			dn = sr.getName()+","+baseDn;
			result = sr.getAttributes();
			if (result == null) {
				throw new NamingException("Could not connect to the directory");
			}
		}
		ldapCtx.close();
		if (dn == null) {
			throw new NamingException("Could not connect to the directory");
		}
        // Authenticate
		environment.put(Context.SECURITY_AUTHENTICATION, "simple");
		environment.put(Context.SECURITY_PRINCIPAL, dn);
		environment.put(Context.SECURITY_CREDENTIALS, password);

        try {
			ldapCtx = new InitialLdapContext(environment, null);
			ldapCtx.close();
		} catch (NamingException e) {
			throw new NamingException("User "+ userName + " could not be found on the directory");
		}
        return result;
	}
	
}
