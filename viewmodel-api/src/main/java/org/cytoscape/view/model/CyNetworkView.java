package org.cytoscape.view.model;

import java.util.Collection;

import org.cytoscape.model.CyDisposable;
import org.cytoscape.model.CyEdge;
import org.cytoscape.model.CyIdentifiable;
import org.cytoscape.model.CyNetwork;
import org.cytoscape.model.CyNode;

/**
 * 
 * Additional methods for CyNetworkView. Network view should implement BOTH View
 * and CyNetworkView.
 * 
 * <p>
 * <i>Warning</i>: if you just added a node or edge in
 * {@link org.cytoscape.model.CyNetwork},
 * {@link #getNodeView} or {@link #getEdgeView}
 * will probably return null for the newly created node or edge.
 * You may have to call
 * {@link org.cytoscape.event.CyEventHelper#flushPayloadEvents}
 * <i>prior</i> to calling {@link #getNodeView} or {@link #getEdgeView},
 * so that the {@code CyNetworkView} gets a chance to create the views.
 * If you are creating a bunch of nodes and edges at once, call {@code flushPayloadEvents}
 * <i>after</i> all the nodes and edges are created.
 * </p>
 * 
 * @CyAPI.Api.Interface
 * @CyAPI.InModule viewmodel-api
 */
public interface CyNetworkView extends ReadableNetworkView, View<CyNetwork>, CyDisposable {

	
	default CyNetworkViewSnapshot createSnapshot() {
		return null;
	}
	
	/**
	 * Returns a View for a specified Node.
	 *
	 * 
	 * @param node Node object
	 * 
	 * @return View for the given node object.
	 */
	@Override
	View<CyNode> getNodeView(CyNode node);

	/**
	 * Returns a list of Views for all CyNodes in the network.
	 * 
	 * @return Collection of all node views in this network.
	 */
	@Override
	Collection<View<CyNode>> getNodeViews();

	/**
	 * Returns a View for a specified Edge.
	 * @param edge the edge to return the view for.
	 * 
	 * @return View model for the edge data.
	 */
	@Override
	View<CyEdge> getEdgeView(CyEdge edge);

	/**
	 * Returns a list of Views for all CyEdges in the network.
	 * 
	 * @return All edge views in this network.
	 */
	@Override
	Collection<View<CyEdge>> getEdgeViews();

	/**
	 * Returns a list of all View including those for Nodes, Edges, and Network.
	 * 
	 * @return All view objects in this network including network view itself.
	 */
	@Override
	Collection<View<? extends CyIdentifiable>> getAllViews();
	
	/**
	 * Utility method to fit content to the presentation container (usually a Swing Window).
	 * This fires event to the presentation layer for updating presentation.
	 */
	void fitContent();
	
	
	/**
	 * Utility method to fit selected graph objects to the presentation container.
	 * This fires event to the presentation layer for updating presentation.
	 */
	void fitSelected();
	
	
	/**
	 * Cascading event for the presentation layer for updating presentation.
	 */
	void updateView();

	
	public default boolean isDirty() {
		return false;
	}
	 
	/**
	 * Sets the default value to be used for the specified visual property.
	 * @param <T> The type of the visual property value. 
	 * @param <V> The default value for the visual property, which must extend T. 
	 * @param vp The visual property whose default value we're specifying.
	 * @param defaultValue The default value to be used for this visual property for this view. 
	 */
	 <T, V extends T> void setViewDefault(final VisualProperty<? extends T> vp, final V defaultValue);

	 
	 default void addNetworkViewListener(CyNetworkViewListener listener) {}
	 
	 default void removeNetworkViewListener(CyNetworkViewListener listener) {}
	 
}
