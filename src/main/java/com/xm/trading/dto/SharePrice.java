package com.xm.trading.dto;

import java.util.Objects;

import org.apache.commons.csv.CSVRecord;

public class SharePrice {

	
    @Override
	public String toString() {
		return "SharePrice [timestamp=" + timestamp + ", symbol=" + symbol + ", price=" + price + "]";
	}

	public Long getTimestamp() {
		return timestamp;
	}

	public void setTimestamp(Long timestamp) {
		this.timestamp = timestamp;
	}

	public String getSymbol() {
		return symbol;
	}

	public void setSymbol(String symbol) {
		this.symbol = symbol;
	}

	public Double getPrice() {
		return price;
	}

	public void setPrice(Double price) {
		this.price = price;
	}

	private Long timestamp;

    
    private String symbol;

    
    private Double price;
    
    
    
    
    public SharePrice ( Long timestamp,String symbol,Double price)
    {
        this.timestamp = Objects.requireNonNull( timestamp );
        this.symbol = Objects.requireNonNull( symbol );
        this.price=Objects.requireNonNull(price);
    }

    // Parsing from Apache Commons CSV record
    static public SharePrice parse ( CSVRecord csvRecord )
    {
    	Long timestamp  =Long.parseLong( Objects.requireNonNull( csvRecord.get( "timestamp" ) )) ;
        String symbol = Objects.requireNonNull( csvRecord.get( "symbol" ) );
        Double price =Double.valueOf(Objects.requireNonNull( csvRecord.get( "price" ) ));
        
        SharePrice sharePrice = new SharePrice(timestamp,symbol,price);
        
        return sharePrice;
    }
}
