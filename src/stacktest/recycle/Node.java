package stacktest.recycle;

/**
 * 
 * �ڵ�
 * 
 * @author zhaohongxu
 * 
 */
public class Node {

	private int id;// �ڵ��Ψһ��ʾ��
	private String value;// �ڵ��ֵ
	private Node nextNode;// �ڵ����һ��ָ��
	private boolean visited;// �ڵ�ķ��ʱ�ʶ
	
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
