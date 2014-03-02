package stacktest.recycle;

import java.util.Stack;

/**
 * 
 * �ڽӱ�
 * 
 * @author zhaohongxu
 * 
 */
public class NodeLink {

	private Stack<Node> stack = new Stack<Node>();// ���ڱ������ջ�ڵ�����
	private Node[] nodes;

	public NodeLink() {
		super();
		stack = new Stack<Node>();
	}

	public Node[] getNodes() {
		return nodes;
	}

	public void setNodes(Node[] nodes) {
		this.nodes = nodes;
	}

	public Stack<Node> getStack() {
		return stack;
	}

	public void setStack(Stack<Node> stack) {
		this.stack = stack;
	}

	/**
	 * ��ʼ��ÿһ�����е�ÿ���ڵ�
	 * 
	 * @param size
	 */
	public NodeLink(int size) {
		stack = new Stack<Node>();
		nodes = new Node[size];
		for (int i = 0; i < size; i++) {
			nodes[i] = new Node();
			nodes[i].setId(i + 1);
			nodes[i].setVisited(false);
			nodes[i].setValue(String.valueOf(2 * (i + 1) + 1));
		}
	}

	/**
	 * ���ݶ�ά�������ڽӱ�
	 * 
	 * @param metrix
	 *            ָ����ά����
	 * @return ���ع�����ڽӱ�
	 */
	public Node[] initialNodeLink(String[][] metrix) {

		int line = metrix.length;
		int cols = metrix[0].length;
		for (int i = 0; i < line; i++) {
			Node tempNode = nodes[i];
			for (int j = 0; j < cols; j++) {
				if (!metrix[i][j].equals("0")) {
					Node node = new Node();
					node.setId(j + 1);
					node.setValue(String.valueOf(2 * (j + 1) + 1));
					node.setVisited(false);
					node.setNextNode(null);
					tempNode.setNextNode(node);
					tempNode = tempNode.getNextNode();
				}
			}
		}
		return nodes;
	}

	/**
	 * 
	 * ���ڽ�������Ϣ���������̨�� �����ʽ�磺(1,3)-->(2,5)....
	 * 
	 * @param nodes
	 *            ָ���Ľڵ�����
	 */
	public void printNodeLinkInfo(Node[] nodes) {
		int size = nodes.length;
		for (int i = 0; i < size; i++) {
			Node tempNode = nodes[i];
			System.out.print("The " + (i + 1) + "th node's link info is:");
			while (tempNode.getNextNode() != null) {
				System.out.print("(" + tempNode.getId() + ","
						+ tempNode.getValue() + ")" + "-->");// +
																// tempNode.isVisited()
				tempNode = tempNode.getNextNode();
			}
			System.out.println("(" + tempNode.getId() + ","
					+ tempNode.getValue() + "),");// + tempNode.isVisited()
		}
	}

	/**
	 * 
	 * ���ڽӱ������еĽڵ�ķ��ʱ�ʾ����Ϊfalse
	 * 
	 * @param nodes
	 *            ָ���ڵ�����
	 * @return ���ش����Ľڵ�����
	 */
	public Node[] makeAllNodesFalse(Node[] nodes) {

		int size = nodes.length;

		for (int i = 0; i < size; i++) {
			Node tempNode = nodes[i];
			while (tempNode.getNextNode() != null) {
				tempNode.setVisited(false);
				tempNode = tempNode.getNextNode();
			}
			tempNode.setVisited(false);
		}
		return nodes;
	}

	/**
	 * 
	 * ���ڽӱ������еĽڵ�ķ��ʱ�ʾ����ΪTrue
	 * 
	 * @param nodes
	 *            ָ���ڵ�����
	 * @return ���ش����Ľڵ�����
	 */
	public Node[] makeAllNodesTrue(Node[] nodes) {
		int size = nodes.length;
		for (int i = 0; i < size; i++) {
			Node tempNode = nodes[i];
			while (tempNode.getNextNode() != null) {
				tempNode.setVisited(true);
				tempNode = tempNode.getNextNode();
			}
			tempNode.setVisited(true);
		}
		return nodes;
	}

	/**
	 * 
	 * ��ָ���ڵ����Ϊid�����нڵ�ķ��ʱ�ʾ��ΪFalse
	 * 
	 * @param id
	 *            ָ����id���
	 * @param nodes
	 *            ָ���Ľڵ�����
	 * @return ���ش����Ľڵ�����
	 */
	public Node[] makeSpecifyNodeFalse(int id, Node[] nodes) {
		int size = nodes.length;
		for (int i = 0; i < size; i++) {
			Node tempNode = nodes[i];
			int nodeId;
			while (tempNode.getNextNode() != null) {
				nodeId = tempNode.getId();
				if (nodeId == id) {
					tempNode.setVisited(false);
				}
				tempNode = tempNode.getNextNode();
			}
			nodeId = tempNode.getId();
			if (nodeId == id) {
				tempNode.setVisited(false);
			}
		}
		return nodes;
	}

	/**
	 * 
	 * ��ָ���ڵ����Ϊid�����нڵ�ķ��ʱ�ʾ��Ϊtrue
	 * 
	 * @param id
	 *            ָ����id���
	 * @param nodes
	 *            ָ���Ľڵ�����
	 * @return ���ش����Ľڵ�����
	 */
	public Node[] makeSpecifyNodeTrue(int id, Node[] nodes) {
		int size = nodes.length;
		for (int i = 0; i < size; i++) {
			Node tempNode = nodes[i];
			int nodeId;
			while (tempNode.getNextNode() != null) {
				nodeId = tempNode.getId();
				if (nodeId == id) {
					tempNode.setVisited(true);
				}
				tempNode = tempNode.getNextNode();
			}
			nodeId = tempNode.getId();
			if (nodeId == id) {
				tempNode.setVisited(true);
			}
		}
		return nodes;
	}

	// ����ʼ���startid����������֮�����Ľڵ����������ȷ���
	public void pushNodeToStack(int startid) {

		Node tempNode = nodes[startid - 1];
		System.out.println("��" + startid + "Ϊ���Ľڵ�Ľڵ㿪ʼ��Ƚ�ջ!");
		while (!tempNode.isVisited()) {
			stack.push(tempNode);
			makeSpecifyNodeTrue(startid, nodes);
			if (tempNode.getNextNode() != null) {
				tempNode = tempNode.getNextNode();
				while (tempNode.isVisited()) {
					if (tempNode.getNextNode() != null) {
						tempNode = tempNode.getNextNode();
					} else {// ִ�г�ջ�������������еĻ�·��Ϣ���������̨��
						System.out.println("��ջ��ϣ���ʼ��ջ��");
						break;
					}
				}
				startid = tempNode.getId();
				tempNode = nodes[startid - 1];// pushNodeToStack(startid);
			}
		}

		System.out.println("���ջ�ĵ�һ��Ԫ�أ�-->" + stack.firstElement().getId());
		System.out.println("���ջ�����һ��Ԫ�أ�-->" + stack.lastElement().getId());
		if (!stack.isEmpty()) {
			int size = stack.size();
			// ��ջ֮ǰ������ýڵ�������Ƿ��Ѿ�ȫ�����ʣ�
			// examPopNodeLinkVisited(popNode);
			if (size >= 3) {
				findRecycleRoutine(stack);// Ѱ���ɳ�ջ���ջ�ڵĵ㹹�ɵĻ�·
				stack.clear();// Ѱ����ϣ����
			} else {
				// ջ��գ�ʹ���´ν�ջʱ��
				stack.clear();
			}
		} else {
			System.out.println("ջ��û��Ԫ�أ�");
		}
	}

	/**
	 * @param stack2
	 *            �ṩ��ʣ��ջ
	 * @param popNode
	 *            ջ�ĵ�һ��Ԫ��
	 */
	private void findRecycleRoutine(Stack<Node> stack2) {
		int size = stack2.size();
		while (size >= 3) {
			Node stackLastNode = stack2.lastElement();
			int i = 0;
			while ((size - i) >= 3) {
				Node stackSecondNode = stack2.get(i);
				boolean result = judeExistsRelationOfLink(stackLastNode,
						stackSecondNode);// �жϽڵ�֮���Ƿ�����Ÿ��ӹ�ϵ
				if (result) {
					System.out.println("������������ʾ�Ļ�·����ʱ��i��" + i);
					// ������������
					int sizeK = stack2.size() - 1;
					while (sizeK != i) {// ������������Ҫ���¸�ֵ
						System.out.print(stack2.get(sizeK).getId() + "-->");
						sizeK--;
					}
					if (i == 0) {
						System.out.println(stack2.get(i).getId());
					} else {
						System.out.println(stack2.get(i).getId());
					}
					System.out.println();
					// System.out.println(stack2.get(sizeK).getId());//��ʱ���
				} else {
					// System.out.println("���������ӻ�·��");
				}
				i++;
			}
			stack2.pop();
			size = stack2.size();
		}
	}

	/**
	 * 
	 * �жϵ�һ���ڵ��Ƿ���ڶ����ڵ�֮����������ӹ�ϵ
	 * 
	 * @param stackFirstNode
	 * @param nodeLink
	 */
	private boolean judeExistsRelationOfLink(Node stackFirstNode,
			Node stackSecondNode) {
		Node[] nodeLink = findStackElementsWithFirstElement(stackSecondNode);
		boolean result = false;
		int size = nodeLink.length;
		int i = 0;
		while (size > i) {
			if (nodeLink[i].getId() == stackFirstNode.getId()) {
				result = true;
				break;
			} else {
				i++;
			}
		}
		// System.out.println(result);
		return result;
	}

	/**
	 * Ѱ����ջ�ڵ�һ��������Ԫ��
	 * 
	 * @param stackFirstNode
	 */
	private Node[] findStackElementsWithFirstElement(Node stackFirstNode) {
		int count = 0;
		Node tempNode = stackFirstNode.getNextNode();
		while (tempNode != null) {
			tempNode = tempNode.getNextNode();
			count++;
		}
		Node[] linkNodes = new Node[count];
		tempNode = stackFirstNode.getNextNode();
		int i = 0;
		while (tempNode != null) {
			linkNodes[i] = tempNode;
			i++;
			tempNode = tempNode.getNextNode();
		}
		return linkNodes;
	}
}
