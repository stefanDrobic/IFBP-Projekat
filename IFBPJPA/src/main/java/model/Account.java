package model;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the account database table.
 * 
 */
@Entity
@NamedQuery(name="Account.findAll", query="SELECT a FROM Account a")
public class Account implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id_account")
	private int idAccount;

	private String password;

	private String username;

	//bi-directional many-to-one association to Comment
	@OneToMany(mappedBy="account")
	private List<Comment> comments;

	//bi-directional many-to-one association to Movie
	@OneToMany(mappedBy="account")
	private List<Movie> movies;

	//bi-directional many-to-one association to ForumThread
	@OneToMany(mappedBy="account")
	private List<ForumThread> forumThreads;

	//bi-directional many-to-one association to MovieTrivia
	@OneToMany(mappedBy="account")
	private List<MovieTrivia> movieTrivias;

	//bi-directional many-to-one association to ThreadResponse
	@OneToMany(mappedBy="account")
	private List<ThreadResponse> threadResponses;

	public Account() {
	}

	public int getIdAccount() {
		return this.idAccount;
	}

	public void setIdAccount(int idAccount) {
		this.idAccount = idAccount;
	}

	public String getPassword() {
		return this.password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getUsername() {
		return this.username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public List<Comment> getComments() {
		return this.comments;
	}

	public void setComments(List<Comment> comments) {
		this.comments = comments;
	}

	public Comment addComment(Comment comment) {
		getComments().add(comment);
		comment.setAccount(this);

		return comment;
	}

	public Comment removeComment(Comment comment) {
		getComments().remove(comment);
		comment.setAccount(null);

		return comment;
	}

	public List<Movie> getMovies() {
		return this.movies;
	}

	public void setMovies(List<Movie> movies) {
		this.movies = movies;
	}

	public Movie addMovy(Movie movy) {
		getMovies().add(movy);
		movy.setAccount(this);

		return movy;
	}

	public Movie removeMovy(Movie movy) {
		getMovies().remove(movy);
		movy.setAccount(null);

		return movy;
	}

	public List<ForumThread> getForumThreads() {
		return this.forumThreads;
	}

	public void setForumThreads(List<ForumThread> forumThreads) {
		this.forumThreads = forumThreads;
	}

	public ForumThread addForumThread(ForumThread forumThread) {
		getForumThreads().add(forumThread);
		forumThread.setAccount(this);

		return forumThread;
	}

	public ForumThread removeForumThread(ForumThread forumThread) {
		getForumThreads().remove(forumThread);
		forumThread.setAccount(null);

		return forumThread;
	}

	public List<MovieTrivia> getMovieTrivias() {
		return this.movieTrivias;
	}

	public void setMovieTrivias(List<MovieTrivia> movieTrivias) {
		this.movieTrivias = movieTrivias;
	}

	public MovieTrivia addMovieTrivia(MovieTrivia movieTrivia) {
		getMovieTrivias().add(movieTrivia);
		movieTrivia.setAccount(this);

		return movieTrivia;
	}

	public MovieTrivia removeMovieTrivia(MovieTrivia movieTrivia) {
		getMovieTrivias().remove(movieTrivia);
		movieTrivia.setAccount(null);

		return movieTrivia;
	}

	public List<ThreadResponse> getThreadResponses() {
		return this.threadResponses;
	}

	public void setThreadResponses(List<ThreadResponse> threadResponses) {
		this.threadResponses = threadResponses;
	}

	public ThreadResponse addThreadRespons(ThreadResponse threadRespons) {
		getThreadResponses().add(threadRespons);
		threadRespons.setAccount(this);

		return threadRespons;
	}

	public ThreadResponse removeThreadRespons(ThreadResponse threadRespons) {
		getThreadResponses().remove(threadRespons);
		threadRespons.setAccount(null);

		return threadRespons;
	}

}