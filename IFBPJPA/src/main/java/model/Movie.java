package model;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the movie database table.
 * 
 */
@Entity
@NamedQuery(name="Movie.findAll", query="SELECT m FROM Movie m")
public class Movie implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id_movie")
	private int idMovie;

	private String actors;

	private String description;

	private String genres;

	@Column(name="image_link")
	private String imageLink;

	private String length;

	private String rating;

	private String title;

	@Column(name="video_link")
	private String videoLink;

	@Column(name="year_of_release")
	private String yearOfRelease;

	//bi-directional many-to-one association to Comment
	@OneToMany(mappedBy="movie")
	private List<Comment> comments;

	//bi-directional many-to-one association to Account
	@ManyToOne
	private Account account;

	//bi-directional many-to-one association to Premiere
	@OneToMany(mappedBy="movie")
	private List<Premiere> premieres;

	public Movie() {
	}

	public int getIdMovie() {
		return this.idMovie;
	}

	public void setIdMovie(int idMovie) {
		this.idMovie = idMovie;
	}

	public String getActors() {
		return this.actors;
	}

	public void setActors(String actors) {
		this.actors = actors;
	}

	public String getDescription() {
		return this.description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getGenres() {
		return this.genres;
	}

	public void setGenres(String genres) {
		this.genres = genres;
	}

	public String getImageLink() {
		return this.imageLink;
	}

	public void setImageLink(String imageLink) {
		this.imageLink = imageLink;
	}

	public String getLength() {
		return this.length;
	}

	public void setLength(String length) {
		this.length = length;
	}

	public String getRating() {
		return this.rating;
	}

	public void setRating(String rating) {
		this.rating = rating;
	}

	public String getTitle() {
		return this.title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getVideoLink() {
		return this.videoLink;
	}

	public void setVideoLink(String videoLink) {
		this.videoLink = videoLink;
	}

	public String getYearOfRelease() {
		return this.yearOfRelease;
	}

	public void setYearOfRelease(String yearOfRelease) {
		this.yearOfRelease = yearOfRelease;
	}

	public List<Comment> getComments() {
		return this.comments;
	}

	public void setComments(List<Comment> comments) {
		this.comments = comments;
	}

	public Comment addComment(Comment comment) {
		getComments().add(comment);
		comment.setMovie(this);

		return comment;
	}

	public Comment removeComment(Comment comment) {
		getComments().remove(comment);
		comment.setMovie(null);

		return comment;
	}

	public Account getAccount() {
		return this.account;
	}

	public void setAccount(Account account) {
		this.account = account;
	}

	public List<Premiere> getPremieres() {
		return this.premieres;
	}

	public void setPremieres(List<Premiere> premieres) {
		this.premieres = premieres;
	}

	public Premiere addPremiere(Premiere premiere) {
		getPremieres().add(premiere);
		premiere.setMovie(this);

		return premiere;
	}

	public Premiere removePremiere(Premiere premiere) {
		getPremieres().remove(premiere);
		premiere.setMovie(null);

		return premiere;
	}

}