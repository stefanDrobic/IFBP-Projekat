package model;

import java.io.Serializable;
import javax.persistence.*;

/**
 * The primary key class for the comment database table.
 * 
 */
@Embeddable
public class CommentPK implements Serializable {
	//default serial version id, required for serializable classes.
	private static final long serialVersionUID = 1L;

	@Column(name="account_id_account", insertable=false, updatable=false)
	private int accountIdAccount;

	@Column(name="movie_id_movie", insertable=false, updatable=false)
	private int movieIdMovie;
	
	public CommentPK() {
	}
	public int getAccountIdAccount() {
		return this.accountIdAccount;
	}
	public void setAccountIdAccount(int accountIdAccount) {
		this.accountIdAccount = accountIdAccount;
	}
	public int getMovieIdMovie() {
		return this.movieIdMovie;
	}
	public void setMovieIdMovie(int movieIdMovie) {
		this.movieIdMovie = movieIdMovie;
	}

	public boolean equals(Object other) {
		if (this == other) {
			return true;
		}
		if (!(other instanceof CommentPK)) {
			return false;
		}
		CommentPK castOther = (CommentPK)other;
		return 
			(this.accountIdAccount == castOther.accountIdAccount)
			&& (this.movieIdMovie == castOther.movieIdMovie);
	}

	public int hashCode() {
		final int prime = 31;
		int hash = 17;
		hash = hash * prime + this.accountIdAccount;
		hash = hash * prime + this.movieIdMovie;
		
		return hash;
	}
}