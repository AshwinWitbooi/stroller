package za.co.ashtech.stroller.controller.entities;

import java.time.LocalDateTime;
import java.util.LinkedHashMap;
import java.util.Map;
import com.fasterxml.jackson.annotation.JsonAnyGetter;
import com.fasterxml.jackson.annotation.JsonAnySetter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

import za.co.ashtech.stroller.db.entities.StrollTransactionLog;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({ "id", "userId", "transactionType", "transactionResult", "transactionTimestamp" })
public class TransactionLogEntry {

	@JsonProperty("id")
	private Long id;
	@JsonProperty("userId")
	private String userId;
	@JsonProperty("transactionType")
	private String transactionType;
	@JsonProperty("transactionResult")
	private String transactionResult;
	@JsonProperty("transactionTimestamp")
	private String transactionTimestamp;
	@JsonIgnore
	private Map<String, Object> additionalProperties = new LinkedHashMap<String, Object>();
	
	public TransactionLogEntry() {
		super();
		this.id = Long.valueOf(0);
		this.userId = "initial@test.co.za";
		this.transactionType = "initial";
		this.transactionResult = "I";
		this.transactionTimestamp = LocalDateTime.now().toString();
		
	}


	public TransactionLogEntry(StrollTransactionLog dbr) {
		super();
		this.id = dbr.getId();
		this.userId = dbr.getUserId();
		this.transactionType = dbr.getTransactionType();
		this.transactionResult = dbr.getTransactionResult();
		this.transactionTimestamp = dbr.getTransactionTimestamp().toString();
		
	}

	@JsonProperty("id")
	public Long getId() {
		return id;
	}

	@JsonProperty("id")
	public void setId(Long id) {
		this.id = id;
	}

	@JsonProperty("userId")
	public String getUserId() {
		return userId;
	}

	@JsonProperty("userId")
	public void setUserId(String userId) {
		this.userId = userId;
	}

	@JsonProperty("transactionType")
	public String getTransactionType() {
		return transactionType;
	}

	@JsonProperty("transactionType")
	public void setTransactionType(String transactionType) {
		this.transactionType = transactionType;
	}

	@JsonProperty("transactionResult")
	public String getTransactionResult() {
		return transactionResult;
	}

	@JsonProperty("transactionResult")
	public void setTransactionResult(String transactionResult) {
		this.transactionResult = transactionResult;
	}

	@JsonProperty("transactionTimestamp")
	public String getTransactionTimestamp() {
		return transactionTimestamp;
	}

	@JsonProperty("transactionTimestamp")
	public void setTransactionTimestamp(String transactionTimestamp) {
		this.transactionTimestamp = transactionTimestamp;
	}

	@JsonAnyGetter
	public Map<String, Object> getAdditionalProperties() {
		return this.additionalProperties;
	}

	@JsonAnySetter
	public void setAdditionalProperty(String name, Object value) {
		this.additionalProperties.put(name, value);
	}

}
