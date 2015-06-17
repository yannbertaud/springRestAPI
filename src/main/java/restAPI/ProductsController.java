package main.java.restAPI;

import java.util.ArrayList;

import main.java.exceptions.ItemNotFoundException;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/products")
public class ProductsController extends DataUtil{
	
	
	@RequestMapping(method = RequestMethod.GET)
	public ArrayList<Product> getAllProducts() {
		ArrayList<Product> products = new ArrayList<Product> ();
		JSONArray productsJson = this.getProductsJson();
		for (int i = 0; i < productsJson.size(); i++) {
			JSONObject jsonProduct = (JSONObject) productsJson.get(i);
			Product product = new Product(jsonProduct);
			products.add(product);
		}
		return products;
	}
	
	@RequestMapping(value = "/count")
	public int getProductCount() {
		return this.getProductsJson().size();
	}
	
	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public Product getProduct(@PathVariable long id, Model model) {
		Product product = this.getById(id);
		if (product == null) {
			throw new ItemNotFoundException(id, "product");
		}
		return product;
	}
	
    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<Product> createProduct(@RequestBody Product item) {
    	int countBefore = this.getProductCount();
    	JSONArray itemsJson = this.getProductsJson();
    	System.out.println("creating product\n" + item.toString());
    	itemsJson.add(item.toJsonObject());
    	
    	System.out.println("new user count: " + itemsJson.size() + ", was: " + countBefore);
    	return new ResponseEntity<Product> (item, HttpStatus.CREATED);
    }
	
    @RequestMapping(value = "/delete/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<Product> deleteUser(@PathVariable long id, Model model) {
    	Product item = getById(id);
    	if (item != null) {
        	return new ResponseEntity<Product> (item, HttpStatus.ACCEPTED);
    	} else {
    		return new ResponseEntity<Product> (item, HttpStatus.NOT_FOUND);
    	}
    }
    
	
	private Product getById(long itemId) {
		JSONArray jsonItems = this.getProductsJson();
		Product product = null;
		for (int i = 0; i < jsonItems.size(); i++) {
			JSONObject jsonItem = (JSONObject) jsonItems.get(i);
			if((long) jsonItem.get("id") == itemId) {
				product = new Product(jsonItem);
				break;
			}
		}
		return product;
	}
}
