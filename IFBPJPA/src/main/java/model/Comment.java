package model;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the comment database table.
 * 
 */
@Entity
@NamedQuery(name="Comment.findAll", query="SELECT c FROM Comment c")
public class Comment implements Serializable {
	private static final long serialVersionUID = 1L;

	@EmbeddedId
	private CommentPK id;

	private String comment;

	private String rating;

	//bi-directional many-to-one association to Account
	@ManyToOne
	@JoinColumn(name="account_id_account", insertable=false, updatable=false)
	private Account account;

	//bi-directional many-to-one association to Movie
	@ManyToOne
	@JoinColumn(name="movie_id_movie", insertable=false, updatable=false)
	private Movie movie;

	public Comment() {
	}

	public CommentPK getId() {
		return this.id;
	}

	public void setId(CommentPK id) {
		this.id = id;
	}

	public String getComment() {
		return this.comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}

	public String getRating() {
		return this.rating;
	}

	public void setRating(String rating) {
		this.rating = rating;
	}

	public Account getAccount() {
		return this.account;
	}

	public void setAccount(Account account) {
		this.account = account;
	}

	public Movie getMovie() {
		return this.movie;
	}

	public void setMovie(Movie movie) {
		this.movie = movie;
	}

}