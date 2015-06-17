package main.java.restAPI;

import java.util.ArrayList;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/entitlements")
public class EntitlementsController {
	DataUtil dataUtil = new DataUtil("entitlements");

	@RequestMapping(method = RequestMethod.GET)
	public ArrayList<Entitlement> getAll() {
		ArrayList<Entitlement> items = new ArrayList<Entitlement> ();
		JSONArray itemsJson = dataUtil.getItems();
		for (int i = 0; i < itemsJson.size(); i++) {
			JSONObject jsonItem = (JSONObject) itemsJson.get(i);
			Entitlement item = new Entitlement(jsonItem);
			items.add(item);
		}
		return items;
	}


}
