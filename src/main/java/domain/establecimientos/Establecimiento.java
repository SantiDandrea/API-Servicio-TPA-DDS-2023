package domain.establecimientos;

import lombok.Getter;

import javax.persistence.*;

@Getter
@Entity
public class Establecimiento {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private int id;

  public Establecimiento (){ }
}
