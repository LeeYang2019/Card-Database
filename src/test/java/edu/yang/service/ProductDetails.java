package edu.yang.service;

import com.fasterxml.jackson.annotation.JsonProperty;
import javax.annotation.Generated;

@Generated("com.robohorse.robopojogenerator")
public class ProductDetails {

	@JsonProperty("cleanName")
	private String cleanName;

	@JsonProperty("modifiedOn")
	private String modifiedOn;

	@JsonProperty("productId")
	private int productId;

	@JsonProperty("imageUrl")
	private String imageUrl;

	@JsonProperty("groupId")
	private int groupId;

	@JsonProperty("name")
	private String name;

	@JsonProperty("categoryId")
	private int categoryId;

	@JsonProperty("url")
	private String url;

	public void setCleanName(String cleanName){
		this.cleanName = cleanName;
	}

	public String getCleanName(){
		return cleanName;
	}

	public void setModifiedOn(String modifiedOn){
		this.modifiedOn = modifiedOn;
	}

	public String getModifiedOn(){
		return modifiedOn;
	}

	public void setProductId(int productId){
		this.productId = productId;
	}

	public int getProductId(){
		return productId;
	}

	public void setImageUrl(String imageUrl){
		this.imageUrl = imageUrl;
	}

	public String getImageUrl(){
		return imageUrl;
	}

	public void setGroupId(int groupId){
		this.groupId = groupId;
	}

	public int getGroupId(){
		return groupId;
	}

	public void setName(String name){
		this.name = name;
	}

	public String getName(){
		return name;
	}

	public void setCategoryId(int categoryId){
		this.categoryId = categoryId;
	}

	public int getCategoryId(){
		return categoryId;
	}

	public void setUrl(String url){
		this.url = url;
	}

	public String getUrl(){
		return url;
	}

	@Override
 	public String toString(){
		return 
			"ProductDetails{" +
			"cleanName = '" + cleanName + '\'' + 
			",modifiedOn = '" + modifiedOn + '\'' + 
			",productId = '" + productId + '\'' + 
			",imageUrl = '" + imageUrl + '\'' + 
			",groupId = '" + groupId + '\'' + 
			",name = '" + name + '\'' + 
			",categoryId = '" + categoryId + '\'' + 
			",url = '" + url + '\'' + 
			"}";
		}
}