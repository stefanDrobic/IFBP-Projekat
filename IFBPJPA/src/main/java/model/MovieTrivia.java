package model;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the movie_trivia database table.
 * 
 */
@Entity
@Table(name="movie_trivia")
@NamedQuery(name="MovieTrivia.findAll", query="SELECT m FROM MovieTrivia m")
public class MovieTrivia implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id_movie_trivia")
	private int idMovieTrivia;

	private String description;

	@Column(name="image_url")
	private String imageUrl;

	private String title;

	@Column(name="video_url")
	private String videoUrl;

	//bi-directional many-to-one association to Account
	@ManyToOne
	private Account account;

	public MovieTrivia() {
	}

	public int getIdMovieTrivia() {
		return this.idMovieTrivia;
	}

	public void setIdMovieTrivia(int idMovieTrivia) {
		this.idMovieTrivia = idMovieTrivia;
	}

	public String getDescription() {
		return this.description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getImageUrl() {
		return this.imageUrl;
	}

	public void setImageUrl(String imageUrl) {
		this.imageUrl = imageUrl;
	}

	public String getTitle() {
		return this.title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getVideoUrl() {
		return this.videoUrl;
	}

	public void setVideoUrl(String videoUrl) {
		this.videoUrl = videoUrl;
	}

	public Account getAccount() {
		return this.account;
	}

	public void setAccount(Account account) {
		this.account = account;
	}

}