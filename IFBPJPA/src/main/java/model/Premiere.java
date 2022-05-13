package model;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the premiere database table.
 * 
 */
@Entity
@NamedQuery(name="Premiere.findAll", query="SELECT p FROM Premiere p")
public class Premiere implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id_premiere")
	private int idPremiere;

	@Column(name="date_of_premiere")
	private String dateOfPremiere;

	private String town;

	//bi-directional many-to-one association to Movie
	@ManyToOne
	private Movie movie;

	public Premiere() {
	}

	public int getIdPremiere() {
		return this.idPremiere;
	}

	public void setIdPremiere(int idPremiere) {
		this.idPremiere = idPremiere;
	}

	public String getDateOfPremiere() {
		return this.dateOfPremiere;
	}

	public void setDateOfPremiere(String dateOfPremiere) {
		this.dateOfPremiere = dateOfPremiere;
	}

	public String getTown() {
		return this.town;
	}

	public void setTown(String town) {
		this.town = town;
	}

	public Movie getMovie() {
		return this.movie;
	}

	public void setMovie(Movie movie) {
		this.movie = movie;
	}

}