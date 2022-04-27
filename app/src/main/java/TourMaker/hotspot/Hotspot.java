// License: GPL. For details, see LICENSE file.
package TourMaker.hotspot;

/**
 *
 * @author Kishan Tripathi
 */
public abstract class Hotspot {
  public double azimuthal;
  public double polar;
  private final HotspotType type;

  public HotspotType getType() {
    return type;
  }

  public String getValue() {
    return value;
  }
  private final String value;
  
  public Hotspot(double az, double po, HotspotType typ, String value) {
    this.azimuthal = az;
    this.polar = po;
    this.type = typ;
    this.value = value;
  }
  public abstract void onClick();
}
