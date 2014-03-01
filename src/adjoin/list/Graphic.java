package adjoin.list;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

/**
 * @author zhaohongxu
 * 
 */
public class Graphic {

	private Node[] nodes;// ͼ���������ĵĽڵ�

	public Node[] getNodes() {
		return nodes;
	}

	public void setNodes(Node[] nodes) {
		this.nodes = nodes;
	}

	public Graphic(Node[] nodes) {
		super();
		this.nodes = nodes;
	}

	public Graphic() {
		super();
	}

	/**
	 * ��ʼ��ͼ�е�ÿһ���ڵ㣬���ҽڵ��ÿһ�����ʱ����false��id �Ǵ�0��ʼ���ε�����ֵ��id+3֮���ֵ
	 * 
	 * @param metrix
	 *            �ڽӾ���
	 */
	public void initialGraphic(String[][] metrix) {

		int number = metrix.length;

		nodes = new Node[number];
		for (int i = 0; i < number; i++) {
			nodes[i] = new Node();
			nodes[i].setNodeId(String.valueOf(i + 1));
			nodes[i].setVisited(false);
			nodes[i].setNextNode(null);
		}
	}

	/**
	 * ���ݸ����Ķ�ά������һ���ڽӱ�
	 * 
	 * @param metrix
	 */
	public void makeAdjoinListAccordingToTwoMetrix(String[][] metrix) {

		int line = metrix.length;// ��ά���������
		int cols = metrix[0].length;// ��ά���������

		for (int i = 0; i < line; i++) {
			Node tempNode = nodes[i];
			Node tempNode2 = new Node();
			int number = 0;
			for (int j = 0; j < cols; j++) {
				if (!metrix[i][j].equals("0")) {

					Node node = new Node();
					node.setNextNode(null);
					node.setNodeId(String.valueOf(j + 1));
					node.setVisited(false);

					nodes[i].setNextNode(node);
					nodes[i] = node;
					number++;
					if (number == 1) {// ��¼ָ��ĵ�һ��Ԫ��
						tempNode2 = nodes[i];
					}
				}
			}
			nodes[i] = tempNode;
			if (number >= 1) {
				nodes[i].setNextNode(tempNode2);
			}
		}
	}

	/**
	 * ����ڵ���������
	 */
	public void printGraphNodesLinkInfo(String[][] metrix) {
		int number = metrix.length;
		for (int i = 0; i < number; i++) {
			System.out.println("��" + (i + 1) + "���ڵ���������");
			Node tempNode = nodes[i];
			while ((tempNode != null)) {// && (!nodes[i].isVisited())
				if (tempNode.getNextNode() == null) {
					System.out.print(tempNode.getNodeId());
				} else {
					System.out.print(tempNode.getNodeId() + "-->");
				}
				tempNode = tempNode.getNextNode();
			}
			System.out.println();
		}
	}

	/**
	 * ������������㷨��ѯ����Ȧ�����Ҹ��ݸ��·�� ��Ѱ�һ�·�� һ���ҵ����·�� �������ж����һ���ڵ�����ڽڵ��Id�Ƿ����丸�ڵ��Id��ͬ��
	 * ����������ͬ����Ƚϸ��ڵ�ǰ��ڵ��Id��������ͬ���򹹳ɻ�·�������·���� �ġ�������ͬ����Ƚ���һ�����ڽڵ㣬ת���������
	 * �塢�ж����ڽڵ��Ƿ��Ѿ��Ƚ��꣬���ǱȽ��꣬�򷵻���һ���ڵ㣬���������������û�бȽ��꣬��ת�������
	 * 
	 * @param number
	 *            ���ڿ����ɹ��ɻ�·�Ľ����Ŀ
	 */
	public void execDFSAccordingToAdjoinList() {

		int number = nodes.length;
		for (int i = 0; i < number; i++) {
			List<String> longRoutineList = new ArrayList<String>();

			longRoutineList = findLongRoutine(nodes, i);// Ѱ�Ҵӵ�i���ڵ����������·�����������ǵĽڵ��ű�����һ���б��С�
			printLongRoutineList(longRoutineList, i);// ����·���ĳ���
			// ת�������еĸýڵ�

			// ����ڵ�δ�����ʹ����������ջ��

			// ����������Ȳ�ѯ��

		}
	}

	/**
	 * 
	 * ����·���ĳ���
	 * 
	 * @param longRoutineList
	 * @param k
	 *            �ӵ�k���������
	 */
	private void printLongRoutineList(List<String> longRoutineList, int k) {
		int size = longRoutineList.size();
		if (size < 3) {
			System.out.println("�ڵ�ĸ���С��3,���ܹ������");
		} else {
			System.out.println("��" + (k + 1) + "������������·��Ϊ��");
			for (int i = 0; i < size; i++) {
				System.out.print(longRoutineList.get(i) + "-->");
			}
			System.out.println();
		}

	}

	/**
	 * Ѱ�Ҵӵ�k���ڵ����������·�����������ǵĽڵ��ű�����һ���б��С�
	 * 
	 * @param nodes2
	 *            �ڽӱ�
	 * @param k
	 *            �ӵ�k���ڵ����
	 * @return �ҵ����·��
	 */
	private List<String> findLongRoutine(Node[] nodes2, int k) {
		List<String> longRoutineList = new ArrayList<String>();

		Node tempNode = nodes2[k];
		String nodeId = tempNode.getNodeId();
		longRoutineList.add(nodeId);// �ӽ��б���
		makeVisitedNodeTrue(nodeId);// ������ͬ�ڵ�ŵĽڵ�ȫ����Ϊtrue;
		tempNode = tempNode.getNextNode();
		if (tempNode != null) {
			int tempNodeId = Integer.parseInt(String.valueOf(tempNode
					.getNodeId()));
			while (!nodes2[tempNodeId - 1].isVisited()) {
				longRoutineList.add(tempNode.getNodeId());// �ӽ��б���
				makeVisitedNodeTrue(tempNode.getNodeId());// ������ͬ�ڵ�ŵĽڵ�ȫ����Ϊtrue;
				tempNode = nodes2[tempNodeId - 1].getNextNode();
				while (tempNode.isVisited()) {
					tempNode = tempNode.getNextNode();
				}
				tempNodeId = Integer.parseInt(String.valueOf(tempNode
						.getNodeId()));
			}

		}

		// ���½��ڵ�ķ��ʱ����Ϊfalse
		makeAllNodesVisitedFalse(nodes2);
		return longRoutineList;
	}

	/**
	 * @param nodes2
	 */
	private void makeAllNodesVisitedFalse(Node[] nodes2) {
		int number = nodes2.length;
		for (int i = 0; i < number; i++) {

			Node tempNode = nodes2[i];
			while (tempNode.getNextNode() != null) {
				tempNode.setVisited(false);
				tempNode = tempNode.getNextNode();
			}
		}
	}

	/**
	 * 
	 * ������ͬ�ڵ�ŵĽڵ�ȫ����Ϊtrue;
	 * 
	 * @param nodeId
	 */
	private void makeVisitedNodeTrue(String nodeId) {
		int number = nodes.length;
		for (int i = 0; i < number; i++) {
			Node tempNode = nodes[i];
			while (tempNode.getNextNode() != null) {
				if (tempNode.getNodeId().equals(nodeId)) {
					tempNode.setVisited(true);
				}
				tempNode = tempNode.getNextNode();
			}
		}
	}

	/**
	 * 
	 * ������ͬ�ڵ�ŵĽڵ�ȫ����Ϊfalse;
	 * 
	 * @param nodeId
	 */
	private void makeVisitedNodeFalse(String nodeId) {
		int number = nodes.length;
		for (int i = 0; i < number; i++) {
			Node tempNode = nodes[i];
			while (tempNode.getNextNode() != null) {
				if (tempNode.getNodeId().equals(nodeId)) {
					tempNode.setVisited(false);
				}
				tempNode = tempNode.getNextNode();
			}
		}
	}

	/**
	 * Ѱ�����ض�node��ų����Ķ����·��
	 * 
	 * @param node
	 *            ��������
	 * @return
	 */
	public String findAllRoutineAccordingToSpecifyNode(String nodeID) {

		// ���Խ��ΪnodeID�����Ľڵ������Ƚ�ջ
		Stack<Node> getStack = pushStackAccordingNodeId(nodeID);
		popStackAccordingStack(getStack);// ���ݸ�����ջִ�г�ջ����
		return nodeID;

	}

	/**
	 * ���ݸ�����ջִ�г�ջ����
	 * 
	 * @param getStack
	 *            ������ջ
	 */
	private void popStackAccordingStack(Stack<Node> getStack) {
		System.out.println("Hello I will pop.");

	}

	Stack<Node> stackNodes = new Stack<Node>();

	/**
	 * 
	 * ����һ�����ݵ����⣻ ���Խ��ΪnodeID�ĳ����ڵ㲢����ýڵ������ӵĽڵ������Ƚ�ջ
	 * 
	 * @param nodeID
	 */
	private Stack<Node> pushStackAccordingNodeId(String nodeID) {

		Node tempNode = nodes[Integer.parseInt(nodeID) - 1];

		String nodeId = tempNode.getNodeId();

		while (tempNode.isVisited()) {
			if (tempNode.getNextNode() != null) {
				tempNode = tempNode.getNextNode();
			} else {
				break;
			}
		}
		while (!tempNode.isVisited()) {
			stackNodes.push(tempNode);
			makeVisitedNodeTrue(nodeId);
			if (tempNode.getNextNode() != null) {
				tempNode = tempNode.getNextNode();
			} else {
				break;
			}

			while (tempNode.isVisited()) {
				if (tempNode.getNextNode() != null) {
					tempNode = tempNode.getNextNode();
				} else {
					break;
				}
			}
			nodeId = tempNode.getNodeId();
			pushStackAccordingNodeId(nodeId);
		}
		return stackNodes;

	}

	/**
	 * 
	 * ִ�г�ջ��������������еĻ�·Ȧ
	 * 
	 * @param tempStack
	 * @return
	 */
	private String execStackPop(Stack<Node> tempStack) {

		if (tempStack.isEmpty()) {
			System.out.println("The stack is null,no elements");
		} else {
			int number = tempStack.capacity();
		}
		tempStack.pop();
		return null;
	}

	/**
	 * Ѱ�ҽ���ΪnodeId�ĸ��ڵ�
	 * 
	 * @param nodeId
	 */
	public void findFatherNode(String nodeId) {

	}
}
