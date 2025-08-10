package za.co.ashtech.stroller.db.entities;

import java.time.LocalDateTime;

import jakarta.persistence.*;

@Entity
@Table(name = "STROLL_USER_COMMENT")
public class StrollUserComment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "first_name")
    private String firstname;
    @Column(name = "last_name")
    private String lastname;
    @Column(name = "email")
    private String email;
    @Column(name = "user_comment")
    private String userComment;
    @Column(name = "comment_timestamp")
    private LocalDateTime commentTimestamp;
    
	public StrollUserComment() {
		super();
	}
    
	public StrollUserComment(String firstname, String lastname, String email, String userComment) {
		super();
		this.firstname = firstname;
		this.lastname = lastname;
		this.email = email;
		this.userComment = userComment;
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
	public String getLastname() {
		return lastname;
	}
	public void setLastname(String lastname) {
		this.lastname = lastname;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getUserComment() {
		return userComment;
	}
	public void setUserComment(String userComment) {
		this.userComment = userComment;
	}
	public LocalDateTime getCommentTimestamp() {
		return commentTimestamp;
	}
	public void setCommentTimestamp(LocalDateTime commentTimestamp) {
		this.commentTimestamp = commentTimestamp;
	}
	
}