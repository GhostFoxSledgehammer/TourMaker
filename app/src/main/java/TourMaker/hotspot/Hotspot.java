// License: GPL. For details, see LICENSE file.
package TourMaker.hotspot;

/**
 *
 * @author Kishan Tripathi
 */
public abstract class Hotspot {
  private double azimuthal;
  private double polar;
  private HotspotType type;
  
  public Hotspot(double az, double po, HotspotType typ) {
    azimuthal = az;
    polar = po;
    type = typ;
  }
  public abstract void onClick();
}
