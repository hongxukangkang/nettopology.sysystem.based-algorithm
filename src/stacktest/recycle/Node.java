package stacktest.recycle;

/**
 * 
 * 节点
 * 
 * @author zhaohongxu
 * 
 */
public class Node {

	private int id;// 节点的唯一标示符
	private String value;// 节点的值
	private Node nextNode;// 节点的下一个指针
	private boolean visited;// 节点的访问标识
	
//	private Node fatherNode[];

	public boolean isVisited() {
		return visited;
	}

	public void setVisited(boolean visited) {
		this.visited = visited;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

	public Node getNextNode() {
		return nextNode;
	}

	public void setNextNode(Node nextNode) {
		this.nextNode = nextNode;
	}

}
