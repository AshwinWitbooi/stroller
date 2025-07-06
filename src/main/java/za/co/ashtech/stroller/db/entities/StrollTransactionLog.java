package za.co.ashtech.stroller.db.entities;

import java.time.LocalDateTime;

import jakarta.persistence.*;

@Entity
@Table(name = "stroll_transaction_log")
public class StrollTransactionLog {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @Column(name = "user_id")
    private String userId;

    @Column(name = "transaction_type")
    private String transactionType;

    @Column(name = "transaction_result")
    private String transactionResult;
    
    @Column(name = "transaction_timestamp")
    private LocalDateTime transactionTimestamp;

    // Constructors
    
    public StrollTransactionLog() {}
    
	public StrollTransactionLog(String userId, String transactionType, String transactionResult) {
		super();
		this.userId = userId;
		this.transactionType = transactionType;
		this.transactionResult = transactionResult;
		this.transactionTimestamp = LocalDateTime.now();
	}
	
	
    // Getters and Setters
	public Long getId() {
		return id;
	}

	public String getUserId() {
		return userId;
	}

	public String getTransactionType() {
		return transactionType;
	}

	public String getTransactionResult() {
		return transactionResult;
	}

	public LocalDateTime getTransactionTimestamp() {
		return transactionTimestamp;
	}

	@Override
	public String toString() {
		return "StrollAuditTrail [id=" + id + ", userId=" + userId + ", transactionType=" + transactionType
				+ ", transactionResult=" + transactionResult + ", transactionTimestamp=" + transactionTimestamp + "]";
	}
    
}
