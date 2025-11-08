package za.co.ashtech.stroller.db.entities;

import java.time.LocalDateTime;

import jakarta.persistence.*;

@Entity
@Table(name = "STROLL_USER_MESSAGE")
public class StrollUserMessage {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "first_name")
    private String firstname;
    @Column(name = "email")
    private String email;
    @Column(name = "user_message")
    private String userMessage;
    @Column(name = "comment_timestamp")
    private LocalDateTime commentTimestamp;
    
	public StrollUserMessage() {
		super();
	}
    
	public StrollUserMessage(String firstname, String email, String userMessage) {
		super();
		this.firstname = firstname;
		this.email = email;
		this.userMessage = userMessage;
		this.commentTimestamp = LocalDateTime.now();
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getFirstname() {
		return firstname;
	}
	public void setFirstname(String firstname) {
		this.firstname = firstname;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}

	public String getUserMessage() {
		return userMessage;
	}

	public void setUserMessage(String userMessage) {
		this.userMessage = userMessage;
	}

	public LocalDateTime getCommentTimestamp() {
		return commentTimestamp;
	}
	public void setCommentTimestamp(LocalDateTime commentTimestamp) {
		this.commentTimestamp = commentTimestamp;
	}
	
}