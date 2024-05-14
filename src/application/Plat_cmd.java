package application;

public class Plat_cmd {
	private int id;
	private String libelle;
	private int quantite;
	private double supplement;
	private double montant = 0;
	private double PU;
	public Plat_cmd(String libelle, int quantite, double supplement, double montant,
			double PU) {
			this.libelle = libelle;
			this.quantite = quantite;
			this.supplement = supplement;
			this.montant = montant;
			this.PU = PU;
			}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getLibelle() {
		return libelle;
	}
	public void setLibelle(String libelle) {
		this.libelle = libelle;
	}
	public int getQuantite() {
		return quantite;
	}
	public void setQuantite(int quantite) {
		this.quantite = quantite;
	}
	public double getSupplement() {
		return supplement;
	}
	public void setSupplement(double supplement) {
		this.supplement = supplement;
	}
	public double getMontant() {
		return montant;
	}
	public void setMontant(double montant) {
		this.montant = montant;
	}
	public double getPU() {
		return PU;
	}
	public void setPU(double pU) {
		PU = pU;
	}
	@Override
	public String toString() {
		return "Plat_cmd [id=" + id + ", libelle=" + libelle + ", quantite=" + quantite + ", supplement=" + supplement
				+ ", montant=" + montant + ", PU=" + PU + "]";
	}
	

}
