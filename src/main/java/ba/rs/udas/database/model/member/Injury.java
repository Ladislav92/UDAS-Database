package ba.rs.udas.database.model.member;

public class Injury {

  private String injury;
  private boolean amputation;

  public Injury(String povreda, boolean amputacija) {
    this.injury = povreda;
    this.amputation = amputacija;
  }


  public String getInjury() {
    return injury;
  }

  public void setInjury(String injury) {
    this.injury = injury;
  }

  public boolean isAmputation() {
    return amputation;
  }

  public void setAmputation(boolean amputation) {
    this.amputation = amputation;
  }
}
