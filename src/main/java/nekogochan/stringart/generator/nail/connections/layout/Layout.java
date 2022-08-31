package nekogochan.stringart.generator.nail.connections.layout;

import nekogochan.stringart.generator.nail.Nail;

import java.util.List;

/**
 * Specifies how the nails are placed on the field
 */
public interface Layout {
  /**
   * @return list of nails corresponding to the layout
   */
  List<Nail> createNails();
}
