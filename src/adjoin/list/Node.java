package adjoin.list;

/**
 * @author zhaohongxu
 * 
 */
public class Node {

	private String nodeId;// ��ʾNode����Ψһ���
	private Object value;// Node�е���ֵ
	private Node nextNode;// ָ����һ�����
	private boolean visited;// ���ʱ�ʶ

	public String getNodeId() {
		return nodeId;
	}

	public void setNodeId(String nodeId) {
		this.nodeId = nodeId;
	}

	public Object getValue() {
		return value;
	}

	public void setValue(Object value) {
		this.value = value;
	}

	public Node getNextNode() {
		return nextNode;
	}

	public void setNextNode(Node nextNode) {
		this.nextNode = nextNode;
	}

	public Node(String nodeId, Object value, Node nextNode) {
		super();
		this.nodeId = nodeId;
		this.value = value;
		this.nextNode = nextNode;
	}

	public Node() {
		super();
	}

	public boolean isVisited() {
		return visited;
	}

	public void setVisited(boolean visited) {
		this.visited = visited;
	}

}
