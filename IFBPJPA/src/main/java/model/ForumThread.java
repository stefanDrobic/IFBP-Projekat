package model;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the forum_thread database table.
 * 
 */
@Entity
@Table(name="forum_thread")
@NamedQuery(name="ForumThread.findAll", query="SELECT f FROM ForumThread f")
public class ForumThread implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id_forum_thread")
	private int idForumThread;

	private String description;

	@Column(name="post_date")
	private String postDate;

	@Column(name="thread_title")
	private String threadTitle;

	//bi-directional many-to-one association to Account
	@ManyToOne
	private Account account;

	//bi-directional many-to-one association to ThreadResponse
	@OneToMany(mappedBy="forumThread")
	private List<ThreadResponse> threadResponses;

	public ForumThread() {
	}

	public int getIdForumThread() {
		return this.idForumThread;
	}

	public void setIdForumThread(int idForumThread) {
		this.idForumThread = idForumThread;
	}

	public String getDescription() {
		return this.description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getPostDate() {
		return this.postDate;
	}

	public void setPostDate(String postDate) {
		this.postDate = postDate;
	}

	public String getThreadTitle() {
		return this.threadTitle;
	}

	public void setThreadTitle(String threadTitle) {
		this.threadTitle = threadTitle;
	}

	public Account getAccount() {
		return this.account;
	}

	public void setAccount(Account account) {
		this.account = account;
	}

	public List<ThreadResponse> getThreadResponses() {
		return this.threadResponses;
	}

	public void setThreadResponses(List<ThreadResponse> threadResponses) {
		this.threadResponses = threadResponses;
	}

	public ThreadResponse addThreadRespons(ThreadResponse threadRespons) {
		getThreadResponses().add(threadRespons);
		threadRespons.setForumThread(this);

		return threadRespons;
	}

	public ThreadResponse removeThreadRespons(ThreadResponse threadRespons) {
		getThreadResponses().remove(threadRespons);
		threadRespons.setForumThread(null);

		return threadRespons;
	}

}