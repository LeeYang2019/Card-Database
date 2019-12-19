package edu.yang.service;

import com.fasterxml.jackson.annotation.JsonProperty;
import javax.annotation.Generated;

/**
 * object TcgPlayer "product details" are mapped to
 */
@Generated("com.robohorse.robopojogenerator")
public class PriceObject {

	@JsonProperty("marketPrice")
	private Object marketPrice;

	@JsonProperty("subTypeName")
	private String subTypeName;

	@JsonProperty("productId")
	private int productId;

	@JsonProperty("directLowPrice")
	private Object directLowPrice;

	@JsonProperty("lowPrice")
	private Object lowPrice;

	@JsonProperty("highPrice")
	private Object highPrice;

	@JsonProperty("midPrice")
	private Object midPrice;

	public void setMarketPrice(Object marketPrice){
		this.marketPrice = marketPrice;
	}

	public Object getMarketPrice(){
		return marketPrice;
	}

	public void setSubTypeName(String subTypeName){
		this.subTypeName = subTypeName;
	}

	public String getSubTypeName(){
		return subTypeName;
	}

	public void setProductId(int productId){
		this.productId = productId;
	}

	public int getProductId(){
		return productId;
	}

	public void setDirectLowPrice(Object directLowPrice){
		this.directLowPrice = directLowPrice;
	}

	public Object getDirectLowPrice(){
		return directLowPrice;
	}

	public void setLowPrice(Object lowPrice){
		this.lowPrice = lowPrice;
	}

	public Object getLowPrice(){
		return lowPrice;
	}

	public void setHighPrice(Object highPrice){
		this.highPrice = highPrice;
	}

	public Object getHighPrice(){
		return highPrice;
	}

	public void setMidPrice(Object midPrice){
		this.midPrice = midPrice;
	}

	public Object getMidPrice(){
		return midPrice;
	}

	@Override
 	public String toString(){
		return 
			"PriceObject{" +
			"marketPrice = '" + marketPrice + '\'' + 
			",subTypeName = '" + subTypeName + '\'' + 
			",productId = '" + productId + '\'' + 
			",directLowPrice = '" + directLowPrice + '\'' + 
			",lowPrice = '" + lowPrice + '\'' + 
			",highPrice = '" + highPrice + '\'' + 
			",midPrice = '" + midPrice + '\'' + 
			"}";
		}
}