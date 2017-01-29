package uk.oaknorth.service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.codec.binary.Base64;
import org.codehaus.jackson.JsonParseException;
import org.codehaus.jackson.map.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import uk.oaknorth.domain.Officer;
import uk.oaknorth.utility.EncryptDecryptAPIKey;


public class Service {

	
	final static String URI = "https://api.companieshouse.gov.uk/search/officers";
	final static String AUTH = "Authorization";
	final static String AUTH_TYPE = "Basic ";
	final static String CREDS = "63dGXMLQM6LXd58xKat3a64RHhsuY2Sr3AqVmBfW:";
	
     public Map<String,List<String>> getOfficersService(String companyName){
	 
	 byte[] plainCredsBytes = CREDS.getBytes();
	 byte[] base64CredsBytes = Base64.encodeBase64(plainCredsBytes);
	 String base64Creds = new String(base64CredsBytes);
	 
	 HttpHeaders headers = new HttpHeaders();
	 headers.add(AUTH, AUTH_TYPE + base64Creds);
	 
	 String uri = URI+"?q="+companyName;
	 RestTemplate restTemplate = new RestTemplate();
	 HttpEntity<String> request = new HttpEntity<String>(headers);
	 ResponseEntity<String> response = restTemplate.exchange(uri, HttpMethod.GET, request, String.class);
	 String jsonResponse = response.getBody();
	 Officer obj = new Officer();
	  try {		
		ObjectMapper mapper = new ObjectMapper();
		obj = mapper.readValue(jsonResponse, Officer.class);
	  } catch (JsonParseException e) {
		e.printStackTrace();
	} catch (IOException e) {
		e.printStackTrace();
	}
	
	List<Map<String,Object>> items = obj.getItems(); 
	
	Map<String,List<String>> result =  new HashMap<String, List<String>>();
	String compName = "";
	String officerid ="";
	List<String> officerList = new ArrayList<String>();
	for(Map item : items){
		compName = (String)item.get("title");
		officerid = ((String)((Map)item.get("links")).get("self"));
		officerid = officerid.substring(10,officerid.lastIndexOf("/"));
		if(result.containsKey(compName)){
			officerList = result.get(compName);
			officerList.add(officerid);
			result.put(compName, officerList);
		}else{
			officerList.add(officerid);
			result.put(compName, officerList);
		}
		
	}	
	 return result;
 }
}
