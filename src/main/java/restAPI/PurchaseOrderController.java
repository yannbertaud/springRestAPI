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
@RequestMapping(value = "/purchaseOrders")
public class PurchaseOrderController {
	private DataUtil dataUtil = new DataUtil("purchaseOrders");
	
    @RequestMapping(method = RequestMethod.GET)
    public ArrayList<PurchaseOrder> getAll() {
    	ArrayList<PurchaseOrder> items = new ArrayList<PurchaseOrder>();
    	JSONArray itemsJson = dataUtil.getItems();
    	for(int i = 0; i < itemsJson.size(); i++) {
    		JSONObject jsonItem = (JSONObject) itemsJson.get(i);
    		PurchaseOrder item = new PurchaseOrder(jsonItem);
    		items.add(item);
    	}
    	return items;
    }
    
    @RequestMapping(value = "/find")
	public ArrayList<PurchaseOrder> find(@RequestParam(value = "userId", required = false) Long userId, 
			@RequestParam(value = "productId", required = false) Long productId) {
		ArrayList<PurchaseOrder> items = new ArrayList<PurchaseOrder>();
		JSONArray itemsJson = dataUtil.getItems();
		for (int i = 0; i < itemsJson.size(); i++) {
			PurchaseOrder item = new PurchaseOrder((JSONObject) itemsJson.get(i));
			if (userId == null && productId == null) {
				items.add(item);
			} else if (productId == null && userId != null && item.getUserId() == userId) {
				items.add(item);
			} else if (productId != null && userId == null && item.getProductId() == productId) {
				items.add(item);
			} else if (productId != null && userId != null && item.getProductId() == productId && item.getUserId() == userId) {
				items.add(item);
			}
		}
		if (items.size() == 0 ) {
			throw new ItemNotFoundException("Could not find purchaseOrder with userId: " + userId + " or with productId: " + productId );
		}
		return items;
	}
    
	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public PurchaseOrder getPurchaseOrder(@PathVariable long id, Model model) {
		PurchaseOrder item = this.getById(id);
		if (item == null) {
			throw new ItemNotFoundException(id, "purchaseOrder");
		}
		return item;
	}
	
	private PurchaseOrder getById(long itemId) {
		JSONArray jsonItems = dataUtil.getItems();
		PurchaseOrder product = null;
		for (int i = 0; i < jsonItems.size(); i++) {
			JSONObject jsonItem = (JSONObject) jsonItems.get(i);
			if((long) jsonItem.get("id") == itemId) {
				product = new PurchaseOrder(jsonItem);
				break;
			}
		}
		return product;
	}

}
