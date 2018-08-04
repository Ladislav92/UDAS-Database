package ba.rs.udas.database.model.member;

public class Injury {

  private int id;
  private String injury;
  private boolean amputation;

  public Injury( int id, String injury, boolean amputation) {
    this.id = id;
    this.injury = injury;
    this.amputation = amputation;
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
