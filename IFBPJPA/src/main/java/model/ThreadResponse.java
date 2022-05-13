package model;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the thread_response database table.
 * 
 */
@Entity
@Table(name="thread_response")
@NamedQuery(name="ThreadResponse.findAll", query="SELECT t FROM ThreadResponse t")
public class ThreadResponse implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id_thread_response")
	private int idThreadResponse;

	@Column(name="response_date")
	private String responseDate;

	@Column(name="response_message")
	private String responseMessage;

	//bi-directional many-to-one association to Account
	@ManyToOne
	private Account account;

	//bi-directional many-to-one association to ForumThread
	@ManyToOne
	@JoinColumn(name="forum_thread_id_forum_thread")
	private ForumThread forumThread;

	public ThreadResponse() {
	}

	public int getIdThreadResponse() {
		return this.idThreadResponse;
	}

	public void setIdThreadResponse(int idThreadResponse) {
		this.idThreadResponse = idThreadResponse;
	}

	public String getResponseDate() {
		return this.responseDate;
	}

	public void setResponseDate(String responseDate) {
		this.responseDate = responseDate;
	}

	public String getResponseMessage() {
		return this.responseMessage;
	}

	public void setResponseMessage(String responseMessage) {
		this.responseMessage = responseMessage;
	}

	public Account getAccount() {
		return this.account;
	}

	public void setAccount(Account account) {
		this.account = account;
	}

	public ForumThread getForumThread() {
		return this.forumThread;
	}

	public void setForumThread(ForumThread forumThread) {
		this.forumThread = forumThread;
	}

}