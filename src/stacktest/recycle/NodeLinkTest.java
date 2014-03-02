package stacktest.recycle;

/**
 * @author zhaohongxu
 * 
 */
public class NodeLinkTest {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// ≤‚ ‘ ˝æ›
		String[][] metrix = { { "0", "1", "1", "0", "0" },
				{ "1", "0", "1", "1", "0" }, { "1", "1", "0", "0", "1" },
				{ "0", "1", "0", "0", "0" }, { "0", "0", "1", "0", "0" } };

		int size = metrix.length;
		NodeLink nodeLink = new NodeLink(size);
		Node[] nodes = nodeLink.initialNodeLink(metrix);

		nodeLink.makeAllNodesFalse(nodes);

		for (int i = 1; i < size + 1; i++) {
			nodeLink.pushNodeToStack(i);
			nodeLink.makeAllNodesFalse(nodes);
		}
		nodeLink.printNodeLinkInfo(nodes);
	}

}
