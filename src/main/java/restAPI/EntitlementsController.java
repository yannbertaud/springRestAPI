package main.java.restAPI;

import java.util.ArrayList;

import main.java.exceptions.ItemNotFoundException;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
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

	@RequestMapping(value = "/count")
	public int getCount() {
		return this.dataUtil.getItems().size();
	}	
	
	@RequestMapping(value = "/find")
	public ArrayList<Entitlement> find(@RequestParam(value = "userId", required = false) Long userId,
			@RequestParam(value = "productId", required = false)  Long productId) {
		ArrayList<Entitlement> items = new ArrayList<Entitlement>();
		JSONArray itemsJson = dataUtil.getItems();
		for (int i = 0; i < itemsJson.size(); i++) {
			Entitlement item = new Entitlement((JSONObject) itemsJson.get(i));
			if (userId != null && productId != null && item.getUserId() == userId && item.getProductId() == productId) {
				items.add(item);
			} else if (userId != null && productId == null && item.getUserId() == userId) {
				items.add(item);
			} else if (userId == null && productId != null && item.getProductId() == productId) {
				items.add(item);
			} else if (userId == null && productId == null) {
				items.add(item);
			}
		}
		if (items.size() == 0 ) {
			throw new ItemNotFoundException("Could not find entitlement containing with userId: " + userId + " or productId: " + productId);
		}

		
		return items;
	}
	
	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public Entitlement getProduct(@PathVariable long id, Model model) {
		Entitlement item = this.getById(id);
		if (item == null) {
			throw new ItemNotFoundException(id, "entitlement");
		}
		return item;
	}
	private Entitlement getById(long itemId) {
		JSONArray jsonItems = dataUtil.getItems();
		Entitlement entitlement = null;
		for (int i = 0; i < jsonItems.size(); i++) {
			JSONObject jsonItem = (JSONObject) jsonItems.get(i);
			if((long) jsonItem.get("id") == itemId) {
				entitlement = new Entitlement(jsonItem);
				break;
			}
		}
		return entitlement;
	}

}
