package nekogochan.stringart.generator.nail.connections.connections;

import nekogochan.stringart.generator.nail.Nail;
import nekogochan.stringart.math.circle.Circle;

/**
 * Describes how nails connected with each others:
 *
 *          {@link Circle.TangentSides}
 * nail -> | FROM_LEFT_TO_LEFT  -> ...nails
 *         | FROM_LEFT_TO_RIGHT -> ...nails
 *         | ...
 */
public interface Connections {
  /**
   * @param nail a nail from which segments can be described
   * @param sides a sides from which segments should be described
   * @return all nails to which a segment can be described for the passed parameters
   */
  Iterable<Nail> getConnections(Nail nail, Circle.TangentSides sides);

  /**
   * @return all nails, that might be used in method {@link #getConnections} as first parameters
   */
  Iterable<Nail> getUsedNails();
}
